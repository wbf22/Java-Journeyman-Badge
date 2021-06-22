package com.nonagon.javajourneymanbadge.graphing;

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


  private int step = 5; //spacing of grid
  private int width = 20; //number of grid squares x
  private int height = 20; //number of grid squares y
  private String[][] currentGraphState = new String[height + 3][width + 4];
  //auxiliary params
  private String leftPadding = "         ";
  private String emptyCell = "    ";

  public void graph(Map<Float, Float> points, ArrayList<String> functions){
    initGraph();

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


    for (int i = 2; i < height + 1; i++){
      currentGraphState[i] = toArrayFromList(makeRow(whiteSpace(4)));
      currentGraphState[i][1] = "|";
    }

    currentGraphState[1] = toArrayFromList(makeRow("___ "));
    currentGraphState[1][1] = "|";

    currentGraphState[0] = toArrayFromList(makeRow(whiteSpace(4)));
    addNumberLabel();

  }
  private void plotPoint(float x, float y){
    float cellSize = ((float) step) / 2.0f;

  }

  //helper functions
  private void addNumberLabel(){
    //vertical axis
    int vLabel = step;
    for (int i = 2; i < height + 1; i+=2){
      currentGraphState[i][0] = injectNummberInPadding(vLabel);
      vLabel += step;
    }

    //horizontal axis
    int hLabel = step;
    currentGraphState[0][0] = leftPadding;
    currentGraphState[0][1] = " ";
    for (int i = 3; i < width + 2; i+=2){
      currentGraphState[0][i - 1] = whiteSpace(4);
      currentGraphState[0][i] = injectNumberInCell(hLabel);
      hLabel += step;
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
  private String injectNummberInPadding(int num){
    String numToString = Integer.toString(num);
    int padding = leftPadding.length() - numToString.length();
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(whiteSpace(padding));
    stringBuilder.append(numToString);
    return stringBuilder.toString();

  }
  private String injectNumberInCell(int num){
    String numToString = Integer.toString(num);
    if (numToString.length() > emptyCell.length() - 1){
      return " " + numToString.charAt(0) + "." + numToString.charAt(1) + "k";
    }
    int padding = emptyCell.length() - numToString.length() - 1;
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(" ");
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
    grapher.graph(null, null);
  }


}
