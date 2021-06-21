package com.nonagon.javajourneymanbadge.printColors;

import java.util.ArrayList;
import java.util.Random;

import static com.nonagon.javajourneymanbadge.printColors.Colors.ANSI_PURPLE;
import static com.nonagon.javajourneymanbadge.printColors.Colors.ANSI_RESET;

public abstract class ColorPrinter {

    protected String type;

    protected ArrayList<String> getColors(){
        ArrayList<String> colors = new ArrayList<>();
        colors.add(ANSI_RESET);
        colors.add(Colors.ANSI_RED);
        colors.add(Colors.ANSI_YELLOW);
        colors.add(Colors.ANSI_GREEN);
        colors.add(Colors.ANSI_BLUE);
        colors.add(Colors.ANSI_CYAN);
        colors.add(ANSI_PURPLE);
        return colors;

    }

    public abstract void printColor();

    protected String getRandomColor(){
        Random random = new Random();
        int s = random.nextInt(7);

        switch (s){
            case 0:
                return Colors.ANSI_RED;
            case 1:
                return Colors.ANSI_YELLOW;
            case 2:
                return Colors.ANSI_GREEN;
            case 3:
                return Colors.ANSI_YELLOW;
            case 4:
                return Colors.ANSI_BLUE;
            case 5:
                return Colors.ANSI_CYAN;
            case 6:
                return Colors.ANSI_PURPLE;
        }

        return Colors.ANSI_RESET;
    }

}
