import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Rental {

    private List<Vehicle> vehicleList = new ArrayList<>();
    private Map<Customer, Set<Vehicle>> vehicleMap;
    private String startDate;
    private String returnDate;
    private double totalPrice;

    public Rental(List<Vehicle> vehicleList) {
        this.vehicleList = vehicleList;
        this.vehicleMap = new HashMap<>();
    }

    public void rentVehicle(Vehicle vehicle, Customer customer) {
        if (!vehicleList.contains(vehicle)) {
            System.out.println("Vehicle is not available for rent.");
            return;
        }

        if (isVehicleRented(vehicle)) {
            System.out.println("Vehicle already rented.");
            return;
        }

        vehicleMap.computeIfAbsent(customer, k -> new HashSet<>()).add(vehicle);
        vehicle.setStatus(true);
        startDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        System.out.println("Vehicle rented by " + customer.getName() + " on " + startDate);
    }

    public void returnVehicle(Vehicle vehicle, Customer customer) {
        if (!isVehicleRented(vehicle)) {
            System.out.println("Vehicle is not currently rented.");
            return;
        }

        vehicle.setStatus(false);
        returnDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        totalPrice = calculateTotalPrice(vehicle, startDate, returnDate);
        System.out.println("Vehicle returned by " + customer.getName() + " on " + returnDate + ", total price: " + totalPrice);
        vehicleMap.get(customer).remove(vehicle);
    }

    private double calculateTotalPrice(Vehicle vehicle, String startDate, String returnDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate start = LocalDate.parse(startDate, formatter);
        LocalDate end = LocalDate.parse(returnDate, formatter);

        long days = ChronoUnit.DAYS.between(start, end);
        return days * vehicle.calculateDailyRentalFee();
    }

    public void printRentalDetails(Customer customer) {
        System.out.println("Rental details for " + customer.getName() + ":");
        Set<Vehicle> rentedVehicles = vehicleMap.get(customer);

        if (rentedVehicles == null || rentedVehicles.isEmpty()) {
            System.out.println("No vehicles rented.");
        } else {
            for (Vehicle vehicle : rentedVehicles) {
                System.out.println(vehicle + " - Daily Fee: " + vehicle.calculateDailyRentalFee());
            }
            System.out.println("Total rental price: " + totalPrice);
        }
    }

    public void printTop5Rental(Customer customer) {
        List<Vehicle> rentedVehicles = new ArrayList<>(vehicleMap.getOrDefault(customer, Collections.emptySet()));
        rentedVehicles.sort(Comparator.comparingDouble(Vehicle::calculateDailyRentalFee).reversed());

        System.out.println("Top 5 rented vehicles by " + customer.getName() + ":");
        for (int i = 0; i < Math.min(5, rentedVehicles.size()); i++) {
            System.out.println(rentedVehicles.get(i) + " - Daily Fee: " + rentedVehicles.get(i).calculateDailyRentalFee());
        }
    }

    // Checks if the given vehicle is currently rented by any customer
    public boolean isVehicleRented(Vehicle vehicle) {
        for (Set<Vehicle> rentedSet : vehicleMap.values()) {
            if (rentedSet.contains(vehicle)) {
                return true;
            }
        }
        return false;
    }

    public Customer getRenter(Vehicle vehicle) {
        for (Map.Entry<Customer, Set<Vehicle>> entry : vehicleMap.entrySet()) {
            if (entry.getValue().contains(vehicle)) {
                return entry.getKey();
            }
        }
        return null;
    }
}
