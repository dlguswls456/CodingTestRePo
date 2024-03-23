package mst;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ14621 {

	static int N, M, result;
	static char[] MW;
	static boolean[] isVisited;
	static ArrayList<ArrayList<Node>> graph = new ArrayList<ArrayList<Node>>();
	static PriorityQueue<Node> pq = new PriorityQueue<Node>();

	static class Node implements Comparable<Node> {
		int node;
		int weight;
		char preMW;

		public Node(int node, int weight, char preMW) {
			this.node = node;
			this.weight = weight;
			this.preMW = preMW;
		}

		@Override
		public int compareTo(Node o) {
			return this.weight - o.weight;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		MW = new char[N + 1];
		isVisited = new boolean[N + 1];
		graph.add(new ArrayList<Node>());
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			MW[i] = st.nextToken().charAt(0);
			graph.add(new ArrayList<Node>());
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int distance = Integer.parseInt(st.nextToken());

			graph.get(a).add(new Node(b, distance, 'a'));
			graph.get(b).add(new Node(a, distance, 'a'));
		}

		prim();
		for (int i = 1; i <= N; i++) {
			if (!isVisited[i]) {
				result = -1;
				break;
			}
		}
		System.out.println(result);

	}

	public static void prim() {
		pq.add(new Node(1, 0, 'a'));

		while (!pq.isEmpty()) {
			Node curNode = pq.poll();
			int node = curNode.node;
			int distance = curNode.weight;
			char preMW = curNode.preMW;
			char curMW = MW[node];

			if (isVisited[node]) {
				continue;
			}
			if (preMW == curMW) {
				continue;
			}

			isVisited[node] = true;
			result += distance;

			ArrayList<Node> curList = graph.get(node);
			for (Node nextNode : curList) {
				if (isVisited[nextNode.node]) {
					continue;
				}

				pq.add(new Node(nextNode.node, nextNode.weight, curMW));
			}
		}
	}

}
