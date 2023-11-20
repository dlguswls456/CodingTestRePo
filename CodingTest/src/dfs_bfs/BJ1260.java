package dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ1260 {

	static int N, M, V;
	static ArrayList<ArrayList<Integer>> nodes = new ArrayList<ArrayList<Integer>>();
	static boolean[] isVisited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		for (int i = 0; i <= N; i++) {
			nodes.add(new ArrayList<Integer>());
		}
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());

			nodes.get(A).add(B);
			nodes.get(B).add(A);
		}
		
		for (int i = 0; i <= N; i++) {
			nodes.get(i).sort(null);
		}

		isVisited = new boolean[N + 1];
		dfs(V);
		System.out.println();
		isVisited = new boolean[N + 1];
		bfs(V);
	}

	public static void bfs(int V) {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(V);
		isVisited[V] = true;
		System.out.print(V + " ");

		while (!queue.isEmpty()) {
			int now = queue.remove();
			for (int node : nodes.get(now)) {
				if (isVisited[node] == false) {
					isVisited[node] = true;
					System.out.print(node + " ");
					queue.add(node);
				}
			}
		}

	}

	public static void dfs(int V) {
		isVisited[V] = true;
		System.out.print(V + " ");

		for (int node : nodes.get(V)) {
			if (isVisited[node] == false) {
				isVisited[node] = true;
				dfs(node);
			}
		}
	}
}
