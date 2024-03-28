package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.StringTokenizer;

public class BJ15685 {

	static int X, Y, D, G, N, result;
	static HashSet<Node> nodeList = new HashSet<Node>();
	static ArrayList<Node> tempList;
	static int[] rotateDy = { 0, -1, -1, 1, 1 };
	static int[] rotateDx = { 0, 1, -1, -1, 1 };
	static int[] dirX = { 1, 0, -1, 0 };
	static int[] dirY = { 0, 1, 0, -1 };// y방향 반대로 생각하기
	static int[][] map = new int[100][100];
	static int[] countDx = { -1, -1, 0, 0 };
	static int[] countDy = { -1, 0, -1, 0 };

	static class Node {
		int x;
		int y;

		Node(int x, int y) {
			this.x = x;
			this.y = y;
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

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			X = Integer.parseInt(st.nextToken());
			Y = Integer.parseInt(st.nextToken());
			D = Integer.parseInt(st.nextToken());
			G = Integer.parseInt(st.nextToken());

			tempList = new ArrayList<Node>();
			// 초기 두 점 넣어주기 -> 0세대
			tempList.add(new Node(0, 0));
			tempList.add(new Node(dirX[D], dirY[D]));

			// 회전
			rotate(0);

			// y좌표 처리 & 진짜 좌표 구해서 Set에 추가
			for (Node node : tempList) {
				int realX = X + node.x;
				int realY = Y + node.y * -1;

				nodeList.add(new Node(realY, realX));
			}
		}

		// 사각형 세기
		for (Node node : nodeList) {
			for (int i = 0; i < 4; i++) {
				int idxX = node.x + countDx[i];
				int idxY = node.y + countDy[i];

				if (isValid(idxX, idxY)) {
					map[idxX][idxY]++;
				}
			}
		}

		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if (map[i][j] >= 4) {
					result++;
				}
			}
		}

		System.out.println(result);
	}

	// 회전
	public static void rotate(int gen) {
		if (gen == G) {
			return;
		}

		int size = tempList.size();
		// 끝점은 가장 마지막에 추가된 점
		Node point = tempList.get(size - 1);

		// 끝점으로부터 이어진 점 확인
		for (int i = size - 2; i >= 0; i--) {
			Node tempNode = tempList.get(i);

			// 현재 점을 기준으로 끝점이 어느 사분면에 있는지 확인
			int diffX = point.x - tempNode.x;
			int diffY = point.y - tempNode.y;

			int where = 0;
			if (diffX >= 0 && diffY >= 0) {// 1사분면
				where = 1;
			} else if (diffX < 0 && diffY >= 0) {// 2
				where = 2;
			} else if (diffX <= 0 && diffY < 0) {// 3
				where = 3;
			} else {// 4
				where = 4;
			}

			// 현재 점을 끝점을 기준으로 시계방향 회전한 좌표 구하기 & 추가
			int newX = point.x + rotateDy[where] * Math.abs(diffY);
			int newY = point.y + rotateDx[where] * Math.abs(diffX);
			tempList.add(new Node(newX, newY));
		}

		// 다음 세대 구하기
		rotate(gen + 1);

	}

	public static boolean isValid(int x, int y) {
		if (x < 0 || x >= 100 || y < 0 || y >= 100) {
			return false;
		}
		return true;
	}

}
