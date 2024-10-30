
package Car;

import Brand.Brand;

public class Car implements Comparable<Car>{
    private String carID;
    private Brand brand = new Brand();
    private String color;
    private String frameID;
    private String engineID;

    public Car() {
    }

    public Car(String carID, Brand brand, String color, String frameID, String engineID) {
        this.carID = carID;
        this.color = color;
        this.frameID = frameID;
        this.engineID = engineID;
        this.brand = brand;
    }

    public String getCarID() {
        return carID;
    }

    public void setCarID(String carID) {
        this.carID = carID;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getFrameID() {
        return frameID;
    }

    public void setFrameID(String frameID) {
        this.frameID = frameID;
    }

    public String getEngineID() {
        return engineID;
    }

    public void setEngineID(String engineID) {
        this.engineID = engineID;
    }

    @Override
    public int compareTo(Car car) {
        int a = this.brand.getBrandName().compareTo(car.brand.getBrandName());
        if (a != 0) {
            return a;
        } else {
            return this.carID.compareTo(car.carID);
        }
    }

    @Override
    public String toString() {
        return this.carID + ", " + this.brand.getBrandID() + ", " + this.color + ", " + this.frameID + ", " + this.engineID;
    }
    
    public String screenString() {
        return this.brand + "\n" + this.carID + ", " + this.color + ", " + this.frameID + ". " + this.engineID;
    }
}

