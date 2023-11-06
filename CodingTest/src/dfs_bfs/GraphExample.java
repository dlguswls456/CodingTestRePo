package dfs_bfs;

import java.util.ArrayList;

public class GraphExample {

	public static void main(String[] args) {

		int n = 5; // 정점의 개수

		// 인접 행렬
		int[][] matrixGraph = new int[n + 1][n + 1]; // 인덱스 1부터 사용하지 위해

		putEdgeMatrix(matrixGraph, 1, 2);
		putEdgeMatrix(matrixGraph, 1, 3);
		putEdgeMatrix(matrixGraph, 1, 4);
		putEdgeMatrix(matrixGraph, 2, 3);
		putEdgeMatrix(matrixGraph, 2, 5);
		putEdgeMatrix(matrixGraph, 3, 4);
		putEdgeMatrix(matrixGraph, 4, 5);

		printMatrix(matrixGraph);

		// 인접 리스트
		ArrayList<ArrayList<Integer>> listGraph = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i <= n; i++) {
			listGraph.add(new ArrayList<Integer>());
		}
		putEdgeList(listGraph, 1, 2);
		putEdgeList(listGraph, 1, 3);
		putEdgeList(listGraph, 1, 4);
		putEdgeList(listGraph, 2, 3);
		putEdgeList(listGraph, 2, 5);
		putEdgeList(listGraph, 3, 4);
		putEdgeList(listGraph, 4, 5);
		
		printList(listGraph);
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

	public static void putEdgeMatrix(int[][] graph, int x, int y) {
		graph[x][y] = 1;
		graph[y][x] = 1;
	}

	public static void printMatrix(int[][] graph) {
		for (int row = 1; row < graph.length; row++) {
			for (int col = 1; col < graph[row].length; col++) {
				System.out.print(graph[row][col] + " ");
			}
			System.out.println();
		}
	}

}
