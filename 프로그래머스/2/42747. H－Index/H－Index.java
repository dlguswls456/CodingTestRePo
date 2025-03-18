import java.util.*;

class Solution {
    static ArrayList<Integer> list = new ArrayList<Integer>();
    
    public int solution(int[] citations) {
        int answer = 0;
        
        for(int citation:citations){
            list.add(citation);
        }
        list.sort(null);
        
        int start = 0;
        int end = list.get(list.size()-1);
        
        int overH = 0;
        int lessH = 0;
        while(start+1<end) {
            int mid = (start+end) / 2;
        
            overH = cntOverH(mid, list);
            lessH = list.size() - overH;
            
            if(overH >= mid && lessH <= mid){
                start = mid;
                end = end;
                answer = mid;
            }else {
                start = start;
                end = mid;
            }
        }
        
        return answer;
    }
    
    static int cntOverH(int bound, ArrayList<Integer> list) {
        for(int i=0;i<list.size();i++){
            if(list.get(i) >= bound){
                return list.size()-i; 
            }
        }
        
        return 0;
    }
}