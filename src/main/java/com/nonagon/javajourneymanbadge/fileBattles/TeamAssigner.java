package com.nonagon.javajourneymanbadge.fileBattles;

import java.util.ArrayList;

public class TeamAssigner {
  ArrayList<Integer> defendRoles = new ArrayList<>();
  ArrayList<Integer> attackRoles = new ArrayList<>();

  public int getDefendTeam(){
    int size = defendRoles.size();
    defendRoles.add(size);
    return size;
  }

  public int getAttackTeam(){
    int size = attackRoles.size();
    attackRoles.add(size);
    return size;
  }




}
