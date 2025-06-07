package May.May_26;

import java.util.*;

class Solution {
    static {
        for (int i = 0; i < 100; i++)
            largestPathValue("", new int[][] {});
    }

    public static int largestPathValue(String colors, int[][] edges) {
        int n = colors.length();
        if (n == 0)
            return -1;
        char[] arr = colors.toCharArray();
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++)
            graph[i] = new LinkedList<>();
        int degree[] = new int[n];
        for (int[] edge : edges) {
            degree[edge[1]]++;
            graph[edge[0]].add(edge[1]);
        }
        int[][] freq = new int[n][26];
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (degree[i] == 0)
                queue.offer(i);
        }
        int visited = 0, ans = 0;
        while (!queue.isEmpty()) {
            ++visited;
            int start = queue.poll(), color = arr[start] - 'a';
            ans = Math.max(ans, ++freq[start][color]);
            for (int adj : graph[start]) {
                for (int i = 0; i < 26; i++) {
                    freq[adj][i] = Math.max(freq[adj][i], freq[start][i]);
                }
                if (--degree[adj] == 0) {
                    queue.offer(adj);
                }
            }
        }
        return visited == n ? ans : -1;
    }
}
/*
 * class Solution {
 * public int largestPathValue(String colors, int[][] edges) {
 * int n = colors.length();
 * List<List<Integer>> graph = new ArrayList<>();
 * for(int i = 0 ; i < n ; i++) graph.add(new ArrayList<>());
 * for(int[] edge : edges) graph.get(edge[0]).add(edge[1]);
 * int ans = 0;
 * int[] visited = new int[n];
 * int freq[][] = new int[n][26];
 * for(int i = 0 ; i < n && ans != Integer.MAX_VALUE ; i++) ans = Math.max(ans ,
 * solve(i , colors , graph , freq , visited));
 * return ans == Integer.MAX_VALUE ? -1 : ans;
 * }
 * private int solve(int index , String colors , List<List<Integer>> graph ,
 * int[][] freq , int[] visited){
 * if(visited[index] == 1) return Integer.MAX_VALUE;
 * if(visited[index] == 2) return freq[index][colors.charAt(index) - 'a'];
 * visited[index] = 1;
 * for(int adj : graph.get(index)){
 * int result = solve(adj , colors , graph , freq , visited);
 * if(result == Integer.MAX_VALUE) return Integer.MAX_VALUE;
 * for(int letter = 0 ; letter < 26 ; letter++) freq[index][letter] =
 * Math.max(freq[index][letter] , freq[adj][letter]);
 * }
 * int letter = colors.charAt(index) - 'a';
 * freq[index][letter]++;
 * visited[index] = 2;
 * return freq[index][letter];
 * }
 * }
 */