package utils;

import java.util.Scanner;

public class CommandReader {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static int readNumericValue() {
        String string = SCANNER.nextLine();
        if (isNumeric(string)) {
            int value = Integer.parseInt(string);
            if (value >= 0 && value <= 999) {
                return value;
            }
        }
        System.out.println("The input value must be an integer and must be between 0 and 999.");
        return readNumericValue();
    }

    private static boolean isNumeric(String s) {
        if (s == null) {
            return false;
        }
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
