package dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ2589 {

	static int[][] landOrSea; // 1: 육지, 0: 바다
	static int N, M;
	static boolean[][] isVisited;

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		landOrSea = new int[N][M];
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				if (line.charAt(j) == 'L') {
					landOrSea[i][j] = 1;
				} else {
					landOrSea[i][j] = 0;
				}
			}
		}

		int maxResult = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				// 모든 육지마다 bfs 수행 후 최대 시간 반환
				if (landOrSea[i][j] == 1) {
					// 할 때마다 방문배열 초기화
					isVisited = new boolean[N][M];
					// 각 최대 시간 중 최대 값 찾기
					maxResult = Math.max(bfs(i, j), maxResult);
				}
			}
		}

		System.out.println(maxResult);

	}

	public static int bfs(int x, int y) {
		int hour = 0;
		int maxHour = 0;
		int[] dx = { -1, 1, 0, 0 };
		int[] dy = { 0, 0, -1, 1 };
		Queue<Integer> queue = new LinkedList<Integer>();
		// 좌표, 걸리는 시간 큐에 같이 담아버림
		queue.add(x);
		queue.add(y);
		queue.add(hour);
		// 방문처리
		isVisited[x][y] = true;

		while (!queue.isEmpty()) {
			int currentX = queue.poll();
			int currentY = queue.poll();
			hour = queue.poll();
			// 최대 시간 갱신
			maxHour = Math.max(maxHour, hour);
			for (int i = 0; i < 4; i++) {
				int newX = currentX + dx[i];
				int newY = currentY + dy[i];
				if (isValid(newX, newY) && !isVisited[newX][newY] && landOrSea[newX][newY] == 1) {
					queue.add(newX);
					queue.add(newY);
					// 한시간씩 더해서 저장
					queue.add(hour + 1);
					isVisited[newX][newY] = true;
				}

			}
		}

		return maxHour;
	}

	public static boolean isValid(int x, int y) {
		if (x < 0 || x >= N || y < 0 || y >= M) {
			return false;
		}

		return true;
	}

}
