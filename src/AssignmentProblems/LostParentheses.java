package AssignmentProblems;

public class LostParentheses {
    public int minResult(String e) {
        // Split expression at '-' signs
        String[] groups = e.split("-");

        // Compute the sum of the first group (this is always added)
        int result = sumOfGroup(groups[0]);

        // Subtract the sum of each subsequent group
        for (int i = 1; i < groups.length; i++) {
            result -= sumOfGroup(groups[i]);
        }

        return result;
    }

    // Helper method to compute the sum of numbers separated by '+'
    private int sumOfGroup(String group) {
        String[] numbers = group.split("\\+");
        int sum = 0;
        for (String num : numbers) {
            sum += Integer.parseInt(num);
        }
        return sum;
    }

    // Test
    public static void main(String[] args) {
        LostParentheses lp = new LostParentheses();
        System.out.println(lp.minResult("55-50+40")); // -35
        System.out.println(lp.minResult("10+20+30+40")); // 100
        System.out.println(lp.minResult("00009-00009")); // 0
    }
}

