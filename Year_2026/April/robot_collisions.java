package April;

import java.util.*;

public class robot_collisions {
    static void main() {
        int[] positions = {11, 44, 16};
        int[] healths = {1, 20, 17};
        String directions = "RLR";
        Solution sol = new Solution();
        System.out.println(sol.survivedRobotsHealths(positions, healths, directions));
    }

    static class Solution {
        public List<Integer> survivedRobotsHealths(int[] positions, int[] healths, String directions) {
            int n = positions.length;
            List<Robot> robots = new ArrayList<>();
            for (int i = 0; i < n; i++) robots.add(new Robot(positions[i], healths[i], directions.charAt(i) == 'L', i));
            robots.sort(Comparator.comparingInt(a -> a.position));
            Stack<Robot> stack = new Stack<>();
            int index = 0;
            while (index < n) {
                while (index < n && !stack.isEmpty() && !stack.peek().left && robots.get(index).left) {
                    if (stack.peek().health == robots.get(index).health) {
                        stack.pop();
                        index++;
                    } else {
                        if (stack.peek().health < robots.get(index).health) {
                            stack.pop();
                            robots.get(index).health--;
                        } else {
                            stack.peek().health--;
                            index++;
                        }
                    }
                }
                stack.push(robots.get(index++));
            }
            List<Robot> result = new ArrayList<>(stack);
            result.sort(Comparator.comparingInt(a -> a.index));
            List<Integer> list = new ArrayList<>();
            for (Robot robot : result) list.add(robot.health);
            return list;
        }

        static class Robot {
            int position;
            int health;
            boolean left;
            int index;

            Robot(int position, int health, boolean left, int index) {
                this.position = position;
                this.health = health;
                this.left = left;
                this.index = index;
            }
        }
    }


    static class Solution2 {
        public List<Integer> survivedRobotsHealths(int[] positions, int[] healths, String directions) {
            int n = positions.length;
            Integer[] indices = new Integer[n];
            for (int i = 0; i < n; i++) indices[i] = i;
            Arrays.sort(indices, Comparator.comparingInt(a -> positions[a]));
            int[] stack = new int[n];
            int top = -1;
            for (int index : indices) {
                if (directions.charAt(index) == 'R') stack[++top] = index;
                else {
                    while (top >= 0 && healths[index] > 0) {
                        int rightRobot = stack[top];
                        if (healths[rightRobot] < healths[index]) {
                            healths[rightRobot] = 0;
                            healths[index]--;
                            top--;
                        } else if (healths[rightRobot] > healths[index]) {
                            healths[index] = 0;
                            healths[rightRobot]--;
                        } else {
                            healths[rightRobot] = 0;
                            healths[index] = 0;
                            top--;
                        }
                    }
                }
            }
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < n; i++) if (healths[i] > 0) list.add(healths[i]);
            return list;
        }
    }
}

