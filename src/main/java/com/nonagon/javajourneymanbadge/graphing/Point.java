package com.nonagon.javajourneymanbadge.graphing;

import java.math.BigDecimal;

public class Point implements Comparable<Point>{
  private BigDecimal x;
  private BigDecimal y;

  public Point(BigDecimal x, BigDecimal y) {
    this.x = x;
    this.y = y;
  }

  public BigDecimal getX() {
    return x;
  }

  public void setX(BigDecimal x) {
    this.x = x;
  }

  public BigDecimal getY() {
    return y;
  }

  public void setY(BigDecimal y) {
    this.y = y;
  }

  @Override
  public int compareTo(Point o) {
    if(this.x.doubleValue() == o.x.doubleValue())
      return 0;
    else
      return this.x.doubleValue() > o.x.doubleValue() ? 1 : -1;
  }
}
