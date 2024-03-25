package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ14891 {

	static int[][] wheel;
	static int K;
	static boolean[] isVisited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		wheel = new int[5][8];

		for (int i = 1; i < 5; i++) {
			String line = br.readLine();
			for (int j = 0; j < 8; j++) {
				wheel[i][j] = line.charAt(j) - '0';
			}
		}

		K = Integer.parseInt(br.readLine());
		for (int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int target = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			isVisited = new boolean[5];

			rotate(target, dir);
		}

		int result = calcScore();
		System.out.println(result);

	}

	public static int calcScore() {
		int result = 0;
		int score = 1;
		for (int i = 1; i <= 4; i++) {
			if (wheel[i][0] != 0) {
				result += score;
			}
			score *= 2;
		}
		return result;
	}

	public static void rotate(int target, int dir) {
		isVisited[target] = true;
		// 양옆 바퀴 회전 알려주기
		int rightMagnet = wheel[target][2];
		int leftMagnet = wheel[target][6];

		if (target + 1 <= 4) {
			if (!isVisited[target + 1]) {
				int rightTargetMagnet = wheel[target + 1][6];
				if (rightMagnet != rightTargetMagnet) {
					rotate(target + 1, dir * -1);
				}
			}
		}

		if (target - 1 >= 1) {
			if (!isVisited[target - 1]) {
				int leftTargetMagnet = wheel[target - 1][2];
				if (leftMagnet != leftTargetMagnet) {
					rotate(target - 1, dir * -1);
				}
			}
		}

		// 회전 시키기
		if (dir == -1) {
			int valueOfIdx0 = wheel[target][0];
			for (int i = 1; i < 8; i++) {
				wheel[target][i - 1] = wheel[target][i];
			}
			wheel[target][7] = valueOfIdx0;
		} else {
			int valueOfIdx7 = wheel[target][7];
			for (int i = 7; i > 0; i--) {
				wheel[target][i] = wheel[target][i - 1];
			}
			wheel[target][0] = valueOfIdx7;
		}

	}
}