package implementation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class SWEA2001 {

	public static int[][] flies;
	static int M, maxFlies;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			flies = new int[N][N];
			maxFlies = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					flies[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			for (int i = 0; i <= N - M; i++) {
				for (int j = 0; j <= N - M; j++) {
					findMaxFlies(i, j);
				}
			}
			bw.write("#" + t + " " + maxFlies);
			bw.newLine();
		}
		bw.close();
	}

	public static void findMaxFlies(int x, int y) {
		int sum = 0;
		for (int i = x; i < x + M; i++) {
			for (int j = y; j < y + M; j++) {
				sum += flies[i][j];
			}
		}
		if (maxFlies < sum) {
			maxFlies = sum;
		}
	}
}
