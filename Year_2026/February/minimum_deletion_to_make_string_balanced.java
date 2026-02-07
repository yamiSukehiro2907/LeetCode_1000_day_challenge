package February;

public class minimum_deletion_to_make_string_balanced{
  public static void main(String[] args){
    
  }
  static class Solution2 {
    public int minimumDeletions(String s) {
        int n = s.length();
        byte[] arr = s.getBytes();
        int[] aCount = new int[n];
        int[] bCount = new int[n];
        aCount[0] = (arr[0] == 97) ? 1 : 0;
        for(int i = 1 ; i < n ; i++) aCount[i] = aCount[i - 1] + ((arr[i] == 97) ? 1 : 0);
        bCount[n - 1] = (arr[n - 1] != 97) ? 1 : 0;
        for(int i = n - 2 ; i >= 0 ; i--) bCount[i] = bCount[i + 1] + ((arr[i] != 97) ? 1 : 0);
        int minRemoval = n;
        for(int i = 0 ; i < n ; i++){
            int countA = aCount[i];
            int countB = bCount[i];
            int length = countA + countB;
            int removal = n - length;
            if(removal < minRemoval) minRemoval = removal;
        }
        return minRemoval;
    }
  }
  static class Solution {
    public int minimumDeletions(String s) {
        int ans = 0;
        int count = 0;
        for(byte t : s.getBytes()){
            if(t != 97) count++; // we encountered b
            else if(count > 0){ans++; count--;} // encountered a and got b earlier so we will be removing the previous b so removalCount increases by 1 and bCount decreases by 1
        }
        return ans;
    }
  }
}
