package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ20546 {

	static int[] price;
	static int seed, bnp, timing;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		seed = Integer.parseInt(br.readLine());

		price = new int[14];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 14; i++) {
			price[i] = Integer.parseInt(st.nextToken());
		}

		calcBNP();
		calcTiming();
		if(bnp>timing) {
			System.out.println("BNP");
		}else if(bnp<timing) {
			System.out.println("TIMING");
		}else {
			System.out.println("SAMESAME");
		}
//		System.out.println(bnp);
//		System.out.println(timing);

	}

	public static void calcTiming() {
		int tempSeed = seed;
		int cntIncrease = 0;
		int cntDecrease = 0;
		for (int i = 1; i < 14; i++) {
			if (price[i - 1] < price[i]) {
				cntIncrease++;
			} else {
				cntIncrease = 0;
			}

			if (price[i - 1] > price[i]) {
				cntDecrease++;
			} else {
				cntDecrease = 0;
			}

			if (cntDecrease == 3) { // 매수
//				System.out.println("매수 " + i);
				timing += tempSeed / price[i];
				tempSeed %= price[i];
				cntDecrease--;
			}

			if (cntIncrease == 3) { // 매도
//				System.out.println("매도 " + i);
				tempSeed += timing * price[i];
				timing = 0;
				cntIncrease--;
			}
		}

		timing = tempSeed + timing * price[13];
	}

	public static void calcBNP() {
		int tempSeed = seed;
		for (int p : price) {
			bnp += tempSeed / p;
			tempSeed %= p;
		}

		bnp = tempSeed + bnp * price[13];
	}

}
