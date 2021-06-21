package com.nonagon.javajourneymanbadge.printColors;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Random;

public class RandomColor extends ColorPrinter{
    public RandomColor(){
        type = "random";
    }


    @Override
    public void printColor() {
        ArrayList<String> colors = getColors();
        StringBuilder print = new StringBuilder(10000);


        for(int i = 0; i < 30; i++){
            for(int j = 0; j < 110; j++){
                print.append(" ");

            }
            print.append("\n");
        }

        StrayController strayController = new StrayController() {
            @Override
            public Pair<Integer, Integer> getNextPoint(int currentX, int currentY, int lastXDirection, int lastYDirection, Random random) {
                if(random.nextBoolean()) lastYDirection = 0;

                int xi = currentX;
                int yi = currentY;

                if(getBoolean(1, random)) xi += random.nextInt(3) - 1;
                else xi += lastXDirection;

                if(getBoolean(1, random)) yi += random.nextInt(3) - 1;
                else yi += lastYDirection;

                if(yi < 0 || xi < 0 || yi >= 30 || xi >= 100) return new Pair<>(currentX, currentY);


                return new Pair<>(xi, yi);
            }

            @Override
            public boolean getBoolean(int falsesForEveryTrue, Random random) {

                for(int i = 0; i < falsesForEveryTrue; i++){
                    if(!random.nextBoolean()) return false;
                }
                return true;
            }
        };

        for(int p = 0; p < 20; p++){
            int x = 50;
            int y = 15;
            String colorName = getRandomColor();
            int in= colors.indexOf(colorName);
            char color = Integer.toString(in).charAt(0);
            Random random = new Random(System.currentTimeMillis() + p);
            int lastXDirection = random.nextInt(3) -1;
            int lastYDirection = random.nextInt(3) -1;
            for(int i = 0; i < 100; i++){
                Pair<Integer, Integer> points = strayController.getNextPoint(x, y, lastXDirection, lastYDirection, random);
                lastXDirection = points.getKey() - x;
                lastYDirection = points.getValue() - y;
                x = points.getKey();
                y = points.getValue();


                int index = x + y * 111;
                print.setCharAt(index, color);

            }
        }

        //System.out.println(print);

        int t = 0;
        while(t < print.length()){
            if(Character.isDigit(print.charAt(t))){
                int colorIndex = Character.getNumericValue(print.charAt(t));
                System.out.print(colors.get(colorIndex));
                System.out.print('#');
            }else{
                System.out.print(print.charAt(t));
            }

            t++;
        }
        System.out.println(colors.get(0));

    }




}
