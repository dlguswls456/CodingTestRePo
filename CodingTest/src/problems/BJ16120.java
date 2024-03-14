package problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//문자열 폭발과 같다
//절대 반복문 안에 String 변수가 들어가면 안됨!
public class BJ16120 {

	static String ppap = "PPAP";

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < line.length(); i++) {
			sb.append(line.charAt(i));
			if (sb.length() >= 4) {
				boolean flag = true;
				for (int j = 0; j < 4; j++) {
					if (sb.charAt(sb.length() - 4 + j) != ppap.charAt(j)) {
						flag = false;
						break;
					}
				}
				if (flag) {
					sb.replace(sb.length() - 4, sb.length(), "P");
				}
			}

		}
		
		if(sb.toString().equals("P")) {
			System.out.println("PPAP");
		}else {
			System.out.println("NP");
		}

	}

}
