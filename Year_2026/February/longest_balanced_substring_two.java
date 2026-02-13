package February;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class longest_balanced_substring_two {
    static void main() {

    }

    static public class Solution {
        private char[] arr;
        private static final int update = 1_000_000;

        public int longestBalanced(String s) {
            this.arr = s.toCharArray();
            if (arr.length < 1000) {
                int length = getLength();
                int maxLengthAB = find('a', 'b');
                if (length < maxLengthAB) length = maxLengthAB;
                int maxLengthBC = find('b', 'c');
                if (length < maxLengthBC) length = maxLengthBC;
                int maxLengthAC = find('a', 'c');
                if (length < maxLengthAC) length = maxLengthAC;
                int maxLengthABC = find();
                if (length < maxLengthABC) length = maxLengthABC;
                return length;
            }
            ExecutorService executor = Executors.newFixedThreadPool(4);
            try {
                Future<Integer> lengthTask = executor.submit(this::getLength);
                Future<Integer> abTask = executor.submit(() -> find('a', 'b'));
                Future<Integer> bcTask = executor.submit(() -> find('b', 'c'));
                Future<Integer> acTask = executor.submit(() -> find('a', 'c'));
                Future<Integer> abcTask = executor.submit(() -> find());
                int maxLength = lengthTask.get();
                maxLength = Math.max(maxLength, abTask.get());
                maxLength = Math.max(maxLength, bcTask.get());
                maxLength = Math.max(maxLength, acTask.get());
                maxLength = Math.max(maxLength, abcTask.get());
                return maxLength;
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException("Error: ", e);
            } finally {
                executor.shutdown();
            }
        }

        private int getLength() {
            int maxA = 0, maxB = 0, maxC = 0;
            int currA = 0, currB = 0, currC = 0;
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == 'a') {
                    currA = ((i > 0 && arr[i - 1] == 'a') ? currA : 0) + 1;
                    if (maxA < currA) maxA = currA;
                } else if (arr[i] == 'b') {
                    currB = ((i > 0 && arr[i - 1] == 'b') ? currB : 0) + 1;
                    if (maxB < currB) maxB = currB;
                } else {
                    currC = ((i > 0 && arr[i - 1] == 'c') ? currC : 0) + 1;
                    if (maxC < currC) maxC = currC;
                }
            }
            return Math.max(maxA, Math.max(maxB, maxC));
        }

        private int find(char ch1, char ch2) {
            int n = arr.length;
            int maxLength = 0;
            int[] freq = new int[(n << 1) + 1];
            Arrays.fill(freq, -2);
            int clear_index = -1, diff = n;
            freq[diff] = -1;
            for (int i = 0; i < n; i++) {
                if (arr[i] != ch1 && arr[i] != ch2) {
                    clear_index = i;
                    diff = n;
                    freq[diff] = clear_index;
                } else {
                    if (arr[i] == ch1) diff++;
                    else diff--;
                    if (freq[diff] < clear_index) freq[diff] = i;
                    else maxLength = Math.max(maxLength, i - freq[diff]);
                }
            }
            return maxLength;
        }

        private int find() {
            long state = Long.MAX_VALUE >> 1L;
            Map<Long, Integer> map = new HashMap<>();
            map.put(state, -1);
            int maxLength = 0;
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == 'a') state += update + 1;
                else if (arr[i] == 'b') state -= update;
                else state--;
                if (map.containsKey(state)) {
                    int length = i - map.get(state);
                    if (maxLength < length) maxLength = length;
                } else map.put(state, i);
            }
            return maxLength;
        }
    }
}
