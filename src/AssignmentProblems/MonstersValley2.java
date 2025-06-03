package AssignmentProblems;

public class MonstersValley2 {

    private int[] dread;
    private int[] price;
    private int n;
    private int minCost;

    public int minimumPrice(int[] dread, int[] price) {
        this.dread = dread;
        this.price = price;
        this.n = dread.length;
        this.minCost = Integer.MAX_VALUE;
        dfs(0, 0, 0);

        return minCost;
    }

    private void dfs(int index, long totalDread, int totalCost) {
        // Base case all encountered
        if (index == n) {
            minCost = Math.min(minCost, totalCost);
            return;
        }

        // Bribe the current monster
        dfs(index + 1, totalDread + dread[index], totalCost + price[index]);

        // Skip bribing (only if monster doesn't attack)
        if (dread[index] <= totalDread) {
            dfs(index + 1, totalDread, totalCost);
        }
    }

}
