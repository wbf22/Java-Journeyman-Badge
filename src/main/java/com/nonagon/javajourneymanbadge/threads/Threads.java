package com.nonagon.javajourneymanbadge.threads;

import com.nonagon.javajourneymanbadge.printColors.Colors;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Queue;
import java.util.concurrent.*;

import static com.nonagon.javajourneymanbadge.printColors.Colors.ANSI_GREEN;
import static com.nonagon.javajourneymanbadge.printColors.Colors.ANSI_RESET;

@Component
public class Threads {


    private DanceRunnable danceRunnable;

    public void threadDance() throws InterruptedException {
        Queue<String> dataTrack = makeDataTrack();
        danceRunnable = new DanceRunnable(dataTrack, new RacerIdAssigner());
        Thread dancer1 = new Thread(danceRunnable);
        Thread dancer2 = new Thread(danceRunnable);

        System.out.println("Mince        Winkle");

        dancer1.start();
        dancer2.start();

        dancer1.join();
        dancer2.join();

        System.out.println(ANSI_GREEN + "Dance ended" + ANSI_RESET);

    }

    public void threadPicture() throws IOException {
        DataInputStream inputStream = new DataInputStream(new FileInputStream("C:\\FAMILY_SEARCH_PROJECTS\\Java-Journeyman-Badge\\src\\main\\resources\\Fireworks.txt"));


        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Future<String> future = executorService.submit(() -> {

            StringBuffer stringBuffer = new StringBuffer();
            try {
                while(inputStream.available() > 0){
                    stringBuffer.append(inputStream.readLine() + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return stringBuffer.toString();

        });
        System.out.print(Colors.getRandomColor());
        try {
            String ouput = future.get();
            DataOutputStream dataOutputStream = new DataOutputStream(System.out);
            dataOutputStream.writeBytes(ouput);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.print(ANSI_RESET);

    }



    private ArrayBlockingQueue<String> makeDataTrack(){
        ArrayBlockingQueue<String> dataTrack = new ArrayBlockingQueue<String>(10000);

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.submit(() -> {
            for(int i = 0; i < 2000; i++){
                dataTrack.add("^");
            }
            for(int i = 0; i < 2000; i++){
                dataTrack.add("-");
            }
            for(int i = 0; i < 2000; i++){
                dataTrack.add("+");
            }


        });



        return dataTrack;

    }

}
