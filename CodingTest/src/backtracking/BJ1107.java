package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1107 {

	public static int[] buttons = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
	public static boolean[] isBroken;
	public static int N;
	public static int closestNum;
	public static int tempNum = 0;
	public static int current = 100;
	public static boolean isFirstZero = true;
	public static boolean isZeroBroken = false;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			N = Integer.parseInt(br.readLine());
			closestNum = current;
			int cntBroken = Integer.parseInt(br.readLine());
			isBroken = new boolean[10];
			if (cntBroken != 0) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int i = 0; i < cntBroken; i++) {
					int num = Integer.parseInt(st.nextToken());
					isBroken[num] = true;
				}
			}

			isZeroBroken = isBroken[0];
			isFirstZero = true;
			if (cntBroken != 0) {
				dfs();
			} else {
				closestNum = N;
			}

			int result = Math.abs(closestNum - N);
			result += String.valueOf(closestNum).length();

			if (Math.abs(N - current) < result) {
				result = Math.abs(N - current);
			}

			System.out.println(closestNum);
			System.out.println(result);
		}
		
	}

	public static void dfs() {
		if (tempNum > 1000000) {
			return;
		}

		if (Math.abs(tempNum - N) < Math.abs(closestNum - N)) {
			if (tempNum == 0 && !isZeroBroken && isFirstZero) {
				closestNum = tempNum;
				isFirstZero = false;
			} else if (isFirstZero && isZeroBroken) {
				isFirstZero = false;
			} else {
				closestNum = tempNum;
			}
		} else if (Math.abs(tempNum - N) == Math.abs(closestNum - N)) {
			if (String.valueOf(tempNum).length() < String.valueOf(closestNum).length()) {
				if (tempNum == 0 && !isZeroBroken && isFirstZero) {
					closestNum = tempNum;
					isFirstZero = false;
				} else if (isFirstZero && isZeroBroken) {
					isFirstZero = false;
				} else {
					closestNum = tempNum;
				}
			} else {

			}

		}

		isFirstZero = false;
		for (int i = 0; i < 10; i++) {
			int temp = tempNum;
			if (!isBroken[i]) {
				tempNum = tempNum * 10 + buttons[i];
				if (tempNum != 0) {
					dfs();
					tempNum = temp;
				}
			}

		}
	}
}