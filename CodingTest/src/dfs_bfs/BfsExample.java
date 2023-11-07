package dfs_bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BfsExample {
	public static ArrayList<Boolean> visited = new ArrayList<Boolean>();
	public static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();

	public static void main(String[] args) {

		for (int i = 0; i <= 8; i++) {
			graph.add(new ArrayList<Integer>());
		}

		putEdgeList(graph, 1, 2);
		putEdgeList(graph, 1, 3);
		putEdgeList(graph, 1, 8);
		putEdgeList(graph, 2, 7);
		putEdgeList(graph, 3, 4);
		putEdgeList(graph, 3, 5);
		putEdgeList(graph, 4, 5);
		putEdgeList(graph, 6, 7);
		putEdgeList(graph, 7, 8);

		printList(graph);

		for (int i = 0; i <= 8; i++) {
			visited.add(false);
		}

		// Å¥ ÀÌ¿ë
		bfsQueue(1);

	}

	public static void bfsQueue(int node) {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(node);
		visited.set(node, true);
		System.out.print(node + " ");

		while (!queue.isEmpty()) {
			int now = queue.remove();
			for (int i : graph.get(now)) {
				if (visited.get(i) == false) {
					queue.add(i);
					visited.set(i, true);
					System.out.print(i + " ");
				}
			}
		}

	}

	public static void printList(ArrayList<ArrayList<Integer>> graph) {
		for (int i = 1; i < graph.size(); i++) {
			System.out.print("node[" + i + "] : ");
			ArrayList<Integer> node = graph.get(i);
			for (int j = 0; j < node.size(); j++) {
				System.out.print(node.get(j) + "->");
			}
			System.out.println();
		}
	}

	public static void putEdgeList(ArrayList<ArrayList<Integer>> graph, int x, int y) {
		graph.get(x).add(y);
		graph.get(y).add(x);
	}
}
