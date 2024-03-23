package mst;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class SWEA1251 {

	static int N;
	static double E;
	static long minPrice;
	static boolean isVisited[];
	static ArrayList<int[]> island;
	static ArrayList<ArrayList<Node>> graph;
	static PriorityQueue<Node> pq;

	static class Node implements Comparable<Node> {
		int node;
		long weight;

		Node(int node, long weight) {
			this.node = node;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return Long.compare(this.weight, o.weight);
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {

			N = Integer.parseInt(br.readLine());
			initVariable();

			StringTokenizer st1 = new StringTokenizer(br.readLine());
			StringTokenizer st2 = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				island.add(new int[] { Integer.parseInt(st1.nextToken()), Integer.parseInt(st2.nextToken()) });
				
			}
			E = Double.parseDouble(br.readLine());

			for (int i = 0; i < N; i++) {
				graph.add(new ArrayList<Node>());
			}
			
			for (int i = 0; i < N - 1; i++) {
				int[] nodeA = island.get(i);
				for (int j = i + 1; j < N; j++) {
					int[] nodeB = island.get(j);
					long distance = ((nodeA[0] - nodeB[0]) * (nodeA[0] - nodeB[0]))
							+ ((nodeA[1] - nodeB[1]) * (nodeA[1] - nodeB[1]));
					System.out.println("distance : " + distance);

					graph.get(i).add(new Node(j, distance));
					graph.get(j).add(new Node(i, distance));
				}
			}

			prim();
			System.out.printf("#%d %.0f\n",  t , E * minPrice);
		}

	}

	public static void prim() {
		pq.add(new Node(0, 0));
		while (!pq.isEmpty()) {
			Node curNode = pq.poll();
			int node = curNode.node;
			long distance = curNode.weight;

			if (isVisited[node]) {
				continue;
			}

//			System.out.println("distance : " + distance);
			minPrice += distance;
			isVisited[node] = true;
			
			for (Node nextNode : graph.get(node)) {
				if (isVisited[nextNode.node]) {
					continue;
				}

				pq.add(nextNode);
			}
		}
	}

	public static void initVariable() {
		isVisited = new boolean[N];
		island = new ArrayList<int[]>();
		graph = new ArrayList<ArrayList<Node>>();
		pq = new PriorityQueue<Node>();
		minPrice = 0;
	}

}
