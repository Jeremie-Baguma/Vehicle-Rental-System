import java.util.Objects;

public class Customer {
    private String name;
    private String address;
    private int age;
    public Customer(String name, String address, int age) {
        this.name = name;
        this.address = address;
        this.age = age;
    }
    public String getName() {
        return name;
    }
    public String getAddress() {
        return address;
    }
    public int getAge() {
        return age;
    }
    public void printCustomerDetails() {
        System.out.println("Customer Name: " + name);
        System.out.println("Customer Address: " + address);
        System.out.println("Customer Age: " + age);
    }
// public void printCustomerDetails
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return age == customer.age && Objects.equals(name, customer.name) && Objects.equals(address, customer.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, address, age);
    }
}
