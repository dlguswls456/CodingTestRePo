package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ9663 {

	static boolean[][] isVisited;
	static int result, N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		isVisited = new boolean[N][N];

		backtracking(0, 0);

		System.out.println(result);
	}

	public static void backtracking(int x, int cnt) {
		if (cnt == N) {
			result++;
			return;
		}

		for (int i = 0; i < N; i++) {
			if (isPossiblePosition(x, i)) {
				isVisited[x][i] = true;
				backtracking(x + 1, cnt + 1);
				isVisited[x][i] = false;
			}

		}

	}

	public static boolean isPossiblePosition(int x, int y) {
		for (int i = 0; i < N; i++) {
			if (isVisited[i][y]) {
				return false;
			}
		}

		int i = x, j = y;
		while (isValid(i, j)) {
			if (isVisited[i][j]) {
				return false;
			}
			i--;
			j--;
		}
		i = x;
		j = y;
		while (isValid(i, j)) {
			if (isVisited[i][j]) {
				return false;
			}
			i++;
			j++;
		}

		i = x;
		j = y;
		while (isValid(i, j)) {
			if (isVisited[i][j]) {
				return false;
			}
			i--;
			j++;
		}
		i = x;
		j = y;
		while (isValid(i, j)) {
			if (isVisited[i][j]) {
				return false;
			}
			i++;
			j--;
		}

		return true;
	}

	public static boolean isValid(int x, int y) {
		if (x < 0 || x >= N || y < 0 || y >= N) {
			return false;
		}

		return true;
	}
}
