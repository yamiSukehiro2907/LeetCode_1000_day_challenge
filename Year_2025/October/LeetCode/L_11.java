import java.util.*;
public class L_11 {
    public static void main(String[] args) {

    }

    private static class Solution {
        public long maximumTotalDamage(int[] power) {
            Arrays.sort(power);
            int n = power.length;
            long[] total = new long[n];
            long totalVal = 0;
            total[0] = power[0];
            for (int i = 1, j = 0; i < n; i++) {
                if (power[i] == power[i - 1]) total[i] = total[i - 1] + power[i];
                else {
                    while (power[j] + 2 < power[i]) totalVal = Math.max(totalVal, total[j++]);
                    total[i] = totalVal + power[i];
                }
            }
            long max = 0;
            for (long num : total) max = Math.max(max, num);
            return max;
        }
    }

    /*
    class Solution {
    public long maximumTotalDamage(int[] power) {
        Map<Integer, Num> map = new HashMap<>();
        for(int num : power){
            if(!map.containsKey(num)) map.put(num , new Num(0));
            map.get(num).value += num;
        }
        List<Integer> keys = new ArrayList<>(map.keySet());
        Collections.sort(keys);
        long maxVal = 0;
        for(int i = 0 ; i < keys.size() ; i++){
            int key = keys.get(i);
            long temp = map.get(key).value;
            int index = i - 1;
            while(index >= 0 && isInRange(keys.get(index) , key)) index--;
            if(index > -1) temp += map.get(keys.get(index)).maxValue;
            if(temp > maxVal) maxVal = temp;
            map.get(key).maxValue = maxVal;
        }
        return maxVal;
    }

    private boolean isInRange(int num , int target){
        return (num + 1 == target) || (num + 2 == target);
    }

    private class Num {
        long value;
        long totalValue;
        long maxValue;

        Num(long value) {
            this.value = value;
            this.totalValue = 0L;
            this.maxValue = 0L;
        }
    }
}
     */
}