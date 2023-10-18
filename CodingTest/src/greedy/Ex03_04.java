package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Ex03_04 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int extractCnt = 0;
		int divideCnt = 0;
		while (N >= K) {
			if (N % K != 0) {
				extractCnt += N % K;
				N /= K;
				divideCnt++;
			} else {
				N /= K;
				divideCnt++;
			}
		}
		extractCnt += N % K - 1;

		System.out.print(extractCnt + divideCnt);
	}

}
