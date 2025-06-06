package Year_2025.June.June_6;

public class Approach {
    public static void main(String[] args) {

    }

    static class Solution {
        public String robotWithString(String str) {
            StringBuilder p = new StringBuilder();
            StringBuilder s = new StringBuilder(str);
            StringBuilder t = new StringBuilder();
            while (!s.isEmpty() && !t.isEmpty()) {
                t.append(s.charAt(0));
                s = s.deleteCharAt(0);
                
            }
            return p.toString();
        }
    }
}
