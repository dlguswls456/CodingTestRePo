package mst;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 최소신장트리 - PRIM
 * https://velog.io/@suk13574/%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98Java-%ED%94%84%EB%A6%BCPrim-%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98
 * 백준 1197
 */
public class PrimExample {

	static ArrayList<ArrayList<Node>> nodeList = new ArrayList<ArrayList<Node>>();
	static PriorityQueue<Node> pq = new PriorityQueue<Node>();
	static boolean[] isVisited;

	static class Node implements Comparable<Node> {
		int node;
		int weight;

		Node(int node, int weight) {
			this.node = node;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return this.weight - o.weight;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		for (int i = 0; i <= N; i++) {
			nodeList.add(new ArrayList<Node>());
		}
		isVisited = new boolean[N + 1];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			nodeList.get(from).add(new Node(to, weight));
			nodeList.get(to).add(new Node(from, weight));
		}

		int result = prim();

		System.out.println(result);
	}

	public static int prim() {
		int result = 0;
		
		pq.add(new Node(1, 0));
		
		while (!pq.isEmpty()) {
			Node node = pq.poll();
			int curNode = node.node;
			int curWeight = node.weight;

			if (!isVisited[curNode]) {
				isVisited[curNode] = true;
				result += curWeight;
				
				ArrayList<Node> curNodeList = nodeList.get(curNode);
				for (int i = 0; i < curNodeList.size(); i++) {
					if (!isVisited[curNodeList.get(i).node]) {
						pq.add(curNodeList.get(i));
					}
				}
			}
		}
		return result;
	}

}
