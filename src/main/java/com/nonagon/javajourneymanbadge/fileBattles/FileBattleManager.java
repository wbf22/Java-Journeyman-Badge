package com.nonagon.javajourneymanbadge.fileBattles;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.Random;


public class FileBattleManager {

  private Boolean battleWon = false;
  private TeamAssigner teamAssigner;



  public void battle() throws IOException, InterruptedException {
    teamAssigner = new TeamAssigner();
    System.out.println("      File Battle!");
    printFileStructure();

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
    while(!battleWon) {

      watchKey = watchService.poll();
      if (watchKey != null) {
        if (watchKey.pollEvents().size() > 0) {
          printFileStructure();
        }
        watchKey.reset();
      }
      File isTeam1 = new File("src/main/java/com/nonagon/javajourneymanbadge/fileBattles/zone1/trophy.txt");
      File isTeam0 = new File("src/main/java/com/nonagon/javajourneymanbadge/fileBattles/zone8/trophy.txt");
      if (isTeam1.exists() || isTeam0.exists()){ battleWon = true; }

    }

    printFileStructure();
    try{
      Files.move(Paths.get("src/main/java/com/nonagon/javajourneymanbadge/fileBattles/zone1/trophy.txt"), Paths.get("src/main/java/com/nonagon/javajourneymanbadge/fileBattles/zone5/trophy.txt"));
    }catch (Exception e) { };
    try{
      Files.move(Paths.get("src/main/java/com/nonagon/javajourneymanbadge/fileBattles/zone8/trophy.txt"), Paths.get("src/main/java/com/nonagon/javajourneymanbadge/fileBattles/zone4/trophy.txt"));
    }catch (Exception e) {};


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

  public static int getSleepTime(){
    Random random = new Random(System.nanoTime());
    return random.nextInt(4000) + 1000;
  }

  public Boolean getBattleWon() {
    return battleWon;
  }

  public TeamAssigner getTeamAssigner() {
    return teamAssigner;
  }
}
