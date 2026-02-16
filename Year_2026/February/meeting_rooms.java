package February;

import java.util.Arrays;

public class meeting_rooms {
    static void main() {

    }

    static class Solution {
        static boolean canAttend(int[][] arr) {
            Arrays.sort(arr , (a , b) -> {
                if(a[0] == b[0]) return a[1] - b[1];
                return a[0] - b[0];
            });
            for(int i = 0 ; i < arr.length - 1 ; i++) if(arr[i][1] > arr[i + 1][0]) return false;
            return true;
        }
    }

}
