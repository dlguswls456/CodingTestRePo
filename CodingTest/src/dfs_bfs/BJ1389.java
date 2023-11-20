package dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ1389 {

	static int N, M;
	static ArrayList<ArrayList<Integer>> nodes = new ArrayList<ArrayList<Integer>>();
	static boolean[] isVisited;
	static int minPerson, minSumCount = Integer.MAX_VALUE, tempCount = 0;
	static int[][] minLength;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		for (int i = 0; i <= N; i++) {
			nodes.add(new ArrayList<Integer>());
		}
		M = Integer.parseInt(st.nextToken());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());

			nodes.get(A).add(B);
			nodes.get(B).add(A);
		}

		minPerson = 0;
		minLength = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			isVisited = new boolean[N + 1];
			dfs(i, i, 1);
		}

		for (int i = 1; i <= N; i++) {
			int sum = 0;
			for (int j = 1; j <= N; j++) {
				sum += minLength[i][j];
			}
			if (minSumCount > sum) {
				minSumCount = sum;
				minPerson = i;
			}
		}

		System.out.println(minPerson);
	}

	public static void dfs(int V, int currNode, int cnt) {
		isVisited[currNode] = true;

		for (int node : nodes.get(currNode)) {
			if (isVisited[node] == false) {
				isVisited[node] = true;

				if (minLength[V][node] == 0) {
					minLength[V][node] = cnt;
				} else {
					minLength[V][node] = Math.min(minLength[V][node], cnt);
				}

				dfs(V, node, cnt + 1);
				isVisited[node] = false;
			}
		}
	}
}
