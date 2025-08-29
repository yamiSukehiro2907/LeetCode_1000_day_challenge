public class GFG_29 {
    public static void main(String[] args) {
        System.out.println(Solution.smallestWindow("timetopractice", "toc"));
    }

    static class Solution {
        public static String smallestWindow(String s, String p) {
            int[] freq = new int[26];
            for (char ch : p.toCharArray()) {
                freq[ch - 'a']++;
            }
            int lFinal = -1, rFinal = -1;
            int left = 0, right = 0;
            int[] temp = new int[26];
            for (int i = 0; i < s.length(); i++) {
                temp[s.charAt(i) - 'a']++;
                right++;
                if (valid(temp, freq)) {
                    System.out.println("Entered loop at index" + " " + i + ": ");
                    while (valid(temp, freq) && left < right) {
                        System.out.print("Original freq array: ");
                        print(freq);
                        System.out.print("Temp freq array: ");
                        print(temp);
                        temp[s.charAt(left++) - 'a']--;
                    }
                    temp[s.charAt(--left) - 'a']++;
                    System.out.print("Exited the loop with: ");
                    print(temp);
                    if ((lFinal == -1 && rFinal == -1) || (rFinal - lFinal > right - left)) {
                        lFinal = left;
                        rFinal = right;
                    }
                }
            }
            return lFinal == -1 ? "" : substring(s, lFinal, rFinal);
        }

        private static String substring(String s, int left, int right) {
            StringBuilder sb = new StringBuilder();
            for (int i = left; i < right; i++) {
                sb.append(s.charAt(i));
            }
            return sb.toString();
        }

        private static boolean valid(int[] temp, int[] freq) {
            for (int i = 0; i < 26; i++) {
                if (temp[i] < freq[i])
                    return false;
            }
            return true;
        }

        private static void print(int[] freq) {
            for (int i = 0; i < 26; i++) {
                int k = freq[i];
                while (k-- > 0) {
                    System.out.print((char) (i + 'a'));
                }
            }
            System.out.println();
        }
    }
}
