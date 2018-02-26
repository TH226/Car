package com.example.asus.car;

import com.example.asus.car.Bean.PersonBean;

import java.util.Comparator;

public class PinyinComparator implements Comparator<PersonBean> {

	@Override
	public int compare(PersonBean lhs, PersonBean rhs) {

		return sort(lhs, rhs);
	}

	private int sort(PersonBean lhs, PersonBean rhs) {
		// 获取ascii值
		int lhs_ascii = lhs.getFirstPinYin().toUpperCase().charAt(0);
		int rhs_ascii = rhs.getFirstPinYin().toUpperCase().charAt(0);
		// 判断若不是字母，则排在字母之后
		if (lhs_ascii < 65 || lhs_ascii > 90)
			return 1;
		else if (rhs_ascii < 65 || rhs_ascii > 90)
			return -1;
		else
			return lhs.getPinYin().compareTo(rhs.getPinYin());
	}

}
