package dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ17086 {

	static int N, M;
	static int[][] sharks, distance;
	static boolean[][] isVisited;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		sharks = new int[N][M];
		distance = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				sharks[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int maxResult = Integer.MIN_VALUE;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				// 모든 상어에서 bfs 수행 -> distance 배열에 상어와의 최소 거리 저장
				if (sharks[i][j] == 1) {
					// 방문 배열 초기화
					isVisited = new boolean[N][M];
					findMinDistance(i, j);
				}
			}
		}

		// 상어와의 최소 거리가 가장 큰 값 찾기
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				maxResult = Math.max(maxResult, distance[i][j]);
			}
		}

		System.out.println(maxResult);

	}

	public static void findMinDistance(int x, int y) {
		int[] dx = { -1, -1, -1, 0, 0, 1, 1, 1 };
		int[] dy = { -1, 0, 1, -1, 1, -1, 0, 1 };
		Queue<Integer> queue = new LinkedList<Integer>();
		// 좌표, 상어와의 거리 전부 큐에 넣기
		queue.add(x);
		queue.add(y);
		queue.add(0);
		isVisited[x][y] = true;

		while (!queue.isEmpty()) {
			int currentX = queue.poll();
			int currentY = queue.poll();
			int move = queue.poll();
			for (int i = 0; i < 8; i++) {
				int newX = currentX + dx[i];
				int newY = currentY + dy[i];
				if (isValid(newX, newY) && !isVisited[newX][newY]) {
					if (sharks[newX][newY] == 0) {
						// 만약 처음 가는 구역이라면 move + 1
						if (distance[newX][newY] == 0) {
							distance[newX][newY] = move + 1;
							// 이미 거리를 구한 적이 있다면 최소 값으로 갱신
						} else {
							distance[newX][newY] = Math.min(move + 1, distance[newX][newY]);
						}
						isVisited[newX][newY] = true;
						queue.add(newX);
						queue.add(newY);
						queue.add(move + 1);
					}
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
