package April;

public class walking_robot_simulation_2 {
    static void main() {

    }

    static class Robot {
        private final static String[] directions = {"North", "East", "South", "West"};
        private final static int[][] movements = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        private final int[] current;
        private final int height, width;

        public Robot(int width, int height) {
            this.width = width;
            this.height = height;
            this.current = new int[4];
            current[2] = 1;
        }

        public void step(int num) {
            current[3] += num;
        }

        private void move() {
            int cycle = 2 * (height + width - 2);
            current[3] %= cycle;
            if (current[3] == 0) current[3] = cycle;
            while (current[3] > 0) {
                int[] movement = movements[current[2]];
                int maxStep = getMaxStep(movement);
                if (maxStep == 0) current[2] = (current[2] + 3) % 4;
                else {
                    int step = Math.min(current[3], maxStep);
                    current[0] = (current[0] + step * movement[0]);
                    current[1] = (current[1] + step * movement[1]);
                    current[3] -= step;
                }
            }
        }

        public int[] getPos() {
            if (current[3] > 0) move();
            return new int[]{current[0], current[1]};
        }

        public String getDir() {
            if (current[3] > 0) move();
            return directions[current[2]];
        }

        private int getMaxStep(int[] movement) {
            int maxStep = 0;
            if (movement[0] == 0) {
                if (movement[1] == -1) maxStep = current[1];
                else maxStep = height - current[1] - 1;
            } else {
                if (movement[0] == -1) maxStep = current[0];
                else maxStep = width - current[0] - 1;
            }
            return maxStep;
        }
    }

}