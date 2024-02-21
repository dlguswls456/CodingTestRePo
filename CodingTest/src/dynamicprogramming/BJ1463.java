package dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BJ1463 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] dp = new int[N + 1];

		dp[1] = 0;
		for (int i = 2; i <= N; i++) {
			int min = Integer.MAX_VALUE;
			if (i % 3 == 0) {
				min = Math.min(dp[i / 3] + 1, min);
			}
			if (i % 2 == 0) {
				min = Math.min(dp[i / 2] + 1, min);
			}

			dp[i] = min = Math.min(dp[i - 1] + 1, min);

		}
		System.out.print(dp[N]);
	}

}