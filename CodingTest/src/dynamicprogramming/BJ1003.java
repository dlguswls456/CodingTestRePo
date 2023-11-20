package dynamicprogramming;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ1003 {

	static int[] fibonacciArr;
	static int[] cnt0Arr;
	static int[] cnt1Arr;
	static int cnt0, cnt1;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			int N = Integer.parseInt(br.readLine());
			fibonacciArr = new int[N + 1];
			cnt0Arr = new int[N + 1];
			cnt1Arr = new int[N + 1];
			cnt0 = 0;
			cnt1 = 0;

			fibonacci(N);
			System.out.println(cnt0Arr[N] + " " + cnt1Arr[N]);
		}

	}

	public static int fibonacci(int targetNum) {
		if (targetNum == 0) {
			fibonacciArr[targetNum] = 0;
			cnt0Arr[0] = 1;
			cnt1Arr[0] = 0;
			return 0;
		}
		if (targetNum == 1) {
			fibonacciArr[targetNum] = 1;
			cnt0Arr[1] = 0;
			cnt1Arr[1] = 1;
			return 1;
		}

		if (fibonacciArr[targetNum] != 0) {
			return fibonacciArr[targetNum];
		} else {
			fibonacciArr[targetNum] = fibonacci(targetNum - 1) + fibonacci(targetNum - 2);
			cnt0Arr[targetNum] = cnt0Arr[targetNum - 1] + cnt0Arr[targetNum - 2];
			cnt1Arr[targetNum] = cnt1Arr[targetNum - 1] + cnt1Arr[targetNum - 2];
			return fibonacciArr[targetNum];
		}

	}

}
