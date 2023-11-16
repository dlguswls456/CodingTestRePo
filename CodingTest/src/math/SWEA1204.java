package math;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class SWEA1204 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());
			int[] scores = new int[101];
			int maxCnt = 0;
			int maxScore = 0;
			StringTokenizer st = new StringTokenizer(br.readLine());
			while (st.hasMoreTokens()) {
				int score = Integer.parseInt(st.nextToken());
				scores[score]++;
				if (maxCnt <= scores[score]) {
					maxCnt = scores[score];
					maxScore = score;
				}
			}

			bw.write("#" + N + " " + maxScore);
			bw.newLine();
		}
		bw.close();
	}
}
