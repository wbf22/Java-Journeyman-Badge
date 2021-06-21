package com.nonagon.javajourneymanbadge.threads.beepBop;

import com.nonagon.javajourneymanbadge.clarice.ClariceBrain;
import com.nonagon.javajourneymanbadge.printColors.Colors;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class BeepBopCallable implements Callable {
    private ReadWriteLock lock;
    private RoleAssigner roleAssigner;
    private List<Integer> outputOpportunities;
    private Boolean done = false;

    public BeepBopCallable(){
        lock = new ReentrantReadWriteLock();
        roleAssigner = new RoleAssigner();
        outputOpportunities = Collections.synchronizedList(new ArrayList<Integer>());
    }

    @Override
    public Object call() throws Exception {

        Roles role = roleAssigner.assignRole();

        Tally tally = null;
        if(role == Roles.writer) tally = writer();
        if(role == Roles.reader1) tally = reader(role);
        if(role == Roles.reader2) tally = reader(role);

        return tally;
    }

    private Tally reader(Roles role) {
        String offset = role == Roles.reader1 ? "                                " : "                                                                ";
        String name = role == Roles.reader1 ? offset + "        Bobby" : offset + "        Tommy";
        Random random = new Random(System.currentTimeMillis() + role.ordinal());
        Tally tally = new Tally();
        if(role == Roles.reader1){
            tally.setName("Bobby");
        }else{ tally.setName("Tommy");}

        while(!isDone()){
            int mult = 0;
            lock.readLock().lock();
            if(outputOpportunities.size() > 0){ mult = outputOpportunities.get(0); }
            lock.readLock().unlock();

            if (mult > 0){
                System.out.println(Colors.getRandomColor());
                printFileWithOffset(ClariceBrain.ROBOT, offset);
                System.out.println(name);
            }
            for(int i = 0; i < mult; i++){
                tally.addBeepsBops();
                System.out.println(Colors.getRandomColor());
                System.out.println("         " + offset + makeSound(random));
            }
            try { TimeUnit.MILLISECONDS.sleep(1000); } catch (InterruptedException e) {}
        }

        return tally;
    }

    private Tally writer() {
        Random random = new Random(System.currentTimeMillis());
        Tally tally = new Tally();
        tally.setName("Chief");
        for(int i = 0; i < 10; i++){
            lock.writeLock().lock();
            int mult = random.nextInt(3);
            System.out.println(Colors.getRandomColor());
            ClariceBrain.printFile(ClariceBrain.ROBOT);
            System.out.println("         Chief");
            try { TimeUnit.MILLISECONDS.sleep(1000); } catch (InterruptedException e) {}
            outputOpportunities.add(mult);

            for(int j = 0; j < mult; j++){
                tally.addBeepsBops();
                System.out.println(Colors.getRandomColor());
                System.out.println("         " + makeSound(random));
            }
            lock.writeLock().unlock();


            try { TimeUnit.MILLISECONDS.sleep(1000); } catch (InterruptedException e) {}
            lock.writeLock().lock();
            outputOpportunities.clear();
            lock.writeLock().unlock();
            try { TimeUnit.MILLISECONDS.sleep(1000); } catch (InterruptedException e) {}
        }
        lock.writeLock().lock();
        done = true;
        lock.writeLock().unlock();

        return tally;
    }


    private String makeSound(Random random){
        return random.nextBoolean() ? "beep" : "bop";
    }

    private void printFileWithOffset(String filePath, String offset){
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
            while(bufferedReader.ready()){
                System.out.println(offset + bufferedReader.readLine());
            }


        } catch (IOException e) { e.printStackTrace(); }
    }


    private boolean isDone(){
        boolean toReturn;
        lock.readLock().lock();
        toReturn = done;
        if(done && outputOpportunities.size() > 0) return false;
        lock.readLock().unlock();
        return  toReturn;
    }
}
