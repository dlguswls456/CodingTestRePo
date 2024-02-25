package dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ2636 {

	static int[][] cheese;
	static boolean[][] isVisited;
	static int R, C, time, size;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static ArrayList<int[]> edges = new ArrayList<int[]>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		cheese = new int[R][C];
		isVisited = new boolean[R][C];
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				if (i == 0 || i == R - 1 || j == 0 || j == C - 1) {
					cheese[i][j] = -1;
					st.nextToken();
				} else {
					cheese[i][j] = Integer.parseInt(st.nextToken());
				}
			}
		}

		while (true) {
			int tempSize = cntCheese();
			if (tempSize == 0) {
				break;
			}

			time++;
			size = tempSize;
			// 가장자리부터 dfs -> 공기 영역
			isVisited = new boolean[R][C];
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					if (!isVisited[i][j] && cheese[i][j] == -1) {
						dfs(i, j);
					}
				}
			}

			// 공기 접촉 가능한 치즈 녹임
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					if (cheese[i][j] == 1) {
						for (int k = 0; k < 4; k++) {
							int newX = i + dx[k];
							int newY = j + dy[k];
							if (isValid(newX, newY) && cheese[newX][newY] == -1) {
								edges.add(new int[] { i, j });
								break;
							}
						}
					}
				}
			}

			for (int[] edge : edges) {
				cheese[edge[0]][edge[1]] = -1;
			}
			edges.clear();

		}

		System.out.println(time);
		System.out.println(size);
	}

	public static int cntCheese() {
		int cnt = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (cheese[i][j] == 1) {
					cnt++;
				}
			}
		}

		return cnt;
	}

	public static void dfs(int x, int y) {
		cheese[x][y] = -1;
		isVisited[x][y] = true;

		for (int i = 0; i < 4; i++) {
			int newX = x + dx[i];
			int newY = y + dy[i];
			if (isValid(newX, newY) && !isVisited[newX][newY] && cheese[newX][newY] == 0) {
				dfs(newX, newY);
			}
		}
	}

	public static boolean isValid(int x, int y) {
		if (x < 0 || x >= R || y < 0 || y >= C) {
			return false;
		}
		return true;
	}

}
