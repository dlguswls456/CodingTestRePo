import java.util.*;

class Solution {
    static ArrayList<Integer> tempList = new ArrayList<>();
    static boolean[] visited;
    static int N;
    static HashSet<Integer> hs = new HashSet<>();
    
    public int solution(String numbers) {
        int answer = 0;
        boolean[] array;
        N = numbers.length();
        visited = new boolean[N];
        
        ArrayList<Integer> list = new ArrayList<Integer>();
        for(int i=0;i<numbers.length();i++){
            int num = numbers.charAt(i) -'0';
            list.add(num);
        }
        
        int num = 0;
        Collections.sort(list, Collections.reverseOrder());
        for(int i: list){
            num *= 10;
            num += i;
        }
        
        array = new boolean[num+1];
        array[0] = true;
        array[1] = true;
        for(int i=2;i<=num;i++){
            if(array[i]){
                continue;
            }
            
            int mul = 2;
            while(i*mul <= num){
                array[i*mul] = true;
                mul++;
            }
        }
        
        makeNum(0, 0,list);
        for(Integer elem: hs){
            if(array[elem]){
                continue;
            }
            
            answer++;
        }
    
        
        return answer;
    }
    
    public static void makeNum(int depth, int tempNum, ArrayList<Integer> list){
        if(depth > N){
            return;
        }
        
        for(int i=0;i<N;i++){
            if(!visited[i]){
                visited[i] = true;
                hs.add(tempNum*10 + list.get(i));
                makeNum(depth+1, tempNum*10 + list.get(i), list);
                visited[i] = false;
            }
        }
    }
}