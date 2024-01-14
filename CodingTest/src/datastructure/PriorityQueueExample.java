package datastructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

// BJ1927, BJ11279, BJ11286
public class PriorityQueueExample {

	static class CustomClass implements Comparable<CustomClass> {
		private int absoluteNum;
		private int originNum;

		public CustomClass(int num) {
			this.absoluteNum = Math.abs(num);
			this.originNum = num;
		}

		public int getOriginNum() {
			return originNum;
		}

		public void setOriginNum(int originNum) {
			this.originNum = originNum;
		}

		public int getAbsoluteNum() {
			return absoluteNum;
		}

		public void setAbsoluteNum(int absoluteNum) {
			this.absoluteNum = absoluteNum;
		}

		@Override
		public int compareTo(CustomClass o) {
			if (this.absoluteNum > o.absoluteNum) {
				return 1;
			} else if (this.absoluteNum < o.absoluteNum) {
				return -1;
			} else {
				return this.originNum - o.originNum;
			}
		}

	}

	static BufferedReader br;
	static int N;

	public static void main(String[] args) throws Exception, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

//		priorityQueueWithMinHeap();
//		priorityQueueWithMaxHeap();
		priorityQueueWithCustomHeap();
	}

	public static void priorityQueueWithCustomHeap() throws IOException {
		PriorityQueue<CustomClass> priorityQueueCustom = new PriorityQueue<CustomClass>();
		for (int i = 0; i < N; i++) {
			int input = Integer.parseInt(br.readLine());
			if (input == 0) {
				CustomClass result = priorityQueueCustom.poll();
				if (result == null) {
					System.out.println(0);
				} else {
					System.out.println(result.getOriginNum());
				}
			} else {
				priorityQueueCustom.add(new CustomClass(input));
			}
		}
	}

	public static void priorityQueueWithMaxHeap() throws IOException {
		PriorityQueue<Integer> priorityQueueHighest = new PriorityQueue<Integer>(Collections.reverseOrder());
		for (int i = 0; i < N; i++) {
			int input = Integer.parseInt(br.readLine());
			if (input == 0) {
				Integer result = priorityQueueHighest.poll();
				if (result == null) {
					System.out.println(0);
				} else {
					System.out.println(result);
				}
			} else {
				priorityQueueHighest.add(input);
			}
		}
	}

	public static void priorityQueueWithMinHeap() throws IOException {
		PriorityQueue<Integer> priorityQueueLowest = new PriorityQueue<Integer>();
		for (int i = 0; i < N; i++) {
			int input = Integer.parseInt(br.readLine());
			if (input == 0) {
				Integer result = priorityQueueLowest.poll();
				if (result == null) {
					System.out.println(0);
				} else {
					System.out.println(result);
				}
			} else {
				priorityQueueLowest.add(input);
			}
		}
	}

}
