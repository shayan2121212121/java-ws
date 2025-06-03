package AssignmentProblems;

import java.util.ArrayList;
import java.util.List;

public class Inchworm {

    public int lunchtime(int branch, int rest, int leaf) {

        //Initial Param contraints
         if(branch>1000000 || branch < 1 || rest > 1000 || rest < 1 || leaf > 1000 || leaf < 1) {
            throw new IllegalArgumentException();
         }
        /*
         * 
            Position = 0
            leafPos = 0
            LeafCount = 1 //leaf at start
            While position< branch    	//distance available to travel
	            Position  += rest
	            leafPos += leaf
	            if Position = leafPos 
		            LeafCount += 1
	
            Return LeafCount
         */
        int currentPosition = 0;
        int currentLeafPosition = 0;
        int noOfLeafConsumed = 0;

        List<Integer> leafPositions = new ArrayList<>();

        for (currentLeafPosition = 0; currentLeafPosition <= branch; currentLeafPosition += leaf) {
            //currentLeafPosition += leaf;
            leafPositions.add(currentLeafPosition);
        }

        for (currentPosition = 0; currentPosition <= branch; currentPosition += rest) {
            
                //currentPosition += rest;
            //currentLeafPosition += leaf;
            if (leafPositions.contains(currentPosition)){
                noOfLeafConsumed += 1;
            }
            
            
        }

        return noOfLeafConsumed;
    }

}
