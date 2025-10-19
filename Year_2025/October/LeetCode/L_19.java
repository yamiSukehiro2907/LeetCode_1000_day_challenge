public class L_19 {
    public static void main(String[] args) {

    }

    private static class Solution {
        public String findLexSmallestString(String s, int a, int b) {
            char[] arr = s.toCharArray();
            int n = s.length();
            char[] temp = new char[n]; // to store changes
            int step = gcd(b, n); // positions where rotation can happen
            int toAdd = gcd(a, 10); // digits possible after adding a
            String ans = s;
            for (int i = 0; i < n; i += step) {
                copyArr(temp, arr, i, 0, n - i); // copying left half
                copyArr(temp, arr, 0, n - i, i); // copying right half in short rotating by point i
                change(temp, 1, toAdd); // change from odd indice that is 1
                if (step % 2 == 1) change(temp, 0, toAdd); // if step odd means indices pos are changed so even indices also possible
                String str = new String(temp); // final small string
                if (str == null || str.compareTo(ans) < 0) ans = str;
            }
            return ans;
        }

        private void copyArr(char[] temp, char[] arr, int arrPosStart, int tempPosStart, int length) {
            int index = 0;
            while (index++ < length) temp[tempPosStart++] = arr[arrPosStart++];
        }

        private int gcd(int divisor, int dividend) {
            return divisor == 0 ? dividend : gcd(dividend % divisor, divisor);
        }

        private void change(char[] temp, int start, int toAdd) {
            int ch = temp[start] - '0';
            int k = ch % toAdd - ch + 10; // minimum possible addition to this number to get negative
            for (int i = start; i < temp.length; i += 2) temp[i] = (char) ('0' + (temp[i] - '0' + k) % 10);
        }
    }
    /*
    class Solution {
    private Set<String> set;
    public String findLexSmallestString(String s, int a, int b) {
        this.set = new HashSet<>();
        fill(s , a , b);
        List<String> nums = new ArrayList<>(set);
        Collections.sort(nums);
        return nums.get(0);
    }
    private void fill(String sb , int a , int b){
        if(set.contains(sb)) return;
        set.add(sb);
        String reversed = reverse(sb , b);
        fill(reversed , a , b);
        String changeDigit = changeDigit(sb , a);
        fill(changeDigit , a , b);
    }
    private String reverse(String s , int b){
        StringBuilder sb = new StringBuilder();
        int index = s.length() - b;
        for(int i = index ; i < s.length() ; i++) sb.append(s.charAt(i));
        for(int i = 0 ; i < index ; i++) sb.append(s.charAt(i));
        return sb.toString();
    }

    private String changeDigit(String s , int a){
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < s.length() ; i++){
            if(i % 2 != 0) sb.append(((s.charAt(i) - '0') + a) % 10);
            else sb.append(s.charAt(i));
        }
        return sb.toString();
    }
}
     */
}