package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SWEA4008 {

	static ArrayList<Integer> numbers;
	static ArrayList<Integer> operators;
	static int N, minResult, maxResult;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			minResult = Integer.MAX_VALUE;
			maxResult = Integer.MIN_VALUE;
			numbers = new ArrayList<Integer>();
			operators = new ArrayList<Integer>();
			sb.setLength(0);
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= 4; i++) {
				int operCnt = Integer.parseInt(st.nextToken());
				operators.add(operCnt);
			}

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				numbers.add(Integer.parseInt(st.nextToken()));
			}

			findOper(0, numbers.get(0));
			System.out.println("#" + t + " " + (maxResult - minResult));

		}

	}

	public static void findOper(int cnt, int calcResult) {
		if (cnt == N - 1) {
			minResult = Math.min(minResult, calcResult);
			maxResult = Math.max(maxResult, calcResult);
			return;
		}

		for (int i = 0; i < operators.size(); i++) {
			if (operators.get(i) > 0) {
				int operCnt = operators.get(i);
				operators.set(i, operCnt - 1);
				int tempResult = 0;
				if (i == 0) {
					tempResult = calcResult + numbers.get(cnt + 1);
				} else if (i == 1) {
					tempResult = calcResult - numbers.get(cnt + 1);
				} else if (i == 2) {
					tempResult = calcResult * numbers.get(cnt + 1);
				} else {
					tempResult = calcResult / numbers.get(cnt + 1);
				}
				findOper(cnt + 1, tempResult);
				operators.set(i, operCnt);
			}
		}
	}

}
