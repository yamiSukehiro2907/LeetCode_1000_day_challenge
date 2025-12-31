package May.May_12;

import java.util.ArrayList;

public class Solve {
    public static void main(String[] args) {

    }

    static class Solution {
        public int[] findEvenNumbers(int[] digits) {
            ArrayList<Integer> list = new ArrayList<>();
            for (int i = 100; i <= 999; i++) {
                int[] freq = new int[10];
                for (int num : digits)
                    freq[num]++;
                boolean possible = true;
                int num = i;
                while (num > 0) {
                    int rem = num % 10;
                    if (freq[rem] == 0) {
                        possible = false;
                        break;
                    } else {
                        freq[rem]--;
                    }
                    num /= 10;
                }
                if (possible) {
                    list.add(i);
                }
            }
            int[] result = new int[list.size()];
            for (int i = 0; i < list.size(); i++) {
                result[i] = list.get(i);
            }
            return result;
        }
    }
}
