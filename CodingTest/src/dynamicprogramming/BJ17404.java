package dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ17404 {

	static int[][] price, R, G, B;
	static int N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		price = new int[N][3];
		R = new int[N][3];
		G = new int[N][3];
		B = new int[N][3];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				price[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int result = Math.min(Math.min(minPrice(R, 0), minPrice(G, 1)), minPrice(B, 2));
		System.out.println(result);

	}

	public static int minPrice(int[][] array, int RGBIdx) {
		// 첫 행 초기화
		for (int i = 0; i < 3; i++) {
			if (i == RGBIdx) {
				array[0][i] = price[0][i];
			} else {
				array[0][i] = Integer.MAX_VALUE - 1000;
			}
		}

		for (int i = 1; i < N; i++) {
			array[i][0] = price[i][0] + Math.min(array[i - 1][1], array[i - 1][2]);
			array[i][1] = price[i][1] + Math.min(array[i - 1][0], array[i - 1][2]);
			array[i][2] = price[i][2] + Math.min(array[i - 1][0], array[i - 1][1]);
		}

		int result = 0;
		if (RGBIdx == 0) {
			result = Math.min(array[N - 1][1], array[N - 1][2]);
		} else if (RGBIdx == 1) {
			result = Math.min(array[N - 1][0], array[N - 1][2]);
		} else {
			result = Math.min(array[N - 1][0], array[N - 1][1]);
		}

		return result;
	}

}
