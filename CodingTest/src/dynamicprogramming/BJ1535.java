package dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1535 {

	static int N, sejunPower = 100, sejunHappiness = 0;
	static int[][] dp;
	static int[] power, happiness;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		power = new int[N + 1];
		happiness = new int[N + 1];

		StringTokenizer st = new StringTokenizer(br.readLine());
		StringTokenizer st2 = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			power[i] = Integer.parseInt(st.nextToken());
			happiness[i] = Integer.parseInt(st2.nextToken());
		}

		dp = new int[N + 1][100];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j < 100; j++) {
				if (power[i] > j) {
					dp[i][j] = dp[i - 1][j];
				} else {
					dp[i][j] = Math.max(happiness[i] + dp[i - 1][j - power[i]], dp[i - 1][j]);
				}
			}
		}
		
		System.out.println(dp[N][99]);
	}

}
