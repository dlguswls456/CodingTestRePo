package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ1931 {

	static class Timetable implements Comparable<Timetable> {
		int start;
		int end;

		Timetable(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Timetable o) {
			if (this.end == o.end) {
				return this.start - o.start;
			} else {
				return this.end - o.end;
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

		int flagTime = 0;
		int result = 0;
		for (Timetable tb : classList) {
			if (flagTime <= tb.start) {
				flagTime = tb.end;
				result++;
			}
		}

		System.out.println(result);
	}

}
