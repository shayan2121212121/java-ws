package AssignmentProblems;

public class CorporationSalary {

    public static long totalSalary(String[] relations) {
        int n = relations.length;
        long[] memo = new long[n];

        long total = 0;
        for (int i = 0; i < n; i++) {
            total += computeSalary(i, relations, memo);
        }

        return total;
    }

    private static long computeSalary(int employee, String[] relations, long[] memo) {
        if (memo[employee] != 0){
            return memo[employee];
        } 

        long salary = 0;
        boolean hasSubordinates = false;

        for (int j = 0; j < relations.length; j++) {
            if (relations[employee].charAt(j) == 'Y') {
                hasSubordinates = true;
                salary += computeSalary(j, relations, memo);
            }
        }
        if (hasSubordinates) {
            memo[employee] = salary;
        } else {
            memo[employee] = 1;
        }
        return memo[employee];
    }

    public static void main(String[] args) {
        String[] edges = {"NNNN",
 "NNNN",
 "NNNN",
 "NNNN"};
       
        long result = totalSalary(edges);
        System.out.println("Network delay time: " + result); // Output: 2
    }

}
