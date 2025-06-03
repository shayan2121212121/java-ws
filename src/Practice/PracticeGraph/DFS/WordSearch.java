package Practice.PracticeGraph.DFS;

public class WordSearch {

    //given char[][] board of characters and String word  return true if word can be made using letters on the board
    //we need possible combinations its a dfs problem
    //use only one character once no repeat

    public static boolean wordSearch(char[][] board, String word){
        //iterate through the board and call dfs
        boolean wordFound = false;
        for(int i=0; i< board.length; i++){
            for(int j=0; j< board[0].length;j++){
                wordFound = dfs(board, i, j, 0, word);
                //break loop once it is found
                if(wordFound){
                    return true;
                }
            }
        }
        return wordFound;
    }
    //practice dfs
    public static boolean dfspractice(char[][] board, int i, int j, int index, String word){
        //bound and invalid cell
        if(i<0 || i>=board.length || j<0 || j>=board[0].length || board[i][j]!=word.charAt(index) || board[i][j]=='#') return false;

        //word found
        if(index==word.length()) return true;

        //visited cell using temp to restore for next iteration 
        char temp = board[i][j];
        board[i][j]='#';

        //directions
        int[][] directions = new int[][]{
            {0,1},//right
            {0,-1},//left
            {1,0},//down
            {-1,0}//up
        };
        for(int[] dir:directions){
            int ni = i+dir[0];
            int nj = j+dir[1];
            boolean found = dfspractice(board, ni, nj, index+1, word);
            if(found) return true;
        }
        //restore temp
        board[i][j]=temp;
        return false;
    }

    //board for navigation i and j for current coordinate on board and index and word to check if word length is reached
    public static boolean dfs(char[][] board, int i, int j, int index, String word){
        //Step1 base case to break recursive call meaning word is formed
        if(index == word.length()){//current index not needed because all chars were matched
            return true;
        }

        //Step2 boundary cond
        if(i<0 || i>=board.length || j<0 || j>= board[0].length){
            return false;
        }
        //Step3 invalid cell meaning char mismatch at the word index
        if(board[i][j]!=word.charAt(index)){
            return false;
        }

        //Step4 marking cell visited for current travel but keeping the char to restore for next travel
        char temp = board[i][j];
        board[i][j]='#';

        //Step5 four direction movement and dfs recursive call
        int[][] directions = new int[][]{
            {1,0},//up
            {-1,0},//down
            {0,1},//right
            {0,-1},//left
        };
        for(int[] dir: directions){
            int ni = i+dir[0];
            int nj = j+dir[1];
            //call dfs with new i j and index
            boolean foundWord = dfs(board, ni, nj, index+1, word);
            if(foundWord){
                return true;
            }
        }

        //Step6 if word not found restore board using temp and return false
        board[i][j]=temp;
        return false;

    }
    public static void main(String[] args) {

        char[][] board = {
            {'A','B','C','E'},
            {'S','F','C','S'},
            {'A','D','E','E'}
        };

        String word = "ABCCED";

        System.out.println("Word found: " + wordSearch(board, word)); // Output: true
    }

}
