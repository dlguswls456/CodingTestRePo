import java.util.*;

class Solution {
        static int[] tools = new int[3];
    static int toolsSize;
    static int[][] tiredness;
    static int result = Integer.MAX_VALUE;

    public static int solution(int[] picks, String[] minerals) {
        tools = Arrays.copyOf(picks, 3);
        toolsSize = tools[0] + tools[1] + tools[2];

        int mineralsMaxIdx = minerals.length - 1;
        tiredness = new int[(mineralsMaxIdx / 5) + 1][3];
        for (int i = 0; i <= mineralsMaxIdx / 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (i * 5 + j <= mineralsMaxIdx) {
                    switch (minerals[i * 5 + j]) {
                        case "diamond": {
                            tiredness[i][0] += 1;
                            tiredness[i][1] += 5;
                            tiredness[i][2] += 25;
                            break;
                        }
                        case "iron": {
                            tiredness[i][0] += 1;
                            tiredness[i][1] += 1;
                            tiredness[i][2] += 5;
                            break;
                        }
                        case "stone": {
                            tiredness[i][0] += 1;
                            tiredness[i][1] += 1;
                            tiredness[i][2] += 1;
                            break;
                        }
                    }
                }
            }
        }

        calcMin(0, 0, 0);
        System.out.println(result);
        return result;
    }

    public static void calcMin(int idx, int toolCnt, int cntTiredness) {
        if (cntTiredness >= result) {
            return;
        }
        
        if (toolCnt == toolsSize || idx == tiredness.length) {
            result = cntTiredness;
            return;
        }

        for (int i = 0; i < 3; i++) {
            if (tools[i] != 0) {
                tools[i]--;
                calcMin(idx + 1, toolCnt + 1, cntTiredness + tiredness[idx][i]);
                tools[i]++;
            }
        }
    }

}