import java.util.*;

class Solution {
    static boolean[][] visited;
    static int[][] minLength;
    static int N, M;
    static int result = Integer.MAX_VALUE;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    
    public int solution(int[][] maps) {
        N = maps.length;
        M = maps[0].length;
        visited = new boolean[N][M];
        minLength = new int[N][M];
        
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                minLength[i][j] = Integer.MAX_VALUE;
            }
        }
        
        bfs(maps);
        
        if(minLength[N-1][M-1] == Integer.MAX_VALUE){
            minLength[N-1][M-1] = -1;
        }
        
        return minLength[N-1][M-1];
    }
    
    public static void bfs(int[][] maps){
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(0);
        queue.add(0);
        queue.add(1);
        
        visited[0][0] = true;
        minLength[0][0] = 1;
        
        while(!queue.isEmpty()){
            int x = queue.poll();
            int y = queue.poll();
            int cnt = queue.poll();
            
            if(x==N-1 && y== M-1){
                minLength[x][y] = cnt;
                return;
            }
            
            for(int i=0;i<4;i++){
                int newX = x+dx[i];
                int newY = y+dy[i];
                
                if(isValid(newX, newY) && !visited[newX][newY] && maps[newX][newY] == 1){
                    queue.add(newX);
                    queue.add(newY);
                    queue.add(cnt+1);
                    
                    visited[newX][newY] = true;
                    minLength[x][y] = cnt+1;
                }
            }
        }
    }
    
    public static boolean isValid(int x, int y){
        if(x<0 || x>=N || y<0 || y>=M){
            return false;
        }
        return true;
    }
}