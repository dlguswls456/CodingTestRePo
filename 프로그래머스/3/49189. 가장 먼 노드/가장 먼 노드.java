import java.util.*;

class Solution {
    static int[] minDis;
    static boolean[] visited;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
    static class Node{
        int node;
        int dis;
        
        Node(int node, int dis){
            this.node = node;
            this.dis = dis;
        }
    }
    
    public int solution(int n, int[][] edge) {
        int answer = 0;
        minDis = new int[n+1];
        visited = new boolean[n+1];
        for(int i=1;i<=n;i++){
            minDis[i] = Integer.MAX_VALUE;
        }
        
        for(int i=0;i<=n;i++){
            graph.add(new ArrayList<Integer>());
        }
        
        for(int[] e: edge){
            graph.get(e[0]).add(e[1]);
            graph.get(e[1]).add(e[0]);
        }
        
        int max = 0;
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(new Node(1,0));
        minDis[1] = 0;
        while(!queue.isEmpty()){
            Node node = queue.poll();
            visited[node.node] = true;
            
            for(int nextNode: graph.get(node.node)){
                if(visited[nextNode]){
                   continue; 
                }
                
                if(minDis[nextNode] > node.dis + 1){
                    minDis[nextNode] = node.dis + 1;
                    queue.add(new Node(nextNode, minDis[nextNode]));
                    max = Integer.max(minDis[nextNode], max);
                }
            }
        }
        
        for(int dis: minDis){
            if(dis == max){
                answer++;
            }
        }
                
        return answer;
    }
}