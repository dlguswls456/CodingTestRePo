import java.util.*;

class Solution {
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
    static PriorityQueue<Node> queue = new PriorityQueue<Node>();
    static int N, wordLen;
    static boolean[] visited;
    
    static class Node implements Comparable<Node>{
        int node;
        int dis;
        
        Node(int node, int dis){
            this.node = node;
            this.dis = dis;
        }
        
        public int compareTo(Node o){
            return this.dis - o.dis;
        }
    }
    
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        
        N = words.length;
        visited = new boolean[N];
        wordLen = words[0].length();
        for(int i=0;i<N;i++){
            graph.add(new ArrayList<Integer>());
        }
        
        // 한글자만 다른 단어 그래프화
        for(int i=0;i<N;i++){
            for(int j=i+1;j<N;j++){
                if(isSimilar(words[i], words[j])){
                    graph.get(i).add(j);
                    graph.get(j).add(i);
                }
            }
        }
        
        // begin 기준 한글자만 다른 단어 찾아서 bfs 시작 노드로 설정
        for(int i=0;i<N;i++){
            if(isSimilar(begin, words[i])){
                queue.add(new Node(i, 1));
            }
        }
        
        answer = bfs(target, words);
        
        return answer;
    }
    
    static int bfs(String target, String[] words){
        while(!queue.isEmpty()){
            Node node = queue.poll();
            
            if(words[node.node].equals(target)){
                return node.dis;
            }
            
            if(visited[node.node]){
                continue;
            }
            visited[node.node] = true;
            
            for(int elem: graph.get(node.node)){
                if(visited[elem]){
                    continue;
                }
                
                queue.add(new Node(elem, node.dis+1));
            }
        }
        
        return 0;
    }
    
    static boolean isSimilar(String A, String B){
        int cnt=0;
        for(int i=0;i<wordLen;i++){
            if(A.charAt(i) != B.charAt(i)){
                cnt++;
            }
            
            if(cnt > 1){
                return false;
            }
        }
        return true;
    }
}