
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class CarList {

    private ArrayList<Car> cars = new ArrayList<>();
    private BrandList brandList;  // Tham chiếu tới danh sách Brand

    // Constructor khởi tạo với danh sách Brand
    public CarList(BrandList bList) {
        this.brandList = bList;
    }

    // Phương thức toString để xuất dữ liệu ra file
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Car car : cars) {
            sb.append(car.getCarID()).append(", ")
                    .append(car.getBrand().getBrandID()).append(", ")
                    .append(car.getColor()).append(", ")
                    .append(car.getFrameID()).append(", ")
                    .append(car.getEngineID()).append("\n");
        }
        return sb.toString();
    }

    // Phương thức screenString để hiển thị thông tin xe ra màn hình
    public String screenString() {
        StringBuilder sb = new StringBuilder();
        for (Car car : cars) {
            sb.append(car.screenString()).append("\n");
        }
        return sb.toString();
    }

    // Phương thức loadFromFile để đọc dữ liệu từ file
    public boolean loadFromFile(String filename) {
        File file = new File(filename);
        if (!file.exists()) {
            return false;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    String carID = parts[0].trim();
                    String brandID = parts[1].trim();
                    String color = parts[2].trim();
                    String frameID = parts[3].trim();
                    String engineID = parts[4].trim();

                    int pos = brandList.searchID(brandID);
                    if (pos != -1) {
                        Brand brand = brandList.getBrands().get(pos);
                        cars.add(new Car(carID, brand, color, frameID, engineID));
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading file: " + e.getMessage());
            return false;
        }
        return true;
    }

    // Phương thức saveToFile để lưu dữ liệu ra file
    public boolean saveToFile(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.write(this.toString());  // Ghi toàn bộ dữ liệu của các xe vào file
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
            return false;
        }
        return true;
    }

    // Phương thức tìm kiếm xe theo carID
    public int searchID(String carID) {
        for (int i = 0; i < cars.size(); i++) {
            if (cars.get(i).getCarID().equals(carID)) {
                return i;  // Trả về vị trí xe tìm thấy
            }
        }
        return -1;  // Không tìm thấy
    }

    // Phương thức tìm kiếm xe theo frameID
    public int searchFrame(String fID) {
        for (int i = 0; i < cars.size(); i++) {
            if (cars.get(i).getFrameID().equals(fID)) {
                return i;
            }
        }
        return -1;
    }

    // Phương thức tìm kiếm xe theo engineID
    public int searchEngine(String eID) {
        for (int i = 0; i < cars.size(); i++) {
            if (cars.get(i).getEngineID().equals(eID)) {
                return i;
            }
        }
        return -1;
    }

    // Phương thức thêm xe mới vào danh sách
    public void addCar() {
        Scanner sc = new Scanner(System.in);
        String carID;
        do {
            System.out.print("Enter Car ID: ");
            carID = sc.nextLine();
        } while (searchID(carID) != -1);  // Kiểm tra carID không trùng

        Menu menu = new Menu();
        Brand brand = menu.ref_getChoice(brandList.getBrands());  // Chọn Brand từ menu

        System.out.print("Enter Car Color: ");
        String color = sc.nextLine();

        String frameID;
        do {
            System.out.print("Enter Frame ID (F0000 format): ");
            frameID = sc.nextLine();
        } while (!frameID.matches("F\\d{4}") || searchFrame(frameID) != -1);  // Kiểm tra frameID không trùng

        String engineID;
        do {
            System.out.print("Enter Engine ID (E0000 format): ");
            engineID = sc.nextLine();
        } while (!engineID.matches("E\\d{4}") || searchEngine(engineID) != -1);  // Kiểm tra engineID không trùng

        cars.add(new Car(carID, brand, color, frameID, engineID));
        System.out.println("New car added successfully.");
    }

    // Phương thức in các xe dựa trên tên thương hiệu
    public void printBasedBrandName() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter part of the brand name: ");
        String partOfBrandName = sc.nextLine();
        int count = 0;

        for (Car car : cars) {
            if (car.getBrand().getBrandName().contains(partOfBrandName)) {
                System.out.println(car.screenString());
                count++;
            }
        }

        if (count == 0) {
            System.out.println("No car is detected!");
        }
    }

    // Phương thức xóa xe theo ID
    public boolean removeCar() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Car ID to remove: ");
        String removedID = sc.nextLine();

        int pos = searchID(removedID);
        if (pos < 0) {
            System.out.println("Car not found!");
            return false;
        }

        cars.remove(pos);
        System.out.println("Car removed successfully.");
        return true;
    }

    // Phương thức cập nhật thông tin xe
    public boolean updateCar() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Car ID to update: ");
        String updatedID = sc.nextLine();

        int pos = searchID(updatedID);
        if (pos < 0) {
            System.out.println("Car not found!");
            return false;
        }

        Menu menu = new Menu();
        Brand brand = menu.ref_getChoice(brandList.getBrands());  // Chọn Brand mới từ menu

        System.out.print("Enter new color: ");
        String color = sc.nextLine();

        String frameID;
        do {
            System.out.print("Enter new Frame ID (F0000 format): ");
            frameID = sc.nextLine();
        } while (!frameID.matches("F\\d{4}") || searchFrame(frameID) != -1);

        String engineID;
        do {
            System.out.print("Enter new Engine ID (E0000 format): ");
            engineID = sc.nextLine();
        } while (!engineID.matches("E\\d{4}") || searchEngine(engineID) != -1);

        cars.get(pos).setBrand(brand);
        cars.get(pos).setColor(color);
        cars.get(pos).setFrameID(frameID);
        cars.get(pos).setEngineID(engineID);

        System.out.println("Car updated successfully.");
        return true;
    }

    // Phương thức sắp xếp các xe theo tên thương hiệu và carID
    public void listCars() {
        Collections.sort(cars);
        for (Car car : cars) {
            System.out.println(car.screenString());
        }
    }
}
