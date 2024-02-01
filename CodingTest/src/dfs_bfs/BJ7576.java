package dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ7576 {

	static int[][] tomato, days;
	static boolean[][] isVisited;
	static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		tomato = new int[N][M];
		days = new int[N][M];

		boolean isAllWellDone = true;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				tomato[i][j] = Integer.parseInt(st.nextToken());
				if (tomato[i][j] != 1) {
					days[i][j] = Integer.MAX_VALUE;
				}

				if (tomato[i][j] == 0) {
					isAllWellDone = false;
				}
			}
		}

		if (isAllWellDone) {
			System.out.println(0);
			return;
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (tomato[i][j] == 1) {
					isVisited = new boolean[N][M];
					cntDays(i, j);
				}
			}
		}

		int maxResult = Integer.MIN_VALUE;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(tomato[i][j] == -1) {
					continue;
				}
				maxResult = Math.max(maxResult, days[i][j]);
			}
		}

		if (maxResult == Integer.MAX_VALUE) {
			maxResult = -1;
		}
		System.out.println(maxResult);

	}

	public static void cntDays(int x, int y) {
		int[] dx = { -1, 1, 0, 0 };
		int[] dy = { 0, 0, -1, 1 };
		Queue<Integer> queue = new LinkedList<Integer>();
		isVisited[x][y] = true;
		queue.add(x);
		queue.add(y);

		while (!queue.isEmpty()) {
			int currentX = queue.poll();
			int currentY = queue.poll();
			for (int i = 0; i < 4; i++) {
				int newX = currentX + dx[i];
				int newY = currentY + dy[i];
				if (isValid(newX, newY) && !isVisited[newX][newY] && tomato[newX][newY] == 0) {
					isVisited[newX][newY] = true;
					queue.add(newX);
					queue.add(newY);

					days[newX][newY] = Math.min(days[currentX][currentY] + 1, days[newX][newY]);
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
