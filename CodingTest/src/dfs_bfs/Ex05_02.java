package dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Ex05_02 {

	static class Node {
		int x, y;

		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static int[][] isRoad;
	public static int[][] distance;
	public static int N;
	public static int M;
	public static Queue<Node> queue = new LinkedList<Node>();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		isRoad = new int[N][M];
		distance = new int[N][M];

		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				isRoad[i][j] = line.charAt(j) - '0';
			}
		}

		bfs(0, 0);

		System.out.print(distance[N - 1][M - 1]);
	}

	public static void bfs(int x, int y) {
		int[] dx = { 1, 0, 0, -1 };
		int[] dy = { 0, 1, -1, 0 };

		if (isRoad[x][y] == 1) {
			queue.add(new Node(x, y));
			isRoad[x][y] = 0;
			distance[x][y] = 1;

			while (!queue.isEmpty()) {
				Node nowNode = queue.remove();
				for (int i = 0; i < 4; i++) {
					int newX = nowNode.x + dx[i];
					int newY = nowNode.y + dy[i];
					if (newX >= 0 && newX < N && newY >= 0 && newY < M) {
						if (isRoad[newX][newY] == 1) {
							queue.add(new Node(newX, newY));
							isRoad[newX][newY] = 0;
							distance[newX][newY] = distance[nowNode.x][nowNode.y] + 1;
						}
					}
				}
			}
		}

	}

}