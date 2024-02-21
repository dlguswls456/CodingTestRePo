package dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1932 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] triangle = new int[N + 1][];
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			triangle[i] = new int[i];
			for (int j = 0; j < i; j++) {
				triangle[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int[][] dp = new int[N + 1][];
		dp[1] = new int[1];
		dp[1][0] = triangle[1][0];
		for (int i = 2; i <= N; i++) {
			dp[i] = new int[i];
			for (int j = 0; j < i; j++) {
				if (j == 0) {
					dp[i][j] = triangle[i][j] + dp[i - 1][j];
				} else if (j == i - 1) {
					dp[i][j] = triangle[i][j] + dp[i - 1][j - 1];
				} else {
					dp[i][j] = triangle[i][j] + Math.max(dp[i - 1][j - 1], dp[i - 1][j]);
				}
			}
		}

		int result = 0;
		for (int i = 0; i < N; i++) {
			result = Math.max(result, dp[N][i]);
		}

		System.out.println(result);
	}

}
