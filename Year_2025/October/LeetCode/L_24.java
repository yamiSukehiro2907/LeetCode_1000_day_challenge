public class L_24 {
    public static void main(String[] args) {

    }

    private static class Solution {
        private static int[] possibleNums = {
                1, 22, 122, 212, 221, 333, 1333, 3133, 3313, 3331, 4444, 14444, 22333, 23233, 23323, 23332, 32233,
                32323,
                32332, 33223, 33232, 33322, 41444, 44144, 44414, 44441, 55555, 122333, 123233, 123323, 123332, 132233,
                132323, 132332, 133223, 133232, 133322, 155555, 212333, 213233, 213323, 213332, 221333, 223133, 223313,
                223331, 224444, 231233, 231323, 231332, 232133, 232313, 232331, 233123, 233132, 233213, 233231, 233312,
                233321, 242444, 244244, 244424, 244442, 312233, 312323, 312332, 313223, 313232, 313322, 321233, 321323,
                321332, 322133, 322313, 322331, 323123, 323132, 323213, 323231, 323312, 323321, 331223, 331232, 331322,
                332123, 332132, 332213, 332231, 332312, 332321, 333122, 333212, 333221, 422444, 424244, 424424, 424442,
                442244, 442424, 442442, 444224, 444242, 444422, 515555, 551555, 555155, 555515, 555551, 666666, 1224444
        };

        public int nextBeautifulNumber(int n) {
            return greater(0, possibleNums.length - 1, n);
        }

        private int greater(int start, int end, int target) {
            int ans = possibleNums[end];
            while (start <= end) {
                int mid = start + ((end - start) / 2);
                if (possibleNums[mid] > target) {
                    ans = possibleNums[mid];
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }
            return ans;
        }
    }
    /*
class Solution {
    private static String[] nums = { "1",
            "22",
            "333", "122", "4444", "1333", "55555", "14444", "22333", "666666", "155555", "224444", "122333" };
    private static int[] possibleNums;

    private static Set<String> set = new HashSet<>();

    static {
        for (String num : nums) {
            generate(num);
        }
        List<String> stringNums = new ArrayList<>(set);
        possibleNums = new int[stringNums.size() + 1];
        int index = 0;
        for (String num : stringNums)
            possibleNums[index++] = Integer.parseInt(num);
        possibleNums[index] = 1224444;
        Arrays.sort(possibleNums);
    }

    private static void generate(String num) {
        char[] arr = num.toCharArray();
        StringBuilder sb = new StringBuilder();
        boolean[] visited = new boolean[arr.length];
        helper(0, arr, sb, visited);
    }

    private static void helper(int index, char[] arr, StringBuilder sb, boolean[] visited) {
        if (sb.length() == arr.length) {
            set.add(sb.toString());
            return;
        }
        for (int i = index; i < arr.length; i++) {
            if (!visited[i]) {
                sb.append(arr[i]);
                visited[i] = true;
                helper(0, arr, sb, visited);
                sb.setLength(sb.length() - 1);
                visited[i] = false;
            }
        }
    }

    public int nextBeautifulNumber(int n) {
        return greater(0, possibleNums.length - 1, n);
    }

    private int greater(int start, int end, int target) {
        int ans = possibleNums[end];
        while (start <= end) {
            int mid = start + ((end - start) / 2);
            if (possibleNums[mid] > target) {
                ans = possibleNums[mid];
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return ans;
    }
}
*/

}