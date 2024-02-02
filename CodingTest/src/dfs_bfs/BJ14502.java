package dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ14502 {

	static int N, M, maxResult = Integer.MIN_VALUE;
	static int[][] lab;
	static boolean[][] isVisited;
	static ArrayList<Node> walls = new ArrayList<Node>();

	static class Node {
		int x;
		int y;

		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		lab = new int[N][M];
		isVisited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				lab[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		backracking(0, 0, 0);

		System.out.println(maxResult);

	}

	public static int bfs() {
		int[] dx = { -1, 1, 0, 0 };
		int[] dy = { 0, 0, -1, 1 };
		Queue<Node> queue = new LinkedList<Node>();

		int[][] cpyLab = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				cpyLab[i][j] = lab[i][j];
				if (lab[i][j] == 2) {
					queue.add(new Node(i, j));
				}
			}
		}
		for (Node wall : walls) {
			cpyLab[wall.x][wall.y] = 1;
		}

		while (!queue.isEmpty()) {
			Node currentNode = queue.poll();
			for (int i = 0; i < 4; i++) {
				int newX = currentNode.x + dx[i];
				int newY = currentNode.y + dy[i];
				if (isValid(newX, newY) && cpyLab[newX][newY] == 0) {
					cpyLab[newX][newY] = 2;
					queue.add(new Node(newX, newY));
				}
			}
		}

		int safeZone = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (cpyLab[i][j] == 0) {
					safeZone++;
				}
			}
		}

		return safeZone;

	}

	public static void backracking(int x, int y, int cnt) {
		if (cnt == 3) {
			maxResult = Math.max(bfs(), maxResult);
			return;
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (i < x && j < y) { // 중복 케이스 막기 위해
					continue;
				}
				if (lab[i][j] == 0 && !isVisited[i][j]) {
					isVisited[i][j] = true;
					walls.add(new Node(i, j));
					backracking(i, j, cnt + 1);
					isVisited[i][j] = false;
					walls.remove(cnt);
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
