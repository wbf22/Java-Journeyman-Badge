package com.nonagon.javajourneymanbadge.graphing;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.*;

public class Grapher {

//height
//  |
// y|
//  |
//  |
//  |_____________________x
//            width

//    15|
//      |
//    10|        *
//      |
//     5|    *
//      |__ ___ ___ ___ ___ ___
//           5       10      15


  private BigDecimal spacing = BigDecimal.valueOf(.2);
  private int width = 80; //number of grid squares x
  private int height = 80; //number of grid squares y
  private String[][] currentGraphState = new String[height + 3][width + 4];
  //auxiliary params
  private BigDecimal step = spacing.multiply(BigDecimal.valueOf(2)); //spacing of grid
  private String leftPadding = "         ";
  private String emptyCell = "    ";
  private MathContext mContext = new MathContext(step.precision());

  public void graph(Map<BigDecimal, BigDecimal> points, ArrayList<String> functions){
    initGraph();

    for (Map.Entry<BigDecimal, BigDecimal> entry : points.entrySet()) {
      plotPoint(entry.getKey(), entry.getValue());
    }

    printGraph();

  }
  private void printGraph(){
    List<List<String>> graphed = new ArrayList<>();
    List<String[]> rows = Arrays.asList(currentGraphState);
    Collections.reverse(rows);
    for (String[] row : rows){
      graphed.add(Arrays.asList(row));
    }

    for (List<String> row : graphed){
      for (String cell : row){
        if(cell != null){ System.out.print(cell); }
      }
      System.out.println();
    }


  }
  private void initGraph(){


    for (int i = 2; i < height + 2; i++){
      currentGraphState[i] = toArrayFromList(makeRow(whiteSpace(4)));
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(currentGraphState[i][1]);
      stringBuilder.setCharAt(0, '|');
      currentGraphState[i][1] = stringBuilder.toString();
    }

    currentGraphState[1] = toArrayFromList(makeRow("___ "));
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(currentGraphState[1][1]);
    stringBuilder.setCharAt(0, '|');
    currentGraphState[1][1] = stringBuilder.toString();

    currentGraphState[0] = toArrayFromList(makeRow(whiteSpace(4)));
    addNumberLabel();

  }
  private void plotPoint(BigDecimal x, BigDecimal y){
    int xc = getCellForPoint(x);
    int yc = getCellForPoint(y);
    if (xc + 1 < width && yc + 2 < height){
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(currentGraphState[yc+2][xc+1]);
      stringBuilder.setCharAt(1, '*');
      currentGraphState[yc+1][xc+1] = stringBuilder.toString();
    }
  }

  //helper functions
  private int getCellForPoint(BigDecimal p){
    return (p.divide(spacing)).round(new MathContext(0)).intValue();
  }
  private void addNumberLabel(){
    //vertical axis
    BigDecimal vLabel = BigDecimal.valueOf(0.0);
    for (int i = 1; i < height + 2; i+=2){
      currentGraphState[i][0] = injectNumberInPadding(vLabel);
       vLabel = vLabel.add(step);
    }

    //horizontal axis
    BigDecimal hLabel = step;
    currentGraphState[0][0] = leftPadding;
    currentGraphState[0][1] = injectNumberInCell(BigDecimal.valueOf(0.0));
    for (int i = 3; i < width + 2; i+=2){
      currentGraphState[0][i - 1] = whiteSpace(4);
      currentGraphState[0][i] = injectNumberInCell(hLabel);
      hLabel = hLabel.add(step);
    }


  }
  private ArrayList<String> makeRow(String grid){
    ArrayList<String> row = new ArrayList<>();
    row.add(leftPadding);
    for (int i = 0; i < width + 1; i++){
      row.add(grid);
    }
    return row;
  }
  private String whiteSpace(int spaces){
    StringBuilder stringBuilder = new StringBuilder();
    for (int i = 0; i < spaces; i++){
      stringBuilder.append(" ");
    }
    return stringBuilder.toString();
  }
  private String injectNumberInPadding(BigDecimal num){
    String numToString = num.toString();
    int padding = leftPadding.length() - numToString.length();
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(whiteSpace(padding));
    stringBuilder.append(numToString);
    return stringBuilder.toString();

  }
  private String injectNumberInCell(BigDecimal num){
    String numToString = num.toString();
    if (numToString.length() > emptyCell.length()){
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(numToString.charAt(0));
      stringBuilder.append(numToString.charAt(1));
      stringBuilder.append(numToString.charAt(2));
      return stringBuilder.toString() + "-";
    }
    int padding = emptyCell.length() - numToString.length();
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(numToString);
    stringBuilder.append(whiteSpace(padding));
    return stringBuilder.toString();
  }
  private String[] toArrayFromList(ArrayList<String> arrayList){
    String[] array = new String[arrayList.size()];
    arrayList.toArray(array);
    return array;
  }


  public static void main(String args[]){
    Grapher grapher = new Grapher();
    HashMap<BigDecimal, BigDecimal> points = new HashMap<>();
    points.put(new BigDecimal("0"), new BigDecimal("0"));
    points.put(new BigDecimal("0.6"), new BigDecimal("1.2"));
    points.put(new BigDecimal("0.3"), new BigDecimal("0.1"));
    points.put(new BigDecimal("0.3"), new BigDecimal("1.1"));
    points.put(new BigDecimal("0.7"), new BigDecimal("0.4"));


    grapher.graph(points, null);
  }


}
