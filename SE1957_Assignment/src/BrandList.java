package Classes.Com;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template

public class BrandList {

    private ArrayList<Brand> brands = new ArrayList<>();

    // Constructor
    public BrandList() {
    }

    // Tra ve list brand
    public ArrayList<Brand> getBrands() {
        return brands;
    }

    // Chon mot brand tu listbrand
    public Brand getUserChoice() {
        return Menu.ref_getChoice(brands);
    }
    // load du lieu brand tu mot file
    //tao 1 file co ten la f, neu khong co thi bao loi
    
    public boolean loadFromFile(String filename) {
        File f = new File(filename);
        if (!f.exists()) {
            System.err.println("File does not exist.");
            return false;
        }
        // ham doc file cho den khi khong con dong nao nua
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 3) {
                    String id = parts[0].trim();
                    String brandName = parts[1].trim();
                    String soundBrand = parts[2].split(":")[0].trim();
                    double price = Double.parseDouble(parts[2].split(":")[1].trim());

                    Brand brand = new Brand(id, brandName, soundBrand, price);
                    brands.add(brand);
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error loading file: " + e.getMessage());
            return false;
        }

        System.out.println("File brands loaded successfully.");
        return true;
    }

    // luu brand vao file
    public boolean saveToFile(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (Brand brand : brands) {
                writer.println(brand.getBrandID() + "," + brand.getBrandName() + "," + brand.getSoundBrand() + ":" + brand.getPrice());
            }
        } catch (IOException e) {
            System.err.println("Error saving file: " + e.getMessage());
            return false;
        }
        System.out.println("File saved successfully.");
        return true;
    }

    //Tim brand bang ID
    public int searchID(String bID) {
        for (int i = 0; i < brands.size(); i++) {
            if (brands.get(i).getBrandID().equals(bID)) {
                return i;
            }
        }
        return -1;
    }

    public void searchBrandByID(String bID) {
        int index = searchID(bID);
        if (index == -1) {
            System.out.println("Brand not found.");
        } else {
            System.out.println(brands.get(index));
        }
    }

    //Add brand moi
    public void addBrand() {
        System.out.print("Enter brand ID: ");
        String brandID = Menu.inputString();

        System.out.print("Enter brand Name: ");
        String brandName = Menu.inputString();

        System.out.print("Enter sound Brand: ");
        String soundBrand = Menu.inputString();

        System.out.print("Enter price: ");
        double price = Menu.inputDouble();

        brands.add(new Brand(brandID, brandName, soundBrand, price));
        System.out.println("New brand added!");
    }

    // Xem co brand nao bi trong khong
    public void listBrands() {
        if (brands.isEmpty()) {
            System.err.println("No brands available.");
            return;
        }
        for (Brand brand : brands) {
            System.out.println(brand);
        }
    }
    
    public void updateBrand() {
        System.out.print("Enter brand ID to update: ");
        String brandID = Menu.inputString();

        int pos = searchID(brandID);
        if (pos < 0) {
            System.err.println("Brand not found!");
            return;
        }

        System.out.print("Enter new brand name: ");
        String brandName = Menu.inputString();

        System.out.print("Enter new sound brand: ");
        String soundBrand = Menu.inputString();

        System.out.print("Enter new price: ");
        double price = Menu.inputDouble();

        brands.get(pos).setBrandName(brandName);
        brands.get(pos).setSoundBrand(soundBrand);
        brands.get(pos).setPrice(price);

        System.out.println("Brand updated successfully.");
    }
}
