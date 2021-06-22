package com.nonagon.javajourneymanbadge.trys;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import com.nonagon.javajourneymanbadge.clarice.ClariceBrain;

public class Trys {

  public void doAllExceptionStuff() throws InterruptedException {
    doSomeRandomErrorsHaha();
    tryWith();
    SillyExceptions();
  }

  private void doSomeRandomErrorsHaha() throws InterruptedException {

    try {
      throwRandomError();
    }
    catch (NullPointerException e) {
      ClariceBrain.printFile(ClariceBrain.CLARICE_CRINGE);
      e.printStackTrace();
      TimeUnit.MILLISECONDS.sleep(100);
    }
    catch (IOException e){
      ClariceBrain.printFile(ClariceBrain.CLARICE_CRINGE);
      e.printStackTrace();
      TimeUnit.MILLISECONDS.sleep(100);
    }
    finally {
      ClariceBrain.printFile(ClariceBrain.CLARICE_SMILE);
      System.out.println("Oh well, I guess sometimes exceptions are thrown, but it's ok");
    }



  }
  private void tryWith(){

    try(Scanner scanner = new Scanner(new File(ClariceBrain.ROBOT))){
      System.out.println("Doing a 'try with', I hope it works!");
      TimeUnit.MILLISECONDS.sleep(3000);

      while(scanner.hasNextLine()){
        System.out.println(scanner.nextLine());
      }

      System.out.println("Yay it worked!");
    }catch (FileNotFoundException | InterruptedException e){ }



  }
  private void SillyExceptions() throws InterruptedException {

    System.out.println("I think I'm going to try to do some math!");
    ClariceBrain.printFile(ClariceBrain.CLARICE_SMILE);
    int total = 0;
    int numMistakes = 0;
    for (int i = 0; i < 101; i++){
      System.out.println(total + " + " + i + " = " + (total + i));
      total += i;
      TimeUnit.MILLISECONDS.sleep(250);
      try{
        chanceToThrowSillyException();
      }catch (ClariceSillyException e){
        System.out.println(total + " + " + 2 + " = " + (total + 2));
        total += 2;
        System.out.println(e.getMessage());
        numMistakes++;
      }

    }

    System.out.println("Hey this is what the total is: " + total);
    System.out.println("Nice!");
    System.out.println("Subtracting my mistakes it really is this: " + (total - numMistakes * 2));


    TimeUnit.MILLISECONDS.sleep(3000);
    System.out.println();
    System.out.println("Phil's a lot fast I'll let him go to 1000");
    ClariceBrain.printFile(ClariceBrain.ROBOT);
    System.out.println("   Phil");
    TimeUnit.MILLISECONDS.sleep(3000);
    int philTotal = 0;
    for (int i = 0; i < 1001; i++){
      System.out.println(philTotal + " + " + i + " = " + (philTotal + i));
      philTotal += i;
      TimeUnit.MILLISECONDS.sleep(10);

    }

    System.out.println();
    System.out.println("Carl's even faster I'll let him go to 1,000,000");
    ClariceBrain.printFile(ClariceBrain.ROBOT);
    System.out.println("   Carl");
    TimeUnit.MILLISECONDS.sleep(3000);
    long carTotal = 0;
    for (long i = 0; i < 1000001; i++){
      System.out.println(carTotal + " + " + i + " = " + (carTotal + i));
      carTotal += i;

    }

    System.out.println("So here's the pattern:");
    System.out.println("0-100 adds to " + (total - numMistakes * 2));
    System.out.println("0-1000 adds to " + philTotal);
    System.out.println("0-1,000,000 adds to " + carTotal);
    System.out.println("Wow that's a weird pattern, it's kind of cool though!");




  }

  private void chanceToThrowSillyException() throws ClariceSillyException{
    Random random = new Random(System.nanoTime());
    if (random.nextInt(100) < 4){
      throw new ClariceSillyException("Oops haha I just added 2 by accident haha!");
    }
  }
  private void throwRandomError() throws NullPointerException, IOException{
    Random random = new Random(System.currentTimeMillis());
    if (random.nextBoolean()){
      NullPointer();
    }else {
      IO();
    }
  }
  public void NullPointer() throws NullPointerException{
    throw new NullPointerException();
  }
  public void IO() throws IOException{
    throw new IOException();
  }





}
