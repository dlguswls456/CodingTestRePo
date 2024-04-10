package dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ2240 {

	static int[] plum;
	static int[][] dp;
	static int T, W;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());

		plum = new int[T + 1];
		dp = new int[W + 1][T + 1];

		for (int i = 1; i <= T; i++) {
			plum[i] = Integer.parseInt(br.readLine());
			if (plum[i] == 1) {
				dp[0][i] = dp[0][i - 1] + 1;
			} else {
				dp[0][i] = dp[0][i - 1];
			}
		}

		for (int i = 1; i <= W; i++) {
			for (int j = 1; j <= T; j++) {
				if (i % 2 == 0) { // 자리1
					if (plum[j] == 1) {
						dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j - 1]) + 1;
					} else {
						dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
					}
				} else { // 자리2
					if (plum[j] == 2) {
						dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j - 1]) + 1;
					} else {
						dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
					}
				}
			}
		}
		
//		for (int i = 0; i <= W; i++) {
//			for (int j = 1; j <= T; j++) {
//				System.out.print(dp[i][j] + " ");
//			}
//			System.out.println();
//		}
		
		System.out.println(dp[W][T]);
	}

}
