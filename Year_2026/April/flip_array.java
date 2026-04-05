package April;

public class flip_array {
    static void main() {

    }

    static class Solution {
        private int[] arr;
        private NodeData[][] dp;
        private int totalSum;

        public int solve(int[] arr) {
            this.arr = arr;
            this.totalSum = 0;
            for (int num : arr) totalSum += num;
            this.dp = new NodeData[arr.length][2 * totalSum + 1];
            return find(0, 0).flip;
        }

        private NodeData find(int index, int currentSum) {
            if (index == arr.length) {
                if (currentSum >= 0) return new NodeData(currentSum, 0);
                return new NodeData(Integer.MAX_VALUE, Integer.MAX_VALUE);
            }
            int sumIndex = currentSum + totalSum;
            if (dp[index][sumIndex] != null) return dp[index][sumIndex];
            NodeData noFlip = find(index + 1, currentSum + arr[index]);
            NodeData temp = find(index + 1, currentSum - arr[index]);
            NodeData flip = new NodeData(temp.sum, temp.flip != Integer.MAX_VALUE ? temp.flip + 1 : Integer.MAX_VALUE);
            if (flip.sum < noFlip.sum) return dp[index][sumIndex] = flip;
            else if (noFlip.sum < flip.sum) return dp[index][sumIndex] = noFlip;
            else return dp[index][sumIndex] = (flip.flip < noFlip.flip) ? flip : noFlip;
        }

        private static class NodeData {
            int sum;
            int flip;

            NodeData(int sum, int flip) {
                this.sum = sum;
                this.flip = flip;
            }
        }
    }
}
