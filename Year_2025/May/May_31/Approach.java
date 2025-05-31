package Year_2025.May.May_31;

public class Approach {
    class Solution {
        public int snakesAndLadders(int[][] board) {
            int size = board.length;
            int target = size * size;
            short[] flatBoard = new short[target + 1];
            int pos = 1;
            // fill the flatBoard with board values
            for (int row = size - 1; row >= 0; row--) {
                for (int col = 0; col < size; col++) {
                    flatBoard[pos++] = (short) board[row][col];
                }
                if (--row < 0) {
                    break;
                }
                for (int col = size - 1; col >= 0; col--) {
                    flatBoard[pos++] = (short) board[row][col];
                }
            }

            short[] queue = new short[size * size];
            int head = 0, tail = 0;
            queue[tail++] = 1;
            int[] steps = new int[target + 1];
            steps[1] = 1;

            while (head != tail) {
                int current = queue[head++];
                head %= target;

                if (current + 6 >= target) {
                    return steps[current];
                }

                int maxRoll = 0;
                for (int roll = 6; roll >= 1; roll--) {
                    int next = current + roll;
                    if (flatBoard[next] >= 0) {
                        next = flatBoard[next];
                        if (next == target) {
                            return steps[current];
                        }
                    } else {
                        if (roll < maxRoll) {
                            continue;
                        }
                        maxRoll = roll;
                    }

                    if (steps[next] == 0) {
                        steps[next] = steps[current] + 1;
                        queue[tail++] = (short) next;
                        tail %= target;

                        if (head == tail) {
                            return 0;
                        }
                    }
                }
            }
            return -1;
        }
    }
}
