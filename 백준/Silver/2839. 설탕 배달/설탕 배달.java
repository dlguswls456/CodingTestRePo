import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        dp(N);

    }

    static void dp(int N) {
        int size = Math.max(6, N + 1);
        int[] dp = new int[size];
        dp[0] = -1;
        dp[1] = -1;
        dp[2] = -1;
        dp[3] = 1;
        dp[4] = -1;
        dp[5] = 1;

        for (int i = 6; i <= N; i++) {
            int a = dp[i - 3];
            int b = dp[i - 5];

            int result = 0;
            if (a == -1 || b == -1) {
                result = Math.max(a, b);
                if (result != -1) {
                    result++;
                }
            } else {
                result = 1 + Math.min(a, b);
            }
            dp[i] = result;
        }

        System.out.println(dp[N]);
    }

    static void math(int N) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < 1667; i++)
            for (int j = 0; j < 1001; j++)
                if (3 * i + 5 * j == N)
                    min = Math.min(min, i + j);
        if (min == Integer.MAX_VALUE)
            System.out.println(-1);
        else
            System.out.println(min);
    }
}