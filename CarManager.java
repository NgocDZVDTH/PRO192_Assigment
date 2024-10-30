
package Menu;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Brand.BrandList;
import Car.CarList;

public class CarManager {

    public static void main(String[] args) throws IOException, FileNotFoundException {
        Scanner input = new Scanner(System.in);
        int temp;
        String temp1;
        boolean thoat = true;
        BrandList brandList = new BrandList();
        CarList carList = new CarList(brandList);
        List<String> options = new ArrayList<String>();
        brandList.loadFromFile("brands.txt");
        carList.loadFromFile("cars.txt");

        options.add("List all brands");
        options.add("Add a new brand");
        options.add("Search a brand based on its ID");
        options.add("Update a brand");
        options.add("Save brands to the file, named brands.txt");
        options.add("List all cars in ascending order of brand names");
        options.add("List cars based on a part of an input brand name");
        options.add("Add a car");
        options.add("Remove a car based on its ID");
        options.add("Update a car based on its ID");
        options.add("Save cars to file cars.txt");
        options.add("Quit");
        
        do {
            temp = Menu.int_getChoice(options);
            switch (temp) {
                case 1:
                    System.out.println("1");
                    brandList.listBrands();
                    break;
                case 2:
                    System.out.println("2");
                    brandList.addBrand();
                    break;
                case 3:
                    System.out.println("3");
                    System.out.println("Input brand ID: ");
                    temp1 = input.nextLine();
                   if (brandList.searchID(temp1) == -1) {
                       System.out.println("Not found");
                       break;
                   }
                    System.out.println("The brand ID is in the position " + brandList.searchID(temp1)); 
                    break;
                case 4:
                    System.out.println("4");
                    brandList.updateBrand();
                    break;
                case 5:
                    System.out.println("5");
                    brandList.saveToFile("brands.txt");
                    break;
                case 6:
                    System.out.println("6");
                    carList.listCars();
                    break;
                case 7:
                    System.out.println("7");
                    carList.printBasedBrandName();
                    break;
                case 8:
                    System.out.println("8");
                    carList.addCar();
                    break;
                case 9:
                    System.out.println("9");
                    carList.removeCar();
                    break;
                case 10:
                    System.out.println("10");
                    carList.updateCar();
                    break;
                case 11:
                    System.out.println("11");
                    carList.saveToFile("cars.txt");
                    break;
                case 12:
                    System.out.println("12");
                    thoat = false;
                    break;               
            }
        } while (thoat);
    }
}
