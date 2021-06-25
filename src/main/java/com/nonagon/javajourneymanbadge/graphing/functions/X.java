package com.nonagon.javajourneymanbadge.graphing.functions;

import java.math.BigDecimal;

public class X extends Term{

  @Override
  public BigDecimal evaluate(BigDecimal var) {
    return var;
  }

  @Override
  public String toString() {
    return "x";
  }

}
