import java.util.*;

class Solution {
    static ArrayList<Integer> tools = new ArrayList<>();
    static ArrayList<ArrayList<Integer>> tiredness = new ArrayList<>();
    static int toolsSize;
    static int result = Integer.MAX_VALUE;

    public static int solution(int[] picks, String[] minerals) {
        int answer = 0;

        for (int i = 0; i < 3; i++) {
            tools.add(picks[i]);
        }
        toolsSize = tools.get(0) + tools.get(1) + tools.get(2);

        int mineralsMaxIdx = minerals.length - 1;
        for (int i = 0; i <= mineralsMaxIdx / 5; i++) {
            ArrayList<Integer> temp = new ArrayList<>();
            temp.add(0);
            temp.add(0);
            temp.add(0);

            tiredness.add(temp);
            for (int j = 0; j < 5; j++) {
                if (i * 5 + j <= mineralsMaxIdx) {
                    switch (minerals[i * 5 + j]) {
                        case "diamond": {
                            tiredness.get(i).set(0, tiredness.get(i).get(0) + 1);
                            tiredness.get(i).set(1, tiredness.get(i).get(1) + 5);
                            tiredness.get(i).set(2, tiredness.get(i).get(2) + 25);
                            break;
                        }
                        case "iron": {
                            tiredness.get(i).set(0, tiredness.get(i).get(0) + 1);
                            tiredness.get(i).set(1, tiredness.get(i).get(1) + 1);
                            tiredness.get(i).set(2, tiredness.get(i).get(2) + 5);
                            break;
                        }
                        case "stone": {
                            tiredness.get(i).set(0, tiredness.get(i).get(0) + 1);
                            tiredness.get(i).set(1, tiredness.get(i).get(1) + 1);
                            tiredness.get(i).set(2, tiredness.get(i).get(2) + 1);
                            break;
                        }
                    }
                }
            }
        }

        for (ArrayList<Integer> t : tiredness) {
            System.out.println(t);
        }

        calcMin(0,0,0);
        answer = result;
        System.out.println(answer);
        return answer;
    }

    public static void calcMin(int idx, int toolCnt, int cntTiredness) {
        if (toolCnt == toolsSize || idx == tiredness.size()) {
            result = Math.min(result, cntTiredness);
            return;
        }

        if (tools.get(0) != 0) {
            tools.set(0, tools.get(0) - 1);
            calcMin(idx + 1, toolCnt + 1, cntTiredness + tiredness.get(idx).get(0));
            tools.set(0, tools.get(0) + 1);
        }

        if (tools.get(1) != 0) {
            tools.set(1, tools.get(1) - 1);
            calcMin(idx + 1, toolCnt + 1, cntTiredness + tiredness.get(idx).get(1));
            tools.set(1, tools.get(1) + 1);
        }

        if (tools.get(2) != 0) {
            tools.set(2, tools.get(2) - 1);
            calcMin(idx + 1, toolCnt + 1, cntTiredness + tiredness.get(idx).get(2));
            tools.set(2, tools.get(2) + 1);
        }
    }

}