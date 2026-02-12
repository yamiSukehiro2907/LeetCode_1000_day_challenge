package February;
import java.util.*;

public class max_min_height {

    static void main() {
        int[] arr = {2 , 3 , 4 , 5,1};
        int k = 2;
        int w = 2;
        Solution solution = new Solution();
        System.out.println(solution.solve(arr , k , w));
    }

    static class Solution {
        private int[] window;
        private int[] updatedHeights;

        public int solve(int[] arr, int k , int w){
            this.updatedHeights = new int[arr.length];
            this.window = new int[arr.length];
            int start = arr[0];
            int end = arr[0] + k;
            for(int num : arr){
                if(start > num) start = num;
                if(end < num + k) end = num + k;
            }
            System.out.println("BASEHEIGHT: " + Arrays.toString(arr));
            int ans = start;
            while(start <= end){
                int mid = start + ((end - start) >> 1);
                System.out.println("Checking for mid: " + mid);
                if(possible(arr , k , w , mid)){
                    start = mid + 1;
                    ans = mid;
                }else end = mid - 1;
            }
            return ans;
        }
        public boolean possible(int[] baseHeight , int maxUpdates , int windowUpdate , int height){
            for(int i = 0 ; i < baseHeight.length ; i++) {
                updatedHeights[i] = 0;
                window[i] = 0;
            }
            for(int i = 0 ; i < baseHeight.length ; i++){
                window[i] += (i > 0 ? window[i - 1] : 0);
                int prevUpdate = window[i];
                updatedHeights[i] = prevUpdate + baseHeight[i];
                if(updatedHeights[i] < height){
                    int toIncrease = height - updatedHeights[i];
                    window[i] += toIncrease;
                    if(i + windowUpdate < baseHeight.length){
                        window[i + windowUpdate] -= toIncrease;
                    }
                    updatedHeights[i] += toIncrease;
                    maxUpdates -= toIncrease;
                }
                System.out.println("Updated Height: " +  Arrays.toString(updatedHeights));
                System.out.println("WINDOW: " + Arrays.toString(window));
            }
            return maxUpdates >= 0;
        }
    }
}
