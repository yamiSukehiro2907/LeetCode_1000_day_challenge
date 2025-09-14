import java.util.*;

public class L_14 {
    public static void main(String[] args) {
        String[] wordlist = {};
        String[] queries = {};
        System.out.println(Arrays.toString(Solution.spellchecker(wordlist, queries)));
    }

    private static class Solution {
        static {
            System.gc();
            for (int i = 0; i < 100; i++) {
                spellchecker(new String[] {}, new String[] {});
            }
        }

        public static String[] spellchecker(String[] wordlist, String[] queries) {
            int m = wordlist.length, n = queries.length;
            if (n == 0)
                return queries;
            Set<String> same = new HashSet<>();
            Map<String, Integer> vowelChanged = new HashMap<>();
            Map<String, Integer> firstOccurrence = new HashMap<>();
            for (int i = m - 1; i >= 0; i--) {
                String word = wordlist[i];
                same.add(word); // exact same word
                word = word.toLowerCase();
                firstOccurrence.put(word, i); // first occurrence of lowercased
                char[] arr = word.toCharArray();
                for (int j = 0; j < arr.length; j++) {
                    if (arr[j] == 'e' || arr[j] == 'i' || arr[j] == 'o' || arr[j] == 'u') {
                        arr[j] = 'a';
                    }
                }
                vowelChanged.put(new String(arr), i);
            }
            for (int i = 0; i < n; i++) {
                String word = queries[i];

                // Case-1 exact same word
                if (same.contains(word)) {
                    continue;
                }
                queries[i] = "";

                // Case-2 capitalization
                word = word.toLowerCase();
                if (firstOccurrence.containsKey(word)) {
                    queries[i] = wordlist[firstOccurrence.get(word)];
                    continue;
                }

                // Case-3 vowel changed
                char[] arr = word.toCharArray();
                for (int j = 0; j < arr.length; j++) {
                    if (arr[j] == 'e' || arr[j] == 'i' || arr[j] == 'o' || arr[j] == 'u') {
                        arr[j] = 'a';
                    }
                }
                String newWord = new String(arr);
                if (vowelChanged.containsKey(newWord)) {
                    queries[i] = wordlist[vowelChanged.get(newWord)];
                    continue;
                }

                queries[i] = "";
            }
            return queries;
        }
    }
    /*
    class Solution {
    public String[] spellchecker(String[] wordlist, String[] queries) {
        int m = wordlist.length;
        int n = queries.length;
        Map<String, String> map = new HashMap<>();
        Set<String> set = new HashSet<>();
        for (String word : wordlist) {
            String l = word.toLowerCase();
            if (!map.containsKey(l)) {
                map.put(l, word);
            }
            set.add(word);
        }
        for (int i = 0; i < n; i++) {
            String word = queries[i];
            String l = word.toLowerCase();
            queries[i] = "";
            if (set.contains(word)) {
                queries[i] = word;
            } else if (map.containsKey(l)) {
                queries[i] = map.get(l);
            } else {
                for(int j = 0 ; j < m ; j++){
                    if(check(l , wordlist[j].toLowerCase())){
                        queries[i] = wordlist[j];
                        break;
                    }
                }
            }
        }
        return queries;
    }

    private boolean check(String word, String target) {
        int wLength = word.length();
        int tLength = target.length();
        if (wLength != tLength) {
            return false;
        }
        for (int i = 0; i < wLength; i++) {
            char wCh = word.charAt(i);
            char tCh = target.charAt(i);
            boolean w = isVowel(wCh);
            boolean t = isVowel(tCh);
            if ((!w && !t) && wCh != tCh) {
                return false; // both consonant and not equal
            }
            if ((!w && t) || (w && !t)) {
                return false; // one vowel , one consonant
            }
        }
        return true;
    }

    private boolean isVowel(char ch) {
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u';
    }
}
     */
}
