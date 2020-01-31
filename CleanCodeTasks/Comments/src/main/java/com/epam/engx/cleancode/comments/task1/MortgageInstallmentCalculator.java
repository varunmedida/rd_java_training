package com.epam.engx.cleancode.comments.task1;

import com.epam.engx.cleancode.comments.task1.thirdpartyjar.InvalidInputException;

public class MortgageInstallmentCalculator {

	public static double calculateMonthlyPayment(int principalAmount, int timeInYears, double rateOfInterest) {

		if (principalAmount < 0 || timeInYears <= 0 || rateOfInterest < 0) {
			throw new InvalidInputException("Negative values are not allowed");
		}

		rateOfInterest /= 100.0;

		double timeInMonths = timeInYears * 12;

		if (rateOfInterest == 0)
			return principalAmount / timeInMonths;

		double monthlyInterest = rateOfInterest / 12.0;

		// The Math.pow() method is used calculate values raised to a power
		double monthlyPayment = (principalAmount * monthlyInterest)
				/ (1 - Math.pow(1 + monthlyInterest, -timeInMonths));

		return monthlyPayment;
	}
}
