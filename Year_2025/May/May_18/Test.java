package May.May_18;

import java.util.*;

public class Test {
    static class Solution {
        private static int MOD = (int) 1e9 + 7;

        public int colorTheGrid(int row, int column) {
            Map<Integer, List<Integer>> validRow = new HashMap<>();
            int totalPossiblepairKey = (int) Math.pow(3, row); // total possible pairKey
            for (int pairKey = 0; pairKey < totalPossiblepairKey; pairKey++) {
                int copy = pairKey; // create a copy so that is does not affect main pairKey value
                List<Integer> temp = new ArrayList<>(); // a temp list containing possible combination
                for (int r = 0; r < row; r++) {
                    temp.add(copy % 3); // make sure to fill with only 0 , 1 , 2 (as there are only 3 colors)
                    copy /= 3;
                }
                boolean isValidpairKey = true;
                for (int i = 0; i < temp.size() - 1; i++) {
                    if (temp.get(i).equals(temp.get(i + 1))) {
                        isValidpairKey = false; // we got same color in a row
                        break;
                    }
                }
                if (isValidpairKey) {
                    validRow.put(pairKey, temp);
                }
            }
            // collecting possible pairs of rows that are possible
            Map<Integer, List<Integer>> adjacentValidRow = new HashMap<>();
            for (int pairKey1 : validRow.keySet()) {
                for (int pairKey2 : validRow.keySet()) {
                    boolean valid = true;
                    for (int index = 0; index < row; index++) {
                        if (validRow.get(pairKey1).get(index).equals(validRow.get(pairKey2).get(index))) {
                            valid = false;
                            break;
                        }
                    }
                    if (valid) {
                        if (adjacentValidRow.containsKey(pairKey1)) {
                            adjacentValidRow.put(pairKey1, new ArrayList<>());
                        }
                        // we put the validRow as key and all possible validRowsKey in list
                        adjacentValidRow.get(pairKey1).add(pairKey2);
                    }
                }
            }
            Map<Integer, Integer> freq = new HashMap<>();
            for (int validRowKey : validRow.keySet()) {
                freq.put(validRowKey, 1);
            }
            for (int index = 1; index < column; index++) {
                Map<Integer, Integer> temp = new HashMap<>();
                for (int validRowKey2 : validRow.keySet()) {
                    for (int validRowKey1 : adjacentValidRow.getOrDefault(validRowKey2, new ArrayList<>())) {
                        temp.put(validRowKey2,
                                (temp.getOrDefault(validRowKey2, 0) + freq.getOrDefault(validRowKey1, 0) % MOD));
                    }
                }
                freq = temp;
            }
            int totalPair = 0;
            for (int num : freq.values()) {
                totalPair = (totalPair + num) % MOD;
            }
            return totalPair;
        }
    }
}
