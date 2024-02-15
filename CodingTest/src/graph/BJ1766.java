package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ1766 {

	static int[] nodeWorks;
	static ArrayList<Integer>[] graph;
	static StringBuilder result = new StringBuilder();
	static PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
	static int E, V;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		nodeWorks = new int[V + 1];
		graph = new ArrayList[V + 1];
		for (int i = 1; i <= V; i++)
			graph[i] = new ArrayList<>();
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());

			nodeWorks[end]++;
			graph[start].add(end);
		}

		for (int i = 1; i <= V; i++) {
			if (nodeWorks[i] == 0) {
				queue.add(i);
			}
		}

		while (!queue.isEmpty()) {
			int currentNode = queue.poll();
			result.append(currentNode).append(" ");

			for (int i = 0; i < graph[currentNode].size(); i++) {
				int nextNode = graph[currentNode].get(i);

				nodeWorks[nextNode]--;
				if (nodeWorks[nextNode] == 0) {
					queue.add(nextNode);
				}
			}
		}

		System.out.println(result);

	}

}
