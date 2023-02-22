package util;

import java.util.Scanner;

public class Input {

    private static Scanner scannerInput;

    public Input() {
        scannerInput = new Scanner(System.in);
    }

    public String getString() {
        return scannerInput.nextLine();
    }

    public String getString(String prompt) {
        System.out.println(prompt);
        return getString();
    }

    public static int getInt(int min, int max) {

        int userNum = scannerInput.nextInt();

        if (userNum >= min && userNum <= max) {
            return userNum;
        } else {
            System.out.printf("Enter a number between %d and %d: ", min, max);
            return getInt(min, max);
        }
    }

    public static void setScannerInput(Scanner scannerInput) {
        Input.scannerInput = scannerInput;
    }
}