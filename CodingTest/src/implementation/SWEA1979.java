package implementation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class SWEA1979 {

	static int[][] puzzle;
	static int cnt, N, K;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			puzzle = new int[N][N];
			cnt = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					puzzle[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			findLeftToRight();
			findTopToDown();
			bw.write("#" + t + " " + cnt);
			bw.newLine();
		}
		bw.close();
	}

	public static void findTopToDown() {
		for (int i = 0; i < N; i++) {
			int tempCnt = 0;
			for (int j = 0; j < N; j++) {
				if (puzzle[j][i] == 1) {
					tempCnt++;
				} else {
					if (tempCnt == K) {
						cnt++;
					}
					tempCnt = 0;
				}
			}
			if (tempCnt == K) {
				cnt++;
			}
		}
	}

	public static void findLeftToRight() {
		for (int i = 0; i < N; i++) {
			int tempCnt = 0;
			for (int j = 0; j < N; j++) {
				if (puzzle[i][j] == 1) {
					tempCnt++;
				} else {
					if (tempCnt == K) {
						cnt++;
					}
					tempCnt = 0;
				}
			}
			if (tempCnt == K) {
				cnt++;
			}
		}
	}
}
