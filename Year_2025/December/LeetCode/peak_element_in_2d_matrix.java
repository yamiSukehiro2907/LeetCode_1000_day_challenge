package December.LeetCode;

import java.util.ArrayList;

public class peak_element_in_2d_matrix {
    static void main() {
        int[][] mat = {{10 , 15 , 12} , {30 , 16 , 32} , {34 , 2 , 4}};
        Solution solution = new Solution();
        ArrayList<Integer> coordinates = solution.findPeakGrid(mat);
        System.out.println(coordinates);
    }

    static class Solution {
        public ArrayList<Integer> findPeakGrid(int[][] mat) {
            int n = mat.length , m = mat[0].length;
            for(int i = 0 ; i < n ; i++){
                int start = 0 , end = m - 1;
                int index = -1;
                while(start <= end){
                    int mid = start + ((end - start) >> 1);
                    int left = (mid > 0) ? mat[i][mid - 1] : Integer.MIN_VALUE;
                    int right = (mid < m - 1) ? mat[i][mid + 1] : Integer.MIN_VALUE;
                    int max = Math.max(left , right);
                    if(mat[i][mid] >= max){
                        index = mid;
                        break;
                    }else if(mat[i][mid] <= left) end = mid - 1;
                    else start = mid + 1;
                }
                if(index != -1){
                    int top = (i > 0) ? mat[i - 1][index] : Integer.MIN_VALUE;
                    int bottom = (i < n - 1) ? mat[i + 1][index] : Integer.MIN_VALUE;
                    int max = Math.max(top , bottom);
                    if(mat[i][index] >= max){
                        ArrayList<Integer> temp = new ArrayList<>();
                        temp.add(i);
                        temp.add(index);
                        return temp;
                    }
                }
            }
            return new ArrayList<>();
        }
    }
}
