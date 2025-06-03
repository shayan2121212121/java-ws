package Practice.PracticeGraph.DFS;

public class NiceOrUgly {
    public String describe(String s) {
        int n = s.length();
        Boolean[][][] memo = new Boolean[n + 1][4][6]; // memo[pos][vowels][consonants]

        boolean[] result = dfs(s.toCharArray(), 0, 0, 0, memo);
        if (result[0] && result[1]) return "42";
        if (result[0]) return "NICE";
        return "UGLY";
    }

    // Return [nice_possible, ugly_possible]
    private boolean[] dfs(char[] s, int pos, int vowels, int cons, Boolean[][][] memo) {
        if (vowels == 3 || cons == 5) { // Ugly
            return new boolean[]{false, true};
        } 
        if (pos == s.length) { // Reached end, Nice
            return new boolean[]{true, false};
         } 

        if (memo[pos][vowels][cons] != null) {// already visited
            
            return new boolean[]{memo[pos][vowels][cons], !memo[pos][vowels][cons]};
        }

        boolean nice = false, ugly = false;
        char currChar = s[pos];
        if (currChar == '?') {
            // Try as vowel
            boolean[] v = dfs(s, pos + 1, vowels + 1, 0, memo);
            if (v[0]) {
                nice = true;
            }
            if (v[1]) {
                ugly = true;
            }

            // Try as consonant
            boolean[] cns = dfs(s, pos + 1, 0, cons + 1, memo);
            nice |= cns[0]; ugly |= cns[1];
            if(cns[0]){
                nice=true;
            }
            if(cns[1]){
                ugly=true;
            }
        } else {
            if (isVowel(currChar)) {
                boolean[] v = dfs(s, pos + 1, vowels + 1, 0, memo);
                if (v[0]) {
                    nice = true;
                }
                if (v[1]) {
                    ugly = true;
                }
            } else {
                boolean[] cns = dfs(s, pos + 1, 0, cons + 1, memo);
                if(cns[0]){
                    nice=true;
                }
                if(cns[1]){
                    ugly=true;
                }
            }
        }

        memo[pos][vowels][cons] = nice; // store if nice possible from this state
        return new boolean[]{nice, ugly};
    }

    private boolean isVowel(char c) {
        return "AEIOU".indexOf(c) >= 0;
    }
}

