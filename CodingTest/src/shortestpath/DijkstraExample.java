package shortestpath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

// 간단하지만 느린 코드
public class DijkstraExample {

	static HashMap<Integer, ArrayList<Node>> graph = new HashMap<Integer, ArrayList<Node>>();
	static boolean[] isVisited;
	static int[] distance;
	static int start, N, M;

	public static class Node {
		int node;
		int weight;

		public Node(int node, int weight) {
			this.node = node;
			this.weight = weight;
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

		dijkstra(start);
		System.out.println(start + "부터 각 지점까지 최단거리");
		for (int i = 1; i < N + 1; i++) {
			if (distance[i] == Integer.MAX_VALUE) {
				System.out.println("노드" + i + " INFINITY");
			} else {
				System.out.println("노드" + i + " " + distance[i]);
			}
		}

	}

	public static int getSmallestNode() {
		int index = 0;
		int minValue = Integer.MAX_VALUE;
		for (int i = 1; i < N + 1; i++) {
			if (!isVisited[i] && minValue > distance[i]) {
				index = i;
				minValue = distance[i];
			}
		}
		return index;
	}

	public static void dijkstra(int start) {
		// 시작 노드에 대해 초기화
		distance[start] = 0;
		isVisited[start] = true;
		for (Node connectedNode : graph.get(start)) {
			distance[connectedNode.node] = connectedNode.weight;
		}

		// 시작노드를 제외한 노드에 대해 반복
		for (int i = 0; i < N - 1; i++) {
			// 현재 최단 거리가 가장 짧은 노드를 꺼내서 방문처리
			int nowNode = getSmallestNode();
			isVisited[nowNode] = true;
			// 현재 노드와 연결된 다른 노드들 확인
			if (graph.containsKey(nowNode)) {
				for (Node connectedNode : graph.get(nowNode)) {
					distance[connectedNode.node] = Math.min(distance[connectedNode.node],
							distance[nowNode] + connectedNode.weight);
				}
			}
		}
	}

}
