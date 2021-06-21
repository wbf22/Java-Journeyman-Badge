package com.nonagon.javajourneymanbadge.rap;

import com.nonagon.javajourneymanbadge.clarice.ClariceBrain;
import com.nonagon.javajourneymanbadge.printColors.Colors;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Rap {
    private ArrayList<String> englishWords;

    private void storeRap(String rap) throws IOException {
        FileWriter rapSave = new FileWriter(new File("C:\\FAMILY_SEARCH_PROJECTS\\Java-Journeyman-Badge\\src\\main\\java\\com\\nonagon\\javajourneymanbadge\\rap\\ClaricesFavoriteRaps.txt"), true);
        PrintWriter printWriter = new PrintWriter(rapSave);
        String title = englishWords.get(new Random(System.currentTimeMillis()).nextInt(englishWords.size()));
        printWriter.printf(title);
        printWriter.printf("\nby Clarice\n\n");
        printWriter.close();
        System.out.println("Wow I liked that one! \n I think I'll save that...");

        FileWriter rapSaveB = new FileWriter(new File("C:\\FAMILY_SEARCH_PROJECTS\\Java-Journeyman-Badge\\src\\main\\java\\com\\nonagon\\javajourneymanbadge\\rap\\ClaricesFavoriteRaps.txt"), true);
        BufferedWriter bufferedWriter = new BufferedWriter(rapSaveB);
        bufferedWriter.append(rap);
        bufferedWriter.append("\n\n\n\n");
        bufferedWriter.close();



    }

    public void rap() throws IOException, InterruptedException {
        ClariceBrain.printFile(ClariceBrain.CLARICE_COOL);
        System.out.println("One sec, let me gather my thoughts yall..");
        TimeUnit.MILLISECONDS.sleep(2000);

        Scanner reader = new Scanner(new File("C:\\FAMILY_SEARCH_PROJECTS\\Java-Journeyman-Badge\\src\\main\\java\\com\\nonagon\\javajourneymanbadge\\rap\\EnglishWords.txt"));
        englishWords = new ArrayList<>();
        while(reader.hasNextLine()){
            englishWords.add(reader.nextLine());
        }

        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random(System.currentTimeMillis());
        for(int i = 0; i < 4; i++){

            ClariceBrain.printFile(ClariceBrain.CLARICE_COOL);
            TimeUnit.MILLISECONDS.sleep(400);
            String word;

            word = englishWords.get(random.nextInt(englishWords.size()));
            stringBuilder.append(word + " ");
            System.out.print(word + " ");
            TimeUnit.MILLISECONDS.sleep(800);

            word = englishWords.get(random.nextInt(englishWords.size()));
            stringBuilder.append(word + " ");
            System.out.print(word + " ");
            TimeUnit.MILLISECONDS.sleep(800);

            word = englishWords.get(random.nextInt(englishWords.size()));
            stringBuilder.append(word + " ");
            System.out.print(word + " ");
            TimeUnit.MILLISECONDS.sleep(400);

            word = englishWords.get(random.nextInt(englishWords.size()));
            stringBuilder.append(word + " ");
            System.out.print(word + " ");
            TimeUnit.MILLISECONDS.sleep(400);

            word = englishWords.get(random.nextInt(englishWords.size()));
            stringBuilder.append(word + "\n");
            System.out.print(word + "\n");
            TimeUnit.MILLISECONDS.sleep(400);




        }
        System.out.println(Colors.getRandomColor());
        ClariceBrain.printFile("C:\\FAMILY_SEARCH_PROJECTS\\Java-Journeyman-Badge\\src\\main\\resources\\Fireworks.txt");
        System.out.println(Colors.ANSI_RESET);
        ClariceBrain.printFile(ClariceBrain.CLARICE_SMILE);

        if(random.nextBoolean()){
            storeRap(stringBuilder.toString());
        }

    }




}
