package dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ7576 {

	static int[][] tomato;
	static int N, M;
	static Queue<Integer> queue = new LinkedList<Integer>();

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		tomato = new int[N][M];

		// 모두 익은 토마토인지 확인하는 변수
		boolean isAllWellDone = true;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				tomato[i][j] = Integer.parseInt(st.nextToken());
				// 익은 토마토면 큐에 저장
				if (tomato[i][j] == 1) {
					queue.add(i);
					queue.add(j);
				}

				// 안익은 토마도 하나라도 있을 때
				if (tomato[i][j] == 0) {
					isAllWellDone = false;
				}
			}
		}

		// 모두 익은 토마토면 bfs 돌릴 필요 없다
		if (isAllWellDone) {
			System.out.println(0);
			return;
		}

		// 최대 며칠 걸리는지 bfs로 확인
		cntDays();

		int maxResult = Integer.MIN_VALUE;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				// 안익은 토마토 있을 시
				if (tomato[i][j] == 0) {
					maxResult = -1;
					System.out.println(maxResult);
					return;
				}
				// 토마토 없는 경우 pass
				if (tomato[i][j] == -1) {
					continue;
				}

				// 최대값 찾기
				maxResult = Math.max(maxResult, tomato[i][j]);
			}
		}
		// 토마토가 1이어서 값이 항상 +1 되어있음 -> 다시 1 빼주기..
		System.out.println(maxResult - 1);

	}

	public static void cntDays() {
		int[] dx = { -1, 1, 0, 0 };
		int[] dy = { 0, 0, -1, 1 };

		while (!queue.isEmpty()) {
			int currentX = queue.poll();
			int currentY = queue.poll();
			for (int i = 0; i < 4; i++) {
				int newX = currentX + dx[i];
				int newY = currentY + dy[i];
				if (isValid(newX, newY) && tomato[newX][newY] == 0) {
					queue.add(newX);
					queue.add(newY);

					// 현재 토마토 + 1일 저장
					tomato[newX][newY] = tomato[currentX][currentY] + 1;
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
