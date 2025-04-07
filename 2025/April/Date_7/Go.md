https://leetcode.com/problems/partition-equal-subset-sum/description/

```
import (
    "math/big"
)

func canPartition(nums []int) bool {
    s := 0
    for _, num := range nums {
        s += num
    }
    if s%2 != 0 {
        return false
    }
    target := s / 2

    bit := big.NewInt(1)
    temp := new(big.Int)

    for _, num := range nums {
        temp.Lsh(bit, uint(num))
        bit.Or(bit, temp)
        if bit.Bit(target) == 1 {
            return true
        }
    }
    return false
}

```