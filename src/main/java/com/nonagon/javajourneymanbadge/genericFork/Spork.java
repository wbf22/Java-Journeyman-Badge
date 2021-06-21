package com.nonagon.javajourneymanbadge.genericFork;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Spork extends Fork{
    private String offset = "";

    public void setOffset(String offset) {
        this.offset = offset;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("C:\\FAMILY_SEARCH_PROJECTS\\Java-Journeyman-Badge\\src\\main\\resources\\Spork.txt"));
            while(bufferedReader.ready()){
                stringBuilder.append(offset + bufferedReader.readLine() + "\n");
            }


        } catch (IOException e) { e.printStackTrace(); }

        return stringBuilder.toString();
    }


}
