package problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA1209 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int t = 1; t <= 10; t++) {
			int test = Integer.parseInt(br.readLine());
			int[][] numbers = new int[100][100];
			for (int i = 0; i < 100; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 100; j++) {
					numbers[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			int result = 0;
			for (int i = 0; i < 100; i++) {
				int sumHor = 0;
				for (int j = 0; j < 100; j++) {
					sumHor += numbers[i][j];
				}
				result = Math.max(result, sumHor);
			}

			for (int i = 0; i < 100; i++) {
				int sumVer = 0;
				for (int j = 0; j < 100; j++) {
					sumVer += numbers[j][i];
				}
				result = Math.max(result, sumVer);
			}

			int rightCross = 0;
			int leftCross = 0;
			for (int i = 0; i < 100; i++) {
				rightCross += numbers[i][i];
				leftCross += numbers[i][99 - i];
			}

			result = Math.max(result, rightCross);
			result = Math.max(result, leftCross);
			System.out.println("#" + t + " " + result);
		}
	}

}