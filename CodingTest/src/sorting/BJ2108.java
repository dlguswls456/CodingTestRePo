package sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class BJ2108 {
	public static class CountNum {
		int positive;
		int negative;

		CountNum() {
			positive = 0;
			negative = 0;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		Integer[] numbers = new Integer[N];
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		CountNum[] countData = new CountNum[4001];
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(br.readLine());
			numbers[i] = num;

			if (num < min) {
				min = num;
			}
			if (num > max) {
				max = num;
			}

			if (num < 0) {
				if (countData[-1 * num] == null) {
					countData[-1 * num] = new CountNum();
				}
				countData[-1 * num].negative++;
			} else {
				if (countData[num] == null) {
					countData[num] = new CountNum();
				}
				countData[num].positive++;
			}
		}

		// Æò±Õ
		int avg = (int) Math.round(sumIntArray(numbers) / N);
		// Áß¾Ó°ª
		Arrays.sort(numbers);
		int mid = numbers[N / 2];
		// ÃÖºó°ª
		int maxCount = Integer.MIN_VALUE;
		int frequentNum = 0;
		for (int i = 0; i < 4001; i++) {
			if (countData[i] == null)
				continue;

			if (countData[i].positive >= maxCount) {
				maxCount = countData[i].positive;
			}

			if (countData[i].negative >= maxCount) {
				maxCount = countData[i].negative;
			}

		}

		// µÎ¹ø¤Š·Î ÀÛÀº ÃÖºó°ª
		ArrayList<Integer> frequentNumbers = new ArrayList<Integer>();
		for (int i = 0; i < 4001; i++) {
			if (countData[i] == null)
				continue;

			if (countData[i].positive == maxCount) {
				frequentNumbers.add(i);
			}

			if (countData[i].negative == maxCount) {
				frequentNumbers.add(-1 * i);
			}
		}

		if (frequentNumbers.size() == 1) {
			frequentNum = frequentNumbers.get(0);
		} else {
			frequentNumbers.sort(null);
			frequentNum = frequentNumbers.get(1);
		}

		System.out.println(avg);
		System.out.println(mid);
		System.out.println(frequentNum);
		System.out.println(max - min);
	}

	public static double sumIntArray(Integer[] array) {
		double sum = 0;
		for (int num : array) {
			sum += num;
		}

		return sum;
	}
}
