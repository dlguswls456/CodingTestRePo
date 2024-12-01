import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Grade implements Comparable<Grade> {
        int korean;
        int english;
        int math;
        String name;

        public Grade(int korean, int english, int math, String name) {
            this.korean = korean;
            this.english = english;
            this.math = math;
            this.name = name;
        }

        public int compareTo(Grade o) {
            if (this.korean == o.korean) {
                if (this.english == o.english) {
                    if (this.math == o.math) {
                        return this.name.compareTo(o.name);
                    } else {
                        return -1 * (this.math - o.math);
                    }
                } else {
                    return this.english - o.english;
                }
            } else {
                return -1 * (this.korean - o.korean);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Grade> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            int korean = Integer.parseInt(st.nextToken());
            int english = Integer.parseInt(st.nextToken());
            int math = Integer.parseInt(st.nextToken());

            pq.add(new Grade(korean, english, math, name));
        }

        while (!pq.isEmpty()){
            sb.append(pq.poll().name).append("\n");
        }

        System.out.println(sb);
    }
}