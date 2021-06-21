package com.nonagon.javajourneymanbadge.genericFork;

import com.nonagon.javajourneymanbadge.clarice.ClariceBrain;
import com.nonagon.javajourneymanbadge.misc.RandomWord;
import com.nonagon.javajourneymanbadge.threads.Threads;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;


public class ForkManager {

    private Random random = new Random(System.currentTimeMillis());
    List<String> protectedStrings = Arrays.asList(new String[]{"i", "our", "the", "oh", "your", "you", "so"});


    public void setUpForks(){





        Fork<String> fork1 = new Fork<>();
        Fork<Spork> fork2 = new Fork<>();

        fork1.setPossession("Minecraft Login:\nUsername: SharpestFork12\nPassword: password");
        fork2.setPossession(new Spork());


        fork1.setName(RandomWord.getRandomWord(random));
        fork2.setName(RandomWord.getRandomWord(random));

        fork1.setOffset("");
        fork2.setOffset("                                                            ");
        fork1.introduce();
        fork2.introduce();


        ForkJoinTask forkJoinTask = new RecursiveAction() {
            private String lastStatment = null;
            private int lastTalker = 1;
            private int conversationCount = 0;
            private int numAssigner = 0;
            ArrayList<String> sayings = getConversationSayings();

            @Override
            protected void compute() {
                int forkNum = numAssigner++;
                String offset = (forkNum == 1) ? fork1.getOffset() : fork2.getOffset();


                while(conversationCount < 30 && sayings.size() > 0){
                    if (lastTalker != forkNum){
                        Collections.shuffle(sayings);
                        if (lastStatment != null) sayings.remove(lastStatment);

                        String statement = "";
                        ArrayList<String> keywords = findKeywords(lastStatment);
                        //System.out.println(offset + "***" + keywords + " " + lastStatment);
                        if (lastStatment != null && lastStatment.contains("?")) statement = findBestMatch(keywords, sayings);
                        else {
                            statement = findBestMatch(keywords, sayings);
                            if (statement.contains("?")) statement = findQuestion(sayings);
                        }

                        System.out.println(offset + statement);
                        lastStatment = statement;
                        lastTalker = forkNum;
                        try { TimeUnit.MILLISECONDS.sleep(3000); } catch (InterruptedException e) {}
                        conversationCount++;
                    }
                }

            }

        };
        forkJoinTask.fork();
        forkJoinTask.fork();

        forkJoinTask.join();


        ClariceBrain.printFile(ClariceBrain.CLARICE_SMILE);

    }


    private ArrayList<String> getConversationSayings(){
        ArrayList<String> sayings = new ArrayList<>();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("C:\\FAMILY_SEARCH_PROJECTS\\Java-Journeyman-Badge\\src\\main\\java\\com\\nonagon\\javajourneymanbadge\\genericFork\\conversation_source.txt"));
            while(bufferedReader.ready()){
                sayings.add(bufferedReader.readLine());
            }


        } catch (IOException e) { e.printStackTrace(); }
        return sayings;
    }
    private String findBestMatch(ArrayList<String> keywords, ArrayList<String> phrases){
        //System.out.println("         CALLED BESTMATCH");
        if(keywords == null){
            return phrases.get(random.nextInt(phrases.size()));
        }
        String bestMatch = phrases.get(random.nextInt(phrases.size()));
        int bestMatchNumberOfMatches = numberOfMatches(keywords, bestMatch);

        for(String phrase : phrases){
            int numberOfMatches = numberOfMatches(keywords, phrase);
            if( numberOfMatches > bestMatchNumberOfMatches ) {
                bestMatch = phrase;
                bestMatchNumberOfMatches = numberOfMatches;
            }
        }

        return bestMatch;
    }
    private String findQuestion(ArrayList<String> phrases){

        for(String phrase : phrases){
           if (phrase.contains("?")) return phrase;
        }

        return null;
    }
    private int numberOfMatches(ArrayList<String> keywords, String phrase){
        int numberOfMatches = 0;
        for(String key : keywords){
            if (!protectedStrings.contains(key)){
                if(phrase.contains(key)) numberOfMatches++;
            }
        }

        return numberOfMatches;
    }
    private ArrayList<String> findKeywords(String lastStatment) {
        if(lastStatment == null){
            return null;
        }
        String statement = lastStatment.toLowerCase();
        ArrayList<String> keywords = new ArrayList<>();

        if (statement.contains("?")) { keywords.add("."); keywords.add("!"); }


        StringTokenizer tokens = new StringTokenizer(statement);
        while (tokens.hasMoreTokens()){
            String nextToken = tokens.nextToken();
            if (!protectedStrings.contains(nextToken)) keywords.add(nextToken);
        }

        return keywords;
    }
}
