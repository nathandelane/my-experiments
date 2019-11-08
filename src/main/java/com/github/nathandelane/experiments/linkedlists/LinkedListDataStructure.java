package com.github.nathandelane.experiments.linkedlists;

public final class LinkedListDataStructure<T> {
	
	ListNode<T> root;
	
	public LinkedListDataStructure(final Iterable<T> values) {
		if (values != null) {
			addAll(values);
		}
	}
	
	public void push(final T value) {
		if (root == null) {
			root = new ListNode<T>(value);
		}
		else {
			ListNode<T> previous = root;
			
			while (previous.getNext() != null) {
				previous = previous.getNext();
			}
			
			final ListNode<T> newNode = new ListNode<T>(value);
			
			previous.setChild(newNode);
		}
	}
	
	public void addAll(final Iterable<T> values) {
		for (final T nextValue : values) {
			push(nextValue);
		}
	}
	
	public int size() {
		int size = 0;
		
		ListNode<T> previous = root;
		
		if (root != null) {
			size = 1;
			
			while (previous.getNext() != null) {
				previous = previous.getNext();
				size++;
			}
		}
		
		return size;
	}
	
	public T get(final int index) {
		int indexCounter = 0;
		
		if (root == null) {
			throw new IndexOutOfBoundsException(String.format("Index %s is out of bounds!", index));
		}
		else {
			ListNode<T> currentNode = root;
			
			while (indexCounter < index && currentNode.getNext() != null) {
				currentNode = currentNode.getNext();
				indexCounter++;
			}
			
			if (indexCounter == index) {
				return currentNode.getValue();
			}
			else {
				throw new IndexOutOfBoundsException(String.format("Index %s is out of bounds!", index));
			}
		}
	}
	
	public void remove(final int index) {
		int indexCounter = 0;
		
		if (root == null) {
			throw new IndexOutOfBoundsException(String.format("Index %s is out of bounds!", index));
		}
		else {
			ListNode<T> lastNode = null;
			ListNode<T> currentNode = root;
			
			while (indexCounter < index && currentNode.getNext() != null) {
				lastNode = currentNode;
				currentNode = lastNode.getNext();
				indexCounter++;
			}
			
			if (indexCounter == index) {
				if (currentNode.getNext() != null) {
					lastNode.setChild(currentNode.getNext());
				}
				else {
					lastNode.setChild(null);
				}
			}
			else {
				throw new IndexOutOfBoundsException(String.format("Index %s is out of bounds!", index));
			}
		}
	}
	
	public void set(final int index, final T value) {
		int indexCounter = 0;
		
		if (root == null) {
			throw new IndexOutOfBoundsException(String.format("Index %s is out of bounds!", index));
		}
		else {
			ListNode<T> lastNode = null;
			ListNode<T> currentNode = root;
			
			while (indexCounter < index && currentNode.getNext() != null) {
				lastNode = currentNode;
				currentNode = lastNode.getNext();
				indexCounter++;
			}
			
			if (indexCounter == index && currentNode != null) {
				final ListNode<T> newCurrentNode = new ListNode<T>(value);
				newCurrentNode.setChild(currentNode.getNext());
				
				lastNode.setChild(newCurrentNode);
			}
			else {
				throw new IndexOutOfBoundsException(String.format("Index %s is out of bounds!", index));
			}
		}
	}
	
	public LinkedListDataStructure<T> reverse() {
		final LinkedListDataStructure<T> newList = new LinkedListDataStructure<>(null);
		
		if(root == null) {
			return newList;
		}
		else {
			if (root != null) {
				reverseNextNode(root, newList);
			}
		}
		
		return newList;
	}
	
	private void reverseNextNode(final ListNode<T> nextNode, final LinkedListDataStructure<T> newList) {
		if (nextNode.getNext() != null) {
			reverseNextNode(nextNode.getNext(), newList);
		}
		
		newList.push(nextNode.value);
	}
	
	@Override
	public boolean equals(final Object other) {
		if (other != null && (other instanceof LinkedListDataStructure)) {
			try {
				final LinkedListDataStructure<?> otherLinkedList = (LinkedListDataStructure<?>) other;
			
				if (this.size() == otherLinkedList.size()) {
					ListNode<?> thisNode = root;
					ListNode<?> otherNode = otherLinkedList.root;
					
					while ((thisNode != null && otherNode != null) && thisNode.value.equals(otherNode.value)) {
						thisNode = thisNode.next;
						otherNode = otherNode.next;
					}
					
					if (thisNode == null && otherNode == null) {
						return true;
					}
				}
			}
			catch (final Exception e) {
				// No-op
			}
		}
	
		return false;
	}
	
}