package com.github.nathandelane.experiments.linkedlists;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;


public class LinkedListDataStructureIterator<T> implements Iterator<T> {
	
	private final LinkedListDataStructure<T> linkedList;
	
	private final AtomicInteger currentIndex;
	
	public LinkedListDataStructureIterator(final LinkedListDataStructure<T> linkedList) {
		this.linkedList = linkedList;
		this.currentIndex = new AtomicInteger(0);
	}

	@Override
	public boolean hasNext() {
		return currentIndex.get() < (linkedList.size() - 2);
	}

	@Override
	public T next() {
		if (hasNext()) {
			return linkedList.get(currentIndex.getAndIncrement());
		}
		else {
			throw new IndexOutOfBoundsException();
		}
	}

}
