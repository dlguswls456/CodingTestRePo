package dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ16236 {

	static int[][] map;
	static boolean[][] isVisited;
	static int N, time;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static Queue<Integer> queue = new LinkedList<Integer>();// 상어 이동용
	static PriorityQueue<Node> pq = new PriorityQueue<Node>();// 먹이 선택용
	static Shark shark;

	static class Shark {
		int x;
		int y;
		int size;
		int cnt;

		public Shark(int x, int y, int size, int cnt) {
			this.x = x;
			this.y = y;
			this.size = size;
			this.cnt = cnt;
		}

		public void eating(Node newLocation) {
			x = newLocation.x;
			y = newLocation.y;
			this.cnt++;

			if (this.cnt == this.size) {
				this.size++;
				this.cnt = 0;
			}
		}
		
	}

	static class Node implements Comparable<Node> {
		int x;
		int y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Node o) {
			if (this.x == o.x) {
				return this.y - o.y;
			} else {
				return this.x - o.x;
			}
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		isVisited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 9) {
					queue.add(i);
					queue.add(j);
					queue.add(0);
					isVisited[i][j] = true;

					shark = new Shark(i, j, 2, 0);
				}
			}
		}

		findMeal();
		System.out.println(time);

	}

	public static void findMeal() {

		int preDepth = 0;
		while (!queue.isEmpty()) {
			int curX = queue.poll();
			int curY = queue.poll();
			int depth = queue.poll();

			// 거리 1단계 증가
			if (preDepth + 1 == depth) {
				if (pq.size() > 0) { // 먹을 수 있음
					Node destination = pq.poll();

					map[shark.x][shark.y] = 0;
					shark.eating(destination);
					time += depth;

					map[destination.x][destination.y] = 0;
					pq.clear();
					queue.clear();
					isVisited = new boolean[N][N];

					queue.add(destination.x);
					queue.add(destination.y);
					queue.add(0);

					preDepth = 0;

					continue;
				} else {

					preDepth = depth;
				}
			}

			for (int i = 0; i < 4; i++) {
				int newX = curX + dx[i];
				int newY = curY + dy[i];

				// 이동
				if (isValid(newX, newY) && !isVisited[newX][newY] && map[newX][newY] <= shark.size) {
					// 먹이감 발견
					if (map[newX][newY] != 0 && map[newX][newY] < shark.size) {
						pq.add(new Node(newX, newY));
					}
					queue.add(newX);
					queue.add(newY);
					queue.add(depth + 1);
					isVisited[newX][newY] = true;
				}
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
