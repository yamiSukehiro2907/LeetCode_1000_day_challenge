static void main(){
}
static class Solution {
    public int maxTwoEvents(int[][] events) {
        int n = events.length , maxVal = 0;
        if(n == 1) return events[0][2];
        Arrays.sort(events , (a , b) -> (a[0] == b[0]) ? a[2] - b[2] : a[0] - b[0]);
        int[] maxValue = new int[n];
        maxValue[n - 1] = events[n - 1][2];
        for(int i = n - 2 ; i >= 0 ; i--) maxValue[i] = Math.max(maxValue[i + 1] , events[i][2]); 
        // store the maxValue from the current Index (all other intervals in left)
        for(int i = 0 ; i < n ; i++){
            int[] event = events[i];
            int index = findNextNonOverLappingIntervalIndex(event[1] , events , i + 1);
            if(index != -1){
                // found another non-overlapping Interval 
                maxVal = Math.max(maxVal , maxValue[index] + event[2]);
            }else{
                // no non-overlapping interval
                maxVal = Math.max(maxVal , event[2]);
            }
        }
        return maxVal;
    }
    private int findNextNonOverLappingIntervalIndex(int endTime , int[][] events , int start){
        int end = events.length - 1 , index = -1;
        while(start <= end){
            int mid = start + ((end - start)/2);
            if(events[mid][0] > endTime){
                index = mid;
                end = mid - 1;
            }else start = mid + 1;
        }
        return index;
    }
}
