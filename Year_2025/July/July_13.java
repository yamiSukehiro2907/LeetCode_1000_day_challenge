import java.util.*;

public class July_13 {
    public static void main(String[] args) {

    }

    @SuppressWarnings("unused")
    static class Solution {
        @SuppressWarnings("UseSpecificCatch")
        public int matchPlayersAndTrainers(int[] players, int[] trainers) {
            Thread t1 = new Thread(() -> Arrays.sort(players));
            Thread t2 = new Thread(() -> Arrays.sort(trainers));
            t1.start();
            t2.start();
            try {
                t1.join();
                t2.join();
            } catch (Exception e) {
            }
            int i = 0, j = 0, count = 0;
            while (i < players.length && j < trainers.length) {
                if (players[i] <= trainers[j]) {
                    count++;
                    i++;
                }
                j++;
            }
            return count;
        }
    }
    /*
    class Solution {
        public int matchPlayersAndTrainers(int[] players, int[] trainers) {
            Thread t1 = new Thread(() -> Arrays.sort(players));
            Thread t2 = new Thread(() -> Arrays.sort(trainers));
            t1.start();
            t2.start();
            try {
                t1.join();
                t2.join();
            } catch (Exception e) {
            }
            int n = players.length; // number of players
            int count = 0; // total mapped players
            int end = n - 1;
            boolean[] visited = new boolean[n]; // players been mapped
            for (int i = trainers.length - 1; i >= 0; i--) { // iterating over trainers
                int index = find(trainers[i], players, visited, 0, end);
                if (index != -1) {
                    visited[index] = true;
                    count++;
                    end = index - 1;
                }
            }
            return count;
        }

        private int find(int target, int[] players, boolean[] visited, int start, int end) {
            int player = -1;
            while (start <= end) {
                int mid = start + ((end - start) / 2);
                if (visited[mid]) {
                    int left = find(target, players, visited, start, mid - 1);
                    if (left == -1) {
                        int right = find(target, players, visited, mid + 1, end);
                        if (right != -1) {
                            player = right;
                        }
                    } else {
                        player = left;
                    }
                    break;
                } else {
                    if (players[mid] <= target) {
                        player = mid;
                        start = mid + 1;
                    } else {
                        end = mid - 1;
                    }
                }
            }
            return player;
        }
    }
    */
}
