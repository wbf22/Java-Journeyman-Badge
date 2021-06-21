package com.nonagon.javajourneymanbadge.threads;

public class RacerIdAssigner {
    private boolean foundFirstRacer = false;

    public int getRacerID(){
        if(!foundFirstRacer){
            foundFirstRacer = true;
            return 1;
        }else{
            return 2;
        }
    }



}
