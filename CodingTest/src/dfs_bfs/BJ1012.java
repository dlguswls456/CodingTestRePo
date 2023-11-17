package dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1012 {

	public static int[][] cabbages;
	public static boolean[][] isVisited;
	public static int cntWarms;
	public static int N, M;
	public static int[] dx = { 1, -1, 0, 0 };
	public static int[] dy = { 0, 0, 1, -1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int i = 0; i < T; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			cabbages = new int[N][M];
			isVisited = new boolean[N][M];
			cntWarms = 0;
			int cntCabbages = Integer.parseInt(st.nextToken());
			for (int j = 0; j < cntCabbages; j++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				cabbages[x][y] = 1;
			}

			for (int j = 0; j < N; j++) {
				for (int k = 0; k < M; k++) {
					if (cabbages[j][k] == 1) {
						dfs(j, k);
						cntWarms++;
					}
				}
			}
			
			System.out.println(cntWarms);
		}
	}

	public static void dfs(int x, int y) {
		isVisited[x][y] = true;
		cabbages[x][y] = 0;

		for (int i = 0; i < 4; i++) {
			int newX = x + dx[i];
			int newY = y + dy[i];
			if (isValid(newX, newY)) {
				if (!isVisited[newX][newY]) {
					if (cabbages[newX][newY] == 1)
						dfs(newX, newY);
				}
			}
		}

	}

	public static boolean isValid(int x, int y) {
		if (x < 0 || x >= N || y < 0 || y >= M) {
			return false;
		}
		return true;
	}
}