import java.util.*;

class Solution {
    
    static int[] A, B;
    static int a, b;
    
    public int solution(int[] topping) {
        int answer = 0;
        
        int max = 0;
        for(int t: topping){
            max = Math.max(max, t);
        }
        
        A = new int[max+1];
        B = new int[max+1];
        
        for(int t: topping){
            A[t]++;
        }
        for(int elem: A){
            if(elem != 0){
                a++;
            }
        }
        
        for(int t: topping) {
            B[t]++;
            if(B[t] == 1){
                b++;
            }
            
            A[t]--;
            if(A[t] == 0){
                a--;
            }
            
            if(a==b){
                answer++;
            }
        }
        
        return answer;
    }
}