package eu.scislo.mobilenext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RunLengthEncoder {

    public static String separator = ",";

    public static String encode(String in) throws InvalidInputException {
        if (in == null || in.equals("")) {
            throw new InvalidInputException("Dane do kodowania nie mogą być puste!");
        }

        char character = in.charAt(0);
        int count = 1;
        String encoded = "";

        for (int i = 1; i < in.length(); i++) {
            // current char been found
            if (character == in.charAt(i)) {
                count++;
                // current char has not been found
            } else {
                // add previous character plus it's count
                encoded += "" + character + count + separator;

                // add new char to temp variables
                character = in.charAt(i);
                count = 1;
            }
        }

        encoded += "" + character + count + separator;

        return encoded;
    }

    public static String decode(String in) throws InvalidInputException {
        if (in == null || in.equals("")) {
            throw new InvalidInputException("Dane do odkodowania nie mogą być puste!");
        }

        String splitted[] = in.split(separator);
        String out = "";

        for (String s : splitted) {
            if (s.length() > 0) {
                char character = s.charAt(0);
                int count = 0;
                Pattern pattern = Pattern.compile("[^\\d](\\d+)");
                Matcher matcher = pattern.matcher(s);
                if (matcher.matches()) {
                    count = Integer.parseInt(matcher.group(1));
                } else {
                    throw new InvalidInputException("Niepoprawne dane do odkodowania!");
                }
                for (int i = 0; i < count; i++) {
                    out += "" + character;
                }
            }
        }

        if (out.equals("")) {
            throw new InvalidInputException("Niepoprawne dane do odkodowania!");
        }

        return out;
    }

}
