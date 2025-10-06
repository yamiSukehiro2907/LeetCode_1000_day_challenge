import java.util.ArrayList;

public class GFG_2 {
    public static void main(String[] args) {
        
    }

    private static class Solution {
        private int target;
        private int maxNum;

        public ArrayList<ArrayList<Integer>> combinationSum(int n, int k) {
            this.target = n;
            this.maxNum = k;
            ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
            ArrayList<Integer> temp = new ArrayList<>();
            boolean[] visited = new boolean[10];
            solve(ans, temp, 0, visited);
            return ans;
        }

        private void solve(ArrayList<ArrayList<Integer>> ans, ArrayList<Integer> temp, int currentSum,
                boolean[] visited) {
            if (temp.size() > maxNum || currentSum > target)
                return;

            if (temp.size() == maxNum && currentSum == target) {
                ans.add(new ArrayList<>(temp));
                return;
            }

            for (int i = 1; i < 10; i++) {
                if ((temp.isEmpty() || temp.get(temp.size() - 1) < i) && !visited[i]) {
                    visited[i] = true;
                    temp.add(i);
                    solve(ans, temp, currentSum + i, visited);
                    temp.remove(temp.size() - 1);
                    visited[i] = false;
                }
            }
        }
    }
}