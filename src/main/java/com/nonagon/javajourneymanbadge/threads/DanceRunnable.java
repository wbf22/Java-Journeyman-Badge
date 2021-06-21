package com.nonagon.javajourneymanbadge.threads;

import org.springframework.stereotype.Component;

import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import static com.nonagon.javajourneymanbadge.printColors.Colors.getRandomColor;

@Component
public class DanceRunnable implements Runnable{

    private Queue<String> dataTrack;
    private ReentrantLock lock;
    private RacerIdAssigner racerIdAssigner;
    private int currentRacer = 1;

    public DanceRunnable(Queue<String> dataTrack, RacerIdAssigner racerIdAssigner){
        this.dataTrack = dataTrack;
        this.racerIdAssigner = racerIdAssigner;
        lock = new ReentrantLock();
    }



    @Override
    public void run() {
        lock.lock();
        int racerId = racerIdAssigner.getRacerID();
        lock.unlock();

        boolean notFinished = true;
        int numberPopped = 0;
        while(notFinished){

            lock.lock();
            if (dataTrack.size() <= 0){
                lock.unlock();
                break;
            }
            dataTrack.remove();
            numberPopped++;
            if(numberPopped >= 99){
                try { TimeUnit.MILLISECONDS.sleep(200); } catch (InterruptedException e) {}
                numberPopped = 0;
                if(currentRacer != racerId) {currentRacer = racerId; System.out.print(getRandomColor());}
                if(racerId == 1) System.out.println(dataTrack.remove());
                if(racerId == 2) System.out.println("              " + dataTrack.remove());
            }

            lock.unlock();

        }


    }



}