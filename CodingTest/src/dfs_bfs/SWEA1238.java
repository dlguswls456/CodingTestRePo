package ssafy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA1238 {

	static int[][] graph;
	static int[] isVisited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int t = 1; t <= 10; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int startNode = Integer.parseInt(st.nextToken());

			graph = new int[101][101];
			isVisited = new int[101];

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N / 2; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());

				graph[from][to] = 1;
			}

			Queue<Integer> queue = new LinkedList<Integer>();
			queue.add(startNode);
			int flag = 101;
			isVisited[startNode] = flag;
			while (!queue.isEmpty()) {
				int currentNode = queue.poll();

				for (int i = 1; i < 101; i++) {
					if (graph[currentNode][i] == 1) {
						int nextNode = i;
						if (isVisited[nextNode] == 0) {
							queue.add(nextNode);
							isVisited[nextNode] = isVisited[currentNode] + 1;
							flag = isVisited[nextNode];
						}
					}
				}
			}

			int result = -1;
			for (int i = 1; i < 101; i++) {
				if (isVisited[i] == flag) {
					result = i;
				}
			}

			System.out.println("#" + t + " " + result);
		}

	}

}
