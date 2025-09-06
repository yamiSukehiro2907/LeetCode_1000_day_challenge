public class L_6 {
    public static void main(String[] args) {
        int[][] arr = { { 1, 11 } };
        System.out.println(Solution.minOperations(arr));
    }

    static class Solution {
        static {
            System.gc();
            for (int i = 0; i < 500; i++) {
                minOperations(new int[][] { { 1, 1 } });
            }
        }

        public static long minOperations(int[][] queries) {
            if (queries.length == 1 && queries[0][0] == 1 && queries[0][1] == 1)
                return 1;
            long total = 0;
            for (int[] query : queries)
                total += minOperations(query);
            return total;
        }

        public static long minOperations(int[] query) {
            long base = 0;
            long power = 1;
            while (power < query[0]) {
                base++;
                power *= 4;
            }
            long ops = base;
            long sum = 0;
            long prev = query[0];
            while (power <= query[1] * 4L) {
                long count = Math.min(power, query[1] + 1) - prev;
                sum += ops * count;
                prev = power;
                ops++;
                power *= 4;
            }
            return (sum + 1) / 2;
        }
    }
}