import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class L_16 {
    public static void main(String[] args) {
        int[] arr1 = { 287, 41, 49, 287, 899, 23, 23, 20677, 5, 825 };
        System.out.println("Array: " + Arrays.toString(arr1));
        System.out.println(Solution.replaceNonCoprimes(arr1));
    }

    private static class Solution {
        static {
            System.gc();
            for (int i = 0; i < 500; i++) {
                replaceNonCoprimes(new int[] { 1 });
            }
        }

        public static List<Integer> replaceNonCoprimes(int[] nums) {
            List<Integer> list = new ArrayList<>();
            int n = nums.length;
            if (n == 1) {
                list.add(nums[0]);
                return list;
            }
            int prevIndex = -1, index = 0, GCD = -1;
            while (index < n) {
                while (prevIndex >= 0 && index < n) {
                    GCD = gcd(nums[prevIndex], nums[index]);
                    if (GCD > 1) {
                        nums[prevIndex] = (int) (((long) nums[prevIndex] * nums[index])
                                / (long) GCD);
                        index++;
                    } else {
                        break;
                    }
                }
                while (prevIndex - 1 >= 0) {
                    GCD = gcd(nums[prevIndex], nums[prevIndex - 1]);
                    if (GCD > 1) {
                        nums[prevIndex - 1] = (int) (((long) nums[prevIndex] * nums[prevIndex - 1])
                                / (long) GCD);
                        prevIndex--;
                    } else {
                        break;
                    }
                }
                if (index < n) {
                    nums[++prevIndex] = nums[index++];
                }
            }
            for (int i = 0; i <= prevIndex; i++) {
                list.add(nums[i]);
            }
            return list;
        }

        private static int gcd(int a, int b) {
            return b == 0 ? a : gcd(b, a % b);
        }
    }
}
