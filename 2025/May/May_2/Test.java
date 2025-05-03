public class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.pushDominoes(".L.R...LR..L.."));
    }
}

class Solution {
    public String pushDominoes(String dominoes) {
        // Total Possible cases:
        // Case 1 : L...L fill with L
        // Case 2 : L...R No change
        // Case 3 : R...L half and half
        // Case 4 : R...R fill with R
        final int size = dominoes.length();
        if (size <= 1)
            return dominoes;
        boolean leftisL = true; // store whether the
        int lastIndex = -1; // store the last letter index
        char[] ans = dominoes.toCharArray();
        for (int i = 0; i < size; i++) {
            switch (dominoes.charAt(i)) {
                case 'L':
                    if (leftisL) {
                        for (int j = lastIndex + 1; j < i; ++j) // we fill all the gapping element will between (L...L)
                                                                // to L
                            ans[j] = 'L';
                    } else {
                        // (R..L) fill the left half between lastindex and currentIndex to 'L' and for
                        // other half 'R'
                        int leftPos = lastIndex + 1, rightPos = i - 1;
                        for (; leftPos < rightPos; ++leftPos, --rightPos) {
                            ans[leftPos] = 'R';
                            ans[rightPos] = 'L';
                        }
                    }
                    leftisL = true;
                    lastIndex = i;
                    break;
                case 'R':
                    if (!leftisL) {
                        // (R ... R ) we fill with R
                        for (int j = lastIndex + 1; j < i; ++j)
                            ans[j] = 'R';
                    }
                    leftisL = false;
                    lastIndex = i;
                    break;
                default:
                    break;
            }
        }
        // if last one is R
        if (!leftisL) {
            for (int j = lastIndex + 1; j < size; ++j)
                ans[j] = 'R';
        }
        return new String(ans);
    }
}