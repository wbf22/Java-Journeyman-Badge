package com.nonagon.javajourneymanbadge.clarice;

import com.nonagon.javajourneymanbadge.fileBattles.FileBattleManager;
import com.nonagon.javajourneymanbadge.genericFork.ForkManager;
import com.nonagon.javajourneymanbadge.misc.StubbornBilly;
import com.nonagon.javajourneymanbadge.parsing.ClauseHelper;
import com.nonagon.javajourneymanbadge.parsing.KeywordManager;
import com.nonagon.javajourneymanbadge.printColors.ColorPrinter;
import com.nonagon.javajourneymanbadge.printColors.RandomColor;
import com.nonagon.javajourneymanbadge.printColors.Square;
import com.nonagon.javajourneymanbadge.printColors.Wheel;
import com.nonagon.javajourneymanbadge.rap.Rap;
import com.nonagon.javajourneymanbadge.threads.Threads;
import com.nonagon.javajourneymanbadge.threads.beepBop.PlayWithRobots;
import com.nonagon.javajourneymanbadge.trys.Trys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;


import static com.nonagon.javajourneymanbadge.JavaJourneymanBadgeApplication.applicationOn;

@Controller
public class ClariceBrain{

    public static final String CLARICE_SMILE = "C:\\FAMILY_SEARCH_PROJECTS\\Java-Journeyman-Badge\\src\\main\\resources\\ClariceSmile.txt";
    public static final String CLARICE_BLANK = "C:\\FAMILY_SEARCH_PROJECTS\\Java-Journeyman-Badge\\src\\main\\resources\\ClariceBlank.txt";
    public static final String CLARICE_LAUGH = "C:\\FAMILY_SEARCH_PROJECTS\\Java-Journeyman-Badge\\src\\main\\resources\\ClariceLaugh.txt";
    public static final String CLARICE_CRY = "C:\\FAMILY_SEARCH_PROJECTS\\Java-Journeyman-Badge\\src\\main\\resources\\ClariceCry.txt";
    public static final String INFO = "C:\\FAMILY_SEARCH_PROJECTS\\Java-Journeyman-Badge\\src\\main\\resources\\ClariceFunctions.txt";
    public static final String CLARICE_CRINGE = "C:\\FAMILY_SEARCH_PROJECTS\\Java-Journeyman-Badge\\src\\main\\resources\\ClariceCringe.txt";
    public static final String CLARICE_SING = "C:\\FAMILY_SEARCH_PROJECTS\\Java-Journeyman-Badge\\src\\main\\resources\\ClariceSing.txt";
    public static final String BILLY_NO = "C:\\FAMILY_SEARCH_PROJECTS\\Java-Journeyman-Badge\\src\\main\\resources\\BillyNo.txt";
    public static final String CLARICE_COOL = "C:\\FAMILY_SEARCH_PROJECTS\\Java-Journeyman-Badge\\src\\main\\resources\\ClariceCool.txt";
    public static final String ROBOT = "C:\\FAMILY_SEARCH_PROJECTS\\Java-Journeyman-Badge\\src\\main\\resources\\Robot.txt";
    public static final String FIREWORKS = "C:\\FAMILY_SEARCH_PROJECTS\\Java-Journeyman-Badge\\src\\main\\resources\\Fireworks.txt";

//    public static final String PROCTECT_KEYWORDS_FILE = "C:\\FAMILY_SEARCH_PROJECTS\\Java-Journeyman-Badge\\src\\main\\resources\\Clauses\\PROTECTED_KEYWORDS.txt";
//    public static final String FAILURE_CLAUSES_FILE = "C:\\FAMILY_SEARCH_PROJECTS\\Java-Journeyman-Badge\\src\\main\\resources\\Clauses\\FAILURE_CLAUSES.txt";
//    public static final String SUCCESS_CLAUSES_FILE = "C:\\FAMILY_SEARCH_PROJECTS\\Java-Journeyman-Badge\\src\\main\\resources\\Clauses\\SUCCESS_CLAUSES.txt";
//    public static final String INFO_CLAUSES_FILE = "C:\\FAMILY_SEARCH_PROJECTS\\Java-Journeyman-Badge\\src\\main\\resources\\Clauses\\INFO_CLAUSES.txt";
//    public static final String THREAD_DANCE_CLAUSES_FILE = "C:\\FAMILY_SEARCH_PROJECTS\\Java-Journeyman-Badge\\src\\main\\resources\\Clauses\\THREAD_DANCE_CLAUSES.txt";
//    public static final String THREAD_PICTURE_CLAUSES_FILE = "C:\\FAMILY_SEARCH_PROJECTS\\Java-Journeyman-Badge\\src\\main\\resources\\Clauses\\THREAD_PICTURE_CLAUSES.txt";
//    public static final String BILLY_NO_CLAUSES_FILE = "C:\\FAMILY_SEARCH_PROJECTS\\Java-Journeyman-Badge\\src\\main\\resources\\Clauses\\BILLY_NO_CLAUSES.txt";
//    public static final String COLORS_CLAUSES_FILE = "C:\\FAMILY_SEARCH_PROJECTS\\Java-Journeyman-Badge\\src\\main\\resources\\Clauses\\COLORS_CLAUSES.txt";
//    public static final String RAP_CLAUSES_FILE = "C:\\FAMILY_SEARCH_PROJECTS\\Java-Journeyman-Badge\\src\\main\\resources\\Clauses\\RAP_CLAUSES.txt";
//    public static final String BEEP_BOP_CLAUSES_FILE = "C:\\FAMILY_SEARCH_PROJECTS\\Java-Journeyman-Badge\\src\\main\\resources\\Clauses\\BEEP_BOP_CLAUSES.txt";
//    public static final String FORK_CLAUSES_FILE = "C:\\FAMILY_SEARCH_PROJECTS\\Java-Journeyman-Badge\\src\\main\\resources\\Clauses\\FORK_CLAUSES.txt";
    public static final String CLAUSE_FILES_INDEX = "C:\\FAMILY_SEARCH_PROJECTS\\Java-Journeyman-Badge\\src\\main\\resources\\CLAUSE_FILES_INDEX.txt";
    public static final HashMap<String, String> ClauseFilesIndex = new HashMap<>();


    @Autowired
    private Threads threads;

    private KeywordManager keywordManager;



    public void run() {
//        try{
//            ClauseHelper.addToClauseFile(PROCTECT_KEYWORDS_FILE, Arrays.asList(new String[]{"add", "remove", "print"}));
//            ClauseHelper.addToClauseFile(FAILURE_CLAUSES_FILE, Arrays.asList(new String[]{"don't", "no", "wrong", "bad", "isn't"}));
//            ClauseHelper.addToClauseFile(SUCCESS_CLAUSES_FILE, Arrays.asList(new String[]{"nice", "good", "great", "awesome", "yes"}));
//            ClauseHelper.addToClauseFile(INFO_CLAUSES_FILE, Arrays.asList(new String[]{"info", "man", "help"}));
//            ClauseHelper.addToClauseFile(THREAD_DANCE_CLAUSES_FILE, Arrays.asList(new String[]{"dance"}));
//            ClauseHelper.addToClauseFile(THREAD_PICTURE_CLAUSES_FILE, Arrays.asList(new String[]{"picture", "image", "pic"}));
//            if(true) return;
//        }catch (Exception e){}

        Thread clariceSingFunction = new Thread(new ClariceSing());
        clariceSingFunction.start();

        printFile(CLARICE_SMILE);
        System.out.println();
        System.out.println("      Hello!");
        System.out.println();
        System.out.println();


        try {
            File file = new File(CLAUSE_FILES_INDEX);
            Scanner scanner = null;
            scanner = new Scanner(file);
            while(scanner.hasNextLine()){
                String clauseFile = scanner.nextLine();
                ClauseFilesIndex.put(clauseFile, "src/main/resources/Clauses/" + clauseFile + ".txt");
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        while(applicationOn){
            Scanner s = new Scanner(System.in);
            if(s.hasNextLine()){
                try { parseInput(s.nextLine()); } catch (Exception e) {
                    e.printStackTrace();
                    printFile(CLARICE_CRINGE);
                }


            }

        }
    }

    private ArrayList<ClariceEmotes> inputTypes = new ArrayList<>();
    private ArrayList<String> functionsToDo = new ArrayList<>();
    private void parseInput(String input) throws IOException, ClassNotFoundException {
//        Set<String> PROCTECT_KEYWORDS = new HashSet<>();
//        Set<String> SUCCESS_CLAUSES = new HashSet<>();
//        Set<String> FAILURE_CLAUSES = new HashSet<>();
//        Set<String> INFO_CLAUSES = new HashSet<>();
//        Set<String> THREAD_PICTURE_CLAUSES = new HashSet<>();
//        Set<String> THREAD_DANCE_CLAUSES = new HashSet<>();
//        Set<String> BILLY_NO_CLAUSES = new HashSet<>();
//        Set<String> COLORS_CLAUSES = new HashSet<>();
//        Set<String> RAP_CLAUSES = new HashSet<>();
//        Set<String> BEEP_BOP_CLAUSES = new HashSet<>();
//        Set<String> FORK_CLAUSES = new HashSet<>();
//        try{
//            PROCTECT_KEYWORDS = ClauseHelper.getClauses(PROCTECT_KEYWORDS_FILE);
//            SUCCESS_CLAUSES = ClauseHelper.getClauses(SUCCESS_CLAUSES_FILE);
//            FAILURE_CLAUSES = ClauseHelper.getClauses(FAILURE_CLAUSES_FILE);
//            INFO_CLAUSES = ClauseHelper.getClauses(INFO_CLAUSES_FILE);
//            THREAD_PICTURE_CLAUSES = ClauseHelper.getClauses(THREAD_PICTURE_CLAUSES_FILE);
//            THREAD_DANCE_CLAUSES = ClauseHelper.getClauses(THREAD_DANCE_CLAUSES_FILE);
//            BILLY_NO_CLAUSES = ClauseHelper.getClauses(BILLY_NO_CLAUSES_FILE);
//            COLORS_CLAUSES = ClauseHelper.getClauses(COLORS_CLAUSES_FILE);
//            RAP_CLAUSES = ClauseHelper.getClauses(RAP_CLAUSES_FILE);
//            BEEP_BOP_CLAUSES = ClauseHelper.getClauses(BEEP_BOP_CLAUSES_FILE);
//            FORK_CLAUSES = ClauseHelper.getClauses(FORK_CLAUSES_FILE);
//        }catch (Exception e){}




//        Set<String> protectedKeywords = checkForClauseWords(input, PROCTECT_KEYWORDS);
//        if(protectedKeywords.size() > 0){ new KeywordManager().doProtectedKeywordFunctions(input, protectedKeywords); return; }
//
//
//        Set<String> successMatches = checkForClauseWords(input, SUCCESS_CLAUSES);
//        for (String s : successMatches) inputTypes.add(ClariceEmotes.SUCCESS);
//
//        Set<String> failureMatches = checkForClauseWords(input, FAILURE_CLAUSES);
//        for (String s : failureMatches) inputTypes.add(ClariceEmotes.FAILURE);
//
//        Set<String> infoMatches = checkForClauseWords(input, INFO_CLAUSES);
//        for (String s : infoMatches) inputTypes.add(ClariceEmotes.SUCCESS);
//        if(infoMatches.size() > 0){ functionsToDo.add(ClariceFunctions.INFO); }
//
//        Set<String> threadRaceMatches = checkForClauseWords(input, THREAD_PICTURE_CLAUSES);
//        if(threadRaceMatches.size() > 0){
//            for (String s : threadRaceMatches) inputTypes.add(ClariceEmotes.SUCCESS);
//            functionsToDo.add(ClariceFunctions.THREAD_PICTURE);
//        }
//
//        Set<String> threadDanceMatches = checkForClauseWords(input, THREAD_DANCE_CLAUSES);
//        if(threadDanceMatches.size() > 0){
//            for (String s : threadDanceMatches) inputTypes.add(ClariceEmotes.SUCCESS);
//            functionsToDo.add(ClariceFunctions.THREAD_DANCE);
//        }
//
//        Set<String> billyMatches = checkForClauseWords(input, BILLY_NO_CLAUSES);
//        for (String s : billyMatches) inputTypes.add(ClariceEmotes.SUCCESS);
//        if(billyMatches.size() > 0){ functionsToDo.add(ClariceFunctions.BILLY_NO); }
//
//        Set<String> colorMatches = checkForClauseWords(input, COLORS_CLAUSES);
//        for (String s : colorMatches) inputTypes.add(ClariceEmotes.SUCCESS);
//        if(colorMatches.size() > 0){ functionsToDo.add(ClariceFunctions.COLORS); }
//
//        Set<String> rapMatches = checkForClauseWords(input, RAP_CLAUSES);
//        for (String s : rapMatches) inputTypes.add(ClariceEmotes.SUCCESS);
//        if(rapMatches.size() > 0){ functionsToDo.add(ClariceFunctions.RAP); }
//
//        Set<String> beepBopMatches = checkForClauseWords(input, BEEP_BOP_CLAUSES);
//        for (String s : beepBopMatches) inputTypes.add(ClariceEmotes.SUCCESS);
//        if(beepBopMatches.size() > 0){ functionsToDo.add(ClariceFunctions.BEEP_BOP); }
//
//        Set<String> forkMatches = checkForClauseWords(input, FORK_CLAUSES);
//        for (String s : forkMatches) inputTypes.add(ClariceEmotes.SUCCESS);
//        if(forkMatches.size() > 0){ functionsToDo.add(ClariceFunctions.FORK); }


        Set<String> protectedKeywords = checkForClauseWords(input, "PROTECTED_KEYWORDS");
        if(protectedKeywords.size() > 0){ keywordManager.doProtectedKeywordFunctions(input, protectedKeywords); return; }

        File file = new File(CLAUSE_FILES_INDEX);
        Scanner scanner = new Scanner(file);
        while(scanner.hasNextLine()){
            String clauseFile = scanner.nextLine();
            Set<String> matches = checkForClauseWords(input, clauseFile);
            if (matches.size() > 0){
                inputTypes.add(ClariceEmotes.SUCCESS);
                functionsToDo.add(clauseFile);
            }
        }

        determineEmoteAndDisplay();
        doFunctions(functionsToDo);
        inputTypes.clear();
        functionsToDo.clear();
    }

    private void doFunctions(ArrayList<String> functionsToDo) throws IOException {

        for (String fun : functionsToDo){
            try {
                switch (fun){
                    case "INFO_CLAUSES":
                        printFile(INFO);
                        break;
                    case "THREAD_DANCE_CLAUSES":
                        try {
                            threads.threadDance();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "THREAD_PICTURE_CLAUSES":
                        try {
                            threads.threadPicture();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "BILLY_NO_CLAUSES":
                        StubbornBilly billy = new StubbornBilly();
                        billy.askToComeOut();
                        break;
                    case "COLORS_CLAUSES":
                        ArrayList<ColorPrinter> colorPrinters = new ArrayList<>();
                        colorPrinters.add(new Wheel());
                        colorPrinters.add(new Square());
                        colorPrinters.add(new RandomColor());
                        colorPrinters.get(new Random().nextInt(colorPrinters.size())).printColor();
                        break;
                    case "RAP_CLAUSES":
                        Rap rap = new Rap();
                        try {
                            rap.rap();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case "BEEP_BOP_CLAUSES":
                        PlayWithRobots playWithRobots = new PlayWithRobots();
                        playWithRobots.playWithRobots();
                        break;

                    case "FORK_CLAUSES":
                        ForkManager forkManager = new ForkManager();
                        forkManager.setUpForks();
                        break;

                    case "CLOSE_CLAUSES":
                        applicationOn = false;
                        break;

                    case "FOLDER_BATTLE_CLAUSES":
                        FileBattleManager fileBattleManager = new FileBattleManager();
                        fileBattleManager.battle();

                    case "EXCEPTION_CLAUSES":
                        Trys trys = new Trys();
                        trys.doAllExceptionStuff();

                }
            }catch (Exception e){
                printFile(CLARICE_CRINGE);
                e.printStackTrace();
            }
        }


    }

    private Set<String> checkForClauseWords(String input, String filename) throws IOException, ClassNotFoundException {
        Set<String> keys  = ClauseHelper.getClauses(ClauseFilesIndex.get(filename));
        StringTokenizer tokenizer = new StringTokenizer(input);
        ArrayList<String> tokens = new ArrayList<>();
        while(tokenizer.hasMoreTokens()){
            tokens.add(tokenizer.nextToken());
        }

        Set<String> matches = new HashSet<>();
        for (String s : tokens){
            if(keys.contains(s.toLowerCase())) matches.add(s);
            if(keys.contains(s.toLowerCase().substring(0, s.length() - 1)) && !Character.isLetter(s.charAt(s.length() - 1))) matches.add(s);
        }


        return matches;
    }

    private void determineEmoteAndDisplay(){
        int[] tallies = new int[ClariceEmotes.NULL.ordinal()];
        for (ClariceEmotes e : inputTypes){
            tallies[e.ordinal()] += 1;
        }

        ClariceEmotes overall = ClariceEmotes.UNKOWN;
        if(tallies[ClariceEmotes.FAILURE.ordinal()] > tallies[overall.ordinal()]) overall = ClariceEmotes.FAILURE;
        if(tallies[ClariceEmotes.SUCCESS.ordinal()] > tallies[overall.ordinal()]) overall = ClariceEmotes.SUCCESS;
        if(tallies[ClariceEmotes.FUN.ordinal()] > tallies[overall.ordinal()]) overall = ClariceEmotes.FUN;

        switch (overall){
            case FAILURE:
                printFile(CLARICE_CRY);
                break;
            case SUCCESS:
                printFile(CLARICE_SMILE);
                printFile(FIREWORKS);
                break;
            case FUN:
                printFile(CLARICE_LAUGH);
                break;
            case UNKOWN:
                printFile(CLARICE_BLANK);
                break;

        }


    }

    public static void printFile(String filePath){
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
            while(bufferedReader.ready()){
                System.out.println(bufferedReader.readLine());
            }

            bufferedReader.close();
        } catch (IOException e) { e.printStackTrace(); }
    }


    private class ClariceSing implements Runnable{


        @Override
        public void run() {
            Random random = new Random(System.currentTimeMillis());
            while(applicationOn){

                try {
                    TimeUnit.MILLISECONDS.sleep(2000);
                    boolean sing = true;
                    for(int i = 0; i < 6; i++){
                        if(!random.nextBoolean()){
                            sing = false;
                            break;
                        }
                    }

                    if(sing) {
                        printFile(CLARICE_SING);
                    }





                } catch (Exception e) { }

            }
        }
    }




}
