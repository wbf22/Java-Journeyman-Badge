package com.nonagon.javajourneymanbadge.fileBattles;

import java.util.ArrayList;

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
    printFol(0);
  }

  private void printFol(int offset){
    offset(offset);
    System.out.println(fileName);
    for (Folder folder : folders){
      folder.printFol(offset + 1);
    }
    for (String file : files){
      offset(offset + 1);
      System.out.println(file);
    }

  }

  private void offset(int offset){
    int i = 0;
    while (i < offset - 1) { System.out.print("|        "); i++;}
    if (i < offset) System.out.print("|________");
  }

}
