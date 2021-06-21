package com.nonagon.javajourneymanbadge.threads.beepBop;

public class RoleAssigner {
    private int numberOfAssignedRoles = 0;

    public Roles assignRole(){
        if(numberOfAssignedRoles == 0){
            numberOfAssignedRoles++;
            return Roles.writer;
        }else if (numberOfAssignedRoles == 1){
            numberOfAssignedRoles++;
            return Roles.reader1;
        }else{
            return Roles.reader2;
        }
    }


}
