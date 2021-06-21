package com.nonagon.javajourneymanbadge.printColors;

import java.util.ArrayList;

public class Square extends ColorPrinter{
    public Square(){
        type = "square";
    }

    @Override
    public void printColor() {
        ArrayList<String> colors = getColors();
        for(int i = 0; i < 12; i++){
            System.out.print(colors.get(1) + "############");
            System.out.print(colors.get(2) + "############");
            System.out.print(colors.get(3) + "############");
            System.out.print(colors.get(4) + "############");
            System.out.print(colors.get(5) + "############");
            System.out.print(colors.get(6) + "############");
            System.out.println();

        }

        System.out.println(colors.get(0));


    }
}
