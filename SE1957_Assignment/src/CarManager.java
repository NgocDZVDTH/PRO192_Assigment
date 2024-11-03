package Classes;

import Classes.Com.BrandList;
import Classes.Com.CarList;
import Classes.Com.Menu;

import java.util.ArrayList;
import java.util.Scanner;

public class CarManager {

    public static void main(String[] args) {
        ArrayList<String> ops = new ArrayList<>(11);
        ops.add("List all brands");
        ops.add("Add a new brand");
        ops.add("Search a brand based on its ID");
        ops.add("Update a brand");
        ops.add("Save brands to the file, named brands.txt");
        ops.add("List all cars in ascending order of brand names");
        ops.add("List cars based on a part of an input brand name");
        ops.add("Add a car");
        ops.add("Remove a car based on its ID");
        ops.add("Update a car based on its ID");
        ops.add("Save cars to file, named cars.txt");

        BrandList brandList = new BrandList();
        brandList.loadFromFile("brands.txt");

        CarList carList = new CarList(brandList);
        carList.loadFromFile("cars.txt");

        int choice;
        Menu menu = new Menu();

        do {
            choice = menu.int_getChoice(ops);
            switch (choice) {
                case 1:
                    brandList.listBrands();
                    break;
                case 2:
                    brandList.addBrand();
                    break;
                case 3:
                    System.out.print("Input brand ID: ");
                    String bID = new Scanner(System.in).nextLine();
                    int brandIndex = brandList.searchID(bID);
                    if (brandIndex == -1) {
                        System.out.println("Brand ID not found !");
                    } else {
                        System.out.println(brandList.get(brandIndex).toString());
                    }
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
                    carList.addCar();
                    break;
                case 9:
                    boolean checkSuccessful = carList.removeCar();
                    if (checkSuccessful) {
                        System.out.println("Car removed successfully!");
                    } else {
                        System.out.println("Car removed unsuccessfully!");
                    }
                    break;
                case 10:
                    checkSuccessful = carList.updateCar();
                    if (checkSuccessful) {
                        System.out.println("Car updated successfully!");
                    } else {
                        System.out.println("Car updated unsuccessfully!");
                    }
                    break;
                case 11:
                    carList.saveToFile("cars.txt");
                    break;
            }
        } while (choice>= 1 && choice < ops.size());
    }
}
