package April;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class huffman_encoding {
    static void main() {
        String str = "qznwdumxisktyh";
        int[] freq = {21, 57, 10, 98, 60, 56, 5, 60, 81, 49, 20, 2, 7, 58};
        Solution obj = new Solution();
        System.out.println(obj.huffmanCodes(str, freq));
    }

    static class Solution {
        private ArrayList<String> ans;

        public ArrayList<String> huffmanCodes(String s, int[] freq) {
            if (s.length() == 1) {
                this.ans = new ArrayList<>();
                ans.add("0");
                return ans;
            }
            PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> {
                if (a.value == b.value) return a.index - b.index;
                return a.value - b.value;
            });
            for (int i = 0; i < freq.length; i++) pq.offer(new Node(freq[i], i));
            while (pq.size() > 1) {
                Node left = pq.poll();
                Node right = pq.poll();
                if (right == null) break;
                Node root = new Node(left.value + right.value, Math.min(left.index, right.index));
                root.left = left;
                root.right = right;
                pq.offer(root);
            }
            Node root = pq.poll();
            StringBuilder sb = new StringBuilder();
            this.ans = new ArrayList<>();
            fill(sb, root);
            return ans;
        }

        private void fill(StringBuilder sb, Node root) {
            if (root == null) return;
            if (root.left == null && root.right == null) {
                ans.add(sb.toString());
                return;
            }
            if (root.left != null) {
                sb.append('0');
                fill(sb, root.left);
                sb.setLength(sb.length() - 1);
            }
            if (root.right != null) {
                sb.append('1');
                fill(sb, root.right);
                sb.setLength(sb.length() - 1);
            }
        }

        static class Node {
            int value;
            Node left;
            Node right;
            int index;

            Node(int value, int index) {
                this.value = value;
                this.index = index;
            }
        }
    }
}

