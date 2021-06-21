package com.nonagon.javajourneymanbadge.threads.beepBop;

import com.nonagon.javajourneymanbadge.printColors.Colors;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class PlayWithRobots {

    public void playWithRobots(){
        BeepBopCallable beepBopCallable = new BeepBopCallable();
        FutureTask beepBopWriter = new FutureTask(beepBopCallable);
        FutureTask beepBopReader1 = new FutureTask(beepBopCallable);
        FutureTask beepBopReader2 = new FutureTask(beepBopCallable);



        Thread one = new Thread(beepBopWriter);
        Thread two = new Thread(beepBopReader1);
        Thread three = new Thread(beepBopReader2);

        one.start();
        two.start();
        three.start();
        List<Tally> tallies = new ArrayList<>();
        try {
            tallies.add((Tally) beepBopWriter.get());
            tallies.add((Tally) beepBopReader1.get());
            tallies.add((Tally) beepBopReader2.get());
        } catch (Exception e) {}

        printTallies(tallies);
        System.out.println(Colors.ANSI_RESET);


    }

    private void printTallies(List<Tally> tallies){

        System.out.println();
        System.out.println("                        Results                             ");
        System.out.println("____________________________________________________________");
        int whiteLeft;
        //60 slots
        //first name
        System.out.print("|");
        printWhiteSpace(4);
        System.out.print(tallies.get(0).getName());
        whiteLeft = 15 - tallies.get(0).getName().length();
        printWhiteSpace(whiteLeft);

        //next name
        printWhiteSpace(5);
        System.out.print(tallies.get(1).getName());
        whiteLeft = 15 - tallies.get(1).getName().length();
        printWhiteSpace(whiteLeft);

        //next name
        printWhiteSpace(5);
        System.out.print(tallies.get(2).getName());
        whiteLeft = 15 - tallies.get(2).getName().length() - 1;
        printWhiteSpace(whiteLeft);
        System.out.print("|\n");


        //first tally
        System.out.print("|");
        printWhiteSpace(4);
        System.out.print(tallies.get(0).getBeepsBops());
        whiteLeft = 15 - Integer.toString(tallies.get(0).getBeepsBops()).length();
        printWhiteSpace(whiteLeft);

        //next tally
        printWhiteSpace(5);
        System.out.print(tallies.get(1).getBeepsBops());
        whiteLeft = 15 - Integer.toString(tallies.get(1).getBeepsBops()).length();
        printWhiteSpace(whiteLeft);

        //next tally
        printWhiteSpace(5);
        System.out.print(tallies.get(2).getBeepsBops());
        whiteLeft = 15 - Integer.toString(tallies.get(2).getBeepsBops()).length() - 1;
        printWhiteSpace(whiteLeft);
        System.out.print("|\n");

        System.out.println("____________________________________________________________");


    }

    private void printWhiteSpace(int spaces){
        for(int i = 0; i < spaces; i++) System.out.print(" ");
    }

}
