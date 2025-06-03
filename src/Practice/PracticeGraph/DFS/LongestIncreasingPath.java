package Practice.PracticeGraph.DFS;

public class LongestIncreasingPath {
    /*
     * given a int[][] grid find the longest increasing path length
     * here each cell will be start for a dfs
     * move to next cell if it has a higher value
     * store the max length of each element in memo then update a max with higher value 
     * return the max length
     */
    public int maxLengthIncreasingPath(int[][] grid){
        //max variable and memo
        int[][] memo = new int[grid.length][grid[0].length];
        int max = 0;
        //loop through grid and call dfs with each element to max length and update max
        for(int i=0;i<grid.length;i++){
            for(int j=0; j<grid[0].length;j++){
                int currlen = dfs(grid, i, j, memo);
                max = Math.max(max, currlen);
            }
        }
        return max;
    }
    //dfs practice
    public int dfspractice(int[][] grid, int i, int j, int[][] memo){
        //memo check
        if(memo[i][j]!=-1) return memo[i][j];
        //max and len for current cell
        int max = 1;
        int len = 1;
        //directions
        int[][] directions = new int[][]{
            {0,1},//right
            {0,-1},//left
            {1,0},//down
            {-1,0}//up
        };
        for(int[] dir:directions){
            int ni = i + dir[0];
            int nj = j + dir[1];
            //bound cond and increment cond
            if(ni>=0 && ni<grid.length && nj>=0 && nj<grid[0].length && grid[i][j]<grid[ni][nj]){
                int newLen = len+dfspractice(grid, ni, nj, memo);
                max = Math.max(max, newLen);
            }
        }
        memo[i][j]=max;
        return max;
    }
    public int dfs(int[][] grid, int i, int j, int[][] memo){
        //memo check-base case
        if(memo[i][j]!=0){
            return memo[i][j];
        }
        //we will cover boundary cond in movement it self and there are no invalid and destination cells
        //initiate max with 1 for current cell will remain 1 if no possible neighbor
        int max = 1;
        int len =1;//current cell 

        //direction
        int[][] directions = new int[][]{
            {1,0},//down
            {-1,0},//up
            {0,1},//right
            {0,-1}//left
        };
        for(int[] dir: directions){
            int ni = i+dir[0];
            int nj = j+dir[1];
            //check boundary and increment condition
            if(ni>=0 && ni<grid.length && nj>=0 && nj<grid[0].length && grid[ni][nj]>grid[i][j]){
                //new len will be 1 for current cell + recursive call
                int newlen = len+dfs(grid, ni, nj, memo);
                max = Math.max(max, newlen);//update max with higher value of each path
            }
        }
        memo[i][j]=max;
        return max;
    }
    public static void main(String[] args) {
        LongestIncreasingPath solver = new LongestIncreasingPath();

        int[][] matrix = {
            {9, 9, 4},
            {6, 6, 8},
            {2, 1, 1}
        };

        System.out.println("Longest Increasing Path: " + solver.maxLengthIncreasingPath(matrix));
        // Output: 4
    }

}
