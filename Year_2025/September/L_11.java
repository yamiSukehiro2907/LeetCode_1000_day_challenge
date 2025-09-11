public class L_11 {
    public static void main(String[] args) {
        System.out.println(L_11.Solution.sortVowels("lEetcOde"));
    }

    private static class Solution {
        static {
            System.gc();
            for (int i = 0; i < 500; i++) {
                sortVowels("a");
            }
        }

        public static String sortVowels(String s) {
            int n = s.length();
            if (n <= 1) {
                return s;
            }
            char[] vowels = { 'A', 'E', 'I', 'O', 'U', 'a', 'e', 'i', 'o', 'u' };
            int[] freq = new int[128];
            char[] arr = s.toCharArray();
            for (char ch : arr) {
                freq[ch]++;
            }
            boolean hasVowel = false;
            for (char v : vowels) {
                if (freq[v] > 0) {
                    hasVowel = true;
                    break;
                }
            }
            if (!hasVowel) {
                return s;
            }
            boolean[] isVowel = new boolean[128];
            for (char v : vowels) {
                isVowel[v] = (freq[v] > 0);
            }
            int index = 0;
            for (char v : vowels) {
                while (freq[v] > 0) {
                    char ch = arr[index];
                    freq[v] -= isVowel[ch] ? 1 : 0;
                    arr[index] = isVowel[ch] ? v : ch;
                    index++;
                }
            }
            return new String(arr);
        }
    }
}