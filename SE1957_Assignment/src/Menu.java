/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Hask
 */
import java.util.Scanner;
import java.util.ArrayList;

public class Menu {

    public <E> int int_getChoice(ArrayList<E> options) {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;
        int size = options.size();

        // In danh sách lựa chọn
        for (int i = 0; i < size; i++) {
            System.out.println((i + 1) + ". " + options.get(i));
        }

        // Yêu cầu người dùng chọn một lựa chọn
        while (true) {
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

    public <E> E ref_getChoice(ArrayList<E> options) {
        int response;
        do {
            response = int_getChoice(options); // Nhận lựa chọn từ người dùng
        } while (response < 1 || response > options.size());

        return options.get(response - 1); // Trả về đối tượng tương ứng với lựa chọn
    }

}
