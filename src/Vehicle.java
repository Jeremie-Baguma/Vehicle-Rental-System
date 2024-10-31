import java.util.Objects;

public abstract class Vehicle {
    private String licencePlate;
    private String color;
    private double pricePerDay;
    private boolean status;

    public Vehicle(String licencePlate, String color, double pricePerDay) {
        this.licencePlate = licencePlate;
        this.color = color;
        this.pricePerDay = pricePerDay;
    }


    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void printVehicleDetails() {
        System.out.println("Licence Plate: " + getLicencePlate());
        System.out.println("Color: " + getColor());
        System.out.println("Price per Day: " + getPricePerDay());
    }

    public abstract double calculateDailyRentalFee();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return Double.compare(pricePerDay, vehicle.pricePerDay) == 0 && status == vehicle.status && Objects.equals(licencePlate, vehicle.licencePlate) && Objects.equals(color, vehicle.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(licencePlate, color, pricePerDay, status);
    }

    @Override
    public String toString() {
        return "licencePlate='" + licencePlate + '\'' +
                ", color='" + color + '\'' +
                ", pricePerDay=" + pricePerDay +
                ", status=" + status;

    }
}
