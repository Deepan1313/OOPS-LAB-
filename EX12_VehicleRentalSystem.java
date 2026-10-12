class Vehicle {
    protected String vehicleId;
    protected String brand;
    protected String model;
    protected double rentPerDay;
    protected boolean available;
    public Vehicle(String vehicleId, String brand, String model, double rentPerDay) {
        this.vehicleId = vehicleId;
        this.brand = brand;
        this.model = model;
        this.rentPerDay = rentPerDay;
        this.available = true;
    }
    public String getVehicleId() {
        return vehicleId;
    }
    public double getRentPerDay() {
        return rentPerDay;
    }
    public boolean isAvailable() {
        return available;
    }
    public void setAvailable(boolean available) {
        this.available = available;
    }
    public void displayVehicle() {
        System.out.println(  "ID: " + vehicleId +  " | Brand: " + brand + " | Model: " + model + " | Rent/Day: ₹" + rentPerDay + " | Status: " + (available ? "Available" : "Rented")   );
    }
}class Car extends Vehicle {
    private int seats;
    public Car(String vehicleId, String brand, String model,  double rentPerDay, int seats) {
        super(vehicleId, brand, model, rentPerDay);
        this.seats = seats;
    }
    public void displayVehicle() {   System.out.println("ID: " + vehicleId +" | Car: " + brand + " " + model +" | Seats: " + seats +" | Rent/Day: ₹" + rentPerDay +" | Status: " + (available ? "Available" : "Rented") );
    }
}
class Bike extends Vehicle {
    private int engineCC;
    public Bike(String vehicleId, String brand, String model, double rentPerDay, int engineCC) {
        super(vehicleId, brand, model, rentPerDay);
        this.engineCC = engineCC;
    }
    public void displayVehicle() {
        System.out.println( "ID: " + vehicleId + " | Bike: " + brand + " " + model + " | Engine: " + engineCC + "cc" + " | Rent/Day: ₹" + rentPerDay +  " | Status: " + (available ? "Available" : "Rented")        );               
    }
}
class Customer {
    private int customerId;
    private String name;
    private String phone;
    public Customer(int customerId, String name, String phone) {
        this.customerId = customerId;
        this.name = name;
        this.phone = phone;
    }
    public int getCustomerId() {
   return customerId;
    }
    public String getName() {
        return name;
    }
    public String getPhone() {
        return phone;
    }
}
class Rental {
    private Vehicle vehicle;
    private Customer customer;
    private int days;
    private double totalCost;
    public Rental(Vehicle vehicle, Customer customer, int days) {
        this.vehicle = vehicle;
        this.customer = customer;
        this.days = days;
        this.totalCost = vehicle.getRentPerDay() * days;
    }
    public Vehicle getVehicle() {
        return vehicle;
    }
    public void displayRental() {
        System.out.println("--------------------------------");
        System.out.println("Customer : " + customer.getName());
        System.out.println("Phone    : " + customer.getPhone());
        System.out.println("Vehicle  : " + vehicle.getVehicleId());
        System.out.println("Days     : " + days);
        System.out.println("Total    : ₹" + totalCost);
        System.out.println("--------------------------------");
    }
}
import java.util.ArrayList;
import java.util.Scanner;
public class EX12_VehicleRentalSystem {
    static ArrayList<Vehicle> vehicles = new ArrayList<>();
    static ArrayList<Rental> rentals = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        vehicles.add(new Car("C101", "Toyota", "Innova", 1500, 7));
        vehicles.add(new Car("C102", "Hyundai", "i20", 1200, 5));
        vehicles.add(new Bike("B101", "Yamaha", "R15", 800, 155));
        vehicles.add(new Bike("B102", "Honda", "Activa", 500, 110));
        int choice;
        do {
            System.out.println("\n=================================");
            System.out.println("      VEHICLE RENTAL SYSTEM");
            System.out.println("=================================");
            System.out.println("1. View Vehicles");
            System.out.println("2. Add Vehicle");
            System.out.println("3. Rent Vehicle");
            System.out.println("4. Return Vehicle");
            System.out.println("5. View Rental History");
            System.out.println("6. Exit");
            System.out.println("=================================");
            System.out.print("Enter your choice: ");
            try {
                choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        viewVehicles();
                break;
                    case 2:
                        addVehicle();
                        break;
                    case 3:
                        rentVehicle();
                        break;
                    case 4:
                        returnVehicle();
                        break;
                    case 5:
                        viewRentalHistory();
                        break;
                    case 6:
                        System.out.println("Thank you for using Vehicle Rental System!");
                        break;
                    default:
                        System.out.println("Invalid choice!");
                }
            } catch (Exception e) {
                System.out.println("Please enter a valid number!");
                sc.nextLine();
                choice = 0;
            }
        } while (choice != 6);
        sc.close();
    }
    static void viewVehicles() {
        System.out.println("\n========== VEHICLE LIST ==========");
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles available.");
            return;
        }
        for (Vehicle v : vehicles) {
            v.displayVehicle();
        }
    }
    static void addVehicle() {
        System.out.println("\n========== ADD VEHICLE ==========");
        System.out.print("Enter Vehicle ID: ");
        String id = sc.next();
        for (Vehicle v : vehicles) {
            if (v.getVehicleId().equalsIgnoreCase(id)) {
                System.out.println("Vehicle ID already exists!");
                return;
            }
        }
        System.out.print("Enter Brand: ");
        String brand = sc.next();
        System.out.print("Enter Model: ");
        String model = sc.next();
        System.out.print("Enter Rent Per Day: ");
        double rent = sc.nextDouble();
        System.out.println("1. Car");
        System.out.println("2. Bike");
        System.out.print("Choose Vehicle Type: ");
        int type = sc.nextInt();
        if (type == 1) {
            System.out.print("Enter Number of Seats: ");
            int seats = sc.nextInt();
            vehicles.add(new Car(id, brand, model, rent, seats));
            System.out.println("Car added successfully!");
        } else if (type == 2) {
            System.out.print("Enter Engine CC: ");
            int cc = sc.nextInt();
            vehicles.add(new Bike(id, brand, model, rent, cc));
            System.out.println("Bike added successfully!");
        } else {
            System.out.println("Invalid vehicle type!");
        }
    }
   static void rentVehicle() {
        System.out.println("\n========== RENT VEHICLE ==========");
        System.out.print("Enter Vehicle ID: ");
        String id = sc.next();
        Vehicle selectedVehicle = null;
        for (Vehicle v : vehicles) {
            if (v.getVehicleId().equalsIgnoreCase(id)) {
                selectedVehicle = v;
                break;
            }
        }
        if (selectedVehicle == null) {
            System.out.println("Vehicle not found!");
            return;
        }
        if (!selectedVehicle.isAvailable()) {
            System.out.println("Vehicle is already rented!");
            return;
        }
        System.out.print("Enter Customer ID: ");
        int customerId = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Customer Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Phone Number: ");
        String phone = sc.next();
        System.out.print("Enter Number of Days: ");
        int days = sc.nextInt();
        if (days <= 0) {
            System.out.println("Days must be greater than 0!");
            return;
        }
   Customer customer = new Customer(customerId, name, phone);
        Rental rental = new Rental(selectedVehicle,  customer, days    );
        rentals.add(rental);
        selectedVehicle.setAvailable(false);
        System.out.println("\nVehicle rented successfully!");
        System.out.println("Customer: " + name);
        System.out.println("Vehicle: " + id);
        System.out.println("Number of Days: " + days);
        System.out.println(
                "Total Cost: ₹" +    (selectedVehicle.getRentPerDay() * days)
        );
    }
    static void returnVehicle() {
        System.out.println("\n========== RETURN VEHICLE ==========");
        System.out.print("Enter Vehicle ID: ");
        String id = sc.next();
        for (Vehicle v : vehicles) {
            if (v.getVehicleId().equalsIgnoreCase(id)) {
                if (v.isAvailable()) {
                    System.out.println("This vehicle is not currently rented.");
                } else {
                    v.setAvailable(true);
                    System.out.println( "Vehicle " + id +" returned successfully!" );
                }
                return;
            }
        }
        System.out.println("Vehicle not found!");
    }   static void viewRentalHistory() {
        System.out.println("\n========== RENTAL HISTORY ==========");
        if (rentals.isEmpty()) {
            System.out.println("No rental history available.");
            return;
        }
        for (Rental r : rentals) {
            r.displayRental();
        }
    }
}
=================================
      VEHICLE RENTAL SYSTEM
=================================
1. View Vehicles
2. Add Vehicle
3. Rent Vehicle
4. Return Vehicle
5. View Rental History
6. Exit
=================================
Enter your choice: 1
ID: C101 | Car: Toyota Innova | Seats: 7 | Rent/Day: ₹1500.0 | Status: Available
ID: C102 | Car: Hyundai i20 | Seats: 5 | Rent/Day: ₹1200.0 | Status: Available
ID: B101 | Bike: Yamaha R15 | Engine: 155cc | Rent/Day: ₹800.0 | Status: Available
ID: B102 | Bike: Honda Activa | Engine: 110cc | Rent/Day: ₹500.0 | Status: Available