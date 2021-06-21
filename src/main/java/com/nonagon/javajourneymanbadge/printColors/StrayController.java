package com.nonagon.javajourneymanbadge.printColors;

import javafx.util.Pair;

import java.util.Random;

public interface StrayController {

    Pair<Integer, Integer> getNextPoint(int currentX, int currentY, int lastXDirection, int lastYDirection, Random random);

    boolean getBoolean(int falsesForEveryTrue, Random random);

}
