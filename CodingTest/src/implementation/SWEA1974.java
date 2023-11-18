package implementation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class SWEA1974 {

	public static int[][] sudoku = new int[9][9];
	static int result;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			for (int i = 0; i < 9; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 9; j++) {
					sudoku[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			result = 1;
			isValidFromLeftToRight();
			isValidFromTopToDown();
			isValidSquare();
			bw.write("#" + t + " " + result);
			bw.newLine();
		}
		bw.close();
	}

	public static void isValidSquare() {
		for (int i = 0; i < 9; i = i + 3) {
			for (int j = 0; j < 9; j = j + 3) {
				boolean[] isVisitied = new boolean[10];
				for (int k = i; k < i + 3; k++) {
					for (int l = j; l < j + 3; l++) {
						if (isVisitied[sudoku[k][l]]) {
							result *= 0;
							return;
						} else {
							isVisitied[sudoku[k][l]] = true;
						}

					}
				}
			}
		}
	}

	public static void isValidFromTopToDown() {
		for (int i = 0; i < 9; i++) {
			boolean[] isVisitied = new boolean[10];
			for (int j = 0; j < 9; j++) {
				if (isVisitied[sudoku[j][i]]) {
					result *= 0;
					return;
				} else {
					isVisitied[sudoku[j][i]] = true;
				}
			}
		}
	}

	public static void isValidFromLeftToRight() {
		for (int i = 0; i < 9; i++) {
			boolean[] isVisitied = new boolean[10];
			for (int j = 0; j < 9; j++) {
				if (isVisitied[sudoku[i][j]]) {
					result *= 0;
					return;
				} else {
					isVisitied[sudoku[i][j]] = true;
				}
			}
		}
	}
}
