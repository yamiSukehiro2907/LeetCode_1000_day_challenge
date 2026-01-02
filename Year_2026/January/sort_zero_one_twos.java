package January;

public class sort_zero_one_twos {
    static void main() {

    }

    static class Solution {
        public void sort012(int[] arr) {
            int zeroIndex = 0 , twoIndex = arr.length - 1 , index = 0;
            while(index <= twoIndex){
                if(arr[index] == 0){
                    swap(zeroIndex , index , arr);
                    index++;
                    zeroIndex++;
                }else if(arr[index] == 2){
                    swap(twoIndex , index , arr);
                    twoIndex--;
                }else index++;
            }
        }
        private void swap(int i , int j , int[] arr){
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }
}
