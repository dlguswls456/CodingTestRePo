package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ1202 {

	static int N, K;
	static long result;
	static PriorityQueue<Jewel> jewelPQ = new PriorityQueue<Jewel>();
	static PriorityQueue<Integer> bags = new PriorityQueue<Integer>();
	static PriorityQueue<Integer> selecting = new PriorityQueue<Integer>(new Comparator<Integer>() {

		@Override
		public int compare(Integer o1, Integer o2) {
			// TODO Auto-generated method stub
			return o2 - o1;
		}
	});

	static class Jewel implements Comparable<Jewel> {
		int weight;
		int price;

		Jewel(int weight, int price) {
			this.weight = weight;
			this.price = price;
		}

		@Override
		public int compareTo(Jewel o) {
			if (this.weight == o.weight) {
				return o.price - this.price;
			} else {
				return this.weight - o.weight;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());
			int V = Integer.parseInt(st.nextToken());

			jewelPQ.add(new Jewel(M, V));
		}
		for (int i = 0; i < K; i++) {
			bags.add(Integer.parseInt(br.readLine()));

		}

		while (!bags.isEmpty()) {
			int curBag = bags.poll();
			
			while (!jewelPQ.isEmpty() && curBag >= jewelPQ.peek().weight) {
				Jewel curJewel = jewelPQ.poll();

				selecting.add(curJewel.price);
			}
			
			if(!selecting.isEmpty()) {
				result+=selecting.poll();
			}
			
		}
		
		System.out.println(result);

	}

}
