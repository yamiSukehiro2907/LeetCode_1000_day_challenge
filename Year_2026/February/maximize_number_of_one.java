package February;

public class maximize_number_of_one {
  public static void main(Stirng[] args){
    Solution solution = new Solution();
    int[] arr = {1 , 1 , 0 , 0 , 1};
    int k = 2;
    System.out.println(solution.maxOnes(arr , k));
  }
  static class class Solution {
    public int maxOnes(int arr[], int k) {
        int maxOne = 0;
        int zeroCount  = 0;
        int oneCount = 0;
        int left = -1;
        for(int right = 0 ; right < arr.length ; right++){
            if(arr[right] == 0) zeroCount++;
            else oneCount++;
            while(zeroCount > k && left < right){
                left++;
                if(arr[left] == 0) zeroCount--;
                else oneCount--;
            }
            int distance = right - left;
            if(distance > maxOne) maxOne = distance;
        }
        return maxOne;
    }
}
}
