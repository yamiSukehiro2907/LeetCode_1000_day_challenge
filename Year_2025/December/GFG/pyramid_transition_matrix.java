package December.GFG;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class pyramid_transition_matrix {
    static void main() {
        List<String> allowed = List.of("AAB", "AAC", "BCD", "BBE", "DEF");
        String bottom = "AAAA";
        Solution sol = new Solution();
        System.out.println(sol.pyramidTransition(bottom, allowed));
    }

    static class Solution {
        private Map<String, StringBuilder> pillarTopMapping;
        private boolean found;

        public boolean pyramidTransition(String bottom, List<String> allowed) {
            this.pillarTopMapping = new HashMap<>();
            fill(allowed);
            this.found = false;
            return possible(0, bottom, new StringBuilder());
        }

        private void fill(List<String> allowed) {
            for (String s : allowed) {
                String pillar = s.substring(0, 2);
                if (!pillarTopMapping.containsKey(pillar)) pillarTopMapping.put(pillar, new StringBuilder());
                pillarTopMapping.get(pillar).append(s.charAt(2));
            }
        }

        private boolean possible(int currentBottomIndex, String currentBottom, StringBuilder nextBottom) {
            if (found) return true;
            if (currentBottomIndex == currentBottom.length() - 1) {
                if (nextBottom.length() == 1) {
                    found = true;
                    return true;
                }
                return possible(0, nextBottom.toString(), new StringBuilder());
            }
            String floor = currentBottom.substring(currentBottomIndex, currentBottomIndex + 2);
            if (!pillarTopMapping.containsKey(floor)) return false;
            String possibleTops = pillarTopMapping.get(floor).toString();
            for (char ch : possibleTops.toCharArray()) {
                nextBottom.append(ch);
                if (possible(currentBottomIndex + 1, currentBottom, nextBottom)) return true;
                nextBottom.deleteCharAt(nextBottom.length() - 1);
            }
            return false;
        }
    }

    static class OptimizedSolution {
        public boolean pyramidTransition(String bottom, List<String> allowed) {
            return new Finder(allowed, bottom.length()).possible(bottom);
        }

        private static final class Finder {
            private final int[][][] allowed;
            private final int[][] cache;

            public Finder(List<String> allowed, int length) {
                this.allowed = new int[6][6][];
                this.cache = new int[length][];
                int size = 6 * 6;
                for (int i = 3; i < length; i++) cache[i] = new int[size *= 6];
                fill(allowed);
            }

            private void fill(List<String> blocks) {
                final int[][] count = new int[6][6];
                for (String s : blocks) count[s.charAt(0) - 'A'][s.charAt(1) - 'A']++;
                for (int i = 0; i < 6; i++) for (int j = 0; j < 6; j++) this.allowed[i][j] = new int[count[i][j]];
                for (String s : blocks) {
                    final int ch1 = s.charAt(0) - 'A', ch2 = s.charAt(1) - 'A', ch3 = s.charAt(2) - 'A';
                    this.allowed[ch1][ch2][--count[ch1][ch2]] = ch3;
                }
            }

            public boolean possible(String bottom) {
                final int[] arr = new int[bottom.length()];
                for (int i = 0; i < bottom.length(); i++) arr[i] = bottom.charAt(i) - 'A';
                return bottom.length() == 2 ? allowed[arr[0]][arr[1]].length > 0 : isPossible(arr, arr.length);
            }

            private boolean isPossible(int[] arr, int length) {
                for (int i = 1; i < length; i++) if (allowed[arr[i - 1]][arr[i]].length == 0) return false;
                return isPossible(new int[length], 0, arr, 0, length);
            }

            private boolean isPossible(int[] prefix, int pLength, int[] suffix, int sIndex, int sLength) {
                if (pLength > 1 && !possible(prefix, pLength)) return false;
                final int nextSIndex = sIndex + 1;
                if (nextSIndex < sLength) {
                    for (int top : allowed[suffix[sIndex]][suffix[nextSIndex]]) {
                        prefix[pLength] = top;
                        if (isPossible(prefix, pLength + 1, suffix, sIndex + 1, sLength)) return true;
                    }
                } else return possible(prefix, pLength);
                return false;
            }

            private boolean possible(int[] prefix, int pLength) {
                if (pLength == 2) return allowed[prefix[0]][prefix[1]].length > 0;
                final var key = encode(prefix, pLength);
                final var cached = cache[pLength][key];
                if (cached != 0) return cached == 1;
                final var result = isPossible(prefix, pLength);
                cache[pLength][key] = result ? 1 : 2;
                return result;
            }

            private int encode(int[] arr, int length) {
                int r = arr[0];
                for (int i = 1; i < length; i++) r = r * 6 + arr[i];
                return r;
            }
        }
    }
}
