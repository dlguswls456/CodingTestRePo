package problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ1259 {
	// ÆÓ¸°µå·Ò ¼ö(µ¥Ä®ÄÚ¸¶´Ï)
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String num = br.readLine();
		while (!num.equals("0")) {
			boolean isTrue = true;
			for (int i = 0; i < num.length() / 2; i++) {
				if (num.charAt(i) != num.charAt(num.length() - 1 - i)) {
					isTrue = false;
					break;
				}
			}
			if (isTrue) {
				System.out.println("yes");
			} else {
				System.out.println("no");
			}
			num = br.readLine();
		}
	}

}
