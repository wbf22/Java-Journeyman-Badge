package com.nonagon.javajourneymanbadge.printColors;

import java.util.Random;
import java.util.WeakHashMap;

public class Colors {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";


    public static String getRandomColor(){
        Random random = new Random();
        int s = random.nextInt(8);

        switch (s){
            case 0:
                return ANSI_BLACK;
            case 1:
                return ANSI_RED;
            case 2:
                return ANSI_GREEN;
            case 3:
                return ANSI_YELLOW;
            case 4:
                return ANSI_BLUE;
            case 5:
                return ANSI_PURPLE;
            case 6:
                return ANSI_CYAN;
            case 7:
                return ANSI_WHITE;
        }

        return ANSI_RESET;

    }

}
