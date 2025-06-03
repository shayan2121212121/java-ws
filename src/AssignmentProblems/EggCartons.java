package AssignmentProblems;

public class EggCartons {

    public int minCartons(int n){

        /*
         * One type contains 6 eggs and the other type contains 8 eggs.
         * John wants to buy exactly n eggs
         * Return the minimal number of egg cartons he must buy. If it's impossible to buy exactly n eggs, return -1.
         * n will be between 1 and 100, inclusive.
         */

         /*
          * x = 6 per cart
          * y = 8 per cart
          * 6x + 8y = n | x = n-/()
          * check if n is divisible by 8 or 6 | if n is odd return -1 
          * if none of above start with 6*1 + 8*1 - > 6*2 + 8*1 -> 6*3 + 8*
          */
          if(n % 2 != 0){
            return -1;
          } else if (n%8 == 0){
            return (n%8);

          }else if (n %6 == 0) {
            return (n%6);
          }else {
            int i = 0;
            int j = 0;
            for(i=0; i<=16; i++){
                for(j=0; j<=12; j++){
                    int x = (6*i) + (8*j);
                    if(x == n){
                        return (i+j);
                }

            }
          }
          }
          

        return -1;
    }
}
