package sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Ex06_03 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		Integer[] A = new Integer[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			A[i] = num;
		}

		Integer[] B = new Integer[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			B[i] = num;
		}

		int max = sumIntArray(A);
		for (int i = 0; i < K; i++) {
			Arrays.sort(A);
			Arrays.sort(B, Collections.reverseOrder());

			if (A[0] >= B[0]) {
				break;
			}

			int temp = A[0];
			A[0] = B[0];
			B[0] = temp;

			int tempSum = sumIntArray(A);
			if (max < tempSum) {
				max = tempSum;
			}
		}

		System.out.print(max);
	}

	public static int sumIntArray(Integer[] array) {
		int sum = 0;
		for (int num : array) {
			sum += num;
		}

		return sum;
	}
}
