package binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA1245 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int C = Integer.parseInt(br.readLine());

		for (int TC = 1; TC <= C; TC++) {
			int N = Integer.parseInt(br.readLine());
			double[] x = new double[N];
			int[] m = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				x[i] = Double.parseDouble(st.nextToken());
			}
			for (int i = 0; i < N; i++) {
				m[i] = Integer.parseInt(st.nextToken());
			}

			System.out.print("#" + TC + " ");

			for (int i = 0; i < N - 1; i++) {
				double left = x[i];
				double right = x[i + 1];
				double location = 0.0;
				for (int locationCount = 0; locationCount < 100; locationCount++) {
					location = (left + right) / 2;
					double sum = 0.0;

					// 왼쪽 합
					for (int leftIdx = 0; leftIdx <= i; leftIdx++) {
						sum += calc(x, m, leftIdx, location);
					}
					// 오른쪽 합
					for (int rightIdx = i + 1; rightIdx < N; rightIdx++) {
						sum -= calc(x, m, rightIdx, location);
					}

					if (sum == 0.0) {
						break;
					}
					if (sum > 0) {
						left = location;
					}
					if (sum < 0) {
						right = location;
					}
				}
				System.out.printf("%.10f ", location);
			}
			System.out.println();
		}

	}

	public static double calc(double[] x, int[] m, int idx, double location) {
		return m[idx] / ((x[idx] - location) * (x[idx] - location));
	}

}
