package shortestpath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ17835 {
	static int N, M, K, maxInterview;
	static ArrayList<ArrayList<Node>> graph;
	static PriorityQueue<Node> pq = new PriorityQueue<Node>();
	static long[] distance;
	static long maxDistance;

	static class Node implements Comparable<Node> {
		int node;
		long distance;

		Node(int node, long distance) {
			this.node = node;
			this.distance = distance;
		}

		@Override
		public int compareTo(Node o) {
			return Long.compare(this.distance, o.distance);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		graph = new ArrayList<ArrayList<Node>>();
		distance = new long[N + 1];

		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<Node>());
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int dis = Integer.parseInt(st.nextToken());

			graph.get(to).add(new Node(from, dis));
		}

		for (int i = 1; i <= N; i++) {
			distance[i] = Long.MAX_VALUE;
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; i++) {
			int interview = Integer.parseInt(st.nextToken());
			pq.add(new Node(interview, 0));
			distance[interview] = 0;
		}
		dijkstra();

		for (int i = 1; i <= N; i++) {
			if (distance[i] == Long.MAX_VALUE) {
				continue;
			}

			if (maxDistance < distance[i]) {
				maxDistance = distance[i];
				maxInterview = i;
			}
		}

		System.out.println(maxInterview);
		System.out.println(maxDistance);
	}

	public static void dijkstra() {

		while (!pq.isEmpty()) {
			Node node = pq.poll();
			int curNode = node.node;
			long tempDistance = node.distance;

			if (tempDistance > distance[curNode]) {
				continue;
			}

			for (int i = 0; i < graph.get(curNode).size(); i++) {
				Node nextNode = graph.get(curNode).get(i);

				if (tempDistance + nextNode.distance < distance[nextNode.node]) {
					distance[nextNode.node] = tempDistance + nextNode.distance;
					pq.add(new Node(nextNode.node, distance[nextNode.node]));
				}
			}
		}
	}

}
