import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static PriorityQueue<Schedule> pq = new PriorityQueue<>();
    static int N, result;

    static class Schedule implements Comparable<Schedule> {
        int start, end, length;

        public Schedule(int start, int end) {
            this.start = start;
            this.end = end;
            this.length = end-start;
        }

        @Override
        public int compareTo(Schedule o) {
            if (this.end == o.end) {
                return this.start - o.start;
            } else {
                return this.end - o.end;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            pq.add(new Schedule(start, end));
        }

        int occupiedEndTime = 0;
        while (!pq.isEmpty()) {
            Schedule tempSchedule = pq.poll();

            if (occupiedEndTime <= tempSchedule.start) {
                result++;
                occupiedEndTime = tempSchedule.end;
            }
        }

        System.out.println(result);
    }
}

