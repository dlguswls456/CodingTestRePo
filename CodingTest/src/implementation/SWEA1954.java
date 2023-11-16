package implementation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class SWEA1954 {
	static int[][] array;
	static int N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			array = new int[N][N];
			int x = 0, y = 0;
			leftToRight(1, x, y);
			
			bw.write("#" + t);
			bw.newLine();
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					bw.append(array[i][j] + " ");
				}
				bw.newLine();
			}
		}
		bw.close();
	}

	public static void leftToRight(int num, int x, int y) {
		if (num > N * N) {
			return;
		}
		while (y < N && array[x][y] == 0) {
			array[x][y] = num;
			num++;
			y++;
		}

		topToBottom(num, x + 1, y - 1);
	}

	public static void topToBottom(int num, int x, int y) {
		if (num > N * N) {
			return;
		}
		while (x < N && array[x][y] == 0) {
			array[x][y] = num;
			num++;
			x++;
		}

		rightToLeft(num, x - 1, y - 1);
	}

	public static void rightToLeft(int num, int x, int y) {
		if (num > N * N) {
			return;
		}
		while (y > -1 && array[x][y] == 0) {
			array[x][y] = num;
			num++;
			y--;
		}

		bottomToTop(num, x - 1, y + 1);
	}

	public static void bottomToTop(int num, int x, int y) {
		if (num > N * N) {
			return;
		}
		while (x > -1 && array[x][y] == 0) {
			array[x][y] = num;
			num++;
			x--;
		}

		leftToRight(num, x + 1, y + 1);
	}

}
