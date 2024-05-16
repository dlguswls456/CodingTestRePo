package programmers;

import java.util.ArrayList;

public class 이모티콘할인행사 {

	static ArrayList<Integer> discountList = new ArrayList<Integer>();
	static int emoticonN, userN, maxUser, maxIncome;
	static int[][] userArr;
	static int[] emoticonArr;

	public int[] solution(int[][] users, int[] emoticons) {
		int[] answer = {};

		emoticonN = emoticons.length;
		userN = users.length;

		userArr = new int[userN][2];
		emoticonArr = new int[emoticonN];

		userArr = users;
		emoticonArr = emoticons;

		findMaxUserAndIncome(0);

		answer = new int[2];
		answer[0] = maxUser;
		answer[1] = maxIncome;

		return answer;
	}

	public void findMaxUserAndIncome(int cnt) {
		if (cnt == emoticonN) {
			calcUserAndIncome();
			return;
		}

		for (int i = 1; i <= 4; i++) {
			int percentage = i * 10;
			discountList.add(percentage);
			findMaxUserAndIncome(cnt + 1);
			discountList.remove(discountList.size() - 1);
		}

	}

	public void calcUserAndIncome() {
		int tempUser = 0;
		int tempIncome = 0;

		for (int i = 0; i < userN; i++) {
			int userMinPercentage = userArr[i][0];
			int userMinPrice = userArr[i][1];

			int sumPrice = 0;
			for (int j = 0; j < emoticonN; j++) {
				int curDiscountPercentage = discountList.get(j);
				int originalPrice = emoticonArr[j];

				if (userMinPercentage <= curDiscountPercentage) {
					sumPrice += originalPrice * (100 - curDiscountPercentage) / 100;
				}
			}

			if (userMinPrice <= sumPrice) {
				tempUser++;
			} else {
				tempIncome += sumPrice;
			}
		}

		if (maxUser < tempUser) {
			maxUser = tempUser;
			maxIncome = tempIncome;
		} else if (maxUser == tempUser) {
			if (maxIncome < tempIncome) {
				maxIncome = tempIncome;
			}
		}
	}
}
