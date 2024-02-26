package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA1952 {

	static int[] schedule;
	static int[] price;
	static int minPrice;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			schedule = new int[13];
			price = new int[4];
			minPrice = Integer.MAX_VALUE;
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 4; i++) {
				price[i] = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine());
			for (int i = 1; i < 13; i++) {
				schedule[i] = Integer.parseInt(st.nextToken());
			}

			findMinPrice(1, 0);
			System.out.println("#" + t + " " + minPrice);
		}

	}

	public static void findMinPrice(int currentMonth, int currentPrice) {
		if (currentMonth >= 13) {
			minPrice = Math.min(minPrice, currentPrice);
			return;
		}

		if (schedule[currentMonth] != 0) {
			for (int i = 0; i < 4; i++) {
				switch (i) {
				case 0: { // 1일
					findMinPrice(currentMonth + 1, currentPrice + price[0] * schedule[currentMonth]);
					break;
				}
				case 1: { // 1달
					findMinPrice(currentMonth + 1, currentPrice + price[1]);
					break;
				}
				case 2: { // 3달
					findMinPrice(currentMonth + 3, currentPrice + price[2]);
					break;
				}
				case 3: { // 1년
					findMinPrice(currentMonth + 12, currentPrice + price[3]);
					break;
				}
				}
			}
		} else {
			findMinPrice(currentMonth + 1, currentPrice);
		}

	}
}
