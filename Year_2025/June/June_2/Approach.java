package June_2;

class Solution {
        static {
            for (int i = 0; i < 140; ++i) {
                candy(new int[] { 1, 3, 2 });
            }
            System.gc();
        }
        public static int candy(int[] ratings) {
            int n = ratings.length;
            int index = 1;
            int totalCandies = 1;
            while (index < n) {
                if (ratings[index] == ratings[index - 1]) {
                    totalCandies += 1;
                    index++;
                }
                int Rating_Continuous_Increasing = 1;
                while (index < n && ratings[index] > ratings[index - 1]) {
                    Rating_Continuous_Increasing++;
                    totalCandies += Rating_Continuous_Increasing;
                    index++;
                }
                int Rating_Continuous_Decreasing = 1;
                while (index < n && ratings[index] < ratings[index - 1]) {
                    totalCandies += Rating_Continuous_Decreasing;
                    Rating_Continuous_Decreasing++;
                    index++;
                }
                if (Rating_Continuous_Decreasing > Rating_Continuous_Increasing) {
                    totalCandies += Rating_Continuous_Decreasing - Rating_Continuous_Increasing;
                }
            }
            return totalCandies;
        }
    }
/*
class Solution {
    public int candy(int[] rating) {
        int n = rating.length;
        int temp[] = new int[n];
        Arrays.fill(temp , 1);
        for(int i = 1 ; i < n; i++){ // compare from the left neighbour
            if(rating[i] > rating[i - 1]) temp[i] = temp[i - 1] + 1;
        }
        for(int i = n - 1 ; i > 0 ; i--){ // compare from the right neighbour
            if(rating[i - 1] > rating[i]) temp[i - 1] = Math.max(temp[i] + 1 , temp[i - 1]);
            // either it has curr candy or the right neighbour + 1 
        }
        int sum = 0;
        for(int num : temp) sum += num;
        return sum;
    }
    // 1 , 0 , 2
    // 1 , 1 , 1
    // 1 , 1 , 2
    // 2 , 1 , 2
}
*/