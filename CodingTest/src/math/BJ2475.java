package math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ2475 {
	//�ŵ�����
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int sum = 0;
		while (st.hasMoreTokens()) {
			int num = Integer.parseInt(st.nextToken());
			sum += Math.pow(num, 2);
		}

		int result = sum % 10;
		System.out.print(result);
	}

}
