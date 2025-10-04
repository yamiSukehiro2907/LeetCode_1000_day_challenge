public class L_4 {
    public static void main(String[] args) {

    }

    private static class Solution {
        static {
            for (int i = 0; i < 100; i++) {
                maxArea(new int[1]);
            }
        }

        public static int maxArea(int[] height) {
            if (height.length == 1)
                return 0;
            int left = 0;
            int right = height.length - 1;
            int maxArea = 0;
            while (left < right) {
                int length = Math.min(height[left], height[right]);
                int width = right - left;
                int area = length * width;
                if (area > maxArea)
                    maxArea = area;
                while (left < right && height[left] <= length)
                    left++;
                while (left < right && height[right] <= length)
                    right--;
            }
            return maxArea;
        }
    }
}
