package binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Ex07_01 {

	static int[] stocks;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		stocks = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			stocks[i] = Integer.parseInt(st.nextToken());
		}

		int M = Integer.parseInt(br.readLine());
		int[] orders = new int[M];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			orders[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(stocks);
		Arrays.sort(orders);
		for (int i = 0; i < M; i++) {
			binarySearch(orders[i], 0, N - 1);
		}
	}

	public static void binarySearch(int target, int start, int end) {
		while (start <= end) {
			int mid = (start + end) / 2;
			if (stocks[mid] == target) {
				System.out.print("yes ");
				return;
			}

			if (stocks[mid] > target) {
				end = mid - 1;
			} else {
				start = mid + 1;
			}
		}
		
		System.out.print("no ");
	}
}
