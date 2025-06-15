package Practice.PracticeAlgo;

public class ExpandString {

    public int howLong(String s) {
        return evaluate(s.toCharArray(), new int[]{0});
    }

    // Helper function that uses an index pointer passed as an array
    private int evaluate(char[] arr, int[] index) {
        int totalLength = 0;

        while (index[0] < arr.length) {
            char ch = arr[index[0]];

            if (Character.isDigit(ch)) {
                // Check if next char is '(', then process group
                if (index[0] + 1 < arr.length && arr[index[0] + 1] == '(') {
                    int repeat = ch - '0';
                    index[0] += 2; // skip digit and '('
                    int subLength = evaluate(arr, index); // get length inside brackets
                    totalLength += repeat * subLength;
                } else {
                    // It's a digit outside of group, count as 1
                    totalLength += 1;
                    index[0]++;
                }
            } else if (ch == ')') {
                // End of current group
                index[0]++;
                return totalLength;
            } else {
                // Any non-digit character is counted as 1 (though spec says only digits and parens)
                totalLength += 1;
                index[0]++;
            }
        }

        return totalLength;
    }

    // For testing
    public static void main(String[] args) {
        ExpandString obj = new ExpandString();
        System.out.println(obj.howLong("123")); // 3
        System.out.println(obj.howLong("10342(76)")); // 8
        System.out.println(obj.howLong("33(562(71(9)))")); // 19
        System.out.println(obj.howLong("0(0)")); // 0
        System.out.println(obj.howLong("1(1(1(1(1(1(1(0(1234567890))))))))")); // 0
        System.out.println(obj.howLong("1()66(5)")); // 7
    }
}


