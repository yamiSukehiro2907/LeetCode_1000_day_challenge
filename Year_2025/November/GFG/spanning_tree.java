public class spanning_tree {
    public static void main(String[] args) {
        ///  vertices = 5
    }

    private static class UnionFind{
        private final int[] parent;

        UnionFind(int n){
            parent = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;
        }

        private int find(int i){
            if(parent[i] == i) return i;
            return find(parent[i]);
        }

        private void union(int i , int j){
            int iParent = find(i);
            int jParent = find(j);
            if(iParent == jParent) return;
            parent[iParent] = jParent;
        }

    }
}
