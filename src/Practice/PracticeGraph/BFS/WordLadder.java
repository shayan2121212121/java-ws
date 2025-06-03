package Practice.PracticeGraph.BFS;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class WordLadder {

    public static int shortestPath(String start, String end, List<String> wordList){
        //create queue
        Queue<String> queue = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();
        queue.add(start); //add root
        visited.add(start); //add root

        int wordCount = 1; //start word

        while(!queue.isEmpty()){
            int queueSize = queue.size();

            for(int i = 0; i<queueSize;i++){
                String currWord = queue.remove(); //get current word
                //check if current is end

                if(currWord.equals(end)){
                    return wordCount;
                }
                
                //add neighbors to queue
                List<String> neighbors = getNeighbors(currWord, wordList);
                for(String word:neighbors){
                    if(!visited.contains(word)){
                        queue.add(word);
                        visited.add(word);
                    }
                }
            }
            wordCount++;//increase after each level of graph
        }
        return wordCount;
    }

    private static List<String> getNeighbors(String currWord, List<String> wordList){
        char[] currWordChar = currWord.toCharArray();
        List<String> neighbors = new ArrayList<>();
        for(int i =0; i< currWordChar.length; i++){
            char oldChar = currWordChar[i];
            for(char c='a';c<='z';c++ ){
                if(currWordChar[i]==c){
                    continue;
                }
                currWordChar[i]=c;
                String newWord = new String(currWordChar);
                if(wordList.contains(newWord)){
                    neighbors.add(newWord);
                }
            }
            currWordChar[i] = oldChar; //restore for next letter loop
        }
        return neighbors;
    }

    public static void main(String[] args) {
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");

        int result = shortestPath(beginWord, endWord, wordList);
        System.out.println("Shortest transformation length is " + result);
    }

}
