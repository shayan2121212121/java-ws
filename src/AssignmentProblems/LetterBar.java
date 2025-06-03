package AssignmentProblems;

import java.util.*;

public class LetterBar{
    

    public int maxLength(String letters) {
        int maxLen = 0;
        int start = 0;
        Set<Character> seen = new HashSet<>();

        for (int end = 0; end < letters.length(); end++) {
            char c = letters.charAt(end);
            while (seen.contains(c)) {
                seen.remove(letters.charAt(start));
                start++;
            }
            seen.add(c);
            maxLen = Math.max(maxLen, end - start + 1);
        }
        return maxLen;
    }
}