package AssignmentProblems;

import java.util.HashMap;

public class CastleGuards {

    public int missing(String[] castle){

        HashMap<Integer,String> rowMap = new HashMap<>();
        HashMap<Integer,String> colMap = new HashMap<>();

        //populate with '.' to initiate
        int n = castle.length;
        for (int i = 0; i<n; i++){
            rowMap.put(i,".");
        }
        int c = castle[0].length();
        for (int i = 0; i<c; i++) {
            colMap.put(i, ".");
        }


        for(int r =0; r < n; r++){//row loop
            for(int col = 0; col< c; col++ ){//column loop
                if(castle[r].charAt(col) == 'X'){
                    //update map with X if X is at (r,col) element
                    rowMap.put(r, "X");
                    colMap.put(col, "X");
                }
            }
        }
        
        int rCount = 0;
        int colCount = 0;
        for (String value : rowMap.values()){
            if(value.equals(".")){
                rCount+=1;
            }
        }
        for (String value : colMap.values()){
            if(value.equals(".")){
                colCount+=1;
            }
        }

        return Math.max(rCount, colCount);
    }

}
