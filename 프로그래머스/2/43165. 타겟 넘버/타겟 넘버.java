import java.util.*;

class Solution {
    static int N;
    static int result = 0;
    static int targetNum;
    static int[] nums;
    
    public int solution(int[] numbers, int target) {
        N = numbers.length;
        targetNum = target;
        nums = numbers;    
        
        calcMath(0,0);
        return result;
    }
    
    public static void calcMath(int cnt, int tempResult){
        if(cnt==N){
            if(tempResult == targetNum){
                result++;
            }       
            return;
        }
        
        // 더하거나
        calcMath(cnt+1, tempResult + nums[cnt]);
        
        // 빼거나
        calcMath(cnt+1, tempResult - nums[cnt]);
    }
}