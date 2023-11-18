package math;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class SWEA1209 {

	public static int[][] array = new int[100][100];
	static int maxSum;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		for (int t = 1; t <= 10; t++) {
			int T = Integer.parseInt(br.readLine());
			maxSum = 0;
			for (int i = 0; i < 100; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 100; j++) {
					array[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			findMaxColSum();
			findMaxCrossSum();
			findMaxRowSum();
			bw.write("#" + T + " " + maxSum);
			bw.newLine();
		}
		bw.close();
	}

	public static void findMaxRowSum() {
		for (int i = 0; i < 100; i++) {
			int sum = 0;
			for (int j = 0; j < 100; j++) {
				sum += array[i][j];
			}
			if (maxSum < sum) {
				maxSum = sum;
			}
		}
	}

	public static void findMaxColSum() {
		for (int i = 0; i < 100; i++) {
			int sum = 0;
			for (int j = 0; j < 100; j++) {
				sum += array[j][i];
			}
			if (maxSum < sum) {
				maxSum = sum;
			}
		}
	}

	public static void findMaxCrossSum() {
		int sum = 0;
		for (int i = 0; i < 100; i++) {
			sum += array[i][i];
			if (maxSum < sum) {
				maxSum = sum;
			}
		}

		sum = 0;
		for (int i = 0; i < 100; i++) {
			sum += array[i][100 - i - 1];
			if (maxSum < sum) {
				maxSum = sum;
			}
		}
	}
}
