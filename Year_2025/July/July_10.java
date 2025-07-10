import java.util.Arrays;

public class July_10 {
    public static void main(String[] args) {

    }

    static class Solution {
    public int maxFreeTime(int eventTime, int[] startTime, int[] endTime) {
        int n = startTime.length;
        Gap[] gaps = new Gap[n + 1];
        gaps[0] = new Gap(startTime[0] - 0, 0); // first gap before the meetings
        for (int i = 1; i < n; i++) {
            // gaps between meetings
            int left = endTime[i - 1];
            int right = startTime[i];
            gaps[i] = new Gap(right - left, i);
        }
        gaps[n] = new Gap(eventTime - endTime[n - 1], n); // gap after the last meeting
        
        int maxCurrentGap = 0;
        for (int i = 0; i < n; i++) {
            if (i == 0) maxCurrentGap = Math.max(maxCurrentGap, startTime[i + 1] - (endTime[i] - startTime[i])); // If we shift the first meeting to adjacent
            else if (i == n - 1) maxCurrentGap = Math.max(maxCurrentGap, eventTime - (endTime[i] - startTime[i]) - endTime[i - 1]); // If we shift the meeting to adjacent
            else maxCurrentGap = Math.max(maxCurrentGap, startTime[i + 1] - (endTime[i] - startTime[i]) - endTime[i - 1]);// If we shift the last meeting to adjacent
        }
        
        PossibleGap[] possible = new PossibleGap[n];
        for (int i = 0; i < n; i++) {
            int possibleVal = gaps[i].val + gaps[i + 1].val + (endTime[i] - startTime[i]);
            if (possibleVal > maxCurrentGap) {
                possible[i] = new PossibleGap(possibleVal, i); // possible gap if a meeting is removed
            } else {
                possible[i] = new PossibleGap(-1, i);
            }
        }
        Arrays.sort(possible, (a, b) -> b.val - a.val); // Sorting on the basis of maximum possibleGaps
        
        if (possible[0].val > maxCurrentGap) {
            Arrays.sort(gaps, (a, b) -> a.val - b.val);
            for (PossibleGap pg : possible) {
                if(pg.val > maxCurrentGap){ // if only possibleGap is greater than currentGap
                    if (isPossible(0, n, pg.index, gaps, startTime, endTime)) return pg.val;
                }else{
                    break;
                }
            }
        }
        return maxCurrentGap;
    }

    private boolean isPossible(int start, int end, int index, Gap[] gaps, int[] startTime, int[] endTime) {
        int meetingDuration = endTime[index] - startTime[index];
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (gaps[mid].index == index || gaps[mid].index == index + 1) { // encountered left or right gap of the meeting to be removed
                boolean leftResult = (mid > start) ? isPossible(start, mid - 1, index, gaps, startTime, endTime) : false; // find in left part
                boolean rightResult = (mid < end) ? isPossible(mid + 1, end, index, gaps, startTime, endTime) : false; // find in right part
                return leftResult || rightResult;
            }
            if (gaps[mid].val >= meetingDuration) return true; // if gap can accomodate return true
            start = mid + 1; // else check in right gaps
        }
        return false;
    }

    private class Gap {
        int index;
        int val;

        Gap(int val, int index) {
            this.val = val;
            this.index = index;
        }
    }

    private class PossibleGap {
        int index;
        int val;

        PossibleGap(int val, int index) {
            this.val = val;
            this.index = index;
        }
    }
}
}
