package February;

public class maximum_number_of_overlapping_intervals {
    static void main() {

    }

    static class Solution {
        public static int overlapInt(int[][] arr) {
            int maxTime = arr[0][1];
            for(int[] meeting : arr) if(meeting[1] > maxTime) maxTime = meeting[1];
            int[] diff = new int[maxTime + 2];
            for(int[] meeting : arr){
                diff[meeting[0]] += 1;
                diff[meeting[1] + 1] -= 1;
            }
            int maxMeeting = 1;
            for(int i = 1 ; i < diff.length ; i++){
                diff[i] += diff[i - 1];
                if(diff[i] > maxMeeting) maxMeeting = diff[i];
            }
            return maxMeeting;
        }
    }

}
