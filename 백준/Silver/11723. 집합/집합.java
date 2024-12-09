import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int numbers = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String order = st.nextToken();

            switch (order) {
                case "add":
                    int adding = Integer.parseInt(st.nextToken());
                    numbers |= (1 << adding);
                    break;
                case "remove":
                    int removing = Integer.parseInt(st.nextToken());
                    numbers &= ~(1 << removing);
                    break;
                case "check":
                    int checking = Integer.parseInt(st.nextToken());
                    int result = (numbers & (1 << checking)) != 0 ? 1 : 0;
                    sb.append(result).append("\n");
                    break;
                case "toggle":
                    int toggling = Integer.parseInt(st.nextToken());
                    numbers ^= (1 << toggling);
                    break;
                case "all":
                    numbers = (1 << 21) - 1;
                    break;
                case "empty":
                    numbers = 0;
                    break;
            }
        }
        System.out.println(sb);
    }
}