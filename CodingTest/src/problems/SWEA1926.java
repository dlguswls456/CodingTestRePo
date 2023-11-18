package problems;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class SWEA1926 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());
		int currentNum = 1;
		while (currentNum <= N) {
			if (isContains(currentNum)) {
				int temp = currentNum;
				while (temp > 0) {
					if (isContains(temp % 10)) {
						bw.write("-");
					}
					temp /= 10;
				}
				bw.write(" ");
				currentNum++;
				continue;
			}

			bw.write(currentNum + " ");
			currentNum++;
		}
		bw.close();
	}

	public static boolean isContains(int num) {
		if (String.valueOf(num).contains("3")) {
			return true;
		}
		if (String.valueOf(num).contains("6")) {
			return true;
		}
		if (String.valueOf(num).contains("9")) {
			return true;
		}

		return false;
	}
}
