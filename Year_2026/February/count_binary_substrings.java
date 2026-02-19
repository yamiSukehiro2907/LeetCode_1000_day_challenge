package February;

public class count_binary_substrings {
    static void main() {
        Solution2 solution = new Solution2();
        System.out.println(solution.countBinarySubstrings("00110011"));
    }

    static class Solution {
        public int countBinarySubstrings(String s) {
            char[] arr = s.toCharArray();
            int count = 0;
            int index = 0;
            while(index < arr.length){
                if(arr[index] == '1'){
                    while(index < arr.length && arr[index] == '1') index++;
                    if(index == arr.length) return count;
                    count += getCount(arr , index - 1 , index , '1' , '0');
                }else{
                    while(index < arr.length && arr[index] == '0') index++;
                    if(index == arr.length) return count;
                    count += getCount(arr , index - 1 , index , '0' , '1');
                }
            }
            return count;
        }

        private int getCount(char[] arr, int left, int right , char leftChar , char rightChar){
            if(left < 0 || right > arr.length) return 0;
            int count = 0;
            while(left >= 0 && right < arr.length && arr[left] == leftChar && arr[right] == rightChar){
                count++;
                left--;
                right++;
            }
            return count;
        }
    }

    static class Solution2 {
        public int countBinarySubstrings(String s) {
            char[] arr = s.toCharArray();
            int count = 0 , start = -1;
            for(int i = 1 ; i < arr.length ; i++){
                if(arr[i] != arr[i - 1]){start = i - 1; count++;}
                else if(start > 0 && arr[i] != arr[--start]) count++;
                else start = -1;
            }
            return count;
        }
    }
}
