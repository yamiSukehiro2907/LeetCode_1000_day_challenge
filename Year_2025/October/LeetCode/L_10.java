public class L_10 {
    public static void main(String[] args) {

    }

    private static class Solution {
        public int maximumEnergy(int[] e, int k) {
            int n = e.length, max = -1000, sum = 0;
            for (int i = n - k; i < n; i++) {
                sum = 0;
                for (int j = i; j >= 0; j -= k) {
                    sum += e[j];
                    if (sum > max) max = sum;
                }
            }
            return max;
        }
    }
    /*
    class Solution {
    public int maximumEnergy(int[] energy, int k) {
        int n = energy.length;
        if(n == 1) return energy[0];
        boolean[] visited = new boolean[n];
        int maxSum = Integer.MIN_VALUE;
        for (int start = 0; start < n; start++) {
            if (!visited[start]) {
                visited[start] = true;
                int index = start + k;
                int sum = energy[start];
                while (index < n) {
                    if(sum > 0) sum += energy[index];
                    else sum = energy[index];
                    visited[index] = true;
                    index += k;
                }
                maxSum = Math.max(sum, maxSum);
            }
        }
        return maxSum;
    }
}
     */
}