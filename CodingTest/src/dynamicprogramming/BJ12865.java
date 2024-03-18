package dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ12865 {

	static int[][] array;
	static int N, K;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		array = new int[N + 1][K + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int W = Integer.parseInt(st.nextToken());
			int V = Integer.parseInt(st.nextToken());

			for (int j = 1; j <= K; j++) {
				if (j < W) {
					array[i][j] = array[i - 1][j];
				} else {
					array[i][j] = Math.max(array[i - 1][j - W] + V, array[i - 1][j]);
				}
			}
		}
		
		System.out.println(array[N][K]);
	}

}
