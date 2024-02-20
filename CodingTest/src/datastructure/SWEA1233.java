package datastructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA1233 {

	static boolean[] tree;
	static int N;
	static int result;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int t = 1; t <= 10; t++) {
			N = Integer.parseInt(br.readLine());
			tree = new boolean[N + 1];
			result = 1;
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int idx = Integer.parseInt(st.nextToken());
				char data = st.nextToken().charAt(0);
				if (data >= '0' && data <= '9') {
					tree[idx] = true;
				} else {
					tree[idx] = false;
				}
			}

			if (isPossible(1)) {
				result = 1;
			} else {
				result = 0;
			}
			System.out.println("#" + t + " " + result);
		}

	}

	public static boolean isPossible(int idx) {
		if (idx * 2 + 1 > N) {
			return tree[idx];
		}

		if (!tree[idx]) {// 연산자
			return isPossible(idx * 2) & isPossible(idx * 2 + 1);
		} else { // 숫자
			return tree[idx] & isPossible(idx * 2) & isPossible(idx * 2 + 1);
		}
	}

}
