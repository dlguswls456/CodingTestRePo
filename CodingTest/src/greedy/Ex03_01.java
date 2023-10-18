package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Ex03_01 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int price = Integer.parseInt(br.readLine());

		List<Integer> changeList = new ArrayList<Integer>();
		changeList.add(500);
		changeList.add(100);
		changeList.add(50);
		changeList.add(10);

		int cnt = 0;
		for (int change : changeList) {
			cnt += price / change;
			price %= change;
			if (price == 0) {
				break;
			}
		}
		
		System.out.print(cnt);
	}

}
