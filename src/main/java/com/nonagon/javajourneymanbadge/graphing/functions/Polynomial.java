package com.nonagon.javajourneymanbadge.graphing.functions;

import java.math.BigDecimal;

public class Polynomial extends Term{
  private BigDecimal coefficient;
  private Term variable;
  private BigDecimal exponent;

  public Polynomial(BigDecimal coefficient, Term variable, BigDecimal exponent) {
    this.exponent = exponent;
    this.coefficient = coefficient;
    this.variable = variable;
  }

  @Override
  public BigDecimal evaluate(BigDecimal var){
    if(variable.getClass() == X.class){
      Double pow = Math.pow(var.doubleValue(), exponent.doubleValue());
      BigDecimal res = BigDecimal.valueOf( pow * coefficient.doubleValue());
      return res;
    }
    return var;
  }

  @Override
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    if (coefficient.doubleValue() < BigDecimal.ZERO.doubleValue()){ stringBuilder.append("- "); }
    else{ stringBuilder.append("+ "); }

    stringBuilder.append(coefficient);
    if (variable.getClass() == Function.class){
      stringBuilder.append("(");
      stringBuilder.append(variable.toString());
      stringBuilder.append(")");
    }else {
      stringBuilder.append(variable.toString());
    }

    if (exponent != BigDecimal.ONE){
      stringBuilder.append("^");
      stringBuilder.append(exponent);
    }

    return stringBuilder.toString();
  }


  public BigDecimal getExponent() {
    return exponent;
  }

  public void setExponent(BigDecimal exponent) {
    this.exponent = exponent;
  }

  public BigDecimal getCoefficient() {
    return coefficient;
  }

  public void setCoefficient(BigDecimal coefficient) {
    this.coefficient = coefficient;
  }
}
