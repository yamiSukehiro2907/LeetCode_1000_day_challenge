https://leetcode.com/problems/partition-equal-subset-sum/description/

## Approach

- If we are going to partition it into two halfs of equal then:
  - 1.  total sum of array should be even to be divided into equal sum of halfs (By checking this we can remove some possible test cases)
  - 2.  We need to find that Is there any subset possible that have sum half of totalSum
- As there is higher constraint so we need to create a boolean array for memoization that will help to know that whether a target is possible to be formed using the element of that array
- We will create two possibilities of whether choosing an element or skipping it
- If we get the required target then we will return true
- If the sum is greater than target then we return false
- If we reached the end of array then we do the final check of getting that target
- One way of removing some space and doing optimizations is to try reducing the target with elements and checking until the start of array
