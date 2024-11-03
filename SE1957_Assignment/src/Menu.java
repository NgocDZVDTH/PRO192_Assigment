package Classes.Com;

import java.util.Scanner;
import java.util.ArrayList;

public class Menu {
    private static final Scanner scanner = new Scanner(System.in);
    public static String inputString() {
        String input;
        do { //Nhập chuỗi không rỗng
            input = scanner.nextLine().trim(); 
            if (input.isEmpty()) {
                System.err.println("Input cannot be empty.");
            }
        } while (input.isEmpty());
        return input;
    }

    public static double inputDouble() {
        Scanner sc = new Scanner(System.in);
        double value;
        while (true) {
            try {
                value = Double.parseDouble(sc.nextLine().trim());
                if (value > 0) {
                    break;
                } else {
                    System.err.println("Price must be a positive number.");
                }
            } catch (NumberFormatException e) {
                System.err.println("Please enter a valid number.");
            }
        }
        return value;
    }

    public static <E> int int_getChoice(ArrayList<E> options) {
        int choice = -1;
        int size = options.size();
        for (int i = 0; i < size; i++) {
            System.out.println((i + 1) + ". " + options.get(i)); // In danh sách lựa chọn
        }  
        while (true) { // Yêu cầu người dùng chọn một lựa chọn
            System.out.print("Please choose an option (1 - " + size + "): ");
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice >= 1 && choice <= size) {
                    break; // Nhập hợp lệ
                } else {
                    System.err.println("Choice must be between 1 - " + size);
                }
            } catch (NumberFormatException e) {
                System.err.println("Please enter a valid number.");
            }
        }
        return choice;
    }
    public static <E> E ref_getChoice(ArrayList<E> options) {
        int response;
        do {
            response = int_getChoice(options); // Nhận lựa chọn từ người dùng
        } while (response < 1 || response > options.size());
        return options.get(response - 1); // Trả về đối tượng tương ứng với lựa chọn
    }
}
