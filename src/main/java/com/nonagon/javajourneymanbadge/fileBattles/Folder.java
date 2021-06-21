package com.nonagon.javajourneymanbadge.fileBattles;

import java.util.ArrayList;

import com.nonagon.javajourneymanbadge.printColors.Colors;

public class Folder {

  String fileName;
  ArrayList<String> files = new ArrayList<>();
  ArrayList<Folder> folders = new ArrayList<>();

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public ArrayList<String> getFiles() {
    return files;
  }

  public void addFile(String file) {
    this.files.add(file);
  }

  public ArrayList<Folder> getFolders() {
    return folders;
  }

  public void addFolder(Folder folder) {
    this.folders.add(folder);
  }

  public void printFolder(){
    printFol(0, 0);
  }

  private void printFol(int offset, int addOn){
    offset(offset);
    String printDefenderEmoji = (addOn == 1 || addOn == 6) ? " :|" : "";
    System.out.println(fileName + printDefenderEmoji);
    for (int i = 0; i < folders.size(); i++){
      if ( i < 2 ) System.out.print(Colors.ANSI_BLUE);
      if ( i > 5 ) System.out.print(Colors.ANSI_RED);
      folders.get(i).printFol(offset + 1, i);
      System.out.print(Colors.ANSI_RESET);
    }
    for (String file : files){
      offset(offset + 1);
      System.out.print(Colors.ANSI_YELLOW);
      System.out.print(file);
      System.out.print(Colors.ANSI_BLUE);
      System.out.print(" :)");
      System.out.print(Colors.ANSI_RED);
      System.out.print(" ;)");
      System.out.println();
    }

  }

  private void offset(int offset){
    int i = 0;
    while (i < offset - 1) { System.out.print("|        "); i++;}
    if (i < offset) System.out.print("|________");
  }

}
