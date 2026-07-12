package recursion;

public class Recursion {

    //Return Nth fibonaci number in series
    public static int fibonaci(int n){

        if(n==0 || n==1)
            return n;
        return fibonaci(n-1) + fibonaci(n-2);
    }

    public static int binarySearch(int[] arr, int target,int start,int last){
        if(start > last)
            return -1;

        int mid = (last + start)/2;

        if(arr[mid] == target)
            return mid;
        else if(arr[mid] > target)
            return binarySearch(arr,target,start,mid-1);
        else
            return binarySearch(arr,target,mid+1,last);
    }

    //Reverse a number
    //1234 (need to take number ( digit-1 ) as args
    public static int reverseNum(int num, int args){
        if(num == 0)
            return 0;
        return (num%10)*(int)Math.pow(10,args) + reverseNum(num/10,args-1);

    }

    //count number of zeros in a number
    public  static int countZaros(int n, int count){
        if (n==0){
            return count;
        }
        if(n%10 == 0)
            ++count;
        return countZaros(n/10,count);
    }

  //Leetcode:- 1342. Number of Steps to Reduce a Number to Zero
    public static int numberOfSteps(int num){

        if(num == 0)
            return 0;
        if(num %2 == 0)
            return 1 + numberOfSteps(num/2);
        else
            return 1 + numberOfSteps(num-1);
    }

    //Array is sorted or not

    public  static boolean sortedArray(int[] arr, int start){
        if(arr.length == start+1)
            return true;
//        if(arr[start] > arr[start+1])
//            return false;
        return (arr[start] <= arr[start+1]) && sortedArray(arr,start+1);
    }

 public static int linearsearch(int[] arr, int index, int target){
        if(index == arr.length)
            return -1;
        return arr[index] == target ? index : linearsearch(arr,index+1,target);
 }

}
