package AssignmentProblems;

public class WhiteShoes {
    
    public int howMany(int[] count) {
        int n = count.length;
        
        for (int w = 0; w <= n; w++) {
            int whiteCount = 0;
            boolean valid = true;

            for (int c : count) {
                if (c == w - 1) {
                    whiteCount++;
                } else if (c != w) {
                    valid = false;
                    break;
                }
            }

            if (valid && whiteCount == w) {
                return w;
            }
        }
        
        return -1;
    }
}
