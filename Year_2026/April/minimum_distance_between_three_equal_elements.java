package April;

public class minimum_distance_between_three_equal_elements {
    static void main() {

    }

    static class Solution {
        private final static int[][] arr = new int[1_00_001][2];

        public int minimumDistance(int[] nums) {
            int n = nums.length;
            for (int i = 0; i <= n; i++) {
                arr[i][0] = -1;
                arr[i][1] = -1;
            }
            int minDis = n + 1;
            for (int i = 0; i < n; i++) {
                int num = nums[i];
                if (arr[num][0] != -1) {
                    int dis = i - arr[num][0];
                    if (dis < minDis) minDis = dis;
                    if (minDis == 2) break;
                }
                arr[num][0] = arr[num][1];
                arr[num][1] = i;
            }
            return minDis == n + 1 ? -1 : 2 * minDis;
        }
    }
}
