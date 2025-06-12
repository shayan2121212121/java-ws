package Practice.PracticeGraph.Tree;

public class FinfTheCeleb {


    private int numOfPeople;
    private int[][] matrix;
    public FinfTheCeleb(int[][] matrix) {
        this.matrix = matrix;
    }
    
    public int findCelebrity(int n) {
        numOfPeople = n;
        int celebCandiate = 0;
        
        
        for(int i=0; i<n; i++){
            if(knows(celebCandiate, i)){
                celebCandiate = i;
            }
        }
        
        if(isCelebrity(celebCandiate)){
            return celebCandiate;
        }
        
        return -1;
        
    }
    
    public boolean isCelebrity(int i){
        
        for(int j=0; j<numOfPeople; j++){
            if(i==j){
                continue;
            }
            if(knows(i,j) || !knows(j,i)){
                return false;
            }
        }
        
        return true;
        
    }
    private boolean knows(int a, int b) {
        return matrix[a][b] == 1;
    }

}
