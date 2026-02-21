package February;

import java.util.ArrayList;
import java.util.List;

public class form_the_largest_number {
   static void main(String[] args){
      
   }

   static class Solution {
    public String findLargest(int[] arr) {
        List<Integer> list = new ArrayList<>();
        for(int num : arr) list.add(num);
        list.sort((a, b) -> {
            String s1 = a + Integer.toString(b);
            String s2 = b + Integer.toString(a);
            return s2.compareTo(s1);
        });
        StringBuilder sb = new StringBuilder();
        for(int num : list) sb.append(num);
        if(sb.charAt(0) == '0') return "0";
        return sb.toString();
    }
}
}
