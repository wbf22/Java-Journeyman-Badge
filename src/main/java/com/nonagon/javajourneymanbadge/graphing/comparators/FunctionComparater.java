package com.nonagon.javajourneymanbadge.graphing.comparators;

import java.math.BigDecimal;
import java.util.Comparator;

import com.nonagon.javajourneymanbadge.graphing.functions.Function;

public class FunctionComparater implements Comparator<Function> {

  private double rangeStart = 0;
  private double rangeEnd = 10;


  public FunctionComparater() {
  }

  public FunctionComparater(double rangeStart, double rangeEnd) {
    this.rangeStart = rangeStart;
    this.rangeEnd = rangeEnd;
  }

  private double increment = 0.01;

  @Override
  public int compare(Function o1, Function o2) {
    double fun1y = o1.integrate(increment, rangeStart, rangeEnd).doubleValue();
    double fun2y = o2.integrate(increment, rangeStart, rangeEnd).doubleValue();


    if(fun1y == fun2y) {
      return 0;
    }
    else {
      return fun1y >  fun2y ? 1 : -1;
    }
  }
}
