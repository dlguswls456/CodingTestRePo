package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SWEA1767 {

	static int N, maxProcessor, minLength, totalProcessor;
	static int[][] map;
	static boolean[][] isVisited;
	static ArrayList<ArrayList<Integer>> dirList;
	static ArrayList<Node> processorList;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	static class Node {
		int x;
		int y;

		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());

			map = new int[N][N];
			isVisited = new boolean[N][N];
			dirList = new ArrayList<ArrayList<Integer>>();
			processorList = new ArrayList<Node>();

			totalProcessor = 0;
			maxProcessor = 0;
			minLength = Integer.MAX_VALUE;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 1) {
						totalProcessor++;
						isVisited[i][j] = true;

						if (i == 0 || i == N - 1 || j == 0 || j == N - 1) {
							maxProcessor++;
							continue;
						}

						processorList.add(new Node(i, j));
					}
				}
			}

			for (int i = 0; i < processorList.size(); i++) {
				Node processor = processorList.get(i);
				dirList.add(new ArrayList<Integer>());

				for (int j = 0; j < 4; j++) {
					int newX = processor.x + dx[j];
					int newY = processor.y + dy[j];

					boolean flag = true;
					while (isValid(newX, newY)) {
						if (isVisited[newX][newY]) {
							flag = false;
							break;
						}

						newX = newX + dx[j];
						newY = newY + dy[j];
					}

					if (flag) {
						dirList.get(i).add(j);
					}
				}

			}

			findProcessor(maxProcessor, 0, 0);
			System.out.println("#" + t + " " + minLength);
		}
	}

	public static void findProcessor(int cnt, int sumLength, int idx) {
		// 갱신
		if (cnt > maxProcessor) {
			maxProcessor = cnt;
			minLength = sumLength;
		} else if (cnt == maxProcessor) {
			minLength = Math.min(minLength, sumLength);
		}

		// 조합
		for (int i = idx; i < processorList.size(); i++) {
			Node processor = processorList.get(i);
			ArrayList<Integer> tempDir = dirList.get(i);
			for (int dir : tempDir) {
				int len = 0;
				int possible = 1;
				// 전선 연결
				int newX = processor.x;
				int newY = processor.y;
				while (true) {
					newX = newX + dx[dir];
					newY = newY + dy[dir];
					if (!isValid(newX, newY)) {
						break;
					}

					if (isVisited[newX][newY]) {// 중간에 다른 전선을 만나는 경우
						returnVisited(processor.x, processor.y, dir, len);
						possible = 0;
						len = 0;
						break;
					}

					isVisited[newX][newY] = true;
					len++;
				}
				findProcessor(cnt + possible, sumLength + len, i + 1);
				// 돌려놓기
				returnVisited(processor.x, processor.y, dir, len);

			}

		}

	}

	public static void returnVisited(int x, int y, int dir, int len) {
		int newX = x;
		int newY = y;
		int cnt = 0;

		while (cnt < len) {
			newX = newX + dx[dir];
			newY = newY + dy[dir];

			isVisited[newX][newY] = false;
			cnt++;
		}
	}

	public static boolean isValid(int x, int y) {
		if (x < 0 || x >= N || y < 0 || y >= N) {
			return false;
		}
		return true;
	}
}
