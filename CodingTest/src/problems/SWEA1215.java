package problems;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class SWEA1215 {

	static char[][] puzzle;
	static int cnt, length;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		for (int t = 1; t <= 10; t++) {
			length = Integer.parseInt(br.readLine());
			puzzle = new char[8][8];
			cnt = 0;
			for (int i = 0; i < 8; i++) {
				String line = br.readLine();
				for (int j = 0; j < 8; j++) {
					puzzle[i][j] = line.charAt(j);
				}
			}

			for (int i = 0; i < 8; i++) {
				for (int j = 0; j <= 8 - length; j++) {
					findLeftToRight(i, j);
				}
			}

			for (int i = 0; i < 8; i++) {
				for (int j = 0; j <= 8 - length; j++) {
					findTopToDown(j, i);
				}
			}
			bw.write("#" + t + " " + cnt);
			bw.newLine();
		}
		bw.close();
	}

	public static void findTopToDown(int x, int y) {
		String word = "";
		for (int i = 0; i < length; i++) {
			word += puzzle[x+i][y];
		}

		if (isPalindrome(word)) {
			cnt++;
		}
	}

	public static void findLeftToRight(int x, int y) {
		String word = "";
		for (int i = 0; i < length; i++) {
			word += puzzle[x][y+i];
		}

		if (isPalindrome(word)) {
			cnt++;
		}
	}

	public static boolean isPalindrome(String word) {
		for (int i = 0; i < word.length() / 2; i++) {
			if (word.charAt(i) != word.charAt(word.length() - 1 - i)) {
				return false;
			}
		}
		return true;
	}
}
