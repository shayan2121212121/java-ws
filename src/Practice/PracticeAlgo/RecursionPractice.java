package Practice.PracticeAlgo;


public class RecursionPractice {

    //sum of digits
    public int digitSum(int digits){
        if (digits == 0 ){
            return 0;
        }

        return (digits % 10) + digitSum(digits/10);
    }

    //reverse string
    public String reverseString(String str){
        if (str.isEmpty()){
            return str;
        }

        return reverseString(str.substring(1)) + str.charAt(0);
    }

    //nth term of fibonacci series
    public int fib(int n){
        if(n == 0){
            return 0;
        } else if(n ==1){
            return 1;
        }
        return fib(n-2) + fib(n-1);
    }

    //factorial of n
    public int factorial(int n){
        if(n == 0){
            return 1;
        }
        return n* factorial(n-1);
    }

    //power of n
    public int power(int x, int n){
        if (x==0){
            return 0;
        } else if (x !=0 && n == 1){
            return x;
        }
        return x * power(x, n-1);
    }

    //Number of digits in an integer
    public int numOfDigits(int num){
        if(num<10) {
            return 1;
        }
        return 1 + numOfDigits(num/10);
    }

    //String palindrome test
    public boolean isPalindrome(String str){
        if(str.length()<=1){
            return true;
        }
        if(str.charAt(0) != str.charAt(str.length() -1)) {
            return false;
        }
        return isPalindrome(str.substring(1,str.length()-1));

    }

    //sum of numbers in array
    public int sumArr(int[] arr, int n){
        if(n == 0) {
            return 0;
        }
        return arr[n-1] + sumArr(arr,n-1);
    }

    //Gcd of two ints
    public int gcd(int a, int b){
        if(b == 0){
            return a;
        }
        return gcd(b,a%b);
    }

    //count number of digits in an integer
    public int countDigits(int n){
        if (n ==0){
            return 0;
        }
        return 1 + countDigits(n/10);
    }

    //int to binary conversion
    public String toBinary(int n){
        if (n == 0){
            return "";
        }
        return toBinary(n/2) + n%2;
    }

    //Remove duplication consecutive strings
    public String removeConsecutiveString(String str){
        if(str.length() <= 1){
            return str;
        }
        if(str.charAt(0) == str.charAt(1)){
            return removeConsecutiveString(str.substring(1));
        } else{
            return str.charAt(0) + removeConsecutiveString(str.substring(1));
        }

    }



    //Count vowels in a string
    public int countVowels(String str){
        
        if(str.isEmpty()){
            return 0;
        }
        if("aeiouAEIOU".indexOf(str.charAt(0)) != -1){
            return 1 + countVowels(str.substring(1));
        } else {
            return countVowels(str.substring(1));
        }
    }

    //quick sort
    public void quickSort(int[] arr, int low, int high){
        //find pivot index
        int pi = pivotIndex(arr, low, high);

        //recursive call for pivot
        quickSort(arr, low, pi-1);
        quickSort(arr, pi+1, high);
    }

    public int pivotIndex(int[] arr, int low, int high){
        //choose last element as initial pivot
        int pi = arr[high];
        //i = low-1
        int i = low-1;
        //loop from low to high using j
        for(int j = low; j<high; j++){
            //if current element < pivot i++ and swap i with j
            if(arr[j]<arr[pi]){
                i++;
                swap(arr,i,j);
            }
        }
            
        //using swap method place pivot at i+1
        swap(arr,i+1,pi);
        //return pivot index i.e. i+1
        return i+1;
    }

    public void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
