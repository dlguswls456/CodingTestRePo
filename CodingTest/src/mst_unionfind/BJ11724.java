package mst_unionfind;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BJ11724 {

	static int[] parents;
	static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		parents = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int nodeA = Integer.parseInt(st.nextToken());
			int nodeB = Integer.parseInt(st.nextToken());

			union(nodeA, nodeB);
		}
		
		HashSet<Integer> hs = new HashSet<Integer>();
		for (int i = 1; i <= N; i++) {
			hs.add(find(i));
		}
		
		System.out.println(hs.size());

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
