package shortestpath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ1238 {

	static ArrayList<ArrayList<Node>> graph = new ArrayList<ArrayList<Node>>();
	static ArrayList<ArrayList<Node>> graphReversed = new ArrayList<ArrayList<Node>>();
	static int N, M, X;
	static int[] distance, distanceReversed;

	static class Node implements Comparable<Node> {
		int node;
		int distance;

		Node(int node, int distance) {
			this.node = node;
			this.distance = distance;
		}

		@Override
		public int compareTo(Node o) {
			return this.distance - o.distance;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());

		distance = new int[N + 1];
		distanceReversed = new int[N + 1];
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<Node>());
			graphReversed.add(new ArrayList<Node>());
			
			distance[i] = Integer.MAX_VALUE;
			distanceReversed[i] = Integer.MAX_VALUE;
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());

			graph.get(from).add(new Node(to, dist));
			graphReversed.get(to).add(new Node(from, dist));
		}

		findShortest(X);
		findShortestReversed(X);

		int[] result = new int[N + 1];
		int maxResult = 0;
		for (int i = 1; i <= N; i++) {
			result[i] = distance[i] + distanceReversed[i];
			maxResult = Math.max(maxResult, result[i]);
		}

		System.out.println(maxResult);

	}

	public static void findShortest(int startPoint) {
		PriorityQueue<Node> queue = new PriorityQueue<Node>();
		queue.add(new Node(startPoint, 0));
		distance[startPoint]= 0;

		while (!queue.isEmpty()) {
			Node curNode = queue.poll();
			int node = curNode.node;
			int dist = curNode.distance;

			if (dist > distance[node]) {
				continue;
			}

			ArrayList<Node> tempList = graph.get(node);
			for (Node nextNode : tempList) {
				if (nextNode.distance + dist < distance[nextNode.node]) {
					distance[nextNode.node] = nextNode.distance + dist;
					queue.add(new Node(nextNode.node, distance[nextNode.node]));
				}
			}

		}
	}

	public static void findShortestReversed(int startPoint) {
		PriorityQueue<Node> queue = new PriorityQueue<Node>();
		queue.add(new Node(startPoint, 0));
		distanceReversed[startPoint] = 0;

		while (!queue.isEmpty()) {
			Node curNode = queue.poll();
			int node = curNode.node;
			int dist = curNode.distance;

			if (dist > distanceReversed[node]) {
				continue;
			}

			ArrayList<Node> tempList = graphReversed.get(node);
			for (Node nextNode : tempList) {
				if (nextNode.distance + dist < distanceReversed[nextNode.node]) {
					distanceReversed[nextNode.node] = nextNode.distance + dist;
					queue.add(new Node(nextNode.node, distanceReversed[nextNode.node]));
				}
			}

		}
	}

}
