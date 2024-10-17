import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static int[][] dp;
    static int[] finalDp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        finalDp = new int[N + 1];
        dp = new int[N + 1][10];
        Arrays.fill(dp[1], 1);
        finalDp[1] = 10;

        for (int i = 2; i <= N; i++) {
            dp[i][0] = finalDp[i - 1] % 10007;
            int sum = dp[i][0];
            for (int j = 1; j < 10; j++) {
                dp[i][j] = dp[i][j - 1] % 10007 - dp[i - 1][j - 1] % 10007;
                if (dp[i][j] < 0) {
                    dp[i][j] += 10007;
                }
                dp[i][j] %= 10007;
                sum += dp[i][j];
            }
            finalDp[i] = sum;
        }

        System.out.print(finalDp[N] % 10007);
    }

}

