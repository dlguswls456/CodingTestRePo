package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ7682_틱택토 {

    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String line = br.readLine();
            if (line.equals("end")) {
                break;
            }

            map = new int[3][3];
            int cntX = 0;
            int cntO = 0;
            for (int i = 0; i < 9; i++) {
                char xo = line.charAt(i);
                if (xo == 'X') {
                    cntX++;
                    map[i / 3][i % 3] = -1;
                } else if (xo == 'O') {
                    cntO++;
                    map[i / 3][i % 3] = 1;
                } else {
                    map[i / 3][i % 3] = 0;
                }
            }

            if (!(cntX == cntO + 1 || cntX == cntO)) {
                System.out.println("invalid");
                continue;
            }

            boolean result;
            boolean isCurrentValid = checkMapValid(-1) | checkMapValid(1);
            boolean isPreviousValid = checkPreviousValid(cntX, cntO);
            if (cntX + cntO == 9) {
                if (isCurrentValid) {
                    if (isPreviousValid) {
                        result = false;
                    } else {
                        result = true;
                    }
                } else {
                    result = true;
                }
            } else {
                if (isCurrentValid) {
                    if (isPreviousValid) {
                        result = false;
                    } else {
                        result = true;
                    }
                } else {
                    result = false;
                }
            }

            if (result) {
                System.out.println("valid");
            } else {
                System.out.println("invalid");
            }
        }

    }

    private static boolean checkPreviousValid(int cntX, int cntO) {
        boolean isPreviousValid = true;

        if (cntX == cntO) { // O를 하나씩 없애면서 확인
            for (int i = 0; i < 3; i++) {
                if (!isPreviousValid) {
                    break;
                }

                for (int j = 0; j < 3; j++) {
                    if (!isPreviousValid) {
                        break;
                    }

                    if (map[i][j] == 1) {
                        map[i][j] = 0;
                        isPreviousValid = isPreviousValid & (checkMapValid(-1) | checkMapValid(1));
                        map[i][j] = 1;
                    }
                }
            }
        } else { // X를 하나씩 없애면서 확인
            for (int i = 0; i < 3; i++) {
                if (!isPreviousValid) {
                    break;
                }

                for (int j = 0; j < 3; j++) {
                    if (!isPreviousValid) {
                        break;
                    }

                    if (map[i][j] == -1) {
                        map[i][j] = 0;
                        isPreviousValid = isPreviousValid & (checkMapValid(-1) | checkMapValid(1));
                        map[i][j] = -1;
                    }
                }
            }
        }
        return isPreviousValid;
    }

    static boolean checkMapValid(int xo) {
        boolean ver = false;
        boolean hor = false;
        boolean cross = false;

        for (int i = 0; i < 3; i++) {
            if (xo == map[i][0] && map[i][0] == map[i][1] && map[i][1] == map[i][2]) {
                hor = true;
            }

            if (xo == map[0][i] && map[0][i] == map[1][i] && map[1][i] == map[2][i]) {
                ver = true;
            }
        }

        if (xo == map[0][0] && map[0][0] == map[1][1] && map[1][1] == map[2][2]) {
            cross = true;
        }
        if (xo == map[0][2] && map[0][2] == map[1][1] && map[1][1] == map[2][0]) {
            cross = true;
        }

        return hor | ver | cross;
    }

}

