package April;

import java.util.*;

public class walking_robot_simulation {
    static void main() {
        int[] commands = {6, -1, -1, 6};
        int[][] obstacles = {{0, 0}};
        Solution solution = new Solution();
        System.out.println(solution.robotSim(commands, obstacles));
    }

    static class Solution {
        private Map<Integer, List<Integer>> xMap;
        private Map<Integer, List<Integer>> yMap;
        private int maxDistance;

        public int robotSim(int[] commands, int[][] obstacles) {
            createMap(obstacles);
            maxDistance = 0;
            simulateMovement(commands);
            return maxDistance;
        }

        private void simulateMovement(int[] commands) {
            char currentDirection = 'U';
            int[] currentCord = new int[2];
            boolean blocked = false;
            int index = 0;
            while (index < commands.length) {
                int command = commands[index++];
                if (command == -2) {
                    currentDirection = changeDirection(currentDirection, true);
                    blocked = false;
                } else if (command == -1) {
                    currentDirection = changeDirection(currentDirection, false);
                    blocked = false;
                } else if (!blocked) blocked = move(currentCord, command, currentDirection);
                maxDistance = Math.max(maxDistance, getDistance(currentCord));
            }
        }

        private char changeDirection(char currentDirection, boolean left) {
            if (currentDirection == 'U') return left ? 'L' : 'R';
            if (currentDirection == 'D') return left ? 'R' : 'L';
            if (currentDirection == 'L') return left ? 'D' : 'U';
            return left ? 'U' : 'D';
        }

        private int getDistance(int[] cord) {
            return cord[0] * cord[0] + cord[1] * cord[1];
        }

        private boolean move(int[] currentCord, int distance, char currentDirection) {
            boolean blocked = false;
            if (currentDirection == 'U') {
                int maxRange = currentCord[1] + distance;
                if (xMap.containsKey(currentCord[0])) {
                    int nextObs = findGreater(xMap.get(currentCord[0]), currentCord[1]) - 1;
                    if (nextObs <= maxRange) {
                        blocked = true;
                        maxRange = nextObs;
                    }
                }
                currentCord[1] = maxRange;
            } else if (currentDirection == 'D') {
                int maxRange = currentCord[1] - distance;
                if (xMap.containsKey(currentCord[0])) {
                    int nextObs = findLower(xMap.get(currentCord[0]), currentCord[1]) + 1;
                    if (nextObs >= maxRange) {
                        blocked = true;
                        maxRange = nextObs;
                    }
                }
                currentCord[1] = maxRange;
            } else if (currentDirection == 'L') {
                int maxRange = currentCord[0] - distance;
                if (yMap.containsKey(currentCord[1])) {
                    int nextObs = findLower(yMap.get(currentCord[1]), currentCord[0]) + 1;
                    if (nextObs >= maxRange) {
                        blocked = true;
                        maxRange = nextObs;
                    }
                }
                currentCord[0] = maxRange;
            } else {
                int maxRange = currentCord[0] + distance;
                if (yMap.containsKey(currentCord[1])) {
                    int nextObs = findGreater(yMap.get(currentCord[1]), currentCord[0]) - 1;
                    if (nextObs <= maxRange) {
                        blocked = true;
                        maxRange = nextObs;
                    }
                }
                currentCord[0] = maxRange;
            }
            return blocked;
        }

        private int findGreater(List<Integer> arr, int target) {
            int start = 0, end = arr.size() - 1;
            int ans = Integer.MAX_VALUE;
            while (start <= end) {
                int mid = start + (end - start) / 2;
                if (arr.get(mid) > target) {
                    end = mid - 1;
                    ans = arr.get(mid);
                } else start = mid + 1;
            }
            return ans;
        }

        private int findLower(List<Integer> arr, int target) {
            int start = 0, end = arr.size() - 1;
            int ans = Integer.MIN_VALUE;
            while (start <= end) {
                int mid = start + (end - start) / 2;
                if (arr.get(mid) < target) {
                    start = mid + 1;
                    ans = arr.get(mid);
                } else end = mid - 1;
            }
            return ans;
        }

        private void createMap(int[][] obstacles) {
            Arrays.sort(obstacles, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
            this.xMap = new HashMap<>();
            for (int[] point : obstacles) {
                if (!xMap.containsKey(point[0])) xMap.put(point[0], new ArrayList<>());
                xMap.get(point[0]).add(point[1]);
            }
            Arrays.sort(obstacles, (a, b) -> a[1] == b[1] ? a[0] - b[0] : a[1] - b[1]);
            this.yMap = new HashMap<>();
            for (int[] point : obstacles) {
                if (!yMap.containsKey(point[1])) yMap.put(point[1], new ArrayList<>());
                yMap.get(point[1]).add(point[0]);
            }
        }
    }

    static class Solution2 {
        private static final class Coord {
            private int x, y;

            private Coord(int x, int y) {
                this.x = x;
                this.y = y;
            }

            private int getDist() {
                return x * x + y * y;
            }

            public boolean equals(Object other) {
                if (!(other instanceof Coord coord)) return false;
                return x == coord.x && y == coord.y;
            }

            public int hashCode() {
                return x * 31 + y;
            }
        }

        private static final int[] dx = {0, 1, 0, -1}, dy = {1, 0, -1, 0};

        public int robotSim(int[] commands, int[][] obstacles) {
            int dir = 0, max = 0;
            Set<Coord> invalid = new HashSet<>(obstacles.length, 1.0f);
            for (int[] x : obstacles) invalid.add(new Coord(x[0], x[1]));
            Coord current = new Coord(0, 0);
            for (int command : commands) {
                if (command == -1) dir = dir == 3 ? 0 : dir + 1;
                else if (command == -2) dir = dir == 0 ? 3 : dir - 1;
                else {
                    for (int i = 0; i < command; i++) {
                        current.x += dx[dir];
                        current.y += dy[dir];
                        if (invalid.contains(current)) {
                            current.x -= dx[dir];
                            current.y -= dy[dir];
                            break;
                        }
                    }
                    max = Math.max(max, current.getDist());
                }
            }
            return max;
        }
    }
}
