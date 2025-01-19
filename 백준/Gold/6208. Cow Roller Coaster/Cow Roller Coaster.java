import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int L, N, B, result = Integer.MIN_VALUE;
    static int[][] dp;
    static PriorityQueue<Component> pq = new PriorityQueue<>();

    static class Component implements Comparable<Component> {
        int start;
        int end;
        int fun;
        int cost;

        Component(int start, int end, int fun, int cost) {
            this.start = start;
            this.end = end;
            this.fun = fun;
            this.cost = cost;
        }

        @Override
        public int compareTo(Component o) {
            return this.start - o.start;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());


        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            int f = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            pq.add(new Component(x, x + w, f, c));
        }

        dp = new int[L + 1][B + 1];
        for (int i = 1; i <= L; i++) {
            Arrays.fill(dp[i], -1);
        }
        findMax();

        for (int fun : dp[L]) {
            result = Math.max(result, fun);
        }
        System.out.println(result);
    }

    public static void findMax() {
        while (!pq.isEmpty()) {
            Component component = pq.poll();

            for (int i = component.cost; i <= B; i++) {
                if (dp[component.start][i] == -1) {
                    break;
                }
                dp[component.end][i - component.cost] =
                        Math.max(dp[component.start][i] + component.fun, dp[component.end][i - component.cost]);
            }
        }
    }
}