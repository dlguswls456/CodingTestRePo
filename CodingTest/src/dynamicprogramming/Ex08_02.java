package dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Ex08_02 {

	static int[] cntStealing;
	static int[] stocks;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		stocks = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			stocks[i] = Integer.parseInt(st.nextToken());
		}
		cntStealing = new int[N];
		System.out.print(findMaxStealing(N - 1));
	}

	public static int findMaxStealing(int targetNum) {
		int n = 2;
		cntStealing[0] = stocks[0];
		cntStealing[1] = Math.max(stocks[0], stocks[1]);
 
		while (n <= targetNum) {
			cntStealing[n] = Math.max(cntStealing[n - 1], cntStealing[n - 2] + stocks[n]);
			n++;
		}

		return cntStealing[targetNum];
	}

}
