import java.util.*;

public class August_11 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "gfgforgeeks";
        System.out.println(solution.maxSum(s));
    }

    static class Solution {
        public int maxSum(String s) {
            int n = s.length();
            List<Pair> list = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                int left = i - 1;
                int right = i + 1;
                while (left >= 0 && right < n) {
                    if (s.charAt(left) == s.charAt(right)) {
                        list.add(new Pair(left, right));
                        left--;
                        right++;
                    } else {
                        break;
                    }
                }
            }
            Collections.sort(list, (a, b) -> a.start - b.start);
            int max = 2;
            for (int i = 0; i < list.size(); i++) {
                Pair temp = list.get(i);
                int s1 = temp.start;
                int e1 = temp.end;
                System.out.println("Current Pair: start:" + s1 + " , end: " + e1);
                int index = bs(e1, list);
                System.out.println("Index: " + index);
                int sum = e1 - s1 + 1;
                if (index == -1) {
                    sum++;
                    if (sum > max) {
                        max = sum;
                    }
                } else {
                    for (int j = index; j < list.size(); j++) {
                        Pair temp2 = list.get(j);
                        int s2 = temp2.start;
                        int e2 = temp2.end;
                        max = Math.max(max, sum + e2 - s2 + 1);
                    }
                }
            }
            return max;
        }

        private int bs(int target, List<Pair> list) {
            int start = 0;
            int end = list.size() - 1;
            int ans = -1;
            while (start <= end) {
                int mid = start + (end - start) / 2;
                if (list.get(mid).start > target) {
                    ans = mid;
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }
            return ans;

        }

        private class Pair {
            int start;
            int end;

            public Pair(int start, int end) {
                this.start = start;
                this.end = end;
            }
        }
    }

}
