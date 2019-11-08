package com.github.nathandelane.experiments.linkedlists;

public final class ListNode<T> {
	
	final T value;
	
	ListNode<T> next;
	
	public ListNode(final T value) {
		this.value = value;
		this.next = null;
	}
	
	public void setChild(final ListNode<T> next) {
		this.next = next;
	}
	
	public T getValue() {
		return value;
	}
	
	public ListNode<T> getNext() {
		return next;
	}
	
	@Override
	public boolean equals(final Object other) {
		return (
			other != null &&
			(other instanceof ListNode<?>) &&
			this.value != null &&
			((ListNode<?>) other).value != null &&
			this.value.equals(((ListNode<?>) other).value)
		);
	}
	
}
