package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BJ1941 {
	static int[][] girls = new int[5][5];
	static int result = 0;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int[][] tempPrincess = new int[5][5];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < 5; i++) {
			String line = br.readLine();
			for (int j = 0; j < 5; j++) {
				char tempChar = line.charAt(j);
				if (tempChar == 'S') {
					girls[i][j] = 1;
				} else {
					girls[i][j] = -1;
				}
			}
		}

		findPrincess(0, 0, 0, 0);
		System.out.println(result);

	}

	public static boolean isConnected() {
		// bfs돌릴 배열 복사
		int[][] cpyPrincess = new int[5][5];
		int idx = 0;
		for (int arr[] : tempPrincess) {
			cpyPrincess[idx] = arr.clone();
			idx++;
		}

		// 원소 하나가지고 bfs돌리기
		boolean isConnected = true;
		Queue<Integer> queue = new LinkedList<Integer>();
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (cpyPrincess[i][j] == 1) { // 1발견하면 큐에 넣고 바로 빠져나옴
					queue.add(i);
					queue.add(j);
					break;
				}
			}
			if (queue.size() > 0) {
				break;
			}
		}

		// bfs
		while (!queue.isEmpty()) {
			int curX = queue.poll();
			int curY = queue.poll();
			cpyPrincess[curX][curY] = 0;

			for (int i = 0; i < 4; i++) {
				int newX = curX + dx[i];
				int newY = curY + dy[i];
				if (isValid(newX, newY) && cpyPrincess[newX][newY] == 1) {
					queue.add(newX);
					queue.add(newY);
				}
			}
		}

		// 남아있는 1이 있는지 확인
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (cpyPrincess[i][j] == 1) { // 남아있으면 연결 안된 것
					isConnected = false;
					break;
				}
			}
			if (!isConnected) {
				break;
			}
		}
		return isConnected;
	}

	public static void findPrincess(int cntS, int cntY, int x, int y) {
		if (cntS + cntY == 7) {
			if (cntS >= 4 && isConnected()) { // 다솜파가 우위이고, 모두 연결되어있는지 확인
				result++;
			}
			return;
		}

		//조합
		for (int i = x; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (i == x && j < y) {
					continue;
				}

				if (tempPrincess[i][j] != 1) {
					tempPrincess[i][j] = 1;
					if (girls[i][j] == 1) {
						findPrincess(cntS + 1, cntY, i, j);
					} else {
						findPrincess(cntS, cntY + 1, i, j);
					}
					tempPrincess[i][j] = 0;
				}

			}
		}
	}

	public static boolean isValid(int x, int y) {
		if (x < 0 || x >= 5 || y < 0 || y >= 5) {
			return false;
		}
		return true;
	}
}
