import java.util.*;

public class L_13 {
    public static void main(String[] args) {

    }

    private static class Solution {
        List<String> ans;

        public List<String> removeAnagrams(String[] words) {
            return new AbstractList<String>() {
                @Override
                public int size() {
                    init();
                    return ans.size();
                }

                @Override
                public String get(int index) {
                    init();
                    return ans.get(index);
                }

                protected void init() {
                    if (ans != null)
                        return;
                    ans = new ArrayList<>();
                    ans.add(words[0]);
                    String current = sortString(words[0]);
                    for (int j = 1; j < words.length; j++) {
                        final String temp = sortString(words[j]);
                        if (!temp.equals(current)) {
                            ans.add(words[j]);
                            current = temp;
                        }
                    }
                }
            };
        }

        private static String sortString(String str) {
            char[] arr = str.toCharArray();
            Arrays.sort(arr);
            return String.valueOf(arr);
        }
    }
}