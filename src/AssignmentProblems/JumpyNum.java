package AssignmentProblems;

public class JumpyNum {

    public int howMany(int low, int high){
        int count = 0;

        for(int i = low; i<=high; i++){
            boolean isJumpy = checkIfJumpy(i);
            if(isJumpy){
                count+=1;
            }
        }
        return count;
    }

    private boolean checkIfJumpy(int num){
        if(num<10){
            return true;
        }
        int lastDigit = num%10;
        num = num/10;
        while (num>0){
            int secondLastDigit = num%10;
            if(Math.abs(secondLastDigit-lastDigit)<2){
                return false;
            }
            lastDigit = secondLastDigit;
            num = num/10;
        }
        return true;
    }

}
