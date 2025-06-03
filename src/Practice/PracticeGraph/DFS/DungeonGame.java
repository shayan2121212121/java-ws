package Practice.PracticeGraph.DFS;


    /*
     * A knight is trapped in a dungeon. The dungeon is represented as a 2D grid where:
     * Positive numbers = health boost
     * Negative numbers = damage
     * 0 = no effect
     * The knight starts at top-left (0,0) and must reach the bottom-right (m-1,n-1) to rescue the princess.
     * The knight can only move right or down.
     * At any point, if his health drops to 0 or below, he dies.
     * Return the minimum initial health he needs to survive the journey and rescue the princess.
     * 
     */
    //minimum health will be
    
    public class DungeonGame {

        int[][] memo;
        int rows, cols;
    
        public int calculateMinimumHP(int[][] dungeon) {
            rows = dungeon.length;
            cols = dungeon[0].length;
            memo = new int[rows][cols];
    
            // Initialize memo array with -1 (unvisited)
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    memo[i][j] = -1;
                }
            }
    
            return dfspractice(dungeon, 0, 0);
        }
        //practice dfs
        private int dfspractice(int[][]dungeon, int i, int j){
            //1 bound only down and right moves so check col end and row end
            if(i>=rows || j>=cols) return Integer.MAX_VALUE;

            //2 check memo and destination this is the base case to terminate recursion
            if(memo[i][j]!=-1) return memo[i][j];
            if(i==rows-1 && j == cols-1) return Math.max(1,1-dungeon[i][j]);//if positive in the end need 1 else val +1

            //3 moves
            int[][] directions = new int[][]{
                {0,1},//right
                {1,0}//down
            };
            int minNext = Integer.MAX_VALUE;//min health to go to next cell
            for(int[] dir : directions){
                int ni = i + dir[0];
                int nj = j + dir[1];
                int next = dfs(dungeon, ni, nj);
                minNext = Math.min(next, minNext);//pick min of right and down
            }

            //min health in the beginning
            int minHealth = Math.max(1, minNext-dungeon[i][j]);
            //add in memo
            memo[i][j] = minHealth;
            return minHealth;
        }
    
        private int dfs(int[][] dungeon, int i, int j) {
            if (i >= rows || j >= cols) return Integer.MAX_VALUE; // Out of bounds = invalid path
    
            if (memo[i][j] != -1) return memo[i][j]; // Memoized
    
            // Base case: princess cell (bottom-right)
            if (i == rows - 1 && j == cols - 1) {
                return Math.max(1, 1 - dungeon[i][j]); // Need at least 1 health
            }
    
            // Explore right and down
            int[][] directions = new int[][]{
                {1,0},//down
                {0,1}//right
            };
            int minNext = Integer.MAX_VALUE;//min health to go to next cell
            for(int[] dir : directions){
                int ni = i+dir[0];
                int nj = j+dir[1];
                int next = dfs(dungeon, ni, nj);
                minNext = Math.min(next, minNext);
            }
    
            // Min health needed at (i,j) to mreach last cell frrom current i,j
            int minHealth = Math.max(1, minNext - dungeon[i][j]);
            memo[i][j] = minHealth;
            return minHealth;
        }
    
        public static void main(String[] args) {
            DungeonGame solver = new DungeonGame();
    
            int[][] dungeon = {
                {-2, -3,  3},
                {-5, -10, 1},
                {10, 30, -5}
            };
    
            System.out.println("Minimum initial health: " + solver.calculateMinimumHP(dungeon));
            // Output: 7
        }
    }
    
