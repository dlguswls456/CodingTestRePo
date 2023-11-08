package dfs_bfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ1707 {

	static class Node {
		boolean isVisited;
		String color;

		Node(boolean isVisited, String color) {
			this.isVisited = isVisited;
			this.color = color;
		}

		Node() {
			this.isVisited = false;
			this.color = "white";
		}

	}

	public static ArrayList<ArrayList<Integer>> graph;
	public static ArrayList<Node> visited;
	public static boolean isBG = true;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int K = Integer.parseInt(br.readLine());

		for (int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
            graph = new ArrayList<ArrayList<Integer>>();
            visited = new ArrayList<Node>();
			isBG = true;
			for (int j = 0; j < V; j++) {
				graph.add(new ArrayList<Integer>());
				visited.add(new Node());
			}

			//�׷��� �׸���
			for (int j = 0; j < E; j++) {
				st = new StringTokenizer(br.readLine());
				int E1 = Integer.parseInt(st.nextToken());
				int E2 = Integer.parseInt(st.nextToken());
				putEdge(E1, E2);
			}

			//Ȯ�� ����
			for (int j = 0; j < V; j++) {
				if (visited.get(j).isVisited == false)
					dfs(j, "red");
			}

			if (isBG)
				bw.append("YES\n");
			else
				bw.append("NO\n");
		}
		
		bw.close();

	}

	public static void putEdge(int a, int b) {
		graph.get(a - 1).add(b - 1);
		graph.get(b - 1).add(a - 1);
	}

	public static void dfs(int node, String color) {
		//�̹� �湮�� ��� �� ��
		if (visited.get(node).color != "white") {
			//������ ��忡 �ٸ� ���� �����ؾ� �Ǵ� ��� -> �̺� �׷��� �ƴ�
			if (visited.get(node).color != color) {
				isBG = false;
			}
			return;
		}

		//ó�� �湮�ϴ� ��� �湮 ǥ��
		visited.get(node).isVisited = true;
		visited.get(node).color = color;

		//�ش� ����� ���� ��� ����� Ȯ��
		for (int i : graph.get(node)) {
			//���� �ݴ�Ǵ� ���� ���� ��忡 ���� �õ�
			if (visited.get(node).color == "red")
				dfs(i, "blue");
			else
				dfs(i, "red");
		}
	}

}