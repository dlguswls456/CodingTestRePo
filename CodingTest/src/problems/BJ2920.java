package problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ2920 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int[] numbers = new int[8];
		for (int i = 0; i < 8; i++) {
			int num = Integer.parseInt(st.nextToken());
			numbers[i] = num;
		}

		boolean isAscending = true;
		boolean isDescending = true;
		for (int i = 0; i < 7; i++) {
			if (numbers[i] + 1 != numbers[i + 1]) {
				isAscending = false;
				break;
			}
		}
		if (isAscending) {
			System.out.print("ascending");
			return;
		}

		for (int i = 0; i < 7; i++) {
			if (numbers[i] - 1 != numbers[i + 1]) {
				isDescending = false;
				break;
			}
		}
		if (isDescending) {
			System.out.print("descending");
			return;
		}

		System.out.print("mixed");
	}

}
