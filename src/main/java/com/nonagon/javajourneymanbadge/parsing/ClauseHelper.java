package com.nonagon.javajourneymanbadge.parsing;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class ClauseHelper {


    public static void addToClauseFile(String fileName, Collection<String> clausesToAdd) throws IOException, ClassNotFoundException {
        HashSet<String> clauses;

        try{
            // Reads the object
            FileInputStream fileIn = new FileInputStream(fileName);
            ObjectInputStream objIn = new ObjectInputStream(fileIn);

            // Reads the objects
            clauses = (HashSet<String>) objIn.readObject();
            objIn.close();
        }catch (Exception e){
            clauses = new HashSet<>();
        }

        clauses.addAll(clausesToAdd);


        FileOutputStream fileOut = new FileOutputStream(fileName);
        // Creates an ObjectOutputStream
        ObjectOutputStream objOut = new ObjectOutputStream(fileOut);

        // Writes objects to the output stream
        objOut.writeObject(clauses);


        objOut.close();
        fileOut.close();
    }

    public static void removeClauseInFile(String fileName, Collection<String> clausesToAdd) throws IOException, ClassNotFoundException {
        // Reads the object
        FileInputStream fileIn = new FileInputStream(fileName);
        ObjectInputStream objIn = new ObjectInputStream(fileIn);

        // Reads the objects
        Set<String> clauses = (HashSet<String>) objIn.readObject();
        clauses.removeAll(clausesToAdd);


        FileOutputStream fileOut = new FileOutputStream(fileName);
        // Creates an ObjectOutputStream
        ObjectOutputStream objOut = new ObjectOutputStream(fileOut);

        // Writes objects to the output stream
        objOut.writeObject(clauses);

        fileIn.close();
        fileOut.close();
        objOut.close();
        objIn.close();
    }

    public static Set<String> getClauses(String fileName){

        try {

            // Reads the object
            FileInputStream fileIn = null;

            fileIn = new FileInputStream(fileName);
            ObjectInputStream objIn = new ObjectInputStream(fileIn);

            // Reads the objects
            Set<String> clauses = (Set<String>) objIn.readObject();

            fileIn.close();
            objIn.close();

            return clauses;
        }
        catch (Exception e) {
            return new HashSet<>();
        }


    }


}
