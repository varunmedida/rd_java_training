package com.epam.engx.cleancode.dry.task1;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class InterestCalculator {

	private static final int AGE = 60;
	private static final double INTEREST_PERCENT = 4.5d;
	private static final double SENIOR_PERCENT = 5.5d;
	private static final int BONUS_AGE = 13;

	public BigDecimal calculateInterest(AccountDetails accountDetails) {
		if (isAccountStartedAfterBonusAge(accountDetails)) {
			return interest(accountDetails);
		} else {
			return BigDecimal.ZERO;
		}
	}

	private boolean isAccountStartedAfterBonusAge(AccountDetails accountDetails) {
		return durationBetweenDatesInYears(accountDetails.getBirth(), accountDetails.getStartDate()) > BONUS_AGE;
	}

	private int durationBetweenDatesInYears(Date from, Date to) {
		Calendar startCalendar = new GregorianCalendar();
		startCalendar.setTime(from);
		Calendar endCalendar = new GregorianCalendar();
		endCalendar.setTime(to);

		int diffYear = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
		if (endCalendar.get(Calendar.DAY_OF_YEAR) < startCalendar.get(Calendar.DAY_OF_YEAR))
			return diffYear - 1;
		return diffYear;
	}

	private BigDecimal interest(AccountDetails accountDetails) {
		double interest = 0;
			if (AGE <= accountDetails.getAge()) {
				interest = calculateInterestBasedOnAge(accountDetails, SENIOR_PERCENT);
			} else {
				interest = calculateInterestBasedOnAge(accountDetails, INTEREST_PERCENT);
			}
		return BigDecimal.valueOf(interest);
	}

	private double calculateInterestBasedOnAge(AccountDetails accountDetails, double percent) {
		double interest = accountDetails.getBalance().doubleValue()
				* durationBetweenDatesInYears(accountDetails.getStartDate(), new Date()) * percent / 100;
		return interest;
	}
}
