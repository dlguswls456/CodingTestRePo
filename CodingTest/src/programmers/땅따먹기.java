package programmers;

class 땅따먹기 {
    int solution(int[][] land) {
        int answer = 0;
        int[][] dp = new int[land.length][4];
        
        dp[0] = land[0];
        for(int row=1;row<land.length;row++){
            for(int i=0; i<4;i++){
                int max = 0;
                for(int j=0;j<4;j++){
                    if(i==j) continue;
                    max = Math.max(max, dp[row-1][j]);
                }
                dp[row][i] = max + land[row][i];
                answer = Math.max(answer, dp[row][i]);
            }   
            
        }
        
        return answer;
    }
}