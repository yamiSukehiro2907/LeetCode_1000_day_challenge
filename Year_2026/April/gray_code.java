package April;

import java.util.ArrayList;
import java.util.Collections;

public class gray_code {
    static void main() {

    }

    static class Solution {
        public ArrayList<String> graycode(int n) {
            ArrayList<String> temp = new ArrayList<>();
            if (n == 1) {
                temp.add("0");
                temp.add("1");
                return temp;
            }
            ArrayList<String> prev = graycode(n - 1);
            for (String s : prev) temp.add("0" + s);
            Collections.reverse(prev);
            for (String s : prev) temp.add("1" + s);
            return temp;
        }
    }
}
