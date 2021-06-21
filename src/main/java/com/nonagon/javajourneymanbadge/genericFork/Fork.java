package com.nonagon.javajourneymanbadge.genericFork;

import javax.print.StreamPrintService;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class Fork<T>{

    private T possession;
    private String name;
    private String offset;

    public void setPossession(T possession){
        this.possession = possession;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    public void introduce() {
        StringBuilder stringBuilder = new StringBuilder();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("C:\\FAMILY_SEARCH_PROJECTS\\Java-Journeyman-Badge\\src\\main\\resources\\Fork.txt"));
            while(bufferedReader.ready()){
                System.out.println(offset + bufferedReader.readLine());
            }
            System.out.println(offset + "I'm " + name + "\n");
            TimeUnit.MILLISECONDS.sleep(2000);
            System.out.println(offset + "This is my most \n" + offset + "prized possession:\n");
            TimeUnit.MILLISECONDS.sleep(2000);
            if(possession.getClass() == Spork.class) ((Spork)possession).setOffset(offset);
            System.out.println(possession.toString());
            TimeUnit.MILLISECONDS.sleep(2000);


        } catch (Exception e) { e.printStackTrace(); }

    }
}
