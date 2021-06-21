package com.nonagon.javajourneymanbadge.fileBattles;

import java.io.IOException;
import java.nio.file.*;
import java.util.concurrent.TimeUnit;

import com.nonagon.javajourneymanbadge.printColors.Colors;

public class AttackerThread implements Runnable{

  private FileBattleManager fileBattleManager;
  private TeamAssigner roleAssigner;
  private Path pathBattlesFolder;

  public AttackerThread(FileBattleManager fileBattleManager) {
    this.fileBattleManager = fileBattleManager;
  }

  @Override
  public void run() {
    int team = fileBattleManager.getTeamAssigner().getAttackTeam();

    pathBattlesFolder = Paths.get("src/main/java/com/nonagon/javajourneymanbadge/fileBattles");
    while (!fileBattleManager.getBattleWon()) {
      try {
        int sleeptime = fileBattleManager.getSleepTime();
        TimeUnit.MILLISECONDS.sleep(sleeptime);
        //System.out.println(sleeptime + " Attacker " + team);

        moveFileOneFolder(team);


      }
      catch (Exception e) { e.printStackTrace(); }
    }
  }


  private void moveFileOneFolder(int team) throws IOException {
    int direction = (team == 0) ? -1 : 1;


    Crawler crawler = new Crawler("trophy.txt", pathBattlesFolder);
    Files.walkFileTree(pathBattlesFolder, crawler);
    if (crawler.getFoundFileLocation().contains("7")){
      Files.move(Paths.get("src/main/java/com/nonagon/javajourneymanbadge/fileBattles/zone7/trophy.txt"), Paths.get("src/main/java/com/nonagon/javajourneymanbadge/fileBattles/zone" + (7 + direction) + "/trophy.txt"));
    }
    if (crawler.getFoundFileLocation().contains("6")){
      Files.move(Paths.get("src/main/java/com/nonagon/javajourneymanbadge/fileBattles/zone6/trophy.txt"), Paths.get("src/main/java/com/nonagon/javajourneymanbadge/fileBattles/zone" + (6 + direction) + "/trophy.txt"));
    }
    if (crawler.getFoundFileLocation().contains("5")){
      Files.move(Paths.get("src/main/java/com/nonagon/javajourneymanbadge/fileBattles/zone5/trophy.txt"), Paths.get("src/main/java/com/nonagon/javajourneymanbadge/fileBattles/zone" + (5 + direction) + "/trophy.txt"));
    }
    if (crawler.getFoundFileLocation().contains("4")){
      Files.move(Paths.get("src/main/java/com/nonagon/javajourneymanbadge/fileBattles/zone4/trophy.txt"), Paths.get("src/main/java/com/nonagon/javajourneymanbadge/fileBattles/zone" + (4 + direction) + "/trophy.txt"));
    }
    if (crawler.getFoundFileLocation().contains("3")){
      Files.move(Paths.get("src/main/java/com/nonagon/javajourneymanbadge/fileBattles/zone3/trophy.txt"), Paths.get("src/main/java/com/nonagon/javajourneymanbadge/fileBattles/zone" + (3 + direction) + "/trophy.txt"));
    }
    if (crawler.getFoundFileLocation().contains("2")){
      Files.move(Paths.get("src/main/java/com/nonagon/javajourneymanbadge/fileBattles/zone2/trophy.txt"), Paths.get("src/main/java/com/nonagon/javajourneymanbadge/fileBattles/zone" + (2 + direction) + "/trophy.txt"));
    }



//    if (team == 0) {System.out.print(Colors.ANSI_RED); System.out.println("Attacker from Team 1 moved the Trophy One Folder!! :)");}
//    if (team == 1) {System.out.print(Colors.ANSI_BLUE); System.out.println("Attacker from Team 2 moved the Trophy One Folder!! :)");}
//    System.out.print(Colors.ANSI_RESET);


  }
}
