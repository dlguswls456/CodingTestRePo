package dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ11055 {

	static int N, result;
	static int[] numbers, dp;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		numbers = new int[N + 1];
		dp = new int[N + 1];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 1; i <= N; i++) {
			for (int j = 0; j < i; j++) {
				if (numbers[i] > numbers[j]) {
					dp[i] = Math.max(dp[j], dp[i]);
				}
			}
			dp[i] += numbers[i];
			result = Math.max(result, dp[i]);
		}

		System.out.println(result);
	}

}
