package dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ2573 {

	static int[][] ice;
	static boolean[][] isVisited;
	static int R, C, time, size, mountain;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static ArrayList<int[]> edges = new ArrayList<int[]>();
	static boolean flag = false;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		ice = new int[R][C];
		isVisited = new boolean[R][C];
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				ice[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int tempMountain = 0;
		while (true) {
			int tempSize = cntIce();
			if (tempSize == 0) { // 다 녹았으면 스탑
				break;
			}

			time++;
			// 산 개수 구하기
			isVisited = new boolean[R][C];
			mountain = tempMountain;
			tempMountain = 0;
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					if (!isVisited[i][j] && ice[i][j] > 0) {
						dfs(i, j);
						tempMountain++;
					}
				}
			}

			if (time > 1 && tempMountain > mountain) {
				flag = true;
				break;
			}

			// 물이 닿으면 녹음
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					if (ice[i][j] > 0) {
						int water = 0;
						for (int k = 0; k < 4; k++) {
							int newX = i + dx[k];
							int newY = j + dy[k];
							if (isValid(newX, newY) && ice[newX][newY] == 0) {
								water++;
							}
						}
						edges.add(new int[] { i, j, water });
					}
				}
			}

			for (int[] edge : edges) {
				ice[edge[0]][edge[1]] -= edge[2];
				if (ice[edge[0]][edge[1]] < 0) {
					ice[edge[0]][edge[1]] = 0;
				}
			}
			edges.clear();

		}

		if(flag) {
			System.out.println(time - 1);			
		}else {
			System.out.println(0);			
		}
	}

	public static int cntIce() {
		int cnt = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (ice[i][j] > 0) {
					cnt++;
				}
			}
		}

		return cnt;
	}

	public static void dfs(int x, int y) {
		isVisited[x][y] = true;

		for (int i = 0; i < 4; i++) {
			int newX = x + dx[i];
			int newY = y + dy[i];
			if (isValid(newX, newY) && !isVisited[newX][newY] && ice[newX][newY] > 0) {
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
