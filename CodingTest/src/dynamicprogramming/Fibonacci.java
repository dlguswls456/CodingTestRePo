package dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Fibonacci {

	// 값을 저장하면서 수행하면 연산을 많이 줄일 수 있다
	static long[] fiboArrayRecursive;
	static long[] fiboArrayWhile;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		fiboArrayRecursive = new long[46];
		fiboArrayWhile = new long[46];
		
		long fiboRecursive = findFiboRecursive(N);
		System.out.println(fiboRecursive);
		
		long fiboWhile = findFiboWhile(N);
		System.out.print(fiboWhile);
	}

	public static long findFiboWhile(int targetNum) {
		fiboArrayWhile[1] = 1;
		fiboArrayWhile[2] = 1;
		int n = 3;
		while (n <= targetNum) {
			fiboArrayWhile[n] = fiboArrayWhile[n - 1] + fiboArrayWhile[n - 2];
			n++;
		}

		return fiboArrayWhile[targetNum];
	}

	public static long findFiboRecursive(int targetNum) {
		if (targetNum == 1 || targetNum == 2) {
			return 1;
		}

		// 이미 결과값이 있는 경우
		if (fiboArrayRecursive[targetNum] != 0) {
			return fiboArrayRecursive[targetNum];
		} else {
			fiboArrayRecursive[targetNum] = findFiboRecursive(targetNum - 1) + findFiboRecursive(targetNum - 2);
			return fiboArrayRecursive[targetNum];
		}

	}
}
