import java.util.*;

class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        
        int A = brown-4;
        double tempRt = Math.sqrt(brown*brown -8*brown + 16 -16*yellow);
        
        int result1 = (int) (A + tempRt) / 4;
        int result2 = (int) (A - tempRt) / 4;
        
        answer[0] = result1+2;
        answer[1] = result2+2;
        return answer;
    }
}