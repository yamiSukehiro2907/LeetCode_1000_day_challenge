https://leetcode.com/problems/minimum-operations-to-make-array-values-equal-to-k/description/

## Approach 1 : Brute Force
- First check is if minValue of array is less than k then it is impossible to make it array identical to k
- We find greatest two values in the array then change all the values to greater than max2 to max2
- We keep on doing until we get all the elements identical
- If we are not able to change the elements and it is not identical then return -1
- After all the elements in array are identical 
- We do the final check if the array's number is equal to k then no more operation needed else one more operation is needed


## Approach 2 : Optimized Approach

- Either create a set OR a boolean array of size 101 (maxValue in array possible according to constraint)
- If the element is less than k return then not possible to make array elements to k
- If the element is not equal to k and element does not exist then increase the number of op by 1
- Set the element that it exists in array
- Return the number of operation