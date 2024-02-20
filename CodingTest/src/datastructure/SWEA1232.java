package datastructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA1232 {
	static String[][] tree;
	static int N;
	static int result;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int t = 1; t <= 10; t++) {
			N = Integer.parseInt(br.readLine());
			tree = new String[N + 1][3];
			result = 0;
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int idx = Integer.parseInt(st.nextToken());
				tree[idx][0] = st.nextToken();
				if (tree[idx][0].equals("+") || tree[idx][0].equals("-") || tree[idx][0].equals("*")
						|| tree[idx][0].equals("/")) {
					tree[idx][1] = st.nextToken();
					tree[idx][2] = st.nextToken();
				}
			}

			result = (int) calcTree(1);
			System.out.println("#" + t + " " + result);

		}
	}

	public static double calcTree(int idx) {
		if (!tree[idx][0].equals("+") && !tree[idx][0].equals("-") && !tree[idx][0].equals("*")
				&& !tree[idx][0].equals("/")) {
			return Double.parseDouble(String.valueOf(tree[idx][0]));
		}

		if (tree[idx][0].equals("+"))
			return calcTree(Integer.parseInt(tree[idx][1])) + calcTree(Integer.parseInt(tree[idx][2]));
		else if (tree[idx][0].equals("-"))
			return calcTree(Integer.parseInt(tree[idx][1])) - calcTree(Integer.parseInt(tree[idx][2]));
		else if (tree[idx][0].equals("*"))
			return calcTree(Integer.parseInt(tree[idx][1])) * calcTree(Integer.parseInt(tree[idx][2]));
		else
			return calcTree(Integer.parseInt(tree[idx][1])) / calcTree(Integer.parseInt(tree[idx][2]));

	}

}
