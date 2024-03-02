package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SWEA2115 {

	static int[][] honey;
	static boolean[][] isVisited;
	static int N, M, C, maxResult;
	static ArrayList<Integer> tempHoney;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			maxResult = 0;
			honey = new int[N][N];
			isVisited = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					honey[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			findHoney(0, 0, 0, 0);
			System.out.println("#" + t + " " + maxResult);
		}

	}

	public static void findHoney(int cnt, int curX, int curY, int sum) {
		if (cnt == 2) {
			maxResult = Math.max(maxResult, sum);
			return;
		}

		for (int i = curX; i < N; i++) {
			for (int j = 0; j <= N - M; j++) {
				if (i == curX && j < curY) {
					continue;
				}

				// 가능성 확인
				boolean isPossible = true;
				for (int k = 0; k < M; k++) {
					if (isVisited[i][j + k]) {
						isPossible = false;
						break;
					}
				}

				if (isPossible) {
					int[] honeyArr = new int[M];
					for (int k = 0; k < M; k++) {
						honeyArr[k] = honey[i][j + k];
						isVisited[i][j + k] = true;
					}

					// 완성된 벌꿀로 나머지 조합 찾기
					tempHoney = new ArrayList<Integer>();
					int tempSum = findMaxCombi(honeyArr, new boolean[M], 0, 0, 0, 0, 0);
					findHoney(cnt + 1, curX, curY + M, sum + tempSum);

					// 되돌리기
					for (int k = 0; k < M; k++) {
						isVisited[i][j + k] = false;
					}
				}

			}
		}
	}

	public static int findMaxCombi(int[] honeyArray, boolean[] isVisited, int cnt, int idx, int maxFinalSum, int sum,
			int finalSum) {
		if (cnt > M || sum > C) {
			return maxFinalSum;
		}

		if (finalSum > maxFinalSum) {
			maxFinalSum = finalSum;
		}
		for (int i = idx; i < M; i++) {
			if (!isVisited[i]) {
				tempHoney.add(honeyArray[i]);
				maxFinalSum = findMaxCombi(honeyArray, isVisited, cnt + 1, i + 1, maxFinalSum, sum + honeyArray[i],
						finalSum + (honeyArray[i] * honeyArray[i]));
				tempHoney.add(tempHoney.size() - 1);
			}
		}

		return maxFinalSum;
	}

}
