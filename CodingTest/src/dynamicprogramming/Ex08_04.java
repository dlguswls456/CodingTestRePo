package dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

//น้มุ 2294
public class Ex08_04 {
	
	static int[] minCase;
	static int[] won;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int price = Integer.parseInt(st.nextToken());
		won = new int[N];
		for (int i = 0; i < N; i++) {
			won[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(won);

		minCase = new int[Math.max(won[N - 1], price) + 1];
		System.out.print(findCases(price));
	}

	public static int findCases(int targetNum) {
		for (int coin : won) {
			minCase[coin] = 1;
		}
		int n = 1;
		while (n <= targetNum) {
			if (minCase[n] > 0) {
				n++;
				continue;
			}
			ArrayList<Integer> tempCoins = new ArrayList<Integer>();
			for (int coin : won) {
				if (minCase[n] == 0) {
					if (n - coin > 0 && minCase[n - coin] != -1) {
						tempCoins.add(minCase[n - coin] + 1);
					}
				}
			}
			tempCoins.sort(null);
			if (tempCoins.size() > 0) {
				minCase[n] = tempCoins.get(0);
			} else {
				minCase[n] = -1;
			}
			n++;
		}

		return minCase[targetNum];
	}

}
