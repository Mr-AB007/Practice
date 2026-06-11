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
}
