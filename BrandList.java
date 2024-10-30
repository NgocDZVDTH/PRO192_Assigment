/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes.Com;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class BrandList extends ArrayList<Brand> {

    Scanner scanner = new Scanner(System.in);

    public BrandList() {
    }

    public boolean loadFromFile(String fileName) {
        try {
            File file = new File(fileName);
            if (!file.exists()) {
                System.out.println(file + " does not exist");
                return false;
            }
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line = reader.readLine();
                while (line != null) {
                    String[] parts = line.split(",");
                    String brandID = parts[0].trim();
                    String brandName = parts[1].trim();
                    String[] soundBrandParts = parts[2].trim().split(":");
                    String soundBrand = soundBrandParts[0].trim();
                    double price = Double.parseDouble(soundBrandParts[1].trim());
                    this.add(new Brand(brandID, brandName, soundBrand, price));
                    line = reader.readLine();
                }
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public boolean saveToFile(String fileName) {
        try {
            try (FileWriter writer = new FileWriter(fileName)) {
                for (Brand brand : this) {
                    String line = brand.getBrandID() + ", " + brand.getBrandName() + ", " + brand.getSoundBrand() + ": " + brand.getPrice() + "\n";
                    writer.write(line);
                }
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public int searchID(String ID) {
        if (!this.isEmpty()) {
            for (int i = 0; i < this.size(); i++) {
                if (this.get(i).getBrandID().equals(ID)) {
                    return i;
                }
            }
        } else {
            System.out.println("List is empty!");
        }
        return -1;
    }

    public Brand getUserChoice() {
        Menu menu = new Menu();
        return (Brand) menu.ref_getChoice(this);
    }

    public void addBrand() {
        String brandID;
        do {
            brandID = inputString("Input brand ID: ", "Invalid");
            if (isDuplicateBrandID(brandID)) {
                System.out.println("Brand ID already exists!");
            }
        } while (isDuplicateBrandID(brandID));
        String brandName = inputString("Input brand name: ", "Invalid");
        String soundBrand = inputString("Input sound brand: ", "Invalid");
        double price = inputDouble("Input price: ", "Invalid");
        this.add(new Brand(brandID, brandName, soundBrand, price));
        System.out.println("Added successfully!");
    }

    public void updateBrand() {
        if (!this.isEmpty()) {
            String brandID = inputString("Input brand ID to update: ", "Invalid");
            int index = searchID(brandID);
            if (index < 0) {
                System.out.println("Not found!");
            } else {
                String brandName = inputString("Input brand name: ", "Invalid");
                String soundBrand = inputString("Input sound brand: ", "Invalid");
                double price = inputDouble("Input price: ", "Invalid");
                Brand brand = this.get(index);
                brand.setBrandname(brandName);
                brand.setSoundBrand(soundBrand);
                brand.setPrice(price);
                System.out.println("Updated successfully!");
            }
        } else {
            System.out.println("List is empty!");
        }
    }

    public void listBrands() {
        if (!this.isEmpty()) {
            this.forEach((brand) -> {
                System.out.println(brand.toString());
            });
        } else {
            System.out.println("List is empty!");
        }
    }

    private double inputDouble(String msgNhap, String msgLoi) {
        double number = 0;
        boolean isValid = false;

        while (!isValid) {
            try {
                System.out.print(msgNhap);
                number = Double.parseDouble(scanner.nextLine());
                if (number > 0) {
                    isValid = true;
                } else {
                    System.out.println("Invalid input. Please enter a positive number.");
                }
            } catch (NumberFormatException e) {
                System.out.println(msgLoi);
            }
        }

        return number;
    }

    private String inputString(String message, String msgLoi) {
        String input = "";

        while (true) {
            try {
                System.out.print(message);
                input = scanner.nextLine().trim();
                if (input.isEmpty()) {
                    System.out.println("Invalid input.");
                } else {
                    break;
                }
            } catch (Exception e) {
                System.out.println(msgLoi);
            }
        }

        return input;
    }
    
    private boolean isDuplicateBrandID(String brandID) {
        return this.stream().anyMatch((brand) -> (brand.getBrandID().equals(brandID)));
    }
}
