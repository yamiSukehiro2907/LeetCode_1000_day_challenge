package February;

public class max_sum_in_configuration{
   public static void main(String[] args){
     
   }
   static class Solution {
      int maxSum(int[] arr) {
         int n = arr.length , max = 0 , currSum = 0 , arrSum = 0;
         for (int i = 0; i < n; i++) {
             arrSum += arr[i];
             currSum += (i * arr[i]);
         }
         max = currSum;
         for (int i = 1; i < n; i++) {
             currSum += arrSum - n * arr[n - i];
             if(currSum > max) max = currSum;
         }
         // when the array rotates all elements shifts by one place
         // so all values contribution increases by their value (1 * arr[i])
         // except for the last one as it's contribution decreases to zero
         // equation comes to be 
         // f(x) = f(x - 1) + (arraySum - arr[lastElement]) - ((n - 1 ) * arr[lastElement])
         return max;
      }
   }
}
