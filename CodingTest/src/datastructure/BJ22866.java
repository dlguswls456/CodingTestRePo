package datastructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BJ22866 {

	static int N;
	static int[] buildings;
	static Stack<Building> toRight = new Stack<Building>();
	static Stack<Building> toLeft = new Stack<Building>();
	static int[] resultCnt, resultCloseBuilding;

	static class Building {
		int num;
		int height;

		Building(int num, int height) {
			this.num = num;
			this.height = height;
		}

		@Override
		public String toString() {
			return "Building [num=" + num + ", height=" + height + "]";
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		buildings = new int[N + 1];
		resultCnt = new int[N + 1];
		resultCloseBuilding = new int[N + 1];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			buildings[i] = Integer.parseInt(st.nextToken());
			resultCloseBuilding[i] = Integer.MAX_VALUE;
		}

		toRight.add(new Building(1, buildings[1]));
		for (int i = 1; i <= N; i++) {
			while (!toRight.isEmpty() && toRight.peek().height <= buildings[i]) {
				toRight.pop();
			}
			resultCnt[i] += toRight.size();
			if (toRight.size() > 0) {
				resultCloseBuilding[i] = toRight.peek().num;
			}

			toRight.add(new Building(i, buildings[i]));
		}

		toLeft.add(new Building(N, buildings[N]));
		for (int i = N - 1; i > 0; i--) {
			while (!toLeft.isEmpty() && toLeft.peek().height <= buildings[i]) {
				toLeft.pop();
			}
			resultCnt[i] += toLeft.size();
			if (toLeft.size() > 0) {
				if (resultCnt[i] != 0) {
					int a = Math.abs(resultCloseBuilding[i] - i);
					int b = Math.abs(toLeft.peek().num - i);

					if (a > b) {
						resultCloseBuilding[i] = toLeft.peek().num;
					}
				}

			}

			toLeft.add(new Building(i, buildings[i]));
		}

		for (int i = 1; i <= N; i++) {
			System.out.print(resultCnt[i] + " ");
			
			if(resultCnt[i] !=0) {
				System.out.print(resultCloseBuilding[i]);
			}
			System.out.println();
		}
	}
}
