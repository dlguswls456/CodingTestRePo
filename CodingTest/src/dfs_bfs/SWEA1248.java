package dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class SWEA1248 {

	public static ArrayList<ArrayList<Integer>> tree;
	public static Object[] ancestor1;
	public static Object[] ancestor2;
	public static int cntNode;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int C = Integer.parseInt(br.readLine());

		for (int TC = 1; TC <= C; TC++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			
			tree = new ArrayList<ArrayList<Integer>>();
			tree.add(new ArrayList<Integer>());// 인덱스가 0인 것
			for (int i = 1; i <= V; i++) {
				tree.add(new ArrayList<Integer>());
			}

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < E; i++) {
				int parent = Integer.parseInt(st.nextToken());
				int child = Integer.parseInt(st.nextToken());
				tree.get(parent).add(child);
			}

			findAncestors(1, node1, node2, V);
			int commonAncestor = findCommonAncestor();
			findAncestors(commonAncestor, 0, 0, V);
			System.out.println("#" + TC + " " + commonAncestor + " " + cntNode);
		}

	}

	public static int findCommonAncestor() {
		int commonAncestor = 0;
		for (int i = ancestor1.length - 1; i >= 0; i--) {
			if (Arrays.asList(ancestor2).contains(ancestor1[i])) {
				commonAncestor = (int) ancestor1[i];
				break;
			}
		}

		return commonAncestor;
	}

	public static void findAncestors(int root, int child1, int child2, int V) {
		boolean[] isVisited = new boolean[V + 1];
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(root);
		isVisited[root] = true;

		cntNode = 1;

		while (!stack.isEmpty()) {
			int now = stack.peek();
			boolean isAllVisited = true;

			for (int node : tree.get(now)) {
				if (node == child1) {
					ancestor1 = stack.toArray();
				}
				if (node == child2) {
					ancestor2 = stack.toArray();
				}

				if (isVisited[node] == false) {
					cntNode++;
					stack.push(node);
					isVisited[node] = true;
					isAllVisited = false;
					break;
				}
			}

			if (isAllVisited) {
				stack.pop();
			}
		}

	}

}
