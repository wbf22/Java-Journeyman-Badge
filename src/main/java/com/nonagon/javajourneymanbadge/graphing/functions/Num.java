package com.nonagon.javajourneymanbadge.graphing.functions;

import java.math.BigDecimal;

public class Num extends Term{
  private BigDecimal val;

  @Override
  public BigDecimal evaluate(BigDecimal var) {
    return val;
  }

  @Override
  public String toString() {
    return val.toString();
  }

  public BigDecimal getVal() {
    return val;
  }

  public void setVal(BigDecimal val) {
    this.val = val;
  }

}
