package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Ex03_02 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int maxResult = 0;

		st = new StringTokenizer(br.readLine());
		List<Integer> numList = new ArrayList<Integer>();
		while (st.hasMoreTokens()) {
			numList.add(Integer.parseInt(st.nextToken()));
		}

		numList.sort(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				return o2 - o1;
			}
		});
		int max = numList.get(0);
		int subMax = numList.get(1);

		// solution1
//		int tempK = K;
//		while (M > 0) {
//			if (tempK > 0) {
//				maxResult += max;
//				tempK--;
//				M--;
//			} else {
//				maxResult += subMax;
//				M--;
//				tempK = K;
//			}
//		}

		// solution2
		int fullAddingNum = max * K + subMax;
		int fullAddingTimes = M / (K + 1);
		int subAddingTimes = M % (K + 1);
		
		maxResult = fullAddingNum * fullAddingTimes + subAddingTimes * max;
		
		System.out.print(maxResult);
	}

}
