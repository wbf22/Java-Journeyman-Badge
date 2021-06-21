package com.nonagon.javajourneymanbadge.misc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class RandomWord {

    public static String getRandomWord(Random random){
        Scanner reader = null;
        try {
            reader = new Scanner(new File("C:\\FAMILY_SEARCH_PROJECTS\\Java-Journeyman-Badge\\src\\main\\java\\com\\nonagon\\javajourneymanbadge\\rap\\EnglishWords.txt"));
            ArrayList<String> englishWords = new ArrayList<>();
            while(reader.hasNextLine()){
                englishWords.add(reader.nextLine());
            }

            return englishWords.get(random.nextInt(englishWords.size()));
        } catch (FileNotFoundException e) {
            return "whoops";
        }



    }
}
