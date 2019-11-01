package com.github.nathandelane.experiments.interview;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * See <a href="https://programmers.blogoverflow.com/2012/08/20-controversial-programming-opinions/">https://programmers.blogoverflow.com/2012/08/20-controversial-programming-opinions/</a>
 * <div>
 * <b>If you're a developer, you should be able to write code.</b>
 * <p>"I did quite a bit of interviewing last year, and for my part of the interview I was supposed to test the way people 
 * thought, and how they implemented simple-to-moderate algorithms on a white board. I'd initially started out with questions like:
 * <blockquote>Given that Pi can be estimated using the function 4 * (1 – 1/3 + 1/5 – 1/7 + …) with more terms giving greater accuracy, 
 * write a function that calculates Pi to an accuracy of 5 decimal places.
 * </blockquote>"It's a problem that should make you think, but shouldn't be out of reach to a seasoned developer (it can be answered in 
 * about 10 lines of C#). However, many of our (supposedly pre-screened by the agency) candidates couldn't even begin to answer it, 
 * or even explain how they might go about answering it. So after a while I started asking simpler questions like:
 * <blockquote>Given the area of a circle is given by Pi times the radius squared, write a function to calculate the area of a circle.</blockquote>
 * "Amazingly, more than half the candidates couldn't write this function in any language (I can read most popular languages so I let them use any 
 * language of their choice, including pseudo-code). We had "C# developers" who could not write this function in C#. I was surprised by this. I had 
 * always thought that developers should be able to write code. It seems that, nowadays, this is a controversial opinion. Certainly it is amongst 
 * interview candidates!
 * <p> 
 * <p>by Greg Beech</p>
 * @author nathanlane
 *
 */
public class InterviewPi5 {
	
	static int getNextDenominator(final int currentDenominator) {
		return ((currentDenominator < 0) ? (currentDenominator - 2) : (currentDenominator + 2)) * (-1);
	}
	
	static double estimatePiToFiveDecimalPlaces() {
		BigDecimal currentValue = BigDecimal.ZERO;
		
		int denominator = 1;
		double accumulator = 0;
		
		while (Double.compare(3.14159, currentValue.doubleValue()) != 0) {
			accumulator = accumulator + (1.0 / (double) denominator);
			denominator = getNextDenominator(denominator);
			
			currentValue = BigDecimal.valueOf(4.0 * accumulator)
				.round(new MathContext(6, RoundingMode.HALF_UP));
		}
		
		return currentValue.doubleValue();
	}
	
	static double piSeries(final int denominator, final double accumulator) {
		final BigDecimal currentValue = BigDecimal.valueOf(4.0 * accumulator)
			.round(new MathContext(6, RoundingMode.HALF_UP));
		
		if (Double.compare(currentValue.doubleValue(), 0.151492) == 0) {
			return 0;
		}
		
		final int newDenominator = getNextDenominator(denominator);
		final double newAccumulator = accumulator + (1.0 / (double) denominator);
		
		return piSeries(newDenominator, newAccumulator);
	}
	
	public static void main(final String[] args) {
		final double pi = estimatePiToFiveDecimalPlaces();

		System.out.format("%f%n", pi);
	}

}
