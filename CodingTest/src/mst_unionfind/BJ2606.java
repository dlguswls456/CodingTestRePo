package mst_unionfind;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ2606 {

	static int N, M;
	static int[] parents;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		parents = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}

		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int nodeA = Integer.parseInt(st.nextToken());
			int nodeB = Integer.parseInt(st.nextToken());

			union(nodeA, nodeB);
		}

		// 다시 parents를 정리해줘야한다
		for (int i = 1; i <= N; i++) {
			find(i);
		}
		
		int result = 0;
		for (int i = 2; i <= N; i++) {
			if (parents[i] == 1) {
				result++;
			}
		}

		System.out.println(result);
	}

	public static int find(int node) {
		if (node != parents[node]) {
			parents[node] = find(parents[node]);
		}

		return parents[node];
	}

	public static void union(int nodeA, int nodeB) {
		int parentA = find(nodeA);
		int parentB = find(nodeB);

		if (parentA < parentB) {
			parents[parentB] = parentA;
		} else {
			parents[parentA] = parentB;
		}
	}

}
