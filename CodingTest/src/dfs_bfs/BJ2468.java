package dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ2468 {

	static int[][] maps;
	static boolean[][] isVisited;
	static int N, cntSafeArea, maxHeight, result;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		maps = new int[N][N];
		isVisited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				maps[i][j] = Integer.parseInt(st.nextToken());
				maxHeight = Math.max(maxHeight, maps[i][j]);
			}
		}

		maxHeight--;
		while (maxHeight >= 0) {
			cntSafeArea = 0;
			isVisited = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!isVisited[i][j] && maps[i][j] > maxHeight) {
						dfs(i, j);
						cntSafeArea++;
					}
				}
			}
			result = Math.max(cntSafeArea, result);
			maxHeight--;
		}

		System.out.println(result);
	}

	public static void dfs(int x, int y) {
		isVisited[x][y] = true;

		for (int i = 0; i < 4; i++) {
			int newX = x + dx[i];
			int newY = y + dy[i];
			if (isValid(newX, newY) && !isVisited[newX][newY] && maps[newX][newY] > maxHeight) {
				dfs(newX, newY);
			}
		}
	}

	public static boolean isValid(int x, int y) {
		if (x < 0 || x >= N || y < 0 || y >= N) {
			return false;
		}
		return true;
	}

}
