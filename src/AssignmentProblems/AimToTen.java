package AssignmentProblems;

public class AimToTen {

    /*
     * int[] - grades array each grade between 0-10
     * determine the minimum number of future assignments that are needed for you to receive a final grade of 10.
     * You will receive a final grade of 10 if your average grade is 9.5 or higher.
     * 
     * -    marks has between 1 and 50 elements, inclusive.
     * Each element of marks is between 0 and 10, inclusive.
     */

     public int need(int[] marks){

        //find average of input grades as currAvg
        //n ints in the array
        //if currAvg is not above 9.5 then add 10 and find new avg keep on till it crosses 9.5

        int sum = 0;
        int n = marks.length;

        // Calculate current sum of grades
        for (int mark : marks) {
            sum += mark;
        }

        // Check if the current average is already 9.5 or more
        while ((double) sum / n < 9.5) {
            sum += 10; //add 10
            n++;      //counter increment 
        }

        return n - marks.length; 
   

     }

     

}
