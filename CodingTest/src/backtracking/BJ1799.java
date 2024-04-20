package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1799 {

	static int[][] map;
	static boolean[] up, down;
	static int N, maxWhiteResult, maxBlackResult, possible;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) {
					possible++;
				}
			}
		}

		up = new boolean[2 * N - 1];
		down = new boolean[2 * N - 1];
		findBlackBishop(0, 0, 0);
		
		up = new boolean[2 * N - 1];
		down = new boolean[2 * N - 1];
		findWhiteBishop(0, 0, 0);
		System.out.println(maxWhiteResult + maxBlackResult);

	}

	public static void findWhiteBishop(int x, int y, int cnt) {
		if (cnt <= 2 * N - 2) {
			maxWhiteResult = Math.max(maxWhiteResult, cnt);
		} else {
			return;
		}

		for (int i = x; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (i == x && j < y) {
					continue;
				}

				if (i % 2 == j % 2) {
					continue;
				}

				if (map[i][j] == 0) {
					continue;
				}

				if (up[i + j] || down[j - i + N - 1]) {
					continue;
				}

				up[i + j] = true;
				down[j - i + N - 1] = true;
				findWhiteBishop(i, j, cnt + 1);
				up[i + j] = false;
				down[j - i + N - 1] = false;

			}
		}
	}

	public static void findBlackBishop(int x, int y, int cnt) {
		if (cnt <= 2 * N - 2) {
			maxBlackResult = Math.max(maxBlackResult, cnt);
		} else {
			return;
		}

		for (int i = x; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (i == x && j < y) {
					continue;
				}

				if (i % 2 != j % 2) {
					continue;
				}

				if (map[i][j] == 0) {
					continue;
				}

				if (up[i + j] || down[j - i + N - 1]) {
					continue;
				}

				up[i + j] = true;
				down[j - i + N - 1] = true;
				findBlackBishop(i, j, cnt + 1);
				up[i + j] = false;
				down[j - i + N - 1] = false;

			}
		}
	}

}
