import java.util.*;

public class alien_dictionary {
    public static void main(String[] args) {
        String[] words = {"baa", "abcd", "abca", "cab", "cad"};
        Solution solution = new Solution();
        System.out.println(solution.findOrder(words));
    }

    private static class Solution {
        public String findOrder(String[] words) {
            List<List<Integer>> adj = new ArrayList<>();
            boolean[] visited = new boolean[26];
            for(int i = 0 ; i < 26 ; i++) adj.add(new ArrayList<>());
            for(int i = 1 ; i < words.length ; i++){
                String prev = words[i - 1] , curr = words[i];
                for(int j = 0 ; j < Math.min(prev.length() , curr.length()) ; j++){
                    if(prev.charAt(j) != curr.charAt(j)){
                        adj.get(prev.charAt(j) - 'a').add(curr.charAt(j) - 'a');
                        visited[curr.charAt(j) - 'a'] = true;
                        visited[prev.charAt(j) - 'a'] = true;
                        ///  mark from prev_char to -> curr_char
                        break;
                    }
                }
            }

            int[] indegree = new int[26];
            for(int i = 0 ; i < 26 ; i++) if(!adj.get(i).isEmpty()) operate(i , adj , -1 , indegree , true);
            System.out.println("Visited Array: " + Arrays.toString(visited));
            System.out.println("Indegree Array: " + Arrays.toString(indegree));
            System.out.println("Adjacency List: ");
            for(int i = 0 ; i < 26 ; i++){
                if(!adj.get(i).isEmpty()){
                    System.out.print(((char)(i + 'a')) + " : ");
                    for(int node : adj.get(i)){
                        System.out.print(((char)(node + 'a')) + " ");
                    }
                    System.out.println();
                }
            }
            Queue<Integer> queue = new LinkedList<>();
            StringBuilder sb = new StringBuilder();
            while(true){
                for(int i = 0 ; i < 26 ; i++){
                    if(visited[i] && indegree[i] == 0){
                        sb.append((char)(i + 'a'));
                        queue.add(i);
                        visited[i] = false;
                    }
                }
                if(queue.isEmpty()) break;
                while(!queue.isEmpty()){
                    int node = queue.poll();
                    operate(node , adj , -1 , indegree , false);
                }
            }
            return sb.toString();
        }

        private void operate(int currentNode , List<List<Integer>> adj , int parent , int[] indegree , boolean increase){
            for(int node : adj.get(currentNode)){
                if(parent != node){
                    if(increase) indegree[node]++;
                    else  indegree[node]--;
                    operate(node, adj, currentNode, indegree , increase);
                }
            }
        }

    }
// Jasmine Callipygian
}
