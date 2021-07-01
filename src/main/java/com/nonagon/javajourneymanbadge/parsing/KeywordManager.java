package com.nonagon.javajourneymanbadge.parsing;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import org.springframework.stereotype.Component;

import static com.nonagon.javajourneymanbadge.clarice.ClariceBrain.*;

@Component
public class KeywordManager {

    public void doProtectedKeywordFunctions(String input, Set<String> keywords) throws IOException {



        for (String key: keywords){
            switch (key){
                case "add":
                    addToClausesFile(input);
                    break;
                case "remove":
                    removeFromClausesFile(input);
                    break;
                case "print":
                    printClausesFile(input);
                    break;
                case "index":
                    addIndexFile(input);
                    break;
                case "unindex":
                    removeIndexFile(input);
                    break;
            }


        }

    }



    private void addIndexFile(String input) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(input);
        ArrayList<String> tokens = new ArrayList<>();
        while(tokenizer.hasMoreTokens()) tokens.add(tokenizer.nextToken().toLowerCase());


        int index = tokens.indexOf("index");
        String newFileName = "src/main/resources/Clauses/" + tokens.get(index + 1).toUpperCase() + ".txt";

        Path newFilePath = Paths.get(newFileName);
        Files.createFile(newFilePath);
        System.out.println("Created new clauses file: " + newFileName);
        File indexFile = new File(CLAUSE_FILES_INDEX);
        FileWriter fr = new FileWriter(indexFile, true);
        fr.write('\n' + tokens.get(index + 1).toUpperCase());
        fr.close();
    }

    private void removeIndexFile(String input) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(input);
        ArrayList<String> tokens = new ArrayList<>();
        while(tokenizer.hasMoreTokens()) tokens.add(tokenizer.nextToken().toLowerCase());


        int index = tokens.indexOf("unindex");
        String fileToDelete = "src/main/resources/Clauses/" + tokens.get(index + 1).toUpperCase() + ".txt";


        if (Files.deleteIfExists(Paths.get(fileToDelete))){
            System.out.println("Deleted file: " + fileToDelete);
            //remove line from CLAUSE_FILES_INDEX.txt
            ArrayList<String> lines = new ArrayList<>();

            File file = new File(CLAUSE_FILES_INDEX);
            Scanner scanner = null;
            scanner = new Scanner(file);
            while(scanner.hasNextLine()){ lines.add(scanner.nextLine()); }
            lines.remove(tokens.get(index + 1).toUpperCase());

            FileWriter myWriter = new FileWriter(CLAUSE_FILES_INDEX);
            boolean first = true;
            for (String clauseFile : lines){
                if (!first) { myWriter.write( '\n');}
                myWriter.write(clauseFile);
                first = false;
            }
            myWriter.close();


        }
    }

    private void addToClausesFile(String input){


        try{
            StringTokenizer tokenizer = new StringTokenizer(input);
            ArrayList<String> tokens = new ArrayList<>();
            while(tokenizer.hasMoreTokens()) tokens.add(tokenizer.nextToken().toLowerCase());


            int index = tokens.indexOf("add");
            int indexTo = tokens.indexOf("to");
            String destination = tokens.get(indexTo + 1);
            ArrayList<String> additions = new ArrayList<>();
            additions.addAll(tokens.subList(index + 1, indexTo));
            String filename = ClauseFilesIndex.get(destination.toUpperCase());
            ClauseHelper.addToClauseFile(filename, additions);

            printFile(CLARICE_SMILE);

        }catch (Exception e){
            e.printStackTrace();
            printFile(CLARICE_CRINGE);
        }
    }

    private void removeFromClausesFile(String input){


        try{
            StringTokenizer tokenizer = new StringTokenizer(input);
            ArrayList<String> tokens = new ArrayList<>();
            while(tokenizer.hasMoreTokens()) tokens.add(tokenizer.nextToken().toLowerCase());


            int index = tokens.indexOf("remove");
            int indexTo = tokens.indexOf("from");
            String destination = tokens.get(indexTo + 1);
            ArrayList<String> toRemove = new ArrayList<>();
            toRemove.addAll(tokens.subList(index + 1, indexTo));
            toRemove.remove("from");
            toRemove.remove(destination);
            ClauseHelper.removeClauseInFile(ClauseFilesIndex.get(destination), toRemove);

            printFile(CLARICE_SMILE);

        }catch (Exception e){
            e.printStackTrace();
            printFile(CLARICE_CRINGE);
        }
    }

    private void printClausesFile(String input){


        try{

            for (Map.Entry<String, String> entry : ClauseFilesIndex.entrySet()) {
                System.out.println(ClauseHelper.getClauses(entry.getValue()));
            }


            printFile(CLARICE_SMILE);

        }catch (Exception e){
            e.printStackTrace();
            printFile(CLARICE_CRINGE);
        }
    }


}
