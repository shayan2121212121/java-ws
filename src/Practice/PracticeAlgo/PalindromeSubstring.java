package Practice.PracticeAlgo;

public class PalindromeSubstring {

    public int countPalindromeSubString(String s){
        int ans = 0;

        for(int i =0; i<s.length();i++){
            ans+= checkPal(s,i,i);
            ans+= checkPal(s,i,i+1);
        }
        return ans;
    }

    public int checkPal(String s, int l, int r){
        int count = 0;

        while(l>=0 && r<s.length() && s.charAt(l)==s.charAt(r)){
            l--;
            r++;
            count++;
        }
        return count;

    }


    public static void main(String[] args) {
        PalindromeSubstring tm = new PalindromeSubstring();

        System.err.println(tm.countPalindromeSubString("bxaba"));

    }


}
