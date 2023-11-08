package dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Ex05_01 {

	static class Node {
		int x, y;

		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static int[][] isBlocked;
	public static int cnt = 0;
	public static int N;
	public static int M;
	public static Queue<Node> queue = new LinkedList<Node>();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		isBlocked = new int[N][M];

		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				isBlocked[i][j] = line.charAt(j) - '0';
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (bfs(i, j)) {
					cnt++;
				}
			}
		}

		System.out.print(cnt);
	}

	public static boolean bfs(int x, int y) {
		int[] dx = { 1, -1, 0, 0 };
		int[] dy = { 0, 0, 1, -1 };

		if (isBlocked[x][y] == 0) {
			queue.add(new Node(x, y));
			isBlocked[x][y] = 1;

			while (!queue.isEmpty()) {
				Node nowNode = queue.remove();
				for (int i = 0; i < 4; i++) {
					int newX = nowNode.x + dx[i];
					int newY = nowNode.y + dy[i];
					if (newX >= 0 && newX < N && newY >= 0 && newY < M) {
						if (isBlocked[newX][newY] == 0) {
							queue.add(new Node(newX, newY));
							isBlocked[newX][newY] = 1;
						}
					}
				}
			}
			return true;
		}

		return false;
	}

	public static boolean findIce(int x, int y) {
		if (x <= -1 || x >= N || y <= -1 || y >= M) {
			return false;
		}

		if (isBlocked[x][y] == 0) {
			isBlocked[x][y] = 1;
			findIce(x - 1, y);
			findIce(x + 1, y);
			findIce(x, y + 1);
			findIce(x, y - 1);
			return true;
		}

		return false;
	}

}