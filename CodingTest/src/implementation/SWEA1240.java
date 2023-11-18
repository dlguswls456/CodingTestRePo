package implementation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

public class SWEA1240 {

	public static int[][] codes;
	static HashMap<String, Integer> numbers = new HashMap<String, Integer>();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());
		numbers.put("3211", 0);
		numbers.put("2221", 1);
		numbers.put("2122", 2);
		numbers.put("1411", 3);
		numbers.put("1132", 4);
		numbers.put("1231", 5);
		numbers.put("1114", 6);
		numbers.put("1312", 7);
		numbers.put("1213", 8);
		numbers.put("3112", 9);
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			codes = new int[N][M];
			for (int i = 0; i < N; i++) {
				String line = br.readLine();
				for (int j = 0; j < M; j++) {
					codes[i][j] = line.charAt(j) - 48;
				}
			}

			int idxI = 0;
			int idxJ = 0;
			for (int i = 0; i < N; i++) {
				for (int j = M - 1; j >= 0; j--) {
					if (codes[i][j] == 1) {
						idxI = i;
						idxJ = j - 56 + 1;
						break;
					}
				}
				if (idxJ != 0) {
					break;
				}
			}

			int oddSum = 0;
			int evenSum = 0;
			int isOdd = 1;
			for (int j = idxJ; j < idxJ + 56; j = j + 7) {
				int first = 0;
				int second = 0;
				int third = 0;
				int fourth = 0;
				String result = "";
				for (int k = j; k < j + 7; k++) {
					if (codes[idxI][k] == 0) {
						if (second == 0) {
							first++;
						} else {
							third++;
						}
					}
					if (codes[idxI][k] == 1) {
						if (third == 0) {
							second++;
						} else {
							fourth++;
						}
					}
				}
				result += String.valueOf(first) + String.valueOf(second) + String.valueOf(third)
						+ String.valueOf(fourth);
				int resultNum = numbers.get(result);
				if (isOdd == 1) {
					oddSum += resultNum;
					isOdd *= -1;
				} else {
					evenSum += resultNum;
					isOdd *= -1;
				}
			}

			int result = 0;
			if ((oddSum * 3 + evenSum) % 10 == 0) {
				result = oddSum + evenSum;
			}

			bw.write("#" + t + " " + result);
			bw.newLine();
		}
		bw.close();
	}

}
