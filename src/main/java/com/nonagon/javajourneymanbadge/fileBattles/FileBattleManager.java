package com.nonagon.javajourneymanbadge.fileBattles;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import com.nonagon.javajourneymanbadge.clarice.ClariceBrain;
import com.nonagon.javajourneymanbadge.printColors.Colors;


public class FileBattleManager {

  private Boolean battleWon = false;
  private TeamAssigner teamAssigner;



  public void battle() throws IOException, InterruptedException {
    printIntro();
    teamAssigner = new TeamAssigner();
    printFileStructure();
    System.out.println("GO!!!!");

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
    winner++;
    System.out.println("Team " + winner + " is the winner!");
    TimeUnit.MILLISECONDS.sleep(3000);
    if (winner == 1){ System.out.print(Colors.ANSI_RED); }
    else { System.out.print(Colors.ANSI_BLUE); }
    ClariceBrain.printFile(ClariceBrain.FIREWORKS);
    ClariceBrain.printFile(getPathFolderLocationOfTrophy().toAbsolutePath().toString());
    System.out.print(Colors.ANSI_RESET);

    defender1.join();
    defender2.join();
    attacker1.join();
    attacker2.join();


    if(winner == 1) { setReigningChampions("Team 1");}
    if(winner == 2) { setReigningChampions("Team 2");}
    setLastMatchDate();
    moveTrophyBackToMiddle();
    TimeUnit.MILLISECONDS.sleep(3000);
    ClariceBrain.printFile(ClariceBrain.CLARICE_SMILE);


  }

  private void printIntro() throws InterruptedException, IOException {
    System.out.println("---------FILE BATTLE!!!!!----------");
    System.out.println("Team 1: " + Colors.ANSI_RED + ";) :| home zone: zone8");
    System.out.print(Colors.ANSI_RESET);
    System.out.println("Team 2: " + Colors.ANSI_BLUE + ":) :| home zone: zone1");
    System.out.print(Colors.ANSI_RESET);
    System.out.println("Teams will face off in a challenge to bring the \n" +
        "trophy file back to their respective zones.\n" +
        "Defenders on each team must stay one zone away\n" +
        "from their home zone, but both attackers can\n" +
        "move to any zone. The first team to bring the trophy\n" +
        "to their home zone wins!");
    System.out.println();
    System.out.println("This rounds reigning champions: " + getReigningChampions() + "!!!!!");
    System.out.println("Last Match was on the Date: " + getLastMatchDate());
    System.out.println("Brought to you by " + getOwnerName());

    TimeUnit.MILLISECONDS.sleep(5000);
  }

  private String getOwnerName() throws IOException {
    Path trophy = getPathFolderLocationOfTrophy();
    FileOwnerAttributeView posixFileAttributeView = Files.getFileAttributeView(trophy, FileOwnerAttributeView.class);
    return posixFileAttributeView.getOwner().getName();
  }

  private Date getLastMatchDate() throws IOException {
    Path trophy = getPathFolderLocationOfTrophy();
    BasicFileAttributeView basicFileAttributeView = Files.getFileAttributeView(trophy, BasicFileAttributeView.class);
    return new Date(basicFileAttributeView.readAttributes().lastModifiedTime().toMillis());
  }

  private void setLastMatchDate() throws IOException {
    Path trophy = getPathFolderLocationOfTrophy();
    BasicFileAttributeView basicFileAttributeView = Files.getFileAttributeView(trophy, BasicFileAttributeView.class);
    BasicFileAttributes basicFileAttributes = basicFileAttributeView.readAttributes();
    FileTime fileTime = FileTime.fromMillis(System.currentTimeMillis());
    basicFileAttributeView.setTimes(fileTime, basicFileAttributes.lastAccessTime(), basicFileAttributes.creationTime());
  }

  private String getReigningChampions() throws IOException {
    try{
      Path trophy = getPathFolderLocationOfTrophy();
      UserDefinedFileAttributeView userDefinedFileAttributeView = Files.getFileAttributeView(trophy, UserDefinedFileAttributeView.class);
      ByteBuffer dst = ByteBuffer.allocate(userDefinedFileAttributeView.size("ReigningChampions"));
      userDefinedFileAttributeView.read("ReigningChampions", dst);
      dst.flip();
      return new String(dst.array(), "UTF-8");

    }catch (Exception e){
      return "null";
    }

  }

  private void setReigningChampions(String newOwners) throws IOException {
    Path trophy = getPathFolderLocationOfTrophy();
    UserDefinedFileAttributeView userDefinedFileAttributeView = Files.getFileAttributeView(trophy, UserDefinedFileAttributeView.class);
    byte[] bytes = newOwners.getBytes("UTF-8");
    ByteBuffer buf = ByteBuffer.allocate(bytes.length);
    buf.put(bytes);
    buf.flip();
    userDefinedFileAttributeView.write("ReigningChampions", buf);
  }

  private void moveTrophyBackToMiddle() throws IOException {
    Path trophyFileLocation = getPathFolderLocationOfTrophy();
    Path middleLocation;

    Random random = new Random(System.nanoTime() + System.currentTimeMillis());
    if (random.nextBoolean()) { middleLocation = Paths.get("src/main/java/com/nonagon/javajourneymanbadge/fileBattles/zone5/trophy.txt"); }
    else { middleLocation = Paths.get("src/main/java/com/nonagon/javajourneymanbadge/fileBattles/zone4/trophy.txt"); }

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

  private Path getPathFolderLocationOfTrophy() throws IOException {
    Path trophyFileLocation = Paths.get("src/main/java/com/nonagon/javajourneymanbadge/fileBattles/zone1/trophy.txt");
    Folder battleFolder = walkDirectory(Paths.get("src/main/java/com/nonagon/javajourneymanbadge/fileBattles"));
    for ( Folder folder : battleFolder.getFolders()){
      if (folder.getFiles().size() > 0){
        trophyFileLocation = Paths.get( "src/main/java/com/nonagon/javajourneymanbadge/fileBattles/" + folder.getFileName() + "/" + folder.getFiles().get(0));
      }
    }
    return trophyFileLocation;
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
    int sub = (random.nextBoolean()) ? 1000 : -1000;
    return random.nextInt(12000) + 4000 + sub;
  }

  public Boolean getBattleWon() {
    return battleWon;
  }

  public TeamAssigner getTeamAssigner() {
    return teamAssigner;
  }
}
