import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int N, mp, mf, ms, mv, minPrice = Integer.MAX_VALUE;
    static int[] p, f, s, v, c;
    static ArrayList<Integer> tempSelected = new ArrayList<>();
    static ArrayList<Integer> selected = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        mp = Integer.parseInt(st.nextToken());
        mf = Integer.parseInt(st.nextToken());
        ms = Integer.parseInt(st.nextToken());
        mv = Integer.parseInt(st.nextToken());

        p = new int[N + 1];
        f = new int[N + 1];
        s = new int[N + 1];
        v = new int[N + 1];
        c = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            p[i] = Integer.parseInt(st.nextToken());
            f[i] = Integer.parseInt(st.nextToken());
            s[i] = Integer.parseInt(st.nextToken());
            v[i] = Integer.parseInt(st.nextToken());
            c[i] = Integer.parseInt(st.nextToken());
        }

        findMinPrice(1, 0, 0, 0, 0, 0);

        if(minPrice == Integer.MAX_VALUE){
            System.out.println(-1);
        }else {
            System.out.println(minPrice);
            for (int elem : selected) {
                System.out.print(elem + " ");
            }
        }
    }

    public static void findMinPrice(int idx, int tempP, int tempF, int tempS, int tempV, int tempC) {
        if (tempC >= minPrice) {
            return;
        }
        if (tempP >= mp && tempF >= mf && tempS >= ms && tempV >= mv) {
            minPrice = tempC;
            selected = new ArrayList<>();
            selected.addAll(tempSelected);
            return;
        }

        if (idx <= N) {
            // 선택하거나
            tempSelected.add(idx);
            findMinPrice(
                    idx + 1,
                    tempP + p[idx],
                    tempF + f[idx],
                    tempS + s[idx],
                    tempV + v[idx],
                    tempC + c[idx]
            );
            tempSelected.remove(tempSelected.size() - 1);

            // 안하거나
            findMinPrice(idx + 1, tempP, tempF, tempS, tempV, tempC);
        }
    }
}