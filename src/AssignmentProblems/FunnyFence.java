package AssignmentProblems;

public class FunnyFence {
    public int getLength(String s) {
        if (s.length() == 0) return 0;

        int maxLen = 1;
        int currLen = 1;

        for (int i = 1; i < s.length(); i++) {
            // If current character is different from previous, continue the fence
            if (s.charAt(i) != s.charAt(i - 1)) {
                currLen++;
                maxLen = Math.max(maxLen, currLen);
            } else {
                // If characters repeat, reset the current fence length
                currLen = 1;
            }
        }

        return maxLen;
    }

    // Optional: main method to test the examples
    public static void main(String[] args) {
        FunnyFence ff = new FunnyFence();
        System.out.println(ff.getLength("|-|-|")); // 5
        System.out.println(ff.getLength("-|-|-|-")); // 7
        System.out.println(ff.getLength("||||||")); // 1
        System.out.println(ff.getLength("|-||-|-")); // 4
        System.out.println(ff.getLength("|-|---|-|---|-|")); // 5
        System.out.println(ff.getLength("|||-||--|--|---|-||-|-|-|--||---||-||-||-|--||")); // 8
    }
}

