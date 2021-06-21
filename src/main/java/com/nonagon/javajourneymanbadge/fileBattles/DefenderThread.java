package com.nonagon.javajourneymanbadge.fileBattles;

import java.nio.file.*;
import java.util.concurrent.TimeUnit;

import com.nonagon.javajourneymanbadge.printColors.Colors;

public class DefenderThread implements Runnable{

  private FileBattleManager fileBattleManager;

  public DefenderThread(FileBattleManager fileBattleManager) {
    this.fileBattleManager = fileBattleManager;
  }

  @Override
  public void run() {
    int team = fileBattleManager.getTeamAssigner().getDefendTeam();

    try {
      Path pathBattlesFolder = Paths.get("src/main/java/com/nonagon/javajourneymanbadge/fileBattles");
      String trophyInHome = null;
      String trophyInBorder = null;
      if (team == 0) {
        trophyInHome = "src/main/java/com/nonagon/javajourneymanbadge/fileBattles/zone7/trophy.txt";
        trophyInBorder = "src\\main\\java\\com\\nonagon\\javajourneymanbadge\\fileBattles\\zone6\\trophy.txt";
      }
      else {
        trophyInHome = "src/main/java/com/nonagon/javajourneymanbadge/fileBattles/zone2/trophy.txt";
        trophyInBorder = "src\\main\\java\\com\\nonagon\\javajourneymanbadge\\fileBattles\\zone3\\trophy.txt";
      }


      while (!fileBattleManager.getBattleWon()){
        TimeUnit.MILLISECONDS.sleep(FileBattleManager.getSleepTime());

        Crawler crawler = new Crawler(
            "trophy.txt", pathBattlesFolder);
        Files.walkFileTree(pathBattlesFolder, crawler);
        if (crawler.getFoundFileLocation().equals(trophyInBorder)){
          Files.move(Paths.get(trophyInBorder), Paths.get(trophyInHome));
          if (team == 0) { System.out.print(Colors.ANSI_RED); System.out.println("Defender From Team 1 Blocks Attacker from Team 2!! ;|"); }
          System.out.print(Colors.ANSI_RESET);
          if (team == 1) { System.out.print(Colors.ANSI_BLUE); System.out.println("Defender From Team 2 Blocks Attacker from Team 1!! ;|"); }
          System.out.print(Colors.ANSI_RESET);
        }

      }
    }
    catch (Exception e) { e.printStackTrace(); }

  }

}
