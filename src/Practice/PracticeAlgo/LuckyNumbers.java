package Practice.PracticeAlgo;

import java.util.*;

public class LuckyNumbers {
    
    public int countLucky(int[] a, int[] b, int[] c) {
        Set<Integer> luckySet = new HashSet<>();

        for (int x : a) {
            for (int y : b) {
                for (int z : c) {
                    int sum = x + y + z;
                    if (isLucky(sum)) {
                        luckySet.add(sum);
                    }
                }
            }
        }

        return luckySet.size();
    }

    private boolean isLucky(int num) {
        while (num > 0) {
            int digit = num % 10;
            if (digit != 5 && digit != 8) return false;
            num /= 10;
        }
        return true;
    }

    // Sample test runner
    public static void main(String[] args) {
        LuckyNumbers ln = new LuckyNumbers();
        System.out.println(ln.countLucky(new int[]{1, 10, 100}, new int[]{3, 53}, new int[]{4, 54})); // 2
        System.out.println(ln.countLucky(new int[]{47}, new int[]{500}, new int[]{33})); // 0
        System.out.println(ln.countLucky(new int[]{100, 1, 10, 100, 1, 1}, 
                                         new int[]{3, 53, 53, 53, 53, 53, 53}, 
                                         new int[]{4, 54, 4, 54, 4, 54})); // 2
        System.out.println(ln.countLucky(new int[]{500, 800}, new int[]{50, 80}, new int[]{5, 8})); // 8
        System.out.println(ln.countLucky(new int[]{30000, 26264}, new int[]{30000, 29294}, new int[]{30000, 29594})); // 3
    }
}

