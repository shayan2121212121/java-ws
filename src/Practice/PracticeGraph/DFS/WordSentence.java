package Practice.PracticeGraph.DFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WordSentence {
    /*
     * Given a string and a list of words check how many words can be made by the string return a list of words
     * e.g s = "catsanddog" and wordDict = ["cat", "cats", "and", "sand", "dog"] return ["cats and dog", "cat sand dog"]
     * constraint is to use all letters in string for each list entry in result list
     */

     /*
      * There is no grid its a 1D problem so movement will be iteration through the string characters
      * 
      */
      public static List<String> wordSearch(String s, List<String> wordDict){
        //convert list to set for matching 
        Set<String> wordSet = new HashSet<>(wordDict);
        //memo to store possible words for a given substring
        Map<String,List<String>> memo = new HashMap<>();
        //calling dfs

        return dfs(s, wordSet, memo);
      }

      //dfs
      public static List<String> dfs(String s, Set<String> wordSet, Map<String,List<String>> memo){
        //check in memo
        if(memo.containsKey(s))return memo.get(s);

        //list to store all possible words
        List<String> resuList = new ArrayList<>();

        //base case empty second part in the end
        if(s==""){
            resuList.add("");
            return resuList;
        }
        //loop through s
        for(int i=1;i<=s.length();i++){//<= because last letter will be from last index to length of string
            String firstPart = s.substring(0, i);
            if(wordSet.contains(firstPart)){
                List<String> secondPartls = dfs(s.substring(i), wordSet, memo);
                for(String secondWord : secondPartls){
                    if(secondWord==""){
                        resuList.add(firstPart);
                    }else{
                        resuList.add(firstPart+" "+secondWord);
                    }
                }
            }
        }
        //add in memo
        memo.put(s, resuList);
        return resuList;
      }
      public static void main(String[] args) {

        String s = "catsanddog";
        List<String> wordDict = Arrays.asList("cat", "cats", "and", "sand", "dog");

        List<String> sentences = wordSearch(s, wordDict);

        System.out.println("Possible sentences:");
        for (String sentence : sentences) {
            System.out.println(sentence);
        }
    }

}
