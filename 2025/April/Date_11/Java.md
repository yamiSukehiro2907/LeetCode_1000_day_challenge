https://leetcode.com/problems/count-symmetric-integers/description/

```
class Solution {
    private static final short[] prefix;
    static{
        prefix = new short[10001];
        create();
    }
    public static int countSymmetricIntegers(int low, int high) {
        return prefix[high] - prefix[low - 1];
    }
    private static void create(){
        for(int num = 1 ; num <= 9 ; num++) prefix[num] = 0;
        for(int num = 10 ; num <= 99 ; num++) prefix[num] = (short)(num/11);
        short prev = prefix[99];
        for(int num = 100 ; num <= 999 ; num++) prefix[num] = prev;
        prev = prefix[999];
        int idx = 1000;
        for(int l1 = 1 ; l1 <= 9 ; l1++){
            for(int l2 = 0 ; l2 <= 9 ; l2++){
                final int leftsum = l1 + l2;
                for(int r1 = 0 ; r1 <= 9 ; r1++){
                    for(int r2 = 0 ; r2 <= 9 ; r2++){
                        final int rightsum = r1 + r2;
                        prefix[idx++] = (short)((leftsum == rightsum) ? ++prev : prev);
                    }
                }
            }
        }
        prefix[10000] = prefix[9999];
    }
}
```