package com.nonagon.javajourneymanbadge.graphing.functions;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Function extends Term{
  ArrayList<Term> terms = new ArrayList<>();

  public Function() {
  }

  public Function(Term term) {
    this.terms.add(term);
  }

  @Override
  public BigDecimal evaluate(BigDecimal var) {
    BigDecimal total = BigDecimal.valueOf(0.0);
    for (Term t : terms){
      BigDecimal termVal = t.evaluate(var);
      total = BigDecimal.valueOf(total.doubleValue() + termVal.doubleValue());
    }
    return total;
  }


  public BigDecimal integrate(double step, double rangeStart, double rangeEnd){
    double total = 0;
    for (double i = rangeStart; i < rangeEnd; i += step){
      total += evaluate(BigDecimal.valueOf(i)).multiply(BigDecimal.valueOf(step)).doubleValue();
    }
    return BigDecimal.valueOf(total);
  }


  @Override
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    for (Term t : terms){
      stringBuilder.append(t.toString());
      stringBuilder.append(" ");
    }

    if (stringBuilder.charAt(0) == '+' || stringBuilder.charAt(0) == '-'){
      return stringBuilder.subSequence(2, stringBuilder.length()).toString();
    }

    return stringBuilder.toString();

  }

  public ArrayList<Term> getTerms() {
    return terms;
  }

  public void setTerms(ArrayList<Term> terms) {
    this.terms = terms;
  }

  public void addTerm(Term term) {
    terms.add(term);
  }


}
