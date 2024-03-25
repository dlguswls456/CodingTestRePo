package dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ2096 {

	static int[][] minNumbers, maxNumbers;
	static int N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		minNumbers = new int[N][3];
		maxNumbers = new int[N][3];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				minNumbers[i][j] = Integer.parseInt(st.nextToken());
				maxNumbers[i][j] = minNumbers[i][j];
			}
		}

		for (int i = 1; i < N; i++) {
			minNumbers[i][0] += Math.min(minNumbers[i - 1][0], minNumbers[i - 1][1]);
			minNumbers[i][1] += Math.min(Math.min(minNumbers[i - 1][0], minNumbers[i - 1][1]), minNumbers[i - 1][2]);
			minNumbers[i][2] += Math.min(minNumbers[i - 1][1], minNumbers[i - 1][2]);

			maxNumbers[i][0] += Math.max(maxNumbers[i - 1][0], maxNumbers[i - 1][1]);
			maxNumbers[i][1] += Math.max(Math.max(maxNumbers[i - 1][0], maxNumbers[i - 1][1]), maxNumbers[i - 1][2]);
			maxNumbers[i][2] += Math.max(maxNumbers[i - 1][1], maxNumbers[i - 1][2]);
		}

		int minResult = Math.min(Math.min(minNumbers[N - 1][0], minNumbers[N - 1][1]), minNumbers[N - 1][2]);
		int maxResult = Math.max(Math.max(maxNumbers[N - 1][0], maxNumbers[N - 1][1]), maxNumbers[N - 1][2]);
		System.out.println(maxResult + " " + minResult);
	}

}
