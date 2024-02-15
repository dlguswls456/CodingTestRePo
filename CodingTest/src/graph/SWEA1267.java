package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA1267 {

	static int[] nodeWorks;
	static int[][] graph;
	static String result;
	static int E, V;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int t = 1; t <= 10; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			nodeWorks = new int[V + 1];
			graph = new int[V + 1][V + 1];
			result = "";
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < E; i++) {
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());

				nodeWorks[end]++;
				graph[start][end] = 1;
			}

			for (int i = 1; i <= V; i++) {
				if (nodeWorks[i] == 0) {
					nodeWorks[i] = -1;
				}
			}

			for (int i = 1; i <= V; i++) {
				if (nodeWorks[i] == -1) {
					dfs(true, i);
				}
			}

			System.out.println("#" + t + " " + result);

		}

	}

	public static void dfs(boolean isPreWorkDone, int node) {
		boolean isWorkDone = false;
		if (isPreWorkDone) {
			nodeWorks[node]--;
		}
		if (nodeWorks[node] <= 0) {
			isWorkDone = true;
		}
		if (isPreWorkDone && isWorkDone) {
			result += node + " ";
		}

		for (int i = 1; i < graph[node].length; i++) {
			if (graph[node][i] == 1 && nodeWorks[i] > 0) {
				dfs(isWorkDone, i);
			}
		}
	}

}
