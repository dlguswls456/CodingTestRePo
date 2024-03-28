package mst_unionfind;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ17472 {

	static int N, M, result, cntIsland;
	static int[][] graph, map;
	static boolean[][] isVisited;
	static int[] parent;
	static PriorityQueue<Bridge> pq = new PriorityQueue<Bridge>();
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static List<HashSet<Node>> edgeNodeList = new ArrayList<HashSet<Node>>();
	static HashSet<Node> tempSet = new HashSet<Node>();

	static class Node {
		int x;
		int y;
		int dir = 0;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public Node(int x, int y, int dir) {
			this.x = x;
			this.y = y;
			this.dir = dir;
		}

		@Override
		public int hashCode() {
			return Objects.hash(x, y);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Node other = (Node) obj;
			return x == other.x && y == other.y;
		}

	}

	static class Bridge implements Comparable<Bridge> {
		int islandA;
		int islandB;
		int distance;

		Bridge(int islandA, int islandB, int distance) {
			this.islandA = islandA;
			this.islandB = islandB;
			this.distance = distance;
		}

		@Override
		public int compareTo(Bridge o) {
			return this.distance - o.distance;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		isVisited = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 섬 찾기
		cntIsland = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 1 && !isVisited[i][j]) {
					cntIsland++;
					dfs(i, j);
				}
			}
		}

		// 경계선 찾기
		findEdge();

		// 짧은 다리 찾기
		findMinBridge();

		// MST - kruskal
		kruskal();

		// 모든 섬이 연결되어있는지 확인
		int root = find(1);
		for (int i = 2; i <= cntIsland; i++) {
			if (find(i) != root) {
				result = -1;
			}
		}

		if (result == 0) {
			result = -1;
		}

		System.out.println(result);
	}

	private static void kruskal() {
		parent = new int[cntIsland + 1];
		for (int i = 1; i <= cntIsland; i++) {
			parent[i] = i;
		}
		for (int i = 1; i <= cntIsland; i++) {
			for (int j = i + 1; j <= cntIsland; j++) {
				if (graph[i][j] != Integer.MAX_VALUE) {
					pq.add(new Bridge(i, j, graph[i][j]));
				}
			}
		}

		while (!pq.isEmpty()) {
			Bridge bridge = pq.poll();
			if (union(bridge.islandA, bridge.islandB)) {
				result += bridge.distance;
			}
		}
	}

	private static void findMinBridge() {
		graph = new int[cntIsland + 1][cntIsland + 1];
		for (int i = 1; i <= cntIsland; i++) {
			for (int j = 1; j <= cntIsland; j++) {
				graph[i][j] = Integer.MAX_VALUE;
			}
		}
		int idx = 0;
		for (HashSet<Node> hs : edgeNodeList) {
			for (Node node : hs) {
				int x = node.x;
				int y = node.y;
				int cnt = 0;
				int arrivedIsland = 0;

				while (isValid(x, y)) {
					if (map[x][y] == 0) {
						cnt++; // 카운트
						x += dx[node.dir];// 이동
						y += dy[node.dir];
					} else {// 섬 만나는 경우
						if (map[x][y] != idx) { // 다른 섬
							arrivedIsland = map[x][y];
						} else { // 본인 섬
							cnt = 0;
						}
						break;
					}
				}

				if (cnt >= 2) {
					graph[idx][arrivedIsland] = Math.min(cnt, graph[idx][arrivedIsland]);
					graph[arrivedIsland][idx] = Math.min(cnt, graph[idx][arrivedIsland]);
				}
			}
			idx++;
		}
	}

	private static void findEdge() {
		for (int i = 0; i <= cntIsland; i++) {
			edgeNodeList.add(new HashSet<Node>());
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0) {
					continue;
				}

				for (int k = 0; k < 4; k++) {
					int newX = i + dx[k];
					int newY = j + dy[k];

					if (isValid(newX, newY) && map[newX][newY] == 0) {
						HashSet<Node> hs = edgeNodeList.get(map[i][j]);
						hs.add(new Node(newX, newY, k));
					}
				}
			}
		}
	}

	public static boolean union(int nodeA, int nodeB) {
		int parentA = find(nodeA);
		int parentB = find(nodeB);

		if (parentA == parentB) {
			return false;
		}

		if (parentA < parentB) {
			parent[parentB] = parentA;
		} else {
			parent[parentA] = parentB;
		}

		return true;
	}

	public static int find(int node) {
		if (node != parent[node]) {
			parent[node] = find(parent[node]);
		}

		return parent[node];
	}

	public static void dfs(int x, int y) {
		isVisited[x][y] = true;
		map[x][y] = cntIsland;

		for (int i = 0; i < 4; i++) {
			int newX = x + dx[i];
			int newY = y + dy[i];

			if (!isValid(newX, newY) || isVisited[newX][newY] || map[newX][newY] != 1)
				continue;

			dfs(newX, newY);
		}
	}

	public static boolean isValid(int x, int y) {
		if (x < 0 || x >= N || y < 0 || y >= M) {
			return false;
		}
		return true;
	}
}
