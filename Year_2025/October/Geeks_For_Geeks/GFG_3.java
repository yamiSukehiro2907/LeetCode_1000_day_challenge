package Geeks_For_Geeks;

import java.util.*;

public class GFG_3 {
    public static void main(String[] args) {
        System.out.println(Solution.possibleWords(new int[] { 0, 2, 2 }));
    }

    private static class Solution {
        private static final char[][] map;

        static {
            map = new char[8][];
            map[0] = new char[] { 'a', 'b', 'c' };
            map[1] = new char[] { 'd', 'e', 'f' };
            map[2] = new char[] { 'g', 'h', 'i' };
            map[3] = new char[] { 'j', 'k', 'l' };
            map[4] = new char[] { 'm', 'n', 'o' };
            map[5] = new char[] { 'p', 'q', 'r', 's' };
            map[6] = new char[] { 't', 'u', 'v' };
            map[7] = new char[] { 'w', 'x', 'y', 'z' };
        }
        private static int[] arr;
        private static ArrayList<String> ans;

        public static ArrayList<String> possibleWords(int[] arr2) {
            int[] arr1 = moveLeft(arr2); // remove all 1s and 0s
            arr = arr1;
            ans = new ArrayList<>();
            StringBuilder sb = new StringBuilder();
            boolean[][] visited = new boolean[arr.length][];
            fill(visited);
            solve(sb, visited, 0);
            return ans;
        }

        private static int[] moveLeft(int[] arr1) {
            int index = 0;
            for (int i = 0; i < arr1.length; i++) {
                if (arr1[i] == 0 || arr1[i] == 1) {
                } else
                    arr1[index++] = arr1[i];
            }
            return Arrays.copyOf(arr1, index);
        }

        private static void solve(StringBuilder sb, boolean[][] visited, int index) {
            if (index >= arr.length && sb.length() == arr.length) {
                ans.add(sb.toString());
                return;
            }
            for (int i = index; i < arr.length; i++) {
                for (int j = 0; j < visited[i].length; j++) {
                    if (visited[i][j]) // if this line processed break
                        break;
                    sb.append(map[arr[i] - 2][j]);
                    visited[i][j] = true;
                    solve(sb, visited, i + 1);
                    sb.setLength(sb.length() - 1);
                    visited[i][j] = false;
                }
            }
        }

        private static void fill(boolean[][] visited) {
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == 7 || arr[i] == 9)
                    visited[i] = new boolean[4];
                else
                    visited[i] = new boolean[3];
            }
        }
    }
}
