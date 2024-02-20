package ssafy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BJ1991 {
	static class Node {
		String node;
		String left;
		String right;

		public Node(String node, String left, String right) {
			this.node = node;
			this.left = left;
			this.right = right;
		}
	}

	static int N;
	static Node[] nodes;
	static LinkedList<String> tree = new LinkedList<String>();
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		nodes = new Node[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			nodes[i] = new Node(st.nextToken(), st.nextToken(), st.nextToken());
//			if(nodes[i].node.equals("A")) {
//				tree.add("A");
//			}
		}

//		tree.add("A");
//		makeTree("A");
//		for (String node : tree) {
//			System.out.print(node + " ");
//		}
//		
		preorder("A");
		System.out.println(sb);
		sb.setLength(0);
		inorder("A");
		System.out.println(sb);
		sb.setLength(0);
		postorder("A");
		System.out.println(sb);

	}

	public static void preorder(String node) {
		for (int i = 0; i < N; i++) {
			Node curNode = nodes[i];
			if (curNode.node.equals(node)) {
				sb.append(curNode.node);
				if (!curNode.left.equals(".")) {
					preorder(curNode.left);
				}
				if (!curNode.right.equals(".")) {
					preorder(curNode.right);
				}
			}
		}
	}

	public static void inorder(String node) {
		for (int i = 0; i < N; i++) {
			Node curNode = nodes[i];
			if (curNode.node.equals(node)) {
				if (!curNode.left.equals(".")) {
					preorder(curNode.left);
				}
				sb.append(curNode.node);
				if (!curNode.right.equals(".")) {
					preorder(curNode.right);
				}
			}
		}
	}

	public static void postorder(String node) {
		for (int i = 0; i < N; i++) {
			Node curNode = nodes[i];
			if (curNode.node.equals(node)) {
				if (!curNode.left.equals(".")) {
					preorder(curNode.left);
				}
				if (!curNode.right.equals(".")) {
					preorder(curNode.right);
				}
				sb.append(curNode.node);
			}
		}
	}

	public static void makeTree(String node) {
		for (int i = 0; i < N; i++) {
			Node curNode = nodes[i];
			if (curNode.node.equals(node)) {
				if (!curNode.left.equals(".")) {
					int idx = tree.indexOf(curNode.node);
					tree.add(idx, curNode.left);
					makeTree(curNode.left);
				}
				if (!curNode.right.equals(".")) {
					int idx = tree.indexOf(curNode.node);
					tree.add(idx + 1, curNode.right);
					makeTree(curNode.right);
				}
				break;
			}
		}
	}

}
