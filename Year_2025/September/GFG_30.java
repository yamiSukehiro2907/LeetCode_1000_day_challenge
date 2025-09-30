import java.util.ArrayList;

public class GFG_30 {

    public static void main(String[] args) {

    }

    private static class Solution {
        public ArrayList<String> binstr(int n) {
            if (n == 1) {
                ArrayList<String> temp = new ArrayList<>();
                temp.add("0");
                temp.add("1");
                return temp;
            }
            ArrayList<String> prev = binstr(n - 1);
            ArrayList<String> ans = new ArrayList<>();
            for (String k : prev)
                ans.add("0" + k);
            for (String k : prev)
                ans.add("1" + k);
            return ans;
        }
    }

}
