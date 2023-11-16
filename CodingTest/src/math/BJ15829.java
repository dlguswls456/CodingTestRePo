package math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class BJ15829 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String line = br.readLine();
		BigInteger result = BigInteger.valueOf(0);
		int r = 31;
		int M = 1234567891;
		for (int i = 0; i < N; i++) {
			int num = line.charAt(i) - 'a' + 1;
			result = result.add(BigInteger.valueOf(r).pow(i).multiply(BigInteger.valueOf(num)));
		}

		result = result.mod(BigInteger.valueOf(M));
		System.out.print(result);
	}

}
