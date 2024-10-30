package Car;
import Brand.Brand;
import Brand.BrandList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import Menu.Menu;
import java.util.Collections;
public class CarList extends ArrayList<Car> {
    BrandList brandlist;
    public CarList() {
        
    }
    
    public CarList(BrandList brandlist) {
        this.brandlist = brandlist;
    }
    public boolean loadFromFile(String filename) throws FileNotFoundException {
        try {
            File file = new File(filename);
            Scanner input = new Scanner(file);
            while (input.hasNext()) {
                String xau[] = input.nextLine().split("[,:]");
                this.add(new Car(xau[0].trim(), brandlist.get(brandlist.searchID(xau[1].trim())), xau[2].trim(), xau[3].trim(), xau[4].trim()));
            }
            return true;
        } catch (FileNotFoundException e) {
            System.out.println("Error in loading from file " + filename);
            return false;
        }
    }
    public boolean saveToFile(String filename) throws IOException {
        try {
            PrintWriter file = new PrintWriter(new FileWriter(filename, true));
            for (int i = 0; i < this.size(); i++) {
                file.write(this.get(i).toString());
            }
            return true;
        } catch (IOException e) {
            System.out.println("Error in writing into file " + filename);
            return false;
        }
    }
    public int searchID(String ID) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getCarID().equals(ID)) {
                return i;
            }
        }
        return -1;
    }
    public int searchFrame(String frameID) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getFrameID().equals(frameID)) {
                return i;
            }
        }
        return -1;
    }
    public int searchEngine(String engineID) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getFrameID().equals(engineID)) {
                return i;
            }
        }
        return -1;
    }
    public void addCar() {
        String carID;
        String color;
        String frameID;
        String engineID;
        int pos;
        Scanner input = new Scanner(System.in);
        Brand brand = brandlist.getUserChoice();
        do {            
            System.out.println("Enter car ID");
            carID = input.nextLine();
            if (this.searchID(carID) != -1) {
                System.out.println("CarID must not be duplicated");
            } else {
                break;
            }
        } while (true);
        do {                
            System.out.println("Input color: ");
            color = input.nextLine();
            if (color.equals("")) {
                System.out.println("Color cannot be blank");
            } else {
                break;
            }
        } while (true);
        do {                
            System.out.println("Input frameID: ");
            frameID = input.nextLine();
            if (this.searchID(frameID) != -1) {
                System.out.println("FrameID must be in the “F0000” and not be duplicated");
            } else {
                break;
            }
        } while (true);
        do {                
            System.out.println("Enter engineID: ");
            engineID = input.nextLine();
            if (this.searchEngine(engineID) != -1) {
                System.out.println("EngineID must be in the “E0000” format and not be duplicated");
            } else {
                break;
            }
        } while (true);
        this.add(new Car(carID, brand, color, frameID, engineID));
    }
    public void printBasedBrandName() {
        String aPartOfBrandName;
        Scanner input = new Scanner(System.in);
        int count = 0;
        do {
            System.out.println("Enter a part of brand name: ");
            aPartOfBrandName = input.nextLine();
            if (aPartOfBrandName.equals("")) {
                System.out.println("The brand name is not blank");
            } else {
                break;
            }
        } while (true);
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getBrand().toString().contains(aPartOfBrandName)) {
                System.out.println( this.get(i).screenString());
                count++;
            }
        }
        if (count == 0) {
            System.out.println("No car is detected");
        }
    }
    public boolean removeCar() {
        Scanner input = new Scanner(System.in);
        String ID;
        System.out.println("Enter car ID");
        ID = input.nextLine();
        if (this.searchID(ID) >= 0) {
            this.remove(this.searchID(ID));
            return true;
        }
        System.out.println("Not found");
        return false;
    }
    public boolean updateCar() {
        Scanner input = new Scanner(System.in);
        int pos;
        String carID;
        Brand brand;
        String color;
        String frameID;
        String engineID;
        do {            
            System.out.println("Enter car ID");
            carID = input.nextLine();
            if (this.searchID(carID) != -1) {
                System.out.println("CarID must not be duplicated");
            } else {
                break;
            }
        } while (true);
        pos = this.searchID(carID);
        if (pos < 0) {
            System.out.println("Not found");
            return false;
        } else {
            brand = brandlist.getUserChoice();
            do {                
                System.out.println("Input color: ");
                color = input.nextLine();
                if (color.equals("")) {
                    System.out.println("Color cannot be blank");
                } else {
                    break;
                }
            } while (true);
            do {                
                System.out.println("Input frameID (F0000 format): ");
                frameID = input.nextLine();
                if (this.searchID(frameID) != -1 || !frameID.matches("F[0-9][0-9][0-9][0-9]")) {
                    System.out.println("FrameID must be in the “F0000” and not be duplicated");
                } else {
                    break;
                }
            } while (true);
            do {                
                System.out.println("Enter engineID (E0000 format): ");
                engineID = input.nextLine();
                if (this.searchEngine(engineID) != -1 || !frameID.matches("E[0-9][0-9][0-9][0-9]")) {
                    System.out.println("EngineID must be in the “E0000” format and not be duplicated");
                } else {
                    break;
                }
            } while (true);
            this.get(pos).setBrand(brand);
            this.get(pos).setColor(color);
            this.get(pos).setFrameID(frameID);
            this.get(pos).setEngineID(engineID);
            return true;
        }
    }
    
    public void listCars() {
        Collections.sort(this);
        for (int i = 0; i < this.size(); i++) {
            System.out.println(this.get(i));
        }
    }
}
