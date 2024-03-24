package dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ2206 {

	static int[][] map;
	static boolean[][][] isVisited;
	static int R, C, minCnt = Integer.MAX_VALUE;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new int[R][C];
		isVisited = new boolean[R][C][2];
		for (int i = 0; i < R; i++) {
			String line = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = line.charAt(j) - '0';
			}
		}

		bfs();
		if (minCnt == Integer.MAX_VALUE) {
			minCnt = -1;
		}
		System.out.println(minCnt);

	}

	public static void bfs() {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(0);// x
		queue.add(0);// y
		queue.add(1);// cnt
		queue.add(-1);// broken or not
		isVisited[0][0][0] = true;

		while (!queue.isEmpty()) {
			int x = queue.poll();
			int y = queue.poll();
			int cnt = queue.poll();
			int isBroken = queue.poll();

			if (x == R - 1 && y == C - 1) {
				minCnt = cnt;
				return;
			}

			for (int i = 0; i < 4; i++) {
				int newX = x + dx[i];
				int newY = y + dy[i];

				if (!isValid(newX, newY)) {
					continue;
				}

				if (map[newX][newY] == 0) { // 길
					if (isBroken == -1 && !isVisited[newX][newY][0]) { // 한번도 부순적 없는 경우
						queue.add(newX);
						queue.add(newY);
						queue.add(cnt + 1);
						queue.add(isBroken);
						isVisited[newX][newY][0] = true;
					} else if (isBroken == 1 && !isVisited[newX][newY][1]) { // 부순적 있는 경우
						queue.add(newX);
						queue.add(newY);
						queue.add(cnt + 1);
						queue.add(isBroken);
						isVisited[newX][newY][1] = true;
					}
				} else { // 벽
					if (isBroken == -1) {
						queue.add(newX);
						queue.add(newY);
						queue.add(cnt + 1);
						queue.add(1);
						isVisited[newX][newY][1] = true;
					}
				}

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
