/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Hask
 */
import java.util.ArrayList;

public class CarManager {

    public static void main(String[] args) {
        BrandList brandList = new BrandList();
        brandList.loadFromFile("brands.txt");
        CarList carList = new CarList(brandList);
        carList.loadFromFile("cars.txt");

        Menu menu = new Menu();
        ArrayList<String> options = new ArrayList<>();
        options.add("List all brands");
        options.add("Add a new brand");
        options.add("Search a brand by ID");
        options.add("Update a brand");
        options.add("Save brands to file");
        options.add("List all cars");
        options.add("Add a new car");
        options.add("Remove a car");
        options.add("Update a car");
        options.add("Save cars to file");

        int choice;
        do {
            choice = menu.int_getChoice(options);
            switch (choice) {
                case 1:
                    brandList.listBrands();
                    break;
                case 2:
                    brandList.addBrand();
                    break;
                // More cases for other operations...
            }
        } while (choice > 0 && choice <= options.size());
    }
}
