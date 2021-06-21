package com.nonagon.javajourneymanbadge.printColors;

import java.util.ArrayList;

public class Wheel extends ColorPrinter {

    public Wheel(){
        type = "wheel";
    }

    @Override
    public void printColor() {
        ArrayList<String> colors = getColors();
        System.out.println( colors.get(1) + "                        ||");
        System.out.println( colors.get(6) + "                  \\\\" + colors.get(1) + "    || " + colors.get(2) + "   //");
        System.out.println( colors.get(5) + "      ==\\\\    " + colors.get(6) + "     \\\\ " + colors.get(1) + "  || " + colors.get(2) + "  //   " + colors.get(3) + "      //==");
        System.out.println( colors.get(5) + "          ==\\\\  " + colors.get(6) + "    \\\\ " + colors.get(1) + " || " + colors.get(2) + " //  " + colors.get(3) + "    //==");
        System.out.println( colors.get(5) + "              ==\\\\ " + colors.get(6) + "  \\\\ " + colors.get(1) + "|| " + colors.get(2) + "// " + colors.get(3) + "  //==");
        System.out.println( colors.get(5) + "                  ==\\\\" + colors.get(6) + "\\\\" + colors.get(1) + "||" + colors.get(2) + "//" + colors.get(3) + "//==");
        System.out.println( colors.get(4) + "     ==================    ==================");
        System.out.println( colors.get(3) + "                  ==//" + colors.get(2) + "//" + colors.get(1) + "||" + colors.get(6) + "\\\\" + colors.get(5) + "\\\\==");
        System.out.println( colors.get(3) + "              ==//  " + colors.get(2) + " // " + colors.get(1) + "|| " + colors.get(6) + "\\\\ " + colors.get(5) + "  \\\\==");
        System.out.println( colors.get(3) + "          ==//  " + colors.get(2) + "    // " + colors.get(1) + " || " + colors.get(6) + " \\\\   " + colors.get(5) + "   \\\\==");
        System.out.println( colors.get(3) + "      ==//    " + colors.get(2) + "     //  " + colors.get(1) + " ||  " + colors.get(6) + " \\\\   " + colors.get(5) + "      \\\\==");
        System.out.println( colors.get(2) + "                  // " + colors.get(1) + "   ||  " + colors.get(6) + "  \\\\");
        System.out.println( colors.get(1) + "                        ||");
        System.out.println(colors.get(0));


    }
}
