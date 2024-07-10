package 엘리스코드;

import java.io.*;

public class 엘리스코드_목표량 {

    static int N, length;
    static int[] numbers;
    static boolean[] isVisited;
    static int result = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        int tempN = N;
        length = 0;
        while (tempN > 0) {
            tempN /= 10;
            length++;
        }
        numbers = new int[length];
        isVisited = new boolean[length];

        tempN = N;
        length = 0;
        while (tempN > 0) {
            numbers[length] = tempN % 10;
            tempN /= 10;
            length++;
        }

        combination(0, 0);
        System.out.println(result);
    }

    static void combination(int cnt, int num) {
        if (cnt == length) {
            if (N < num) {
                result = Math.min(num, result);
            }
            return;
        }

        for (int i = 0; i < length; i++) {
            if (isVisited[i])
                continue;

            num = num * 10 + numbers[i];
            isVisited[i] = true;
            combination(cnt + 1, num);
            num = (num - numbers[i]) / 10;
            isVisited[i] = false;
        }
    }
}
