package shortestpath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ18352 {

	static ArrayList<Node>[] graph2;
	static boolean[] isVisited;
	static int[] distance;
	static int start, N, M, K;
	static PriorityQueue<Node> priorityQueue = new PriorityQueue<Node>();

	public static class Node implements Comparable<Node> {
		int node;
		int weight;
		int distance;

		public Node(int node, int weight) {
			this.node = node;
			this.weight = weight;
		}

		public Node(int node, int weight, int distance) {
			this(node, weight);
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
		K = Integer.parseInt(st.nextToken());
		isVisited = new boolean[N + 1];
		distance = new int[N + 1];
		graph2 = new ArrayList[N + 1];
		for (int i = 0; i < N + 1; i++) {
			distance[i] = Integer.MAX_VALUE;
		}
		start = Integer.parseInt(st.nextToken());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			if (graph2[a] == null) {
				ArrayList<Node> tempList = new ArrayList<Node>();
				tempList.add(new Node(b, 1));
				graph2[a] = tempList;
			} else {
				graph2[a].add(new Node(b, 1));
			}
		}

		fastDijkstra(start);
		boolean hasResult = false;
		for (int i = 1; i < N + 1; i++) {
			if (distance[i] == K) {
				System.out.println(i);
				hasResult = true;
			}
		}
		if (!hasResult) {
			System.out.println(-1);
		}

	}

	public static void fastDijkstra(int start) {
		// 시작 노드에 대해 초기화
		distance[start] = 0;
		priorityQueue.add(new Node(start, 0, distance[start]));

		// 시작노드를 제외한 노드에 대해 반복
		while (!priorityQueue.isEmpty()) {
			Node nowNode = priorityQueue.poll();
			if (isVisited[nowNode.node]) {
				continue;
			}
			isVisited[nowNode.node] = true;
			if (graph2[nowNode.node] != null) {
				for (Node connectedNode : graph2[nowNode.node]) {
					if (distance[connectedNode.node] > distance[nowNode.node] + connectedNode.weight) {
						distance[connectedNode.node] = distance[nowNode.node] + connectedNode.weight;
						priorityQueue.add(new Node(connectedNode.node, 0, distance[connectedNode.node]));
					}
				}
			}
		}
	}

}
