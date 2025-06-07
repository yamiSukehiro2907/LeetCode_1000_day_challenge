package June.June_7;

public class Approach {
    class Solution {
        public String clearStars(String s) {
            int len = s.length();
            byte[] chars = new byte[len + 1];
            byte[] temp = s.getBytes();
            System.arraycopy(temp, 0, chars, 0, len);
            chars[len] = '*';
            int startIdx = 0;
            int starCount = 0;
            for (int i = 0; i < len; i++) {
                if (chars[i] == '*') {
                    starCount++;// counting the stars
                    if (starCount == ((i + 2) >> 1)) startIdx = i + 1;// if the count of stars == count of non-stars in left them move startIdx
                }
            }
            if (starCount == 0) return s;
            if (startIdx == len) return "";
            int[] prev = new int[len];
            int[] last = new int[26];
            for (int i = 0; i < 26; i++) last[i] = -1;
            for (int i = startIdx;; i++) {
                int ch = chars[i];
                if (ch == '*') {
                    if (i >= len) break;
                    chars[i] = 0;
                    for (int c = 0; c < 26; c++) {
                        if (last[c] >= 0) {
                            chars[last[c]] = 0;
                            // we mark removed character's as zero
                            last[c] = prev[last[c]];
                            break;
                        }
                    }
                } else {
                    // we are creating a linked list where we save last occurrence each character's
                    // index in last and before that one's indexes in previous
                    prev[i] = last[ch - 'a'];
                    last[ch - 'a'] = i;
                    // suppose string is aaba
                    // last -> 3 , 2 , -1 ,....
                    // prev -> -1 , 0 , -1 , 1 ....
                    // links-> 3(last) -> 1(prev) -> 0(prev) for character 'a'
                }
            }
            int writeIdx = 0; // travel and keep on shifting
            for (int i = startIdx; i < len; i++) {
                if (chars[i] != 0) chars[writeIdx++] = chars[i];
            }
            return new String(chars, 0, writeIdx);
        }
    }
/*
class Solution {
    public String clearStars(String s) {
        PriorityQueue<Pair> queue = new PriorityQueue<>(new Sort());
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '*') {
                if (!queue.isEmpty()) queue.poll();
            } else queue.offer(new Pair(i, ch));
        }
        List<Pair> list = new ArrayList<>();
        while (!queue.isEmpty()) list.add(queue.poll());
        Collections.sort(list, (p1, p2) -> Integer.compare(p1.index, p2.index));
        StringBuilder sb = new StringBuilder();
        for (Pair temp : list) sb.append(temp.ch);
        return sb.toString();
    }

    public class Sort implements Comparator<Pair> {
        @Override
        public int compare(Pair p1, Pair p2) {
            if (p1.ch == p2.ch)
                return Integer.compare(p2.index, p1.index);
            return Character.compare(p1.ch, p2.ch);
        }
    }

    public class Pair {
        int index;
        char ch;

        Pair(int index, char ch) {
            this.index = index;
            this.ch = ch;
        }
    }
} 
*/
}
