package Practice.PracticeGraph.DFS;

public class UniquePathCount {
    //DFS + Memo problem
    //count all unique path from start cell (1) to end cell(2)
    //0 represents open cell and -1 represents closed cell
    //count all unique paths from the start to the end, 
    //condition is to count path that visits all 0s not just some1
    //dfs will give all paths to reach end maintain a counter for path where all 0s are visited and return that
    int result =0;
    int totalZero = 0;
    
    public int numOfUniquePaths(int[][] grid){
        //dfs will need the ij of start and index to keep count 
        //start index from -1 as we need to compare it to everyZero and index will also count the start cell 1
        //so index = at the end will be all 0s plus start cell

        int x = 0;
        int y = 0;

        //loop to calculate 0s count and find start i,j
        for(int i = 0; i<grid.length;i++){
            for(int j=0; j<grid[0].length;j++){
                if(grid[i][j]==0){
                    totalZero++;
                }
                else if(grid[i][j]==1){
                    //start cell
                    x = i;
                    y = j;
                }
            }
        }
        //call dfs with grid, x,y and index=-1
        dfs(grid, x, y, -1);

        return result;
    }

    //dfs practice
    public void dfspractice(int[][] grid, int i, int j, int index){//void because we use result clas variable to maintatin count
        //bound and invalid cell check
        if(i<0 || i>=grid.length || j<0 || j>=grid[0].length || grid[i][j]==-1 || grid[i][j]==3) return;

        //dest check
        if(grid[i][j]==2){
            //we check if all 0s covered,index=zerocount then increase result
            if(index==totalZero){
                result++;
            }
            return;
        }
        //mark visited with temp as we are counting path
        int temp = grid[i][j];
        grid[i][j]=3;

        //directions
        int[][] directions = new int[][]{
            {1,0},//down
            {-1,0},//up
            {0,1},//right
            {0,-1}//left
        };
        for(int[] dir:directions){
            int ni = i+dir[0];
            int nj = j+dir[1];
            dfspractice(grid, ni, nj, index+1);
        }
        //restore temp
        grid[i][j]=temp;

    }

    private void dfs(int[][] grid, int i, int j, int index){
        
        //2 boundary
        if(i<0 || i>=grid.length || j<0 || j>=grid[0].length){
            return;
        }
        //3 invalid value -1 and 3
        if(grid[i][j]==-1|| grid[i][j]==3){
            return;
        }
        //1 base case
        if(grid[i][j]==2){
            //check if all 0s visited increase result
            if(index==totalZero){
                result++;
            }
            return;
        }
        
        //4 visited cell mark and temp needed as we backtrack for all possible paths
        int temp = grid[i][j];
        grid[i][j]=3;

        //5 directions to make recursive calls
        int[][] directions = new int[][]{
            {1,0},//down
            {-1,0},//up
            {0,1},//right
            {0,-1}//left
        };
        for(int[] dir: directions){
            int ni=i+dir[0];
            int nj=j+dir[1];
            dfs(grid, ni, nj, index+1);
        }
        //6 restore temp
        grid[i][j] = temp;

    }

    public static void main(String[] args) {
        UniquePathCount solver = new UniquePathCount();


        int[][] grid = {
            {1, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 2, -1}
        };

        System.out.println("Unique Paths: " +solver.numOfUniquePaths(grid)); // Output: 2
    }

}
