package backtracking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BJ15649 {

	static int N;
	static int M;
	static boolean[] isVisitedNumber;
	static BufferedWriter bw;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		isVisitedNumber = new boolean[N + 1];
		backTracking(0, "");
		
		bw.flush();
	}

	public static void backTracking(int cnt, String result) throws IOException {
		if (cnt == M) {
			bw.append(result);
			bw.newLine();
			return;
		}

		for (int i = 1; i <= N; i++) {
			if (isVisitedNumber[i] == false) {
				isVisitedNumber[i] = true;
				backTracking(cnt + 1, result + i + " ");
				isVisitedNumber[i] = false;
			}
		}
	}
}
