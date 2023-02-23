package util;

import java.util.Scanner;

public class Input {

    private static Scanner scannerInput;

    public Input() {
        scannerInput = new Scanner(System.in);
    }

    public static int getInt(int min, int max) {

        int userNum = scannerInput.nextInt();

        if (userNum >= min && userNum <= max) {
            return userNum;
        } else {
            System.out.printf("Enter a number between %d and %d: \n", min, max);
            return getInt(min, max);
        }
    }

    public static int getInt(int min, int max, String prompt) {

        System.out.println(prompt);

        int userNum = scannerInput.nextInt();

        if (userNum >= min && userNum <= max) {
            return userNum;
        } else {
            System.out.printf("Enter a number between %d and %d: ", min, max);
            return getInt(min, max);
        }
    }

    public static void clearBuffer(){
        scannerInput.nextLine();
    }

    public static String getString() {
        return scannerInput.nextLine();
    }

    public static String getString(String prompt) {
        System.out.println(prompt);
        return getString();
    }

    public static boolean yesNo() {
        String choice = scannerInput.nextLine(); //calss example = getString(); called encapsulation but it makes things clean and simple
        if (choice.equalsIgnoreCase("y")) {
            return true;
        } else if (choice.equalsIgnoreCase("yes")){
            return true;
        } else {
            return false;
        }
    }


    public static boolean yesNo(String prompt) {
        System.out.println(prompt);
        return yesNo();

    }

    public static void setScannerInput(Scanner scannerInput) {
        util.Input.scannerInput = scannerInput;
    }
}