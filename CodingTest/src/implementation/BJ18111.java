package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ18111 {

	static int[][] blocks;
	static int minTime = Integer.MAX_VALUE;
	static int maxHeight = Integer.MIN_VALUE;
	static int N, M, B;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		blocks = new int[N][M];
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int height = Integer.parseInt(st.nextToken());
				blocks[i][j] = height;

				if (height > max) {
					max = height;
				}
				if (height < min) {
					min = height;
				}
			}
		}

		for (int i = max; i >= min; i--) {
			calcTime(i);
		}

		System.out.print(minTime + " " + maxHeight);

	}

	public static void calcTime(int targetHeight) {
		int tempTime = 0;
		int tempB = B;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (blocks[i][j] > targetHeight) {
					int diff = blocks[i][j] - targetHeight;
					tempTime += 2 * diff;
					tempB += diff;
				}
				if (blocks[i][j] < targetHeight) {
					int diff = targetHeight - blocks[i][j];
					tempTime += 1 * diff;
					tempB -= diff;
				}
				if (tempTime > minTime) {
					return;
				}
			}
		}

		if (tempB < 0) {
			return;
		}

		if (tempTime < minTime) {
			minTime = tempTime;
			maxHeight = targetHeight;
		}
	}

}
