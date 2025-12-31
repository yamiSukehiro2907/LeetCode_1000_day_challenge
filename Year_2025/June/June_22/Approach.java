public class Approach {
    public static void main(String[] args) {

    }

    static class Solution {
        public String[] divideString(String s, int k, char fill) {
            StringBuilder str = new StringBuilder(s);
            while (str.length() % k != 0) {
                str.append(fill);
            }
            String arr[] = new String[str.length() / k];
            for (int i = 0; i < str.length() / k; i++) {
                arr[i] = str.substring(i * k, (i + 1) * k);
            }
            return arr;
        }
    }
    /*
    class Solution {
    public String[] divideString(String s, int k, char fill) {
        int n = s.length();
        int partition = n / k;
        if(n % k != 0) partition++;
        String[] ans = new String[partition];
        int index = 0;
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < n ; i+=k){
            sb = new StringBuilder(s.substring(i , Math.min(n , i + k)));
            if(sb.length() == k) ans[index++] = sb.toString();
        }
        if(sb.length() < k){
            while(sb.length() != k) sb.append(fill);
            ans[index] = sb.toString();
        }
        return ans;
    }
}
     */

}
