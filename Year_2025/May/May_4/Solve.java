package Year_2025.May.May_4;

public class Solve {
    public static void main(String[] args) {
        System.out.println(Solution.numTilings(60));
    }

    static class Solution {
        static {
            initialize();
        }
        private static int[] answer;

        private static final int MOD = 1000000007;

        private static void initialize() {
            answer = new int[1001];
            answer[0] = 1;
            answer[1] = 1;
            answer[2] = 2;
            answer[3] = 5;
            for (int i = 4; i < 1001; i++) {
                long val1 = (2L * answer[i - 1]) % MOD;
                long val2 = answer[i - 3];
                answer[i] = (int) ((val1 + val2) % MOD);
            }
        }

        public static int numTilings(int n) {
            print();
            return answer[n];
        }

        private static void print() {
            for (int i = 1; i < 1001; i++) {
                System.out.print(answer[i] + " ");
                if (i % 10 == 0) {
                    System.out.println();
                }
            }
            System.out.println();
        }
    }
    /*
    class Solution {
    static {
        initialize();
    }
    private static int[] answer;

    private static final int MOD = 1000000007;

    private static void initialize() {
        answer = new int[1001];
        answer[0] = 1;
        answer[1] = 1;
        answer[2] = 2;
        answer[3] = 5;
        for (int i = 4; i < 1001; i++) {
            long val1 = (2L * answer[i - 1]) % MOD;
            long val2 = answer[i - 3];
            answer[i] = (int) ((val1 + val2) % MOD);
        }
    }

    public static int numTilings(int n) {
        return answer[n];
    }
}
     */
}
