import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Vehicle> vehicleList = new ArrayList<>();
        vehicleList.add(new Truck("ABC123", "Red", 100.0, 5000));
        vehicleList.add(new Truck("DEF456", "Blue", 120.0, 6000));
        vehicleList.add(new Truck("GHI789", "Green", 110.0, 5500));
        vehicleList.add(new Truck("JKL012", "White", 105.0, 5200));
        vehicleList.add(new Truck("MNO345", "Black", 115.0, 5800));
        vehicleList.add(new Truck("PQR678", "Yellow", 130.0, 6200));
        vehicleList.add(new Truck("STU901", "Grey", 125.0, 6100));
        vehicleList.add(new Truck("VWX234", "Purple", 140.0, 6500));
        vehicleList.add(new Truck("YZA567", "Orange", 135.0, 6400));
        vehicleList.add(new Truck("BCD890", "Silver", 150.0, 7000));

        Rental rental = new Rental(vehicleList);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Rental System Menu ---");
            System.out.println("1. Rent a Vehicle");
            System.out.println("2. Return a Vehicle");
            System.out.println("3. View All Rented Vehicles");
            System.out.println("4. View All Available Vehicles");
            System.out.println("5. View Rental Prices of Rented Vehicles");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter customer name: ");
                    String name = scanner.nextLine();
                    Customer customer = new Customer(name, "Address", 30);  // Address and age are placeholders
                    System.out.println("Select a vehicle to rent by entering the licence plate:");
                    for (Vehicle vehicle : vehicleList) {
                        if (!rental.isVehicleRented(vehicle)) {
                            System.out.println(vehicle);
                        }
                    }
                    String plate = scanner.nextLine();
                    Vehicle selectedVehicle = null;
                    for (Vehicle vehicle : vehicleList) {
                        if (vehicle.getLicencePlate().equals(plate)) {
                            selectedVehicle = vehicle;
                            break;
                        }
                    }
                    if (selectedVehicle != null) {
                        rental.rentVehicle(selectedVehicle, customer);
                    } else {
                        System.out.println("Invalid licence plate entered.");
                    }
                    break;

                case 2:
                    System.out.print("Enter customer name for vehicle return: ");
                    String returnName = scanner.nextLine();
                    Customer returnCustomer = new Customer(returnName, "Address", 30);  // Address and age are placeholders
                    System.out.print("Enter licence plate of vehicle to return: ");
                    String returnPlate = scanner.nextLine();
                    Vehicle vehicleToReturn = null;
                    for (Vehicle vehicle : vehicleList) {
                        if (vehicle.getLicencePlate().equals(returnPlate) && rental.isVehicleRented(vehicle)) {
                            vehicleToReturn = vehicle;
                            break;
                        }
                    }
                    if (vehicleToReturn != null) {
                        rental.returnVehicle(vehicleToReturn, returnCustomer);
                    } else {
                        System.out.println("Invalid or unavailable vehicle.");
                    }
                    break;

                case 3:
                    System.out.println("Rented Vehicles:");
                    for (Vehicle vehicle : vehicleList) {
                        if (rental.isVehicleRented(vehicle)) {
                            System.out.println(vehicle + " - Rented by " + rental.getRenter(vehicle));
                        }
                    }
                    break;

                case 4:
                    System.out.println("Available Vehicles:");
                    for (Vehicle vehicle : vehicleList) {
                        if (!rental.isVehicleRented(vehicle)) {
                            System.out.println(vehicle);
                        }
                    }
                    break;

                case 5:
                    System.out.println("Rental Prices of Rented Vehicles:");
                    for (Vehicle vehicle : vehicleList) {
                        if (rental.isVehicleRented(vehicle)) {
                            System.out.println(vehicle + " - Daily Rental Price: " + vehicle.calculateDailyRentalFee());
                        }
                    }
                    break;

                case 0:
                    System.out.println("Exiting the rental system. Thank you!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}
