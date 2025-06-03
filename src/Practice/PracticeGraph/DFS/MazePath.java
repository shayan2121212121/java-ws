package Practice.PracticeGraph.DFS;

public class MazePath {
    //given a int[][] grid of 0s and 1s check if a path exists from start to end 
    //its dfs problem because we just need to check if a path exists from start to end.
    // no need to find shortest path that would be a bfs problem

    public static boolean pathExists(int[][] maze, int[] start, int[] end){
        boolean foundPath = false;
        foundPath = dfs(maze, start[0], start[1], end);
        //call bfs to check
        return foundPath;
    }

    //dfs practice
    public static boolean dfspractice(int[][] maze, int i, int j, int[] end){
        //bound and invalid cell
        if(i<0 || i>=maze.length || j<0 || j>=maze[0].length || maze[i][j]==1) return false;

        //destination check base case
        if(i==end[0] && j==end[1])return true;

        //mark visited no need for temp as we dont need to revisit for no of path
        maze[i][j]=1;

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
            boolean found = dfspractice(maze, ni, nj, end);
            if(found)return true;
        }
        return false;
    }

    public static boolean dfs(int[][] maze, int i, int j, int[] end){
        //Step1 base case end found
        if(i == end[0] && j == end[1]) return true;

        //Step2 boundary cond
        if(i<0 || i>=maze.length || j<0 || j>maze[0].length) return false;

        //Step3 invalid cell i.e 1
        if(maze[i][j]==1) return false;

        //step4 mark visited cell by converting 0 to 1 no need of temp as we will not revisit as no of paths are not needed
        maze[i][j]=1;

        //Step5 neighbor movement
        int[][] directions = new int[][]{
            {1,0},//down
            {-1,0},//up
            {0,1},//right
            {0,-1}//left
        };
        for(int[] dir: directions){
            int ni = i+dir[0];
            int nj = j+dir[1];
            //recursive call of dfs
            boolean found = dfs(maze, ni, nj, end);
            if(found) return true;
        }

        //return false if not found
        return false;
    }

    public static void main(String[] args) {

        int[][] maze = {
            {0, 0, 1, 0},
            {1, 0, 1, 0},
            {1, 0, 0, 1},
            {1, 1, 1, 0}
        };

        int[] start = new int[]{0,0};
        int[] end = new int[] {3,3};

        System.out.println("Path exists? " + pathExists(maze, start, end));
        // Output: false
    }

}
