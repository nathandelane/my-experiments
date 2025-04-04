package com.github.nathandelane.experiments.hashing;

import java.util.Arrays;

public class HashTable {

	private final int arraySize;

	private final boolean[] totems;

	private final String[] keys;

	private final String[] values;

	public HashTable(final int size) {
		this.arraySize = size;
		this.totems = new boolean[arraySize];
		Arrays.fill(totems, false);
		this.keys = new String[arraySize];
		this.values = new String[arraySize];
	}

	public static int hashString(final String value, final int arraySize) {
		final int PRIME_CONST = 31;

		long sum = 0;

		for (int charIndex = 0; charIndex < value.length(); charIndex++) {
			sum += (value.charAt(charIndex) * (int) Math.pow(PRIME_CONST, charIndex)) % arraySize;
		}

		return (int) sum % arraySize;
	}

	public boolean put(final String key, final String value) {
		final int hash = hashString(key, arraySize);

		boolean foundSpot = false;

		String keyThere = keys[hash];
		boolean isTotem = totems[hash];

		if (!isTotem && (keyThere == null || keyThere.equals(key))) {
			values[hash] = value;
			keys[hash] = key;
			foundSpot = true;
		}
		else {
			int hashAddend = 1;

			while (!foundSpot && ((hash + hashAddend) != hash)) {
				keyThere = keys[(hash + hashAddend)];
				isTotem = totems[(hash + hashAddend)];

				if (!isTotem && (keyThere == null || keyThere.equals(key))) {
					values[(hash + hashAddend)] = value;
					keys[(hash + hashAddend)] = key;
					foundSpot = true;
				}

				if (!foundSpot) {
					if (hash < (arraySize - 1)) {
						hashAddend++;
					} else {
						hashAddend = (arraySize * -1);
					}
				}
			}
		}

		return foundSpot;
	}

	public String get(final String key) {
		final int hash = hashString(key, arraySize);

		String value = null;

		if (!totems[hash] && keys[hash].equals(key)) {
			value = values[hash];
		}
		else {
			int hashAddend = 1;
			boolean keyFound = false;

			while (!keyFound && ((hash + hashAddend) != hash)) {
				if (!totems[(hash + hashAddend)] && keys[(hash + hashAddend)].equals(key)) {
					keyFound = true;
				}

				if (!keyFound) {
					if (hash < (arraySize - 1)) {
						hashAddend++;
					} else {
						hashAddend = (arraySize * -1);
					}
				}
			}

			if (keyFound) {
				value = values[(hash + hashAddend)];
			}
		}

		return value;
	}

	public boolean remove(final String key) {
		final int hash = hashString(key, arraySize);

		boolean keyFound = false;

		if (!totems[hash] && keys[hash].equals(key)) {
			totems[hash] = true;
			keyFound = true;
		}
		else {
			int hashAddend = 1;

			while (!keyFound && ((hash + hashAddend) != hash)) {
				if (!totems[(hash + hashAddend)] && keys[(hash + hashAddend)].equals(key)) {
					totems[(hash + hashAddend)] = true;
					keyFound = true;
				}

				if (!keyFound) {
					if (hash < (arraySize - 1)) {
						hashAddend++;
					} else {
						hashAddend = (arraySize * -1);
					}
				}
			}
		}

		return keyFound;
	}

}
