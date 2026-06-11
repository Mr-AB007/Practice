package recursion;

public class Runner {
    public static void main(String[] args) {
        //System.out.println(Recursion.fibonaci(7)); //13

        int[] arr = {1,3,7,9,10,16,98,700};
        System.out.println(Recursion.binarySearch(arr,10,0,arr.length -1));
    }
}
