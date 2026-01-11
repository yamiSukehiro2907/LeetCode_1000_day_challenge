package January;

import java.util.Arrays;
import java.util.Stack;

public class max_rectangle {

    static void main() {

    }

    static class Solution {
        static int maxArea(int[][] mat) {
            int height = mat.length, width = mat[0].length;
            int[] heights = new int[width];
            int maxArea = 0;
            for (int[] ints : mat) {
                for (int j = 0; j < width; j++) {
                    if (ints[j] == 0) heights[j] = 0;
                    else heights[j]++;
                }
                int area = getMaxArea(heights);
                if (area > maxArea) maxArea = area;
            }
            return maxArea;
        }

        static int getMaxArea(int[] heights) {
            Stack<Integer> stack = new Stack<>();
            int maxArea = 0;
            for (int i = 0; i < heights.length; i++) {
                while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                    int height = heights[stack.pop()];
                    int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                    maxArea = Math.max(maxArea, width * height);
                }
                stack.push(i);
            }
            while (!stack.isEmpty()) {
                int height = heights[stack.pop()];
                int width = stack.isEmpty() ? heights.length : heights.length - stack.peek() - 1;
                maxArea = Math.max(maxArea, width * height);
            }
            return maxArea;
        }
    }

    static class Solution2 {
        public int maximalRectangle(char[][] matrix) {
            int height = matrix.length, width = matrix[0].length;
            int[] heights = new int[width];
            int[] left = new int[width];
            int[] right = new int[width];
            int maxArea = 0;
            Arrays.fill(right, width);
            for (char[] row : matrix) {
                updateHeightAndLeft(row, heights, left);
                updateRight(row, heights, right);
                maxArea = findArea(heights, left, right, maxArea);
            }
            return maxArea;
        }

        private void updateHeightAndLeft(char[] row, int[] heights, int[] left) {
            int leftBoundary = 0;
            for (int i = 0; i < row.length; i++) {
                if (row[i] == '0') {
                    leftBoundary = i + 1;
                    left[i] = 0;
                    heights[i] = 0;
                } else {
                    heights[i]++;
                    left[i] = Math.max(left[i], leftBoundary);
                }
            }
        }

        private void updateRight(char[] row, int[] heights, int[] right) {
            int rightBoundary = row.length;
            for (int i = row.length - 1 ; i >= 0 ; i--) {
                if (row[i] == '0') {
                    right[i] = rightBoundary;
                    rightBoundary = i;
                } else right[i] = Math.min(right[i], rightBoundary);
            }
        }

        private int findArea(int[] heights, int[] left, int[] right, int maxArea) {
            for (int i = 0; i < heights.length; i++) maxArea = Math.max(maxArea, (right[i] - left[i]) * heights[i]);
            return maxArea;
        }
    }
}
