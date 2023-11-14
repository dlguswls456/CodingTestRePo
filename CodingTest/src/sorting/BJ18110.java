package sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ18110 {

	static int[] opinions = new int[30 + 1];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			int opinion = Integer.parseInt(br.readLine());
			opinions[opinion]++;
		}

		int startIgnore = (int) Math.round(N * 0.15);
		int endIgnore = (int) Math.round(N * 0.15);
		for (int i = 0; i < 31; i++) {
			if (startIgnore == 0 && endIgnore == 0) {
				break;
			}
			while (opinions[i] > 0) {
				if (startIgnore == 0) {
					break;
				}
				opinions[i]--;
				startIgnore--;
			}

			while (opinions[30 - i] > 0) {
				if (endIgnore == 0) {
					break;
				}
				opinions[30 - i]--;
				endIgnore--;
			}

		}

		int result = 0;
		double cnt = 0.0;
		for (int i = 1; i < 31; i++) {
			if (opinions[i] > 0) {
				cnt += opinions[i];
				result += i * opinions[i];
			}
		}
		result = (int) Math.round(result / cnt);

		System.out.print(result);

	}

}
