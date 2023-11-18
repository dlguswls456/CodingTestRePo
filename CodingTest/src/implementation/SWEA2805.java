package implementation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class SWEA2805 {

	public static int[][] farms;
	static int profit;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			farms = new int[N][N];
			profit = 0;
			for (int i = 0; i < N; i++) {
				String line = br.readLine();
				for (int j = 0; j < N; j++) {
					farms[i][j] = line.charAt(j) - 48;
				}
			}

			int midIdx = N / 2;
			for (int i = 0; i < N; i++) {
				if (i <= midIdx) {
					for (int j = midIdx - i; j <= midIdx + i; j++) {
						profit += farms[i][j];
					}
				} else {
					for (int j = i - midIdx; j < N - (i - midIdx); j++) {
						profit += farms[i][j];
					}
				}
			}

			bw.write("#" + t + " " + profit);
			bw.newLine();
		}
		bw.close();
	}

}
