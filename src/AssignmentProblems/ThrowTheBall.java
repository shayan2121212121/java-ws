package AssignmentProblems;

public class ThrowTheBall {
    public int timesThrown(int N, int M, int L) {
        int[] received = new int[N + 1]; // player numbers from 1 to N
        int currentPlayer = 1;
        int t = 0;

        received[currentPlayer] = 1; // player 1 starts with the ball

        while (received[currentPlayer] < M) {
            // Decide direction based on how many times currentPlayer received the ball
            if (received[currentPlayer] % 2 == 0) {
                // Even → pass to the left
                currentPlayer -= L;
                if (currentPlayer <= 0) {
                    currentPlayer += N;
                }
            } else {
                // Odd → pass to the right
                currentPlayer += L;
                if (currentPlayer > N) {
                    currentPlayer -= N;
                }
            }

            received[currentPlayer]++;
            t = t+1;
        }

        return t;
    }

    // Optional: main method for testing
    public static void main(String[] args) {
        ThrowTheBall game = new ThrowTheBall();
        System.out.println(game.timesThrown(5, 3, 2)); // 10
        System.out.println(game.timesThrown(4, 1, 3)); // 0
        System.out.println(game.timesThrown(10, 3, 5)); // 4
        System.out.println(game.timesThrown(15, 4, 9)); // 15
    }
}

