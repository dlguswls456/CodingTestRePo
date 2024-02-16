package ssafy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ14501 {

	static int[] time;
	static int[] price;
	static boolean[] isVisited;
	static int result, N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		time = new int[N + 1];
		price = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			time[i] = Integer.parseInt(st.nextToken());
			price[i] = Integer.parseInt(st.nextToken());
		}

		result = 0;

		findMaxPrice(1, 0);

		System.out.println(result);
	}

//	public static void findMaxPrice(int days, int nPrice) {
//		result = Math.max(result, nPrice);
//		if (days > N) {
//			return;
//		}
//		// 일 하는거
//		if (days + time[days] - 1 <= N) {
//			findMaxPrice(days + time[days], nPrice + price[days]);
//		}
//
//		// 일 안하는거
//		if (days + 1 <= N) {
//			findMaxPrice(days + 1, nPrice);
//		}
//	}

	public static void findMaxPrice(int days, int nPrice) {
		if (days - 1 > N) {
			return;
		}

		result = Math.max(result, nPrice);
		for (int i = days; i <= N; i++) {
			findMaxPrice(i + time[i], nPrice + price[i]);
		}

	}

}
