import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int result = Integer.MAX_VALUE;
    static int N;
    static int[][] ingredient;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        ingredient = new int[N][2];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int sour = Integer.parseInt(st.nextToken());
            int bitter = Integer.parseInt(st.nextToken());

            ingredient[i][0] = sour;
            ingredient[i][1] = bitter;
        }

//        backtracking(0, 0, 1, 0);
        bitMasking();

        System.out.println(result);
    }

    private static void bitMasking() {
        int comb = 1 << N;

        for (int i = 1; i < comb; i++) {
            int sour = 1;
            int bitter = 0;
            for (int j = 0; j < N; j++) {
                if ((i & (1 << j)) > 0) {
                    sour *= ingredient[j][0];
                    bitter += ingredient[j][1];
                }
            }
            result = Math.min(Math.abs(sour - bitter), result);
        }
    }

    private static void backtracking(int turn, int selected, int sour, int bitter) {
        if (turn == N) {
            if (selected == 0) return;

            result = Math.min(Math.abs(sour - bitter), result);
            return;
        }

        backtracking(turn + 1, selected + 1, sour * ingredient[turn][0], bitter + ingredient[turn][1]);
        backtracking(turn + 1, selected, sour, bitter);
    }
}