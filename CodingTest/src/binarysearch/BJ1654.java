package binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ1654 {

	static int[] sticks;
	static int[] cuts;
	static long M;
	static long maxHeight = 0;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		sticks = new int[N];
		cuts = new int[N];

		for (int i = 0; i < N; i++) {
			sticks[i] = Integer.parseInt(br.readLine());
		}

		Arrays.sort(sticks);

		binarySearch(1, sticks[N - 1]);
		System.out.print(maxHeight);
	}

	public static void binarySearch(long start, long end) {
		while (start <= end) {
			long mid = (start + end) / 2;
			long totalSticks = 0;
			for (int i = 0; i < cuts.length; i++) {
				long cntSticks = sticks[i] / mid;
				totalSticks += cntSticks;
			}

			if (totalSticks >= M) {
				// 어차피 반복하면서 최적의 값을 찾기 때문에 진짜 더 큰지 비교할 필요가 없다
				maxHeight = mid;
				start = mid + 1;
			} else {
				end = mid - 1;
			}
		}
	}
}
