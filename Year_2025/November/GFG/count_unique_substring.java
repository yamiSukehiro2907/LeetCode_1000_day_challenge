public class count_unique_substring {
    public static void main(String[] args) {
        String str = "ababa";
        System.out.println(Solution.countSubs(str));
    }

    ///  [...](https://www.geeksforgeeks.org/problems/count-of-distinct-substrings)

    private static class Solution {
        public static int countSubs(String s) {
            TrieNode root = new TrieNode();
            int count = 0;
            for(int i = 0 ; i < s.length() ; i++) count += countUnique(s , i , root);
            return count;
        }

        private static int countUnique(String s , int index , TrieNode root){
            if(index >= s.length()) return 0;
            char ch = s.charAt(index);
            int count = 0;
            if(root.tries[ch - 'a'] == null){
                root.tries[ch - 'a'] = new TrieNode();
                count++;
                root.isEnd = true;
            }
            count += countUnique(s , index + 1, root.tries[ch - 'a']);
            return count;
        }

        private static class TrieNode{
            TrieNode[] tries;
            boolean isEnd;

            TrieNode(){
                this.tries = new TrieNode[26];
                this.isEnd = false;
            }
        }
    }
}
