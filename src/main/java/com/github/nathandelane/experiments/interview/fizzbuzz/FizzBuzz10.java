package com.github.nathandelane.experiments.interview.fizzbuzz;

public final class FizzBuzz10 {

	public static void main(final String[] args) {
		byte num = 0;

		while (num++ < 100)
			System.out.println(
				num % 15 == 0 ? "FizzBuzz" :
					num % 3 == 0 ? "Fizz" :
						num % 5 == 0 ? "Buzz" :
							String.valueOf(num)
			);
	}

}
