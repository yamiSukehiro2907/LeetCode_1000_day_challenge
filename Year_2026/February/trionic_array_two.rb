def max_sum_trionic(nums)
    maxSum = -Float::INFINITY
    n = nums.length
    startIndex = 0
    while startIndex < n do
       currentIndex = startIndex + 1
       currentSum = 0
       while currentIndex < n && nums[currentIndex] > nums[currentIndex - 1] do
          currentIndex += 1
        end
        peakIndex = currentIndex - 1
        if peakIndex == startIndex
           startIndex += 1
           next
        end
        # get the top two elements of the rising part
        currentSum += nums[peakIndex] + nums[peakIndex - 1]
        while currentIndex < n && nums[currentIndex - 1] > nums[currentIndex] do
           currentSum += nums[currentIndex]
           currentIndex += 1
        end
        valleyIndex = currentIndex - 1
        hasNoDescent = (valleyIndex == peakIndex)
        valleyAtEnd = (valleyIndex == n - 1)
        noProperSecondAscent = (currentIndex < n && nums[currentIndex] <= nums[valleyIndex])
        if hasNoDescent || valleyAtEnd || noProperSecondAscent
           startIndex = valleyIndex
           next
        end
        currentSum += nums[valleyIndex + 1]
        # expanding in right window
        forwardIndex = valleyIndex + 2
        forwardSum = 0
        maxForwardSum = 0
        while forwardIndex < n && nums[forwardIndex] > nums[forwardIndex - 1] do
           forwardSum += nums[forwardIndex]
           if forwardSum > maxForwardSum
              maxForwardSum = forwardSum
           end
           forwardIndex += 1
        end
        # expand in left window
        backWardIndex = peakIndex - 2
        backWardSum = 0
        maxBackwardSum = 0
        while backWardIndex >= startIndex do
           backWardSum += nums[backWardIndex]
           if maxBackwardSum < backWardSum
              maxBackwardSum = backWardSum
           end
           backWardIndex -= 1
        end
        finalSum = maxBackwardSum + maxForwardSum + currentSum
        if finalSum > maxSum
           maxSum = finalSum
        end
        startIndex = valleyIndex
    end
    return maxSum.to_i
end