package com.nonagon.javajourneymanbadge.fileBattles;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

import static java.nio.file.FileVisitResult.CONTINUE;
import static java.nio.file.FileVisitResult.TERMINATE;

public class Crawler implements FileVisitor<Path> {

  private String FILE_NAME;
  private Path START_DIR;
  private String foundFileLocation;


  public Crawler(String FILE_NAME, Path startDir) {
    this.FILE_NAME = FILE_NAME;
    this.START_DIR = startDir;
  }

  @Override
  public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
    return CONTINUE;
  }

  @Override
  public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
    String fileName = file.getFileName().toString();
    FileSystem fileSystem = FileSystems.getDefault();
    PathMatcher pathMatcher = fileSystem.getPathMatcher("glob:**/trophy.txt");
    if (pathMatcher.matches(file)){
      foundFileLocation = file.toString();
      return TERMINATE;
    }
//    if (FILE_NAME.equals(fileName)) {
//      //System.out.println("File found: " + file.toString());
//      foundFileLocation = file.toString();
//      return TERMINATE;
//    }
    return CONTINUE;
  }

  @Override
  public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
    return CONTINUE;
  }

  @Override
  public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
    boolean finishedSearch = Files.isSameFile(dir, START_DIR);
    if (finishedSearch) {
      //System.out.println("File:" + FILE_NAME + " not found");
      foundFileLocation = "";
      return TERMINATE;
    }
    return CONTINUE;
  }

  public String getFoundFileLocation() {
    return foundFileLocation;
  }
}
