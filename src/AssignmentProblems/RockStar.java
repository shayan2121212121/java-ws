package AssignmentProblems;

public class RockStar {
    public int getNumSongs(int ff, int fs, int sf, int ss) {
        int total = 0;

        if (ff > 0 || fs > 0) {
            
            total = 0; 
            if (ff > 0) {
                total += 1;
                ff -= 1; 
            } 
            total += ff;

            if(fs>0 && sf>0){
                int pairs = Math.min(fs, sf);
                total += 2 * pairs;
                int remainingFs = fs - pairs;
                if (remainingFs > 0) {
                    total += 1;
                }
                total += ss;
            }else if (fs > 0) {
                total += 1;
                total += ss;
            }

        } else {
            total = ss;
            if (sf > 0) {
                total += 1; 
            }
        }

        return total;
    }
}





