import java.util.*;

class Solution {
    static int N;
    static int max = Integer.MIN_VALUE;
    static boolean[] visited;
    
    public int solution(int k, int[][] dungeons) {
        int answer = -1;
        
        N = dungeons.length;
        visited = new boolean[N];
        
        find(-1,0,k,dungeons);
        return max;
    }
    
    static void find(int idx, int cnt, int energy, int[][] dungeons){
        if(idx != -1){
            visited[idx] = true;
        }
        max = Math.max(cnt, max);
        
        for(int i=0;i<N;i++){
            if(visited[i]){
                continue;
            }
            
            int minE = dungeons[i][0];
            if(energy >= minE){ // 가능
                find(i, cnt+1, energy - dungeons[i][1], dungeons);
            }
        }
        if(idx != -1){
            visited[idx] = false;
        }
    }
}