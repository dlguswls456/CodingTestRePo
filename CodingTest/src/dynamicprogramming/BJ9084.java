package dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ9084 {
	static int N, T, price;
	static int[] cash;
	static int[][] dp;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(br.readLine());
			cash = new int[N + 1];
			dp = new int[N + 1][10001];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				cash[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(cash);

			price = Integer.parseInt(br.readLine());

			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= price; j++) {
					if (j < cash[i]) {
						dp[i][j] = dp[i - 1][j];
					} else if (j == cash[i]) {
						dp[i][j] = dp[i - 1][j] + dp[i][j - cash[i]] + 1;
					} else {
						dp[i][j] = dp[i - 1][j] + dp[i][j - cash[i]];
					}
				}
			}

			System.out.println(dp[N][price]);

		}
	}
}
