package shortestpath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// BJ1753
// ���� ���������� ���� �ڵ�
public class FastDijkstraExample {

	static HashMap<Integer, ArrayList<Node>> graph = new HashMap<Integer, ArrayList<Node>>();
	static boolean[] isVisited;
	static int[] distance;
	static int start, N, M;
	static PriorityQueue<NodeDistance> priorityQueue = new PriorityQueue<NodeDistance>();

	public static class Node {
		int node;
		int weight;

		public Node(int node, int weight) {
			this.node = node;
			this.weight = weight;
		}
	}

	public static class NodeDistance implements Comparable<NodeDistance> {
		int node;
		int distance;

		public NodeDistance(int node, int distance) {
			this.node = node;
			this.distance = distance;
		}

		@Override
		public int compareTo(NodeDistance o) {
			return this.distance - o.distance;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		isVisited = new boolean[N + 1];
		distance = new int[N + 1];
		for (int i = 0; i < N + 1; i++) {
			distance[i] = Integer.MAX_VALUE;
		}
		start = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			if (!graph.containsKey(a)) {
				ArrayList<Node> tempList = new ArrayList<Node>();
				tempList.add(new Node(b, w));
				graph.put(a, tempList);
			} else {
				graph.get(a).add(new Node(b, w));
			}
		}

		fastDijkstra(start);
		System.out.println(start + "���� �� �������� �ִܰŸ�");
		for (int i = 1; i < N + 1; i++) {
			if (distance[i] == Integer.MAX_VALUE) {
				System.out.println("���" + i + " INFINITY");
			} else {
				System.out.println("���" + i + " " + distance[i]);
			}
		}

	}

	public static void fastDijkstra(int start) {
		// ���� ��忡 ���� �ʱ�ȭ
		distance[start] = 0;
		priorityQueue.add(new NodeDistance(start, distance[start]));

		// ���۳�带 ������ ��忡 ���� �ݺ�
		while (!priorityQueue.isEmpty()) {
			NodeDistance nowNode = priorityQueue.poll();
			if(isVisited[nowNode.node]) {
				continue;
			}
			isVisited[nowNode.node] = true;
			if (graph.containsKey(nowNode.node)) {
				for (Node connectedNode : graph.get(nowNode.node)) {
					if (distance[connectedNode.node] > distance[nowNode.node] + connectedNode.weight) {
						distance[connectedNode.node] = distance[nowNode.node] + connectedNode.weight;
						priorityQueue.add(new NodeDistance(connectedNode.node, distance[connectedNode.node]));
					}
				}
			}
		}
	}

}
