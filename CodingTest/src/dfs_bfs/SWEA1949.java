package dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA1949 {

	static int[][] map;
	static int N, K, maxHeight, result;
	static boolean[][] isVisited;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			maxHeight = 0;
			result = Integer.MIN_VALUE;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (maxHeight < map[i][j]) {
						maxHeight = map[i][j];
					}
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (maxHeight == map[i][j]) {
						isVisited = new boolean[N][N];
						dfs(i, j, 1, false);
					}
				}
			}

			System.out.println("#" + t + " " + result);
		}

	}

	public static void dfs(int x, int y, int cnt, boolean isUsed) {
		result = Math.max(result, cnt);
		isVisited[x][y] = true;
		for (int i = 0; i < 4; i++) {
			int newX = x + dx[i];
			int newY = y + dy[i];
			if (isValid(newX, newY) && !isVisited[newX][newY]) {
				if (map[newX][newY] < map[x][y]) {
					dfs(newX, newY, cnt + 1, isUsed);

				} else {
					if (!isUsed) {
						for (int j = 1; j <= K; j++) {
							if (map[newX][newY] - j < map[x][y]) {
								map[newX][newY] -= j;
								dfs(newX, newY, cnt + 1, true);
								map[newX][newY] += j;
							}
						}
					}
				}
			}
		}
		isVisited[x][y] = false;
	}

	public static boolean isValid(int x, int y) {
		if (x < 0 || x >= N || y < 0 || y >= N) {
			return false;
		}
		return true;
	}

}
