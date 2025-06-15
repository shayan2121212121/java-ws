package Practice.PracticeAlgo;

public class NiceBoxes {

    public int minRemove(int a, int b, int c) {
        int minEaten = Integer.MAX_VALUE;

        for (int cPrime = 1; cPrime <= c; cPrime++) {
            for (int bPrime = 1; bPrime < cPrime && bPrime <= b; bPrime++) {
                int aPrime = Math.min(a, bPrime - 1);
                if (aPrime >= 1) {
                    int eaten = (a - aPrime) + (b - bPrime) + (c - cPrime);
                    minEaten = Math.min(minEaten, eaten);
                }
            }
        }

        return minEaten == Integer.MAX_VALUE ? -1 : minEaten;
    }

    // Test cases
    public static void main(String[] args) {
        NiceBoxes nb = new NiceBoxes();
        System.out.println(nb.minRemove(15, 40, 22));     // 19
        System.out.println(nb.minRemove(5, 6, 6));         // 2
        System.out.println(nb.minRemove(6, 1, 3000));      // -1
        System.out.println(nb.minRemove(6, 4, 2));         // -1
        System.out.println(nb.minRemove(1, 1234, 3000));   // 0
        System.out.println(nb.minRemove(2789, 2400, 1693));// 1806
    }
}

