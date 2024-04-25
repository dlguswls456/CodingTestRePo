package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ2625 {

	static int N, M;
	static int[] leftSinger;
	static boolean[] isVisited;
	static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
	static Queue<Integer> queue = new LinkedList<Integer>();
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		leftSinger = new int[N + 1];
		isVisited = new boolean[N + 1];
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<Integer>());
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			Integer.parseInt(st.nextToken());
			int preSinger = Integer.parseInt(st.nextToken());
			while (st.hasMoreTokens()) {
				int nextSinger = Integer.parseInt(st.nextToken());
				graph.get(preSinger).add(nextSinger);
				leftSinger[nextSinger]++;

				preSinger = nextSinger;
			}
		}

		for (int i =1; i <= N; i++) {
			if (!isVisited[i] && leftSinger[i] == 0) {
				singing(i);
			}
		}

		for (int i =1; i <= N; i++) {
			if(leftSinger[i] !=0) {
				System.out.println(0);
				return;
			}
		}
		System.out.println(sb);

	}

	public static void singing(int startSinger) {
		queue.add(startSinger);

		while (!queue.isEmpty()) {
			int singer = queue.poll();
			isVisited[singer] = true;
			sb.append(singer + "\n");

			ArrayList<Integer> tempList = graph.get(singer);
			for (int nextSinger : tempList) {
				leftSinger[nextSinger]--;
				if (leftSinger[nextSinger] == 0) {
					queue.add(nextSinger);
				}
			}
		}
	}

}
