package February;

public class find_kth_rotation {
   public static void main(String[] args){
      
   }
   static class Solution {
    public int findKRotation(int arr[]) {
        int n = arr.length;
        int start = 0 , end = n - 1;
        while(start <= end){
            int mid = start + ((end - start) >> 1);
            if(arr[mid] <= arr[n - 1]) end = mid - 1; // we are in right part of rotated sorted array
            else start = mid + 1; // this may be peak element so we return start to get the kth rotation
        }
        return start;
    }
}
}
