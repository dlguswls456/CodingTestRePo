package shortestpath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1389_01 {

	static int N, M;
	static int[][] nodes, minLength;
	static boolean[] isVisited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		nodes = new int[N + 1][N + 1];
		minLength = new int[N + 1][N + 1];
		M = Integer.parseInt(st.nextToken());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());

			nodes[A][B] = 1;
			nodes[B][A] = 1;
			minLength[A][B] = 1;
			minLength[B][A] = 1;
		}

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (i == j) {
					continue;
				}
				if (minLength[i][j] == 0) {
					minLength[i][j] = Integer.MAX_VALUE;
				}
			}
		}

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (j == i) {
					continue;
				}
				for (int k = 1; k <= N; k++) {
					if (k == i || k == j) {
						continue;
					}
					if (minLength[j][i] == Integer.MAX_VALUE || minLength[i][k] == Integer.MAX_VALUE) {
						continue;
					}
					minLength[j][k] = Math.min(minLength[j][k], minLength[j][i] + minLength[i][k]);
				}
			}

		}

		int minPerson = 0;
		int minSumCount = Integer.MAX_VALUE;
		for (int i = 1; i <= N; i++) {
			int sum = 0;
			for (int j = 1; j <= N; j++) {
				if (minLength[i][j] == Integer.MAX_VALUE) {
					continue;
				}
				sum += minLength[i][j];
			}
			if (minSumCount > sum) {
				minPerson = i;
				minSumCount = sum;
			}
		}

		System.out.println(minPerson);
	}
}

