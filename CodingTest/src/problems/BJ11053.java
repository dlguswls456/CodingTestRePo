package problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//LIS with dp
//https://sskl660.tistory.com/89
public class BJ11053 {

	static int N, maxCnt;
	static int[] numbers;
	static int[] dp;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		numbers = new int[N + 1];
		dp = new int[N + 1];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}

		dp[1] = 1;
		maxCnt = 1;
		for (int i = 2; i <= N; i++) {
			for (int j = 0; j < i; j++) {
				if (numbers[j] < numbers[i]) {
					dp[i] = Math.max(dp[j] + 1, dp[i]);
				}
			}
			maxCnt = Math.max(maxCnt, dp[i]);
		}

		System.out.println(maxCnt);
	}

}
