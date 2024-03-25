package problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA1216 {

	static int[][] map;
	static StringBuilder sb;
	static int result;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = 10;
		for (int t = 1; t <= T; t++) {
			int tt = Integer.parseInt(br.readLine());
			map = new int[100][100];
			result = 1;

			for (int i = 0; i < 100; i++) {
				String line = br.readLine();
				for (int j = 0; j < 100; j++) {
					map[i][j] = line.charAt(j) - 'A' + 1;
				}
			}

			for (int i = 0; i < 100; i++) {
				for (int j = 0; j < 100; j++) {
					result = Math.max(result, findPalindrome(i, j, true));
					result = Math.max(result, findPalindrome(i, j, false));
				}
			}
			System.out.printf("#%d %d\n", tt, result);
		}
	}

	public static int findPalindrome(int x, int y, boolean isRow) {
		int cntOdd = 1;
		int cntEven = 0;

		if (isRow) {
			for (int i = 1; i <= 50; i++) {
				if (y - i < 0 || y + i >= 100) {
					break;
				}
				if (map[x][y - i] == map[x][y + i]) {
					cntOdd += 2;
				} else {
					break;
				}
			}

			for (int i = 0; i <= 50; i++) {
				if (y - i < 0 || y + i + 1 >= 100) {
					break;
				}
				if (map[x][y - i] == map[x][y + i + 1]) {
					cntEven += 2;
				} else {
					break;
				}
			}
		} else {
			for (int i = 1; i <= 50; i++) {
				if (x - i < 0 || x + i >= 100) {
					break;
				}
				if (map[x - i][y] == map[x + i][y]) {
					cntOdd += 2;
				} else {
					break;
				}
			}

			for (int i = 0; i <= 50; i++) {
				if (x - i < 0 || x + i + 1 >= 100) {
					break;
				}
				if (map[x - i][y] == map[x + i + 1][y]) {
					cntEven += 2;
				} else {
					break;
				}
			}
		}

		return Math.max(cntOdd, cntEven);
	}

}