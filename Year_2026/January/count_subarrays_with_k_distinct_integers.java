package January;

import java.util.HashMap;
import java.util.Map;

public class count_subarrays_with_k_distinct_integers {
    static void main() {

    }

    static class Solution {
        public int countAtMostK(int[] arr, int k) {
            Map<Integer , Integer> map = new HashMap<>();
            int left = 0 , count = 0;
            for(int right = 0 ; right < arr.length ; right++){
                map.put(arr[right] , map.getOrDefault(arr[right] , 0) + 1);
                while(left < right && map.size() > k){
                    map.put(arr[left] , map.get(arr[left]) - 1);
                    if(map.get(arr[left]) == 0) map.remove(arr[left]);
                    left++;
                }
                count += (right - left + 1);
            }
            return count;
        }
    }

}
