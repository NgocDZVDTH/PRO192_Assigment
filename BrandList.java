
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Hask
 */
public class BrandList {

    private ArrayList<Brand> brands = new ArrayList<>();

    // Constructor
    public BrandList() {
    }

    // Phương thức trả về danh sách các Brand
    public ArrayList<Brand> getBrands() {
        return brands;
    }

    // Phương thức thêm một Brand vào danh sách
    // Trường hợp load brand đã có trong file text
    public void addBrand(Brand brand) {
        brands.add(brand);
    }

    // Method to load brands from a file
    public boolean loadFromFile(String filename) {
        File f = new File(filename);
        if (!f.exists()) {
            System.out.println("File does not exist.");
            return false;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(", ");
                if (parts.length >= 3) {
                    String id = parts[0].trim();
                    String brandName = parts[1].trim();
                    String soundBrand = parts[2].split(":")[0].trim();
                    double price = Double.parseDouble(parts[2].split(":")[1].trim());

                    Brand brand = new Brand(id, brandName, soundBrand, price);
                    addBrand(brand);
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error loading file: " + e.getMessage());
            return false;
        }
        return true;
    }

    // Method to save brands to a file
    public boolean saveToFile(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (Brand brand : brands) {
                writer.println(brand.getBrandID() + "," + brand.getBrandName() + "," + brand.getSoundBrand() + "," + brand.getPrice());
            }
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
            return false;
        }
        return true;
    }

    // Method to search for a brand by ID
    public int searchID(String bID) {
        for (int i = 0; i < brands.size(); i++) {
            if (brands.get(i).getBrandID().equals(bID)) {
                return i;
            }
        }
        return -1;
    }

    // Method for user to choose a brand from the list
    public Brand getUserChoice() {
        Menu menu = new Menu();
        return menu.ref_getChoice(brands);
    }

    // Method to add a new brand
    public void addBrand() {
        Scanner sc = new Scanner(System.in);

        String brandID;
        do {
            System.out.print("Enter Brand ID: ");
            brandID = sc.nextLine();
        } while (searchID(brandID) != -1);

        String brandName;
        do {
            System.out.print("Enter Brand Name: ");
            brandName = sc.nextLine();
        } while (brandName.isEmpty());

        String soundBrand;
        do {
            System.out.print("Enter Sound Brand: ");
            soundBrand = sc.nextLine();
        } while (soundBrand.isEmpty());

        double price;
        do {
            System.out.print("Enter Price: ");
            price = sc.nextDouble();
        } while (price <= 0);

        brands.add(new Brand(brandID, brandName, soundBrand, price));
        System.out.println("New brand added!");
    }

// Method to list all unique brand names
    public void listBrands() {
        ArrayList<String> uniqueNames = new ArrayList<>();
        int n = brands.size();
        for (int i = 0; i < n; i++) {
            String brandName = brands.get(i).getBrandName();
            if (!uniqueNames.contains(brandName)) {
                uniqueNames.add(brandName);
                System.out.println(brandName);
            }
        }
    }

    // Method to update an existing brand
    public void updateBrand() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Brand ID to update: ");
        String brandID = sc.nextLine();

        int pos = searchID(brandID);
        if (pos < 0) {
            System.out.println("Brand not found!");
            return;
        }

        System.out.print("Enter new Brand Name: ");
        String brandName = sc.nextLine();
        System.out.print("Enter new Sound Brand: ");
        String soundBrand = sc.nextLine();
        System.out.print("Enter new Price: ");
        double price = sc.nextDouble();

        brands.get(pos).setBrandName(brandName);
        brands.get(pos).setSoundBrand(soundBrand);
        brands.get(pos).setPrice(price);

        System.out.println("Brand updated successfully.");
    }
}
