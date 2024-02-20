package datastructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ9934 {

	static int K, N, order;
	static int[] tree, numbers;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());
		N = (int) Math.pow(2, K);
		order = 1;
		tree = new int[N];
		numbers = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i < N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}

		makeTree(1);

		int flag = 0;
		int cnt = 0;
		for (int i = 1; i < N; i++) {
			cnt++;
			System.out.print(tree[i] + " ");
			if (cnt == Math.pow(2, flag)) {
				System.out.println();
				flag++;
				cnt = 0;
			}
		}

	}

	public static void makeTree(int idx) {
		if (idx >= N) {
			return;
		}
		makeTree(idx * 2);
		tree[idx] = numbers[order];
		order++;
		makeTree(idx * 2 + 1);
	}

}
