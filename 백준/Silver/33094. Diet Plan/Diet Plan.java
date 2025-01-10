import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M, K, result;
    static int[] plan;
    static PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        plan = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            plan[i] = Integer.parseInt(st.nextToken());
        }

        findMaxValuePQ();
        System.out.println(result);
    }

    public static void findMaxValuePQ() {
        int tempMilk = 0;
        for (int i = 1; i <= N; i++) {
            pq.add(plan[i]);
            tempMilk += plan[i];
            result = i;

            if (tempMilk > M) {
                if (K > 0) {
                    tempMilk -= pq.poll();
                    K--;
                } else {
                    result = i - 1;
                    break;
                }
            }
        }
    }
    
    // 시간 초과
    public static void findMaxValue(int day, int milk, int biscuit) {
        result = Math.max(result, day);

        if (day < N) {
            // 마시거나
            if (milk + plan[day + 1] <= M) {
                findMaxValue(day + 1, milk + plan[day + 1], biscuit);
            }
            // 먹거나
            if (biscuit > 0) {
                findMaxValue(day + 1, milk, biscuit - 1);
            }
        }
    }
}