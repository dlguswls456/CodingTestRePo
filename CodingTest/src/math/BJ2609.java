package math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ2609 {
	// �ִ����� �ּҰ����
	public static int num1;
	public static int num2;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		num1 = Integer.parseInt(st.nextToken());
		num2 = Integer.parseInt(st.nextToken());

		int GCD;
		if (num1 >= num2) {
			GCD = greatestCommonDivisor(num1, num2);
		} else {
			GCD = greatestCommonDivisor(num2, num1);
		}
		System.out.println(GCD);

		int LCM = leastCommonMultiple(GCD);
		System.out.println(LCM);
	}

	// �ִ����� = (a,b) -> (b,a%b) ��� �ݺ� ���⼭ a�� b���� ũ�ų� ����
	public static int greatestCommonDivisor(int num1, int num2) {
		if (num1 % num2 == 0) {
			return num2;
		}
		return greatestCommonDivisor(num2, num1 % num2);
	}

	// �ּҰ���� = �� �ڿ����� ��/�ִ�����
	public static int leastCommonMultiple(int GCD) {
		return num1 * num2 / GCD;
	}

}
