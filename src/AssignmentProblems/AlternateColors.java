package AssignmentProblems;

public class AlternateColors {

    /*
     * getter method to get the color of ball
     */
    public String getColor(long r, long g, long b, long k) {

        //order of destruction red-green-blue with k denoting the current iteration
        /*
         * Step 1 will be counting the number of full cycles (fc) which will be equal to the minimum value of r,g and b.
         * Step 2 will be to calculate total balls in full cycle (tbfc) which will be fc * 3
         * Step 3 will be to check if k is lower than tbfc then just return RED, GREEN or BLUE based on remainder of k devided by 3
         * Step 4 else if k is greater than tbfc, readjust k, r, g and b. New k will be K-tbfc and r, g and b will be subtracted by fc
         * Step 5 will be to check if new k is lower than equal to available colors in r - g - b order
         */

        long fc = Math.min(r, Math.min(g,b));
        long tbfc = fc * 3;

        if (k <= tbfc ) {
            if (k % 3 == 1) return "RED";
            if (k % 3 == 2) return "GREEN";
            return "BLUE";
        }

        k -= tbfc;
        r -= fc;
        g -= fc;
        b -= fc;

        if (r > 0) {
            if (k <= r) return "RED";
            k -= r;
        }

        if (g > 0) {
            if (k <= g) return "GREEN";
            k -= g;
        }
        return "BLUE";
    }

}
