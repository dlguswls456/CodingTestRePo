package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ11000 {
	static class Timetable implements Comparable<Timetable> {
		int start;
		int end;

		Timetable(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Timetable o) {
			if (this.start == o.start) {
				return this.end - o.end;
			} else {
				return this.start - o.start;
			}
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		ArrayList<Timetable> classList = new ArrayList<Timetable>();
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			classList.add(new Timetable(start, end));
		}

		classList.sort(null);

		int result = 0;
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		pq.add(classList.get(0).end);
		for (int i = 1; i < N; i++) {
			if (pq.peek() <= classList.get(i).start) {
				pq.poll();
			}
			pq.offer(classList.get(i).end);
		}
		
		result = pq.size();
		System.out.println(result);
	}

}
