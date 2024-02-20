package datastructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA1231 {
	static String[] tree;
	static int N;
	static StringBuilder result;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int t = 1; t <= 10; t++) {
			N = Integer.parseInt(br.readLine());
			tree = new String[N + 1];
			result = new StringBuilder();
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int idx = Integer.parseInt(st.nextToken());
				tree[idx] = st.nextToken();
			}

			readTree(1);
			System.out.println("#" + t + " " + result);

		}
	}

	public static void readTree(int idx) {
		if (idx > N) {
			return;
		}

		readTree(idx * 2);
		result.append(tree[idx]);
		readTree(idx * 2 + 1);
	}

}
