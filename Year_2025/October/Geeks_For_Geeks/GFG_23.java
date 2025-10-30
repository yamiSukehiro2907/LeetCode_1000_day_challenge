
import java.util.ArrayList;
import java.util.Arrays;

public class GFG_23 {

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] points = {{}};
        int k = 2;
        solution.kClosest(points, k);
    }

    private static class Solution {

        public ArrayList<ArrayList<Integer>> kClosest(int[][] points, int k) {
            Arrays.sort(points, (a, b) -> Double.compare(distance(a[0], a[1]), distance(b[0], b[1])));
            ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
            for (int i = 0; i < k; i++) {
                ArrayList<Integer> temp = new ArrayList<>();
                temp.add(points[i][0]);
                temp.add(points[i][1]);
                ans.add(temp);
            }
            return ans;
        }

        private double distance(int x, int y) {
            return Math.sqrt(x * x + y * y);
        }
    }
    /*
    class Solution {
    public ArrayList<ArrayList<Integer>> kClosest(int[][] points, int k) {
        PriorityQueue<Pair> queue = new PriorityQueue<>(new Sort());
        for(int[] point : points) queue.add(new Pair(point[0] , point[1]));
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        for(int i = 0 ; i < k ; i++){
            Pair pair = queue.poll();
            ArrayList<Integer> temp = new ArrayList<>();
            temp.add(pair.xCord);
            temp.add(pair.yCord);
            ans.add(temp);
        }
        return ans;
    }
    
    private class Pair{
        int xCord;
        int yCord;
        
        public Pair(int xCord , int yCord){
            this.xCord = xCord;
            this.yCord = yCord;
        }
    }
    
    private class Sort implements Comparator<Pair>{
        @Override
        public int compare(Pair p1 , Pair p2){
            return Double.compare(distanceFromOrigin(p1) , distanceFromOrigin(p2));
        }
    }
    
    private double distanceFromOrigin(Pair pair){
        return Math.sqrt(square(pair.xCord) + square(pair.yCord));
    }
    
    private int square(int n){
        return n * n;
    }
}
     */
}
