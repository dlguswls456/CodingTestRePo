package shortestpath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ1916 {

	static int N, M, A, B;
	static int[] minPrice;
	static PriorityQueue<Bus> pq = new PriorityQueue<Bus>();
	static ArrayList<ArrayList<Bus>> graph = new ArrayList<ArrayList<Bus>>();

	static class Bus implements Comparable<Bus> {
		int target;
		int price;

		public Bus(int target, int price) {
			this.target = target;
			this.price = price;
		}

		@Override
		public int compareTo(Bus o) {
			return this.price - o.price;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		minPrice = new int[N + 1];
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<Bus>());
			minPrice[i] = Integer.MAX_VALUE;
		}

		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int target = Integer.parseInt(st.nextToken());
			int price = Integer.parseInt(st.nextToken());

			if (graph.get(start) != null) {
				graph.get(start).add(new Bus(target, price));
			} else {
				ArrayList<Bus> tempList = new ArrayList<Bus>();
				tempList.add(new Bus(target, price));
				graph.set(start, tempList);
			}
		}

		StringTokenizer st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());

		findMinPrice();
		System.out.println(minPrice[B]);
	}

	public static void findMinPrice() {
		minPrice[A] = 0;
		pq.add(new Bus(A, 0));

		while (!pq.isEmpty()) {
			Bus curBus = pq.poll();
			// 이미 처리되었다면 건너뛰기
			if (minPrice[curBus.target] < curBus.price) {
				continue;
			}

			for (Bus nextBus : graph.get(curBus.target)) {
				int tempPrice = nextBus.price + curBus.price;
				// 현재 노드를 거쳐서 다음 노드를 가는게 빠른지 비교
				if (tempPrice < minPrice[nextBus.target]) {
					minPrice[nextBus.target] = tempPrice;
					pq.add(new Bus(nextBus.target, minPrice[nextBus.target]));
				}
			}
		}
	}
}
