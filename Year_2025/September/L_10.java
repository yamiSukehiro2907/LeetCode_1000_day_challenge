
import java.util.Arrays;
import java.util.BitSet;
import java.util.HashSet;
import java.util.Set;

public class L_10 {
    public static void main(String[] args) {

    }

    private static class Solution {
        static {
            for (int i = 0; i < 100; i++) {
                minimumTeachings(0, new int[][] {}, new int[][] {});
            }
        }

        public static int minimumTeachings(int n, int[][] L, int[][] F) {
            if (n == 0)
                return 0;
            BitSet[] bit = new BitSet[L.length];
            Arrays.setAll(bit, o -> new BitSet(n + 1));
            for (int i = 0; i < L.length; i++) {
                for (int l : L[i])
                    bit[i].set(l);
            }
            Set<Integer> teach = new HashSet<>();
            for (int[] f : F) {
                BitSet t = (BitSet) bit[f[0] - 1].clone();
                t.and(bit[f[1] - 1]);
                if (t.isEmpty()) {
                    teach.add(f[0] - 1);
                    teach.add(f[1] - 1);
                }
            }
            int[] count = new int[n + 1];
            for (int person : teach) {
                for (int l : L[person])
                    count[l]++;
            }
            return teach.size() - Arrays.stream(count).max().getAsInt();
        }
    }
    /*
    class Solution {
    public int minimumTeachings(int n, int[][] languages, int[][] friendships) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int i = 0; i < languages.length; i++) {
            for (int j = 0; j < languages[i].length; j++) {
                if (!map.containsKey(languages[i][j])) map.put(languages[i][j] , new HashSet());
                map.get(languages[i][j]).add(i + 1); // hashmap to store the user's where key is language
            }
        }
        boolean[] visited = new boolean[friendships.length];
        for (int i = 0 ; i < friendships.length ; i++) {
            if(bothKnowSame(map , friendships[i][0] , friendships[i][1])) visited[i] = true;
        }
        int minOp = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            if(!map.containsKey(i)) continue;
            Set<Integer> taught = new HashSet();
            for (int j = 0 ; j < friendships.length ; j++) {
                if(!visited[j]){
                    if(!map.get(i).contains(friendships[j][0]) && !taught.contains(friendships[j][0])){
                        taught.add(friendships[j][0]);
                    }
                    if(!map.get(i).contains(friendships[j][1]) && !taught.contains(friendships[j][1]) ){
                        taught.add(friendships[j][1]);
                    }
                }
            }
            if(minOp > taught.size()) minOp = taught.size();
        }
        return minOp;
    }
    private boolean bothKnowSame(Map<Integer , Set<Integer>> map , int u1 , int u2){
        for(int language : map.keySet()){
            if(map.get(language).contains(u1) && map.get(language).contains(u2)) return true;
        }
        return false;
    }
}
    */
}