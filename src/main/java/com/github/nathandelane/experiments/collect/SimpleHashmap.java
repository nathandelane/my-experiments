package com.github.nathandelane.experiments.collect;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SimpleHashmap<TKey, TValue> {

	private int numberOfEntries;

	private List<TKey> keys;

	private List<List<TValue>> values;

	public SimpleHashmap() {
		this(10);
	}

	public SimpleHashmap(final int numberOfEntries) {
		this.numberOfEntries = numberOfEntries;
		this.keys = new ArrayList<>(this.numberOfEntries);
		this.values = new ArrayList<>(this.numberOfEntries);
	}

	public boolean put(final TKey key, final TValue value) {
		final int keyIndex = hashKey(key);

		if (keys.get(keyIndex) == null) {
			keys.set(keyIndex, key);
			values.set(keyIndex, new LinkedList<>());
		}

		List<TValue> valueList = values.get(keyIndex);
		valueList.add(value);

		return true;
	}

	private int hashKey(final Object key) {
		return (key.hashCode() % numberOfEntries);
	}

}
