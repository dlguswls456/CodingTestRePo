package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1783 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int result = 0;
		// 높이가 1이면 어차피 다른 칸으로 이동 불가
		if (N == 1) {
			result = 1;
		// 높이가 2이면 위아래 1칸씩 이동밖에 선택지가 없다
		} else if (N == 2) {
			result = Math.min(4, (M + 1) / 2);
		// 높이가 3이상일 때
		} else {
			// 넓이가 7이하면 4번 이상 움직일 수 없고
			// 최대한 많이 움직여야하기 때문에 오른쪽으로 1칸씩 이동하는 경우가 최대이다
			if (M < 7) {
				result = Math.min(4, M);
			// 넓이가 7이상이면 4번 이상 움직일 수 있고
			// 최대한 많이 움직여야하기 때문에 
			// 4경우 전부 사용 후 오른쪽으로 1칸씩 이동하는 경우가 최대이다
			// 1 + 4 + M - 7 = M - 2 
			} else {
				result = Math.max(5, M - 2);
			}
		}

		System.out.println(result);

	}

}
