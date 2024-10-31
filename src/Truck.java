import java.util.Objects;

public class Truck extends Vehicle{
    private int cargoCapacity;
    public Truck(String licencePlate, String color, double pricePerDay, int cargoCapacity) {
        super(licencePlate, color, pricePerDay);
        this.cargoCapacity = cargoCapacity;
    }

    @Override
    public double calculateDailyRentalFee() {
        return getPricePerDay() * 1.1;
    }

    @Override
    public void printVehicleDetails() {
        System.out.println("Truck Details:");
        super.printVehicleDetails();
        System.out.println("Cargo Capacity: " + cargoCapacity + " tons");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Truck truck = (Truck) o;
        return cargoCapacity == truck.cargoCapacity;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(cargoCapacity);
    }

    @Override
    public String toString() {
        return super.toString() + "cargoCapacity=" + cargoCapacity ;
    }
}
