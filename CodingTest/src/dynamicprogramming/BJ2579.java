package dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ2579 {

	static int[] dp, array;
	static int N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		array = new int[N + 1];
		dp = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			array[i] = Integer.parseInt(br.readLine());
		}

		dp[1] = array[1];

		if (N >= 2) {
			dp[2] = dp[1] + array[2];
			for (int i = 3; i <= N; i++) {
				dp[i] = Math.max(dp[i - 2], dp[i - 3] + array[i - 1]) + array[i];
			}
		}

		System.out.println(dp[N]);
	}
}
