package December.LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class find_mentioned_users {

    static void main() {
        String[][] str = {{"MESSAGE", "1", "ALL"}, {"OFFLINE", "66", "1"}, {"MESSAGE", "66", "HERE"}, {"OFFLINE", "5", "1"}};
        Solution solution = new Solution();
        List<List<String>> events = new ArrayList<>();
        for (String[] event : str) {
            events.add(List.of(event[0], event[1], event[2]));
        }
        System.out.println(Arrays.toString(solution.countMentions(3, events)));
    }

    private static class Solution {
        public int[] countMentions(int numberOfUsers, List<List<String>> events) {
            int[] mentions = new int[numberOfUsers];
            int[] isActive = new int[numberOfUsers];
            events.sort((a, b) -> {
                int t1 = Integer.parseInt(a.get(1)), t2 = Integer.parseInt(b.get(1));
                if (t1 != t2) return t1 - t2;
                if (a.get(0).equals("MESSAGE")) return 1;
                if (b.getFirst().equals("MESSAGE")) return 1;
                return 0;
            });
            for (List<String> event : events) {
                System.out.println(event);
            }
            for (List<String> event : events) {
                int timeStamp = Integer.parseInt(event.get(1));
                if (event.get(0).equals("OFFLINE")) {
                    int id = Integer.parseInt(event.get(2));
                    isActive[id] = timeStamp + 60;
                } else if (event.get(0).equals("MESSAGE")) {
                    String users = event.get(2);
                    if (users.equals("ALL")) {
                        for (int i = 0; i < numberOfUsers; i++) mentions[i]++;
                    } else if (users.equals("HERE")) {
                        for (int i = 0; i < numberOfUsers; i++) if (isActive[i] <= timeStamp) mentions[i]++;
                    } else {
                        int index = 0, num = 0;
                        while (index < users.length()) {
                            char ch = users.charAt(index);
                            if (ch == 'i') index += 2;
                            else if (ch == ' ') {
                                mentions[num]++;
                                num = 0;
                                index += 3;
                            } else {
                                num = num * 10 + (ch - '0');
                                index++;
                            }
                        }
                        mentions[num]++;
                    }
                }
            }
            return mentions;
        }
    }

}
