package dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ex08_03 {

	static int[] cntCase;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		cntCase = new int[N + 1];
		System.out.print(findCases(N) % 796796);
	}

	public static int findCases(int targetNum) {
		int n = 3;
		cntCase[1] = 1;
		cntCase[2] = 3;
		while (n <= targetNum) {
			cntCase[n] = cntCase[n - 1] + 2 * cntCase[n - 2];
			n++;
		}
		return cntCase[targetNum];
	}

}
