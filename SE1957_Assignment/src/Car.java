package Classes.Com;

public class Car implements Comparable<Car> {

    private String carID;
    private Brand brand;
    private String color;
    private String frameID;
    private String engineID;

    public Car() {

    }

    public Car(String carID, Brand brand, String color, String frameID, String engineID) {
        this.carID = carID;
        this.brand = brand;
        this.color = color;
        this.frameID = frameID;
        this.engineID = engineID;
    }

    // Getters and Setters for all fields
    public String getCarID() {
        return carID;
    }

    public Brand getBrand() {
        return brand;
    }

    public String getColor() {
        return color;
    }

    public String getFrameID() {
        return frameID;
    }

    public String getEngineID() {
        return engineID;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setFrameID(String frameID) {
        this.frameID = frameID;
    }

    public void setEngineID(String engineID) {
        this.engineID = engineID;
    }

    @Override
    public int compareTo(Car other) {
        int brandComparison = this.brand.getBrandName().compareTo(other.brand.getBrandName());
        if (brandComparison != 0) {
            return brandComparison;
        }
        return this.carID.compareTo(other.carID);
    }

    @Override
    public String toString() {
        return carID + ", " + brand.getBrandID() + ", " + color + ", " + frameID + ", " + engineID;
    }

    // Method to return a string representation for displaying
    public String screenString() {
        return carID + ", " + brand.getBrandName() + ", " + color + ", " + frameID + ", " + engineID;
    }
}
