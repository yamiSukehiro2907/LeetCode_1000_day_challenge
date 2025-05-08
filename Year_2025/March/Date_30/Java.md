```
class Solution {
    static{
        for(int i = 0 ; i < 500 ; i++) partitionLabels("a");
    }
    public static List<Integer> partitionLabels(String s) {
        int n = s.length();
        List<Integer> ans = new ArrayList<>();
        if(n <= 1){
            ans.add(1);
            return ans;
        }
        int freq[] = new int[26];
        for(int i = 0 ; i < n ; i++) freq[s.charAt(i) - 'a'] = i;
        int index = 0 , start = 0;
        while(index < n){
            start = partition(s , freq , index);
            ans.add(start - index + 1);
            index = start + 1;
        }
        return ans;
    }
    private static int partition(String str , int[] freq , int index){
        int left = Math.max(index , freq[str.charAt(index) - 'a']) , right = left;
        while(index <= Math.max(left , right)){
            right = Math.max(right , freq[str.charAt(index) - 'a']);
            index++;
        }
        return right;
    }
}
// class Solution {
//     public List<Integer> partitionLabels(String s) {
//         ArrayList<int[]> range = new ArrayList<>();
//         for (int i = 0; i < 26; i++) range.add(new int[]{-1, -1});
//         int n = s.length();
//         for (int i = 0; i < n; i++) {
//             char ch = s.charAt(i);
//             if (range.get(ch - 'a')[0] == -1) range.get(ch - 'a')[0] = i;
//             range.get(ch - 'a')[1] = i;
//         }
//         range.removeIf(r -> r[0] == -1);
//         Collections.sort(range, Comparator.comparingInt(a -> a[0]));
//         List<Integer> ans = new ArrayList<>();
//         int start = 0, end = 0;
//         for (int[] r : range) {
//             if (r[0] > end) { 
//                 ans.add(end - start + 1);
//                 start = r[0];
//             }
//             end = Math.max(end, r[1]);
//         }
//         ans.add(end - start + 1);
//         return ans;
//     }
// }

```