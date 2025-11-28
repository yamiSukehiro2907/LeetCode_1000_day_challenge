import java.util.ArrayList;

public class subset_xor {
    public static void main(String[] args) {
        System.out.println(Solution.subsetXOR(4));
    }

    private static class Solution {
        public static ArrayList<Integer> subsetXOR(int n) {
            int xor = 0;
            ArrayList<Integer> ans = new ArrayList<>();
            for (int i = 1; i <= n; i++) {
                xor ^= i;
                ans.add(i);
            }
            if (xor == n) return ans;
            int k = xor ^ n;
            ans.remove(Integer.valueOf(k));
            return ans;
        }
    }
}

