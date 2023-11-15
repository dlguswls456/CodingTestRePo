package dfs_bfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

public class SWEA1249 {

	static int[][] roads;
	static int[][] minRoads;
	static boolean[][] isVisited;
	static int answer = 90000;
	static int N;
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int C = Integer.parseInt(br.readLine());

		for (int TC = 1; TC <= C; TC++) {
			N = Integer.parseInt(br.readLine());
			roads = new int[N][N];
			minRoads = new int[N][N];
			isVisited = new boolean[N][N];
			answer = 90000;
			for (int i = 0; i < N; i++) {
				String line = br.readLine();
				for (int j = 0; j < N; j++) {
					roads[i][j] = line.charAt(j) - '0';
					minRoads[i][j] = 90000;
				}
			}
			minRoads[0][0] = 0;
			isVisited[0][0] = true;
			findMinRootBFS(0, 0);

			bw.write("#" + TC + " " + answer);
			bw.newLine();
		}
		bw.flush();
	}

	public static void findMinRootDFS(int x, int y) {
		if (x == N - 1 && y == N - 1) {
			if (answer > minRoads[N - 1][N - 1]) {
				answer = minRoads[N - 1][N - 1];
			}
			return;
		}

		if (answer <= minRoads[x][y]) {
			return;
		}

		for (int i = 0; i < 4; i++) {
			int newx = x + dx[i];
			int newy = y + dy[i];
			if (newx < 0 || newx >= N || newy < 0 || newy >= N) {
				continue;
			}
			if (!isVisited[newx][newy] || minRoads[newx][newy] > minRoads[x][y] + roads[newx][newy]) {
				minRoads[newx][newy] = minRoads[x][y] + roads[newx][newy];
				isVisited[newx][newy] = true;
				findMinRootDFS(newx, newy);
			}
		}
	}

	static class Location {
		int x, y;

		Location(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void findMinRootBFS(int x, int y) {
		Queue<Location> queue = new LinkedList<Location>();
		queue.add(new Location(x, y));
		isVisited[x][y] = true;

		while (!queue.isEmpty()) {
			Location now = queue.peek();
			for (int i = 0; i < 4; i++) {
				int newx = now.x + dx[i];
				int newy = now.y + dy[i];
				if (newx < 0 || newx >= N || newy < 0 || newy >= N) {
					continue;
				}
				if (!isVisited[newx][newy] || minRoads[newx][newy] > minRoads[now.x][now.y] + roads[newx][newy]) {
					minRoads[newx][newy] = minRoads[now.x][now.y] + roads[newx][newy];
					queue.add(new Location(newx, newy));
					isVisited[newx][newy] = true;
				}
			}
			queue.remove();
		}

		answer = minRoads[N - 1][N - 1];

	}

}