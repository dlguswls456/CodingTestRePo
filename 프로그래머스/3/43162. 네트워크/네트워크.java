import java.util.*;

class Solution {
    static boolean[] visited;
    static int result = 0;
    static int N;

    public int solution(int n, int[][] computers) {
        visited = new boolean[n];
        N = n;
            
        for(int i=0;i<N;i++) {
            if(!visited[i]) {
                bfs(i, computers);
                result++;
            }
        }
        
        return result;
    }
    
    public static void bfs(int start, int[][] computers){
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(start);
        
        while(!queue.isEmpty()){
            int node = queue.poll();
            
            for(int i=0;i<N;i++){
                if(i==node){
                    continue;
                }
                
                if(computers[node][i] == 1 && !visited[i]){
                    queue.add(i);
                }
            }
            
            visited[node] = true;
        }
    }
}