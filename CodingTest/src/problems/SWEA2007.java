package problems;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class SWEA2007 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			int result = 0;
			String line = br.readLine();
			for (int i = 1; i < 10; i++) {
				if (line.substring(0, i).equals(line.substring(i, i + i))) {
					result = i;
				}
				if (isReallySame(line, i)) {
					break;
				}
			}
			bw.write("#" + t + " " + result);
			bw.newLine();
		}
		bw.close();
	}

	public static boolean isReallySame(String line, int length) {
		for (int i = 0; i < 30 / length; i++) {
			if (!line.substring(i, i + length).equals(line.substring(i + length, i + length * 2))) {
				return false;
			}
		}
		return true;
	}
}
