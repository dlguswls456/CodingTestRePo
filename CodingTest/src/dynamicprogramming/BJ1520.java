package dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1520 {
	static int[][] map, dp;
	static int N, M, result;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		dp = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				dp[i][j] = -1;
			}
		}

		dfs(0, 0);
		System.out.println(dp[0][0]);

	}

	public static int dfs(int x, int y) {
		if (x == N - 1 && y == M - 1) {
			return 1;
		}

		dp[x][y] = 0;
		for (int i = 0; i < 4; i++) {
			int newX = x + dx[i];
			int newY = y + dy[i];

			if (!isValid(newX, newY) || map[x][y] <= map[newX][newY]) {
				continue;
			}

			if (dp[newX][newY] != -1) { // 방문한 적 있음
				dp[x][y] += dp[newX][newY];
			} else { // 방문한 적 없음
				dp[x][y] += dfs(newX, newY);
			}

		}

		return dp[x][y];
	}

	public static boolean isValid(int x, int y) {
		if (x < 0 || x >= N || y < 0 || y >= M) {
			return false;
		}

		return true;
	}
}
