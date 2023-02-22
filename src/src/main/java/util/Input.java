package util;

import java.util.Scanner;

public class Input {

    private Scanner scannerInput;

    public int getInt(int min, int max) {

        int userNum = scannerInput.nextInt();

        if (userNum >= min && userNum <= max) {
            return userNum;
        } else {
            System.out.printf("Enter a number between %d and %d: ", min, max);
            return getInt(min, max);
        }
    }
}
