package AssignmentProblems;

public class BlackAndWhiteSolitaire {
    public int minimumTurns(String cardFront) {
        int flipsStartWithB = 0;
        int flipsStartWithW = 0;

        for (int i = 0; i < cardFront.length(); i++) {
            char expectedCharForB;
            if (i % 2 == 0) {
                expectedCharForB = 'B';
            } else {
                expectedCharForB = 'W';
            }

            char expectedCharForW;
            if (i % 2 == 0) {
                expectedCharForW = 'W';
            } else {
                expectedCharForW = 'B';
            }

            if (cardFront.charAt(i) != expectedCharForB) {
                flipsStartWithB++;
            }

            if (cardFront.charAt(i) != expectedCharForW) {
                flipsStartWithW++;
            }
        }

        if (flipsStartWithB < flipsStartWithW) {
            return flipsStartWithB;
        } else {
            return flipsStartWithW;
        }
    }
    public static void main(String[] args) {
        BlackAndWhiteSolitaire obj = new BlackAndWhiteSolitaire();
        System.out.println(obj.minimumTurns("BBBW"));               // 1
        System.out.println(obj.minimumTurns("WBWBW"));              // 0
        System.out.println(obj.minimumTurns("WWWWWWWWW"));          // 4
        System.out.println(obj.minimumTurns("BBWBWWBWBWWBBBWBWBWBBWBBW")); // 10
    }
    
}

