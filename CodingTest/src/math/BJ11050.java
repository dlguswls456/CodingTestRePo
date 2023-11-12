package math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ11050 {
	//이항계수
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int result = 1;
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		for (int i = 0; i < K; i++) {
			result *= (N - i);
		}

		for (int i = 1; i <= K; i++) {
			result /= i;
		}
		System.out.print(result);
	}

}
