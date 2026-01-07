package January;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class count_distinct_elements {

    static void main() {
        int[] arr = {1 , 2 , 1 , 3 , 4 ,2, 3};
        int k = 4;
        Solution solution = new Solution();
        ArrayList<Integer> list = solution.countDistinct(arr, k);
        System.out.println(list);
    }

    static class Solution {
        ArrayList<Integer> countDistinct(int[] arr, int k) {
            int maxElement = arr[0];
            for(int num : arr) if(num > maxElement) maxElement = num;
            int[] freq = new int[maxElement + 1];
            int uniqueCount = 0;
            for(int i = 0 ; i < k ; i++){
                if(freq[arr[i]] == 0) uniqueCount++;
                freq[arr[i]]++;
            }
            ArrayList<Integer> list = new ArrayList<>();
            list.add(uniqueCount);
            for(int i = k ; i < arr.length ; i++){
                if(arr[i] != arr[i - k]){
                    freq[arr[i - k]]--;
                    if(freq[arr[i - k]] == 0) uniqueCount--;
                    if(freq[arr[i]] == 0) uniqueCount++;
                    freq[arr[i]]++;
                }
                list.add(uniqueCount);
            }
            return list;
        }
    }
}
