package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ14659 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] heights = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			heights[i] = Integer.parseInt(st.nextToken());
		}

		int result = 0;
		for (int i = 0; i < N; i++) {
			int tempResult = 0;
			for (int j = i + 1; j < N; j++) {
				if (heights[i] > heights[j]) {
					tempResult++;
				}else { // 하나라도 같거나 높은거 만나면 잠들어버림
					break;
				}
			}
			result = Math.max(result, tempResult);
		}
		
		System.out.println(result);
	}
}
