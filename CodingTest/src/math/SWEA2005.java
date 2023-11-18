package math;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class SWEA2005 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			bw.write("#" + t);
			bw.newLine();

			int N = Integer.parseInt(br.readLine());
			for (int i = 0; i < N; i++) {
				for (int j = 0; j <= i; j++) {
					bw.append(calc(i, j) + " ");
				}
				bw.newLine();
			}

		}
		bw.close();
	}

	public static int calc(int a, int b) {
		int result = 1;
		if (a == 0 || b == 0) {
			return result;
		}
		for (int i = 0; i < b; i++) {
			result *= (a - i);
			result /= (i + 1);
		}

		return result;
	}

}
