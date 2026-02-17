package February;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class binary_watch {

    static void main() {

    }

    static class Solution {
        private List<String> possibleCombinations;

        public List<String> readBinaryWatch(int turnedOn) {
            this.possibleCombinations = new ArrayList<>();
            char[] arr = new char[10];
            Arrays.fill(arr, '0');
            generate(0, turnedOn, arr);
            return possibleCombinations;
        }

        private void generate(int index, int turnedOn, char[] arr) {
            if (turnedOn == 0) {
                String str = convert(arr);
                if (!str.isEmpty()) possibleCombinations.add(str);
                return;
            }
            if (index >= arr.length) return;
            arr[index] = '1';
            generate(index + 1, turnedOn - 1, arr);
            arr[index] = '0';
            generate(index + 1, turnedOn, arr);
        }

        private String convert(char[] arr) {
            int hours = 0, minutes = 0;
            for (int index = 0; index < arr.length; index++) {
                if (arr[index] != '0') {
                    if (index <= 3) hours += ((1 << index));
                    else minutes += ((1 << (index - 4)));
                }
            }
            if (minutes > 59 || hours > 11) return "";
            StringBuilder sb = new StringBuilder();
            sb.append(Integer.toString(hours));
            sb.append(":");
            if (minutes < 10) sb.append("0");
            sb.append(Integer.toString(minutes));
            return sb.toString();
        }
    }
}
