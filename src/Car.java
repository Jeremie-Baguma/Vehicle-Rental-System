public class Car extends Vehicle{
    public Car(String licencePlate, String color, double pricePerDay) {
        super(licencePlate, color, pricePerDay);
    }

    @Override
    public double calculateDailyRentalFee() {
        return getPricePerDay() * 1.1;
    }
}
