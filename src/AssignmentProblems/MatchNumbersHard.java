package AssignmentProblems;

public class MatchNumbersHard {
    public String[] maxNumber(String[] matches, String n) {
        // Convert input n to a long
        long totalMatches = Long.parseLong(n);
        
        // Convert match array to a long array for easier comparison
        long[] matchCounts = new long[matches.length];
        for (int i = 0; i < matches.length; i++) {
            matchCounts[i] = Long.parseLong(matches[i]);
        }
        
        // Find the minimum number of matches required to form a single digit
        long minMatches = Long.MAX_VALUE;
        for (int i = 0; i < matchCounts.length; i++) {
            if (matchCounts[i] < minMatches) {

            }
        }
        
        // If there are not enough matches to create any digit
        if (totalMatches < minMatches) {
            return new String[] {"0", "", ""};
        }
        
        // Step 1: Calculate the maximum number of digits we can form using minMatches
        long maxDigits = totalMatches / minMatches;
        long remainingMatches = totalMatches % minMatches;
        
        // Step 2: Start forming the largest possible number
        StringBuilder result = new StringBuilder();
        
        for (long i = 0; i < maxDigits; i++) {
            // Try to use the largest possible digit for each position
            for (int j = matchCounts.length-1; j >= 0; j--) {
                if (matchCounts[j] <= remainingMatches + minMatches) {
                    result.append(j);
                    remainingMatches -= (matchCounts[j] - minMatches);
                    break;
                }
            }
        }
        
        // Convert the result to a string
        String resultString = result.toString();
        int resultLength = resultString.length();
        
        // Step 3: Create the final result array
        String[] resultArray = new String[3];
        resultArray[0] = String.valueOf(resultLength);
        
        if (resultLength > 50) {
            resultArray[1] = resultString.substring(0, 50);
            resultArray[2] = resultString.substring(resultLength - 50);
        } else {
            resultArray[1] = resultString;
            resultArray[2] = resultString;
        }
        
        return resultArray;
    }
}
