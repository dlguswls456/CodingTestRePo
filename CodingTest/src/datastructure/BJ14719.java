package datastructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BJ14719 {

	static int N, M, result;
	static int[] rain, toRightAmount, toLeftAmount;
	static boolean[] isVisited;
	static Stack<Integer> toRight = new Stack<Integer>();
	static Stack<Integer> toLeft = new Stack<Integer>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		rain = new int[M];
		toRightAmount = new int[M];
		toLeftAmount = new int[M];
		isVisited = new boolean[M];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			rain[i] = Integer.parseInt(st.nextToken());
		}

		toRight.add(rain[0]);
		toRightAmount[0] = 0;
		for (int i = 1; i < M; i++) {
			while (!toRight.isEmpty() && toRight.peek() <= rain[i]) {
				toRight.pop();
			}

			if (!toRight.isEmpty()) {
				toRightAmount[i] = toRightAmount[i - 1] + rain[i - 1] - rain[i];
			}

			toRight.add(rain[i]);
		}

		toLeft.add(rain[M - 1]);
		toLeftAmount[M - 1] = 0;
		for (int i = M - 2; i >= 0; i--) {
			while (!toLeft.isEmpty() && toLeft.peek() <= rain[i]) {
				toLeft.pop();
			}

			if (!toLeft.isEmpty()) {
				toLeftAmount[i] = toLeftAmount[i + 1] + rain[i + 1] - rain[i];
			}

			toLeft.add(rain[i]);
		}

		for (int i = 0; i < M; i++) {
			result += Math.min(toRightAmount[i], toLeftAmount[i]);			
		}
		
		System.out.println(result);
	}

}
