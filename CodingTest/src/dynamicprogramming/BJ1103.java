package dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1103 {

	static int[][] map, dp;
	static boolean[][] visited;
	static int N, M, maxResult;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		dp = new int[N][M];
		visited = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				if (line.charAt(j) == 'H') {
					map[i][j] = 10;
				} else {
					map[i][j] = line.charAt(j) - '0';
				}

				dp[i][j] = -1;
			}
		}

		dfs(0, 0);
		if (maxResult != -1) {
			maxResult = dp[0][0];
		}
		System.out.println(maxResult);

	}

	public static int dfs(int x, int y) {
		visited[x][y] = true;

		if (dp[x][y] == -1) {
			dp[x][y] = 0;
		}

		for (int i = 0; i < 4; i++) {
			int newX = x + dx[i] * map[x][y];
			int newY = y + dy[i] * map[x][y];

			if (!isValid(newX, newY) || map[newX][newY] == 10) {
				continue;
			}

			if (visited[newX][newY]) {
				maxResult = -1;
				return -1;
			}

			if (dp[newX][newY] >= 0) {
				dp[x][y] = Math.max(dp[newX][newY], dp[x][y]);
			} else {
				dp[x][y] = Math.max(dfs(newX, newY), dp[x][y]);
			}

		}

		visited[x][y] = false;

		if (maxResult == -1) {
			return -1;
		} else {
			return ++dp[x][y];
		}
	}

	public static boolean isValid(int x, int y) {
		if (x < 0 || x >= N || y < 0 || y >= M) {
			return false;
		}
		return true;
	}
}
