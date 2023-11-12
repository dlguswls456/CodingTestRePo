package binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Ex07_02 {

	static int[] sticks;
	static int[] cuts;
	static int M;
	static int maxHeight = 0;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		sticks = new int[N];
		cuts = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			sticks[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(sticks);
		for (int i = 0; i < M; i++) {
			binarySearch(0, sticks[N - 1]);
		}
		System.out.print(maxHeight);
	}

	public static void binarySearch(int start, int end) {
		while (start <= end) {
			int mid = (start + end) / 2;
			for (int i = 0; i < cuts.length; i++) {
				int leftCut = sticks[i] - mid;
				if (leftCut < 0) {
					cuts[i] = 0;
				} else {
					cuts[i] = leftCut;
				}
			}
			int totalSticks = sumCuts();

			if (totalSticks >= M) {
				//어차피 반복하면서 최적의 값을 찾기 때문에 진짜 더 큰지 비교할 필요가 없다
				maxHeight = mid;
				start = mid + 1;
			} else {
				end = mid - 1;
			}
		}
	}

	public static int sumCuts() {
		int sum = 0;
		for (int cut : cuts) {
			sum += cut;
		}
		return sum;
	}
}
