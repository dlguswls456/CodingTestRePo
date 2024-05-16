package datastructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.StringTokenizer;

public class BJ2179 {

	static int N, length;
	static PriorityQueue<Point> pq = new PriorityQueue<Point>();

	static class Point implements Comparable<Point> {
		int x;
		int y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Point o) {
			if (this.x == o.x) {
				return this.y - o.y;
			} else {
				return this.x - o.x;
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			pq.add(new Point(x, y));
		}

		Point pre = pq.poll();
		while (!pq.isEmpty()) {
			Point cur = pq.poll();

			if (pre.y >= cur.x) {
				pre.y = Math.max(pre.y, cur.y);
			} else {
				length += pre.y - pre.x;
				pre = cur;
			}
		}
		length += pre.y - pre.x;

		System.out.println(length);
	}

}

