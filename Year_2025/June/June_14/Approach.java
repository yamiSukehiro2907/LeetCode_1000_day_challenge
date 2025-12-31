public class Approach {
    public static void main(String[] args) {

    }

    static class Solution {
        public int minMaxDifference(int num) {
            return find(num);
        }

        private int find(int num) {
            int firstNonZero = -1, firstNonNine = -1, remaining = num;
            while (remaining > 0) {
                int digit = remaining % 10;
                if (digit != 9) {
                    firstNonNine = digit;
                }
                firstNonZero = digit;
                remaining /= 10;
            }
            remaining = num;
            int min = 0, max = 0;
            int place = 1;
            while (remaining > 0) {
                int digit = remaining % 10;
                int minDigit = digit, maxDigit = digit;
                if (minDigit == firstNonZero) {
                    minDigit = 0;
                }
                if (maxDigit == firstNonNine) {
                    maxDigit = 9;
                }
                min += place * minDigit;
                max += place * maxDigit;
                place *= 10;
                remaining /= 10;
            }
            return (max - min);
        }
    }
    /*
    class Solution {
    public int minMaxDifference(int num) {
        int min = find(num , '0');
        int max = find(num , '9');
        return max - min;
    }
    private int find(int num , char toReplace){
        StringBuilder sb = new StringBuilder(Integer.toString(num));
        int index = 0;
        char digit = sb.charAt(index);
        while(index < sb.length()){
            if(sb.charAt(index) != toReplace){
                digit = sb.charAt(index);
                break;
            }
            index++;
        }
        for(int i = 0 ; i < sb.length() ;i++){
            if(sb.charAt(i) == digit){
                sb.setCharAt(i , toReplace);
            }
        }
        int ans = 0;
        for(int i = 0 ; i < sb.length() ; i++){
            ans = ans * 10 + (sb.charAt(i) - '0');
        }
        return ans;
    }
}
     */
}