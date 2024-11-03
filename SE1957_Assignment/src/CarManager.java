package Classes;

import Classes.Com.Menu;
import Classes.Com.BrandList;
import Classes.Com.CarList;
import java.util.ArrayList;

public class CarManager {

    public static void main(String[] args) {
        BrandList brandList = new BrandList();
        brandList.loadFromFile("brands.txt");
        CarList carList = new CarList(brandList);
        carList.loadFromFile("cars.txt");

        ArrayList<String> options = new ArrayList<>();
        System.out.println("-===== The Car Showroom Minh Trang BMW =====-");
        options.add("List all brands");
        options.add("Add a new brand");
        options.add("Search a brand by ID");
        options.add("Update a brand");
        options.add("Save brands to file");
        options.add("List all cars");
        options.add("List cars base brand name");
        options.add("Search a car base ID");
        options.add("Search a car base frame");
        options.add("Search a car base engine");
        options.add("Add a new car");
        options.add("Remove a car");
        options.add("Update a car");
        options.add("Save cars to file");
        options.add("Exit");

        int choice;
        do {
            choice = Menu.int_getChoice(options);
            switch (choice) {
                case 1:
                    brandList.listBrands();
                    break;
                case 2:
                    brandList.addBrand();
                    break;
                case 3:
                    System.out.print("Enter brand ID to search: ");
                    String bID = Menu.inputString();
                    brandList.searchBrandByID(bID);
                    break;
                case 4:
                    brandList.updateBrand();
                    break;
                case 5:
                    brandList.saveToFile("brands.txt");
                    break;
                case 6:
                    carList.listCars();
                    break;
                case 7:
                    carList.printBasedBrandName();
                    break;
                case 8:
                    System.out.print("Enter car ID to search: ");
                    String cID = Menu.inputString();
                    carList.searchCarByID(cID);
                    break;
                case 9:
                    System.out.print("Enter frame to search: ");
                    String fID = Menu.inputString();
                    carList.searchCarByFrame(fID);
                    break;
                case 10:
                    System.out.print("Enter engine to search: ");
                    String eID = Menu.inputString();
                    carList.searchCarByEngine(eID);
                    break;
                case 11:
                    carList.addCar();
                    break;
                case 12:
                    carList.removeCar();
                    break;
                case 13:
                    carList.updateCar();
                    break;
                case 14:
                    carList.saveToFile("cars.txt");
                    break;
                case 15:
                    return;
                default:
                    System.err.println("Invalid choice!");
                    break;
            }
        } while (choice > 0 && choice <= options.size());
    }
}
