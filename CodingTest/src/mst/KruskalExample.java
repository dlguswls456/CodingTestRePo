package mst;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 최소신장트리 - Kruskal
 * https://velog.io/@jxlhe46/%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98-MST 백준 1197
 * BJ1197
 */
public class KruskalExample {

	static int[] parents;
	static int N, M, result;
	static PriorityQueue<Edge> pq = new PriorityQueue<Edge>();

	static class Edge implements Comparable<Edge> {
		int nodeA;
		int nodeB;
		int weight;

		public Edge(int nodeA, int nodeB, int weight) {
			this.nodeA = nodeA;
			this.nodeB = nodeB;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int nodeA = Integer.parseInt(st.nextToken());
			int nodeB = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			pq.add(new Edge(nodeA, nodeB, weight));
		}

		kruskal();
		System.out.println(result);
	}

	public static void kruskal() {
		parents = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}

		while (!pq.isEmpty()) {
			Edge curEdge = pq.poll();
			int nodeA = curEdge.nodeA;
			int nodeB = curEdge.nodeB;
			int weight = curEdge.weight;

			if (union(nodeA, nodeB)) {
				result += weight;
			}
		}
	}

	public static int find(int node) { // 루트 조상 찾기
		if (parents[node] != node) {
			parents[node] = find(parents[node]);
		}

		return parents[node];
	}

	public static boolean union(int x, int y) { // 같은 그래프로 합치기
		// 루트 조상을 연결시켜줘야 한다
		int parentX = find(x);
		int parentY = find(y);

		if (parentX == parentY) {
			return false;
		}

		if (parentX < parentY) {
			parents[parentY] = parentX;
		} else {
			parents[parentX] = parentY;
		}
		
		return true;
	}

}
