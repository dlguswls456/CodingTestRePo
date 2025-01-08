import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int result;
    static int[] prices;
    static int[] count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        prices = new int[N];
        count = new int[4];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            prices[i] = Integer.parseInt(st.nextToken());
            count[prices[i]]++;
        }

        findMaxValue(3,0,3);
        findMaxValue(3,1,2);
        findMaxValue(2,0,2);
        findMaxValue(2,1,3);
        findMaxValue(1,0,1);
        findMaxValue(1,2,3);

        System.out.println(result);
    }

    public static void findMaxValue(int value, int idx1, int idx2) {
        int min = Math.min(count[idx1], count[idx2]);
        result += value * min;
        count[idx1]-= min;
        count[idx2]-= min;
    }
}