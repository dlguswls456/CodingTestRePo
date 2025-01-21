import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] time, price;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        time = new int[N + 1];
        price = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken());
            price[i] = Integer.parseInt(st.nextToken());
        }
        
        int[] dp = new int[N + 2];
        for (int i = N; i >= 1; i--) {
            int idx = i + time[i];
            if (idx >= N + 2) {
                dp[i] = dp[i+1];
                continue;
            }

            dp[i] = Math.max(dp[i+1], dp[idx] + price[i]);
        }

        System.out.println(dp[1]);
    }
}