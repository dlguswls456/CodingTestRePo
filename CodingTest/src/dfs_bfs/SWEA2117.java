package dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA2117 {

	static int N, M, result, maxIncome;
	static int[][] map;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static boolean[][] isVisited;
	static Queue<Integer> queue;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			map = new int[N][N];
			isVisited = new boolean[N][N];
			result = 0;
			maxIncome = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 1) {
						maxIncome += M;
					}
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					bfs(i, j);
				}
			}
			System.out.println("#" + t + " " + result);
		}

	}

	public static void bfs(int i, int j) {
		queue = new LinkedList<Integer>();
		isVisited = new boolean[N][N];
		int cntIncome = map[i][j];
		result = Math.max(result, cntIncome);
		int cost = 1; // K가 1일 때

		queue.add(i);
		queue.add(j);
		queue.add(1);
		isVisited[i][j] = true;

		int preK = 1;
		while (!queue.isEmpty()) {
			int x = queue.poll();
			int y = queue.poll();
			int k = queue.poll();

			// 거리 늘었을 때
			if (preK + 1 == k) {
				cost = k * k + (k - 1) * (k - 1);
				if (maxIncome - cost < 0) {
//                    System.out.println(preK);
					break;
				}

				if (cntIncome * M - cost >= 0) {
					result = Math.max(result, cntIncome);
				}

				preK = k;
			}

			for (int idx = 0; idx < 4; idx++) {
				int newX = x + dx[idx];
				int newY = y + dy[idx];

				if (!isValid(newX, newY) || isVisited[newX][newY])
					continue;

				if (map[newX][newY] == 1) {
					cntIncome++;
				}

				queue.add(newX);
				queue.add(newY);
				queue.add(k + 1);
				isVisited[newX][newY] = true;

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
