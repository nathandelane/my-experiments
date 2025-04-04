package com.github.nathandelane.experiments.arrays;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class ArrayOfClasses {

	interface Animal {

		String makeSound();

	}

	public static class Horse implements Animal {

		@Override
		public String makeSound() {
			return "Neigh";
		}

	}

	public static class Pig implements Animal {

		@Override
		public String makeSound() {
			return "Oink";
		}

	}

	public static class Dog implements Animal {

		@Override
		public String makeSound() {
			return "Woof";
		}

	}

	public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
		List<Class<? extends Animal>> classArray = new ArrayList<>();

		classArray.add(Horse.class);
		classArray.add(Pig.class);
		classArray.add(Dog.class);

		for (final Class<? extends Animal> nextClass : classArray) {
			final Constructor<? extends Animal> constructor = nextClass.getConstructor();
			final Animal instance = constructor.newInstance();

			System.out.println(instance.makeSound());
		}
	}

}
