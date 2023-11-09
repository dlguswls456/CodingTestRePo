package sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;

public class Ex06_01 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		for (int i = 0; i < N; i++) {
			int number = Integer.parseInt(br.readLine());
			numbers.add(number);
		}

		numbers.sort(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				return o2 - o1;
			}
		});

		for (int number : numbers) {
			System.out.print(number + " ");
		}
	}
}
