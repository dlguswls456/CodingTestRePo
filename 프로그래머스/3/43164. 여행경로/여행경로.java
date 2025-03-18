import java.util.*;

class Solution {
    static HashMap<String, ArrayList<Path>> graph = new HashMap<String, ArrayList<Path>>();
    static boolean[] visited;
    static int N;
    
    static class Path implements Comparable<Path>{
        int idx;
        String start;
        String end;
        
        Path(int idx, String start, String end){
            this.idx = idx;
            this.start = start;
            this.end = end;
        }
        
        public int compareTo(Path o){
            return this.end.compareTo(o.end);
        }
        
    }
    
    public String[] solution(String[][] tickets) {
        N = tickets.length;
        visited = new boolean[N];
        
        for(int i=0;i<N;i++){
            graph.put(tickets[i][0], new ArrayList<Path>());
            graph.put(tickets[i][1], new ArrayList<Path>());
        }
        
        for(int i=0;i<N;i++){
            graph.get(tickets[i][0]).add(new Path(i, tickets[i][0], tickets[i][1]));
        }
        
        for(int i=0;i<N;i++){
            graph.get(tickets[i][0]).sort(null);
        }
        
        ArrayList<String> list = new ArrayList<String>();
        //Path pastPath = null;
        for(Path path: graph.get("ICN")){
            list = new ArrayList<String>();
            list.add("ICN");
            
            // Path path = graph.get("ICN").poll();
            // if(pastPath != null){
            //     graph.get("ICN").add(pastPath);
            // }
            boolean found = dfs(1, path, list);
            if(found){
                break;
            }
            
            // else{
            //     pastPath = path;
            // }
        }
        
        String[] answer = new String[list.size()];
        for(int i=0;i<list.size();i++){
            answer[i] = list.get(i);
        }
        
        return answer;
    }
    
    public boolean dfs(int depth, Path path, ArrayList<String> tempPaths){
        if(depth == N){ // 종료 조건
            tempPaths.add(path.end);
            return true;
        }
        
        visited[path.idx] = true;
        
        // ArrayList<Path> polled = new ArrayList<Path>();
        
        for(Path nextPath : graph.get(path.end)){
        // while(!graph.get(path.end).isEmpty()){
            //Path nextPath = graph.get(path.end).poll();
            //polled.add(nextPath);
            
            if(visited[nextPath.idx]){
                continue;
            }
            
            tempPaths.add(nextPath.start);
            boolean found = dfs(depth+1, nextPath, tempPaths);
            if(found){
                return found;
            }
            tempPaths.remove(tempPaths.size() - 1);
            //graph.get(path.end).add(nextPath);
        }
        
        // for(Path p :polled){
        //     graph.get(path.end).add(p);
        // }
        visited[path.idx] = false;
        
        return false;
    }
}