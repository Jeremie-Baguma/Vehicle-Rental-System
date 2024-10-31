public class Motorcycle extends Vehicle{
    private String make;
    public Motorcycle(String make, String licensePlate, String vehicleType, double rentalPricePerDay) {
        super(licensePlate, vehicleType, rentalPricePerDay);
        this.make = make;

    }

    @Override
    public void printVehicleDetails() {
        super.printVehicleDetails();
        System.out.println("Make: " + make);
    }

    @Override
    public double calculateDailyRentalFee() {
        return getPricePerDay() * 1.2; // 20% more rental price for motorcycles
    }
}
