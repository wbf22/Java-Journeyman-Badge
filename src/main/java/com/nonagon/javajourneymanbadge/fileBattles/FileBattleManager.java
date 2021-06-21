package com.nonagon.javajourneymanbadge.fileBattles;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import com.nonagon.javajourneymanbadge.printColors.Colors;


public class FileBattleManager {

  private Boolean battleWon = false;
  private TeamAssigner teamAssigner;



  public void battle() throws IOException, InterruptedException {
    teamAssigner = new TeamAssigner();
    System.out.println("      File Battle!");
    printFileStructure();
    System.out.println("Start");

    Thread defender1 = new Thread(new DefenderThread(this));
    defender1.start();
    Thread attacker1 = new Thread(new AttackerThread(this));
    attacker1.start();

    Thread defender2 = new Thread(new DefenderThread(this));
    defender2.start();
    Thread attacker2 = new Thread(new AttackerThread(this));
    attacker2.start();

    Path pathBattlesFolder = Paths.get("src/main/java/com/nonagon/javajourneymanbadge/fileBattles");
    WatchService watchService = pathBattlesFolder.getFileSystem().newWatchService();
    pathBattlesFolder.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);
    WatchKey watchKey = null;
    int winner = 0;
    long lastPrint = System.currentTimeMillis();
    int lastLocationOfTrophy = findNewFolderLocationOfTrophy();
    while(!battleWon) {

      int locationOfTrophy = findNewFolderLocationOfTrophy();
      if (locationOfTrophy > lastLocationOfTrophy) { System.out.print(Colors.ANSI_RED); System.out.println("Team 1 moves the trophy to zone " + (locationOfTrophy + 1)); System.out.print(Colors.ANSI_RESET);}
      if (locationOfTrophy < lastLocationOfTrophy) { System.out.print(Colors.ANSI_BLUE); System.out.println("Team 2 moves the trophy to zone " + (locationOfTrophy + 1)); System.out.print(Colors.ANSI_RESET);}
      lastLocationOfTrophy = locationOfTrophy;

      watchKey = watchService.poll();
      if (watchKey != null) {
        if (watchKey.pollEvents().size() > 0) {
          if (lastPrint + 100 < System.currentTimeMillis()){
            lastPrint = System.currentTimeMillis();
            printFileStructure();
          }
        }
        watchKey.reset();
      }
      File isTeam1 = new File("src/main/java/com/nonagon/javajourneymanbadge/fileBattles/zone1/trophy.txt");
      File isTeam0 = new File("src/main/java/com/nonagon/javajourneymanbadge/fileBattles/zone8/trophy.txt");
      if (isTeam1.exists()){
        battleWon = true;
        winner = 1;
      }else if (isTeam0.exists()){
        battleWon = true;
        winner = 0;
      }

    }
    printFileStructure();
    System.out.println("Team " + (winner + 1) + " is the winner!");
    defender1.join();
    defender2.join();
    attacker1.join();
    attacker2.join();


    moveTrophyBackToMiddle();


  }

  private void moveTrophyBackToMiddle() throws IOException {
    Path trophyFileLocation =Paths.get("src/main/java/com/nonagon/javajourneymanbadge/fileBattles/zone1/trophy.txt");
    Path middleLocation;

    Folder battleFolder = walkDirectory(Paths.get("src/main/java/com/nonagon/javajourneymanbadge/fileBattles"));
    for ( Folder folder : battleFolder.getFolders()){
      if (folder.getFiles().size() > 0){
        trophyFileLocation = Paths.get( "src/main/java/com/nonagon/javajourneymanbadge/fileBattles/" + folder.getFileName() + "/" + folder.getFiles().get(0));
      }
    }


    Random random = new Random(System.nanoTime());
    if (random.nextBoolean()) { middleLocation = Paths.get("src/main/java/com/nonagon/javajourneymanbadge/fileBattles/zone4/trophy.txt"); }
    else { middleLocation = Paths.get("src/main/java/com/nonagon/javajourneymanbadge/fileBattles/zone5/trophy.txt"); }

    Files.move(trophyFileLocation, middleLocation);

  }

  private void printFileStructure() throws IOException {

    Path path = Paths.get("src/main/java/com/nonagon/javajourneymanbadge/fileBattles");

    Folder battleDirectory = walkDirectory(path);

    battleDirectory.printFolder();

  }

  private Folder walkDirectory(Path path) throws IOException {
    Folder currentFolder = new Folder();
    currentFolder.setFileName(path.getFileName().toString());
    DirectoryStream<Path> directory = Files.newDirectoryStream(path);
    for (Path p : directory){
      if (Files.isDirectory(p)){
        currentFolder.addFolder(walkDirectory(p));
      }else {
        if (!p.toString().endsWith(".java")) currentFolder.addFile(p.getFileName().toString());
      }

    }

    return currentFolder;
  }

  private int findNewFolderLocationOfTrophy() throws IOException {
    Folder battleFolder = walkDirectory(Paths.get("src/main/java/com/nonagon/javajourneymanbadge/fileBattles"));

    ArrayList<Folder> folders = battleFolder.getFolders();
    for (Folder folder : folders){
      if (folder.getFiles().size() > 0){
        return folders.indexOf(folder);
      }
    }

    return 4;
  }

  public static int getSleepTime(){
    Random random = new Random(System.nanoTime());
    return random.nextInt(12000) + 4000;
  }

  public Boolean getBattleWon() {
    return battleWon;
  }

  public TeamAssigner getTeamAssigner() {
    return teamAssigner;
  }
}
