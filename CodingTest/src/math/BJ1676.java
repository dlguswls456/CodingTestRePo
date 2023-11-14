package math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class BJ1676 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		BigInteger factorial = new BigInteger("1");
		for (int i = 1; i <= N; i++) {
			factorial = factorial.multiply(BigInteger.valueOf(i));
		}

		int result = 0;
		while (factorial.compareTo(BigInteger.valueOf(0)) > 0) {
			int lastNum = (factorial.mod(BigInteger.valueOf(10))).intValue();
			if (lastNum == 0) {
				result++;
			} else {
				break;
			}
			factorial = factorial.divide(BigInteger.valueOf(10));
		}

		System.out.print(result);
	}

}
