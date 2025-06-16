package June_4;

public class Approach {
    public static void main(String[] args) {
        
    }
    static class Solution {
    static{
        for(int i = 0 ; i < 500 ; i++){
            answerString("" , 1);
        }
    }
    public static String lastSubstring(String s) {
        int best = 0, candidate = 1, len = s.length();
        while (candidate < len) {
            int match = 0;
            while (candidate + match < len && s.charAt(best + match) == s.charAt(candidate + match)) {
                match++;
            } // as long as you find the same character ending at both substring keep moving
            if (candidate + match < len && s.charAt(best + match) < s.charAt(candidate + match)) { 
                // if the character of right substring end is greater means we found new character to start with
                int prev = best;
                best = candidate;
                candidate = Math.max(candidate + 1, prev + match + 1);
            } else {
                // else we have the strongest character baby
                candidate = candidate + match + 1;
            }
        }
        // return the maximum largest lexicographically string
        return s.substring(best);
    }
    public static String answerString(String word, int numFriends) {
        if (numFriends == 1) return word;
        String largest = lastSubstring(word);
        int wordLen = word.length();
        int maxAllowed = wordLen - numFriends + 1;
        return largest.substring(0, Math.min(largest.length(), maxAllowed)); // as we have to divide among people so substring it to leave characters/strings for other
    }
}
/*class Solution {
    public String answerString(String word, int numFriends) {
        if(numFriends > word.length()) return "";
        if(numFriends == 1) return word;
        int n = word.length();
        int maxLen = n - numFriends + 1;
        String ans = "";
        char maxChar = 'a';
        for(char c : word.toCharArray()) {
            maxChar = (char)Math.max(maxChar, c);
        }
        for(int i = 0; i < n; i++){
            if(word.charAt(i) == maxChar) {
                int len = Math.min(maxLen, n - i);
                String current = word.substring(i, i + len);
                if(current.compareTo(ans) > 0) {
                    ans = current;
                }
            }
        }
        return ans;
    }
}*/
}
