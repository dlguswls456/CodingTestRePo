package math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class SWEA17937 {

	static BigInteger result = BigInteger.valueOf(1);

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int C = Integer.parseInt(br.readLine());

		for (int TC = 1; TC <= C; TC++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			BigInteger A = new BigInteger(st.nextToken());
			BigInteger B = new BigInteger(st.nextToken());

			GCD(A, B);
			System.out.println("#" + TC + " " + result);
		}
	}

	public static void GCD(BigInteger A, BigInteger B) {
		if(A.equals(B)) {
			result = A;
		}else {
			result = BigInteger.valueOf(1);
		}
	}
}