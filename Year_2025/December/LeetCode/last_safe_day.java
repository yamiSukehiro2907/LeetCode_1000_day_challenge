package December.LeetCode;

public class last_safe_day {
    static void main() {

    }
    static class Solution {
        private final static int[][] directions = { { 1, -1 }, { 1, 0 }, { 1, 1 }, { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, 1 }, { 0, -1 } };
        private int leftWaterBoundary;
        private int rightWaterBoundary;
        private int col;
        private UnionFind unionFind;

        // approach is simple
        // think of virus spreading from two different sides of country
        // the traveling would be easier and possible if the virus have not divided the country into two regions
        // as soon as they form unique boundary and dividing the whole region into two regions

        public int latestDayToCross(int row, int c, int[][] cells) {
            this.col = c;
            int size = row * col;
            this.unionFind = new UnionFind(size);
            this.leftWaterBoundary = size;
            this.rightWaterBoundary = size + 1;
            int[][] visited = new int[row][col];
            for (int i = 0; i < cells.length; i++) {
                int x = cells[i][0] - 1, y = cells[i][1] - 1;
                int index = getId(x, y);
                visited[x][y] = 1;
                if (y == 0) unionFind.union(leftWaterBoundary, index); // any water block in left or right most will start the boundary
                else if (y == col - 1) unionFind.union(rightWaterBoundary, index);
                for (int[] dir : directions) {
                    int newX = dir[0] + x, newY = dir[1] + y;
                    if (newX >= 0 && newY >= 0 && newX < row && newY < col && visited[newX][newY] == 1) unionFind.union(index, getId(newX, newY));
                    // connect the water regions with connected blocks to create a single region
                }
                if (boundariesMatched()) return i;
            }
            return -1;
        }

        private static class UnionFind {
            private final int[] parent , rank;
            private final int size;

            public UnionFind(int n) {
                this.size = n + 2;
                this.parent = new int[size];
                this.rank = new int[size];
                initialize();
            }

            private void initialize() {
                for (int i = 0; i < size; i++) this.parent[i] = i;
            }

            public int find(int index) {
                if (index == parent[index]) return index;
                return parent[index] = find(parent[index]); // path compression so that next search should be faster because each finding makes sure that all the childs
                // will have same parent in next searching
            }

            public void union(int node1, int node2) {
                int parent1 = find(node1), parent2 = find(node2);
                // the ranking helps in finding the parent faster
                // in normal unionFind we use nodes to point to just one of the node of other set and the new tree's height (hops) = tree1 + tree2
                // but in ranking unionFind
                // if same ranks then make one of the parent grandparent and point another to that
                // if p1's rnk is greater than p2's rnk then directly just point
                // suppose nodes are located at two different branches then in normal unionFind we will go to top even though while going to top you will visit nodes
                // that are similar in both the parent findings that's why by using ranking searching could be optimized
                if (rank[parent1] == rank[parent2]) {
                    rank[parent1]++;
                    // make parent1's tree tall and make parent2 point to that
                    parent[parent2] = parent1;
                } else if (rank[parent1] > rank[parent2]) parent[parent2] = parent[parent1];
                else parent[parent1] = parent[parent2];
            }
        }

        private int getId(int i, int j) {
            return col * i + j;
        }

        private boolean boundariesMatched() {
            return unionFind.find(leftWaterBoundary) == unionFind.find(rightWaterBoundary);
        }
    }
}
