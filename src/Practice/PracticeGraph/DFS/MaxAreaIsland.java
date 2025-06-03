package Practice.PracticeGraph.DFS;

public class MaxAreaIsland {
    /*
     * given a 0-1 matrix where 1 represents islands
     * fine the area of biggest island
     */
    //note since its not a path finding problem 
    public static int getMaxArea(int[][] grid){
        //we will loop through grid and call dfs for each 1 we encounter
        int maxArea = 0;
        for(int i=0; i<grid.length;i++){
            for(int j=0; j<grid[0].length;j++){
                if(grid[i][j]==1){
                    int currArea = dfs(i,j,grid);
                    maxArea = Math.max(maxArea, currArea);
                }
            }
        }
        return maxArea;

    }
    //dfs practice to get curr island area
    public int dfspractice(int i, int j, int[][] grid){
        //bound cond and invalid cond
        if(i<0 || i>=grid.length || j<0|| j>=grid[0].length || grid[i][j]==0) return 0;

        //mark visited cell, no temp as we don't revisit this island in next call
        grid[i][j]=0;

        //initial area
        int area = 1;

        //directions
        int[][] directions = new int[][]{
            {1,0},//down
            {-1,0},//up
            {0,1},//right
            {0,-1},//left
        };
        for(int[] dir:directions){
            int ni = i+dir[0];
            int nj = j+dir[1];
            area = area+dfspractice(ni, nj, grid);//update area for all 1s
        }
        return area;

    }

    public static int dfs(int i, int j, int[][] grid ){
        //Step1: boundary cond
        if(i<0 || i>=grid.length || j<0 || j>= grid[0].length){
            return 0;
        }
        //Step2: check invalid or visited i.e 0 value cell
        if(grid[i][j]==0){
            return 0;
        }
        //step3: mark node/cell visited
        //to mark cell visited we will convert it to 0
        grid[i][j]=0;

        //initiate area with 1 for the 1st cell
        int area = 1;

        //Step4: explore all other directions using recursive dfs call
        //this will be in all four directions
        area = area + dfs(i+1,j,grid); //down
        area = area + dfs(i-1,j,grid);//up
        area = area + dfs(i,j+1,grid);//right
        area = area + dfs(i,j-1,grid);//left

        return area;
    }

}
