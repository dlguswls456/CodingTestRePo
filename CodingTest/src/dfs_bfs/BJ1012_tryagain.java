package dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1012_tryagain {

	static int T, M, N, K;
	static int[][] cabbages;
	static boolean[][] isVisited;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());

		// 입력
		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			cabbages = new int[N][M];
			isVisited = new boolean[N][M];
			for (int k = 0; k < K; k++) {
				st = new StringTokenizer(br.readLine());
				int Y = Integer.parseInt(st.nextToken());
				int X = Integer.parseInt(st.nextToken());

				cabbages[X][Y] = 1;
			}

			int cnt = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					// 방문하지 않은 배추들마다 dfs 실행
					if (!isVisited[i][j] && cabbages[i][j] == 1) {
						cntBugs(i, j);
						cnt++; // 배추 무리 카운트
					}
				}
			}

			System.out.println(cnt);

		}
	}

	// dfs
	public static void cntBugs(int x, int y) {
		int[] dx = { 1, -1, 0, 0 };
		int[] dy = { 0, 0, 1, -1 };

		// 방문 처리
		isVisited[x][y] = true;
		// 인접 배추 확인
		for (int i = 0; i < 4; i++) {
			int newX = x + dx[i];
			int newY = y + dy[i];
			if (isValid(newX, newY) && !isVisited[newX][newY] && cabbages[newX][newY] == 1) {
				cntBugs(newX, newY);
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
