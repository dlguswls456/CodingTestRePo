import java.util.*;

class Solution {
    
    static PriorityQueue<Integer> queue = new PriorityQueue<>();
    
    public int solution(int[] scoville, int K) {
        int answer = 0;
        
        for(int elem: scoville){
            queue.add(elem);
        }
        
        while(queue.size() >= 2 && queue.peek() < K){
            int min1 = queue.poll();
            int min2 = queue.poll();
            
            int newS = min1 + 2*min2;
            
            queue.add(newS);
            answer++;
        }
        
        if(queue.poll() < K){
            answer = -1;
        }
        
        return answer;
    }
}