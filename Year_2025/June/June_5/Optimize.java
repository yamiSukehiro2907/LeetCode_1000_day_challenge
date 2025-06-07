package Year_2025.June.June_5;

class Solution {
    static {
        for (int i = 0; i < 500; i++) {
            smallestEquivalentString("", "", "");
        }
    }
    private static int parent[];

    public static int find(int val) {
        if (parent[val] != val) {
            parent[val] = find(parent[val]);
        }
        return parent[val];
    }

    private static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA < rootB) {
            parent[rootB] = rootA;
        } else {
            parent[rootA] = rootB;
        }
    }

    public static String smallestEquivalentString(String s1, String s2, String baseStr) {
        int n = baseStr.length();
        parent = new int[26];
        for (int i = 0; i < 26; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < s1.length(); i++) {
            union(s1.charAt(i) - 'a', s2.charAt(i) - 'a');
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char ch = baseStr.charAt(i);
            sb.append((char) (find(ch - 'a') + 'a'));
        }
        return sb.toString();
    }
}
/*
 * class Solution {
 * private class Union {
 * private int[] rank, parent;
 * private int size;
 * public Union(int n) {
 * this.size = n;
 * this.rank = new int[n];
 * this.parent = new int[n];
 * for (int i = 0; i < n; i++) {
 * parent[i] = i;
 * }
 * }
 * public void union(int x, int y) {
 * if (x == y)
 * return;
 * int xRoot = find(x), yRoot = find(y);
 * if (xRoot == yRoot) {
 * return;
 * }
 * if (rank[xRoot] > rank[yRoot]) {
 * parent[yRoot] = xRoot;
 * } else if (rank[yRoot] > rank[xRoot]) {
 * parent[xRoot] = yRoot;
 * } else {
 * if (xRoot < yRoot) {
 * parent[yRoot] = xRoot;
 * rank[xRoot]++;
 * } else {
 * parent[xRoot] = yRoot;
 * rank[yRoot]++;
 * }
 * }
 * }
 * public int find(int i) {
 * if (parent[i] != i) {
 * parent[i] = find(parent[i]);
 * }
 * return parent[i];
 * }
 * private void print(int[] arr) {
 * for (int num : arr) {
 * System.out.print(num + " ");
 * }
 * System.out.println();
 * }
 * }
 * 
 * public String smallestEquivalentString(String s1, String s2, String baseStr)
 * {
 * Union union = new Union(26);
 * for (int i = 0; i < s1.length(); i++) {
 * char ch1 = s1.charAt(i);
 * char ch2 = s2.charAt(i);
 * union.union(ch1 - 'a', ch2 - 'a');
 * }
 * System.out.println("Printing ranks of character: ");
 * union.print(union.rank);
 * System.out.println("Printing parent of character: ");
 * union.print(union.parent);
 * StringBuilder sb = new StringBuilder();
 * for (int i = 0; i < baseStr.length(); i++) {
 * int root = union.find(baseStr.charAt(i) - 'a');
 * int smallestChar = root;
 * for (int j = 0; j < 26; j++) {
 * if (union.find(j) == root && j < smallestChar) {
 * smallestChar = j;
 * }
 * }
 * sb.append((char) (smallestChar + 'a'));
 * }
 * return sb.toString();
 * }
 * }
 */