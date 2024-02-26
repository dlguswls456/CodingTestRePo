package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 파이프
public class BJ17070 {
	static int[][] house;
	static int result, N;

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		house = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				house[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		movePipe(1, 2, 0);
		System.out.println(result);
	}

	public static void movePipe(int x, int y, int position) {
		if (x == N && y == N) {
			result++;
			return;
		}

		// position 0: 가로, 1: 세로, 2: 대각선
		if (position == 0) {
			if (isValid(x, y + 1)) {
				movePipe(x, y + 1, 0);
			}
			if (isValid(x, y + 1) && isValid(x + 1, y) && isValid(x + 1, y + 1)) {
				movePipe(x + 1, y + 1, 2);
			}
		} else if (position == 1) {
			if (isValid(x + 1, y)) {
				movePipe(x + 1, y, 1);
			}
			if (isValid(x, y + 1) && isValid(x + 1, y) && isValid(x + 1, y + 1)) {
				movePipe(x + 1, y + 1, 2);
			}
		} else {
			if (isValid(x, y + 1)) {
				movePipe(x, y + 1, 0);
			}
			if (isValid(x + 1, y)) {
				movePipe(x + 1, y, 1);
			}
			if (isValid(x, y + 1) && isValid(x + 1, y) && isValid(x + 1, y + 1)) {
				movePipe(x + 1, y + 1, 2);
			}
		}
	}

	public static boolean isValid(int x, int y) {
		if (x > N || y > N || house[x][y] == 1) { // 가려는 방향이 1이면 안됨
			return false;
		}
		return true;
	}

}
