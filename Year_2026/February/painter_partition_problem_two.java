package February;

public class painter_partition_problem_two {
    static void main() {
        int[] arr = {5, 10, 30, 20, 15};
        int k = 3;
        Solution  solution = new Solution();
        System.out.println(solution.minTime(arr , k));
    }

    static class Solution {
        public int minTime(int[] arr, int k) {
            int start = arr[0];
            int end = 0;
            for (int num : arr) {
                if (num > start) start = num;
                end += num;
            }
            int ans = start;
            while (start <= end) {
                int mid = start + ((end - start) >> 1);
                if (possible(mid, arr, k)) {
                    end = mid - 1;
                    ans = mid;
                } else start = mid + 1;
            }
            return start;
        }

        private boolean possible(int time, int[] arr, int painter) {
            int currentPainterTimeUsed = 0;
            for (int num : arr) {
                if (num + currentPainterTimeUsed <= time) {
                    currentPainterTimeUsed += num;
                } else {
                    currentPainterTimeUsed = num;
                    painter--;
                }
                if (painter < 0) return false;
            }
            return painter >= 1;
        }
    }

}
