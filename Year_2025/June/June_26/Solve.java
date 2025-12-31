public class Solve {
    public static void main(String[] args) {

    }

    static class Solution {
        public int longestSubsequence(String s, int k) {
            int total_sum = 0;
            int count = 0;
            int total_bits = (int) (Math.log(k) / Math.log(2)) + 1;
            for (int i = 0; i < s.length(); i++) {
                char ch = s.charAt(s.length() - 1 - i);
                if (ch == '1') {
                    if (i < total_bits && total_sum + (1 << i) <= k) { // if adding 2 ^ i does not exceed k
                        total_sum += 1 << i;
                        count++;
                    }
                } else {
                    count++;
                }
            }
            return count;
        }
    }
    /*
    class Solution {
    public int longestSubsequence(String s, int k) {
        int ans = 0 , index = 0;
        while(index < s.length() && s.charAt(index) == '0'){
            index++;
            ans++;
        }
        if(index >= s.length()) return s.length();
        StringBuilder sb = new StringBuilder();
        while(index < s.length()) sb.append(s.charAt(index++));
        index = 0;
        while(index < sb.length()){
            if(sb.charAt(index) == '0') index++;
            else{
                if(keep(sb , k))return ans + sb.length();
                else sb.deleteCharAt(index);
            }
        }
        return sb.length() + ans;
    }
    private boolean keep(StringBuilder sb , int k){
        return value(sb.toString()) <= k;
    }

    private int value(String s){
        int index = s.length() - 1;
        long val = 0;
        int place = 0;
        while(index >= 0){
            val = val + (long)((s.charAt(index--) - '0') * (long)(Math.pow(2 , place++)));
            if(val > Integer.MAX_VALUE) return Integer.MAX_VALUE;
        }
        return (int)val;
    }
}
     */
}
