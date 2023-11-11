package binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ1920 {

	static int[] numbers;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		numbers = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			numbers[i] = num;
		}
		Arrays.sort(numbers);

		int M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			int num = Integer.parseInt(st.nextToken());
			binarySearch(num, 0, N - 1);
		}

	}

	public static void binarySearch(int num, int start, int end) {
		while (true) {
			int middle = (start + end) / 2;
			if (numbers[start] == num || numbers[middle] == num || numbers[end] == num) {
				System.out.println(1);
				return;
			}

			if (numbers[middle] > num) {
				end = middle - 1;
			} else {
				start = middle + 1;
			}

			if (start >= end) {
				System.out.println(0);
				return;
			}
		}
	}
}
