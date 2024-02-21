package dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ14501 {

	static int[][] timeTable;
	static int N, result;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		timeTable = new int[N + 1][2];
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			timeTable[i][0] = Integer.parseInt(st.nextToken());
			timeTable[i][1] = Integer.parseInt(st.nextToken());
		}

//		int maxPrice = 0;
//		for (int i = 1; i <= N; i++) {
//			int[] tempPriceWorking = new int[N + 1];
//			int idx = i;
//			while (idx <= N) {
//				int price = timeTable[idx][1];
//				idx += timeTable[idx][0] - 1; // 일을 마치는 날
//				for (int j = idx; j <= N; j++) {
//					tempPriceWorking[j] += price;
//				}
//				idx++;
//			}
//			maxPrice = Math.max(maxPrice, tempPriceWorking[N]);
//		}

		findMaxPrice(1, 0);
		System.out.println(result);

	}

	public static void findMaxPrice(int days, int price) {
		result = Math.max(result, price);
		if (days > N) {
			return;
		}

		if (days + timeTable[days][0] - 1 <= N) {
			findMaxPrice(days + timeTable[days][0], price + timeTable[days][1]);
		}
		if (days + 1 <= N) {
			findMaxPrice(days + 1, price);
		}
	}

}
