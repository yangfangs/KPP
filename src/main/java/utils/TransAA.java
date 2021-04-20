package utils;

import java.util.Arrays;

public class TransAA {

    public static String tarnsToHY(String seq){
        char[] x = seq.toUpperCase().toCharArray();

        // convert to H Y
        for (int i = 0; i < x.length; i++) {
            switch (x[i]) {
                case 'R':
                case 'K':
                case 'D':
                case 'E':
                case 'Q':
                case 'N':
                case 'H':
                case 'S':
                case 'T':
                case 'Y':
                case 'C':
                case 'W': { x[i] = 'Y'; break; }
                case 'A':
                case 'I':
                case 'L':
                case 'M':
                case 'F':
                case 'V':
                case 'P':
                case 'G': { x[i] = 'H'; break; }
            }
        }
        return String.valueOf(x);

    }

    public static String tarnsToPO(String seq){
        char[] x = seq.toUpperCase().toCharArray();

        // convert to H Y
        for (int i = 0; i < x.length; i++) {
            switch (x[i]) {
                case 'Q':
                case 'N':
                case 'H':
                case 'S':
                case 'Y':
                case 'C':
                case 'W':
                case 'T': { x[i] = 'P'; break; }
                case 'R':
                case 'K':
                case 'D':
                case 'E':
                case 'A':
                case 'I':
                case 'L':
                case 'M':
                case 'F':
                case 'V':
                case 'P':
                case 'G': { x[i] = 'O'; break; }
            }
        }
        return String.valueOf(x);

    }

    public static String tarnsToCH(String seq){
        char[] x = seq.toUpperCase().toCharArray();

        // convert to C H
        for (int i = 0; i < x.length; i++) {
            switch (x[i]) {
                case 'R':
                case 'K':
                case 'D':
                case 'E': { x[i] = 'C'; break; }
                case 'Q':
                case 'N':
                case 'H':
                case 'S':
                case 'T':
                case 'Y':
                case 'C':
                case 'W':
                case 'A':
                case 'I':
                case 'L':
                case 'M':
                case 'F':
                case 'V':
                case 'P':
                case 'G': { x[i] = 'H'; break; }
            }
        }
        return String.valueOf(x);

    }

    public static String tarnsToCHP(String seq){
        char[] x = seq.toUpperCase().toCharArray();

        // convert to C H P
        for (int i = 0; i < x.length; i++) {
            switch (x[i]) {
                case 'R':
                case 'K':
                case 'D':
                case 'E': { x[i] = 'C'; break; }
                case 'Q':
                case 'N':
                case 'H':
                case 'S':
                case 'T':
                case 'Y':
                case 'C':
                case 'W':{ x[i] = 'P'; break; }
                case 'A':
                case 'I':
                case 'L':
                case 'M':
                case 'F':
                case 'V':
                case 'P':
                case 'G': { x[i] = 'H'; break; }
            }
        }
        return String.valueOf(x);

    }

}
