package dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ex08_01 {

	static int[] cntCall;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		cntCall = new int[N + 1];
		System.out.print(countCall(N));
	}

	public static int countCall(int targetNum) {
		int n = 2;
		while (n <= targetNum) {
			int cnt1 = Integer.MAX_VALUE;
			int cnt2 = Integer.MAX_VALUE;
			int cnt3 = Integer.MAX_VALUE;
			int cnt4 = Integer.MAX_VALUE;
			
			cnt1 = cntCall[n - 1];
			if (n % 2 == 0)
				cnt2 = cntCall[n / 2];
			if (n % 3 == 0)
				cnt3 = cntCall[n / 3];
			if (n % 5 == 0)
				cnt4 = cntCall[n / 5];

			cntCall[n] = Math.min(Math.min(Math.min(cnt1, cnt2), cnt3), cnt4) + 1;
			n++;
		}

		return cntCall[targetNum];
	}

}
