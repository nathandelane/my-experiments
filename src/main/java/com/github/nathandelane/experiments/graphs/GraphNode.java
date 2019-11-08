package com.github.nathandelane.experiments.graphs;

import java.util.Vector;

public class GraphNode<T> {

	final T value;
	
	final Vector<GraphNode<T>> outgoingNodes;
	
	public GraphNode(final T value) {
		this.value = value;
		this.outgoingNodes = new Vector<>();
	}
	
	public void addOutgoingNode(final GraphNode<T> node) {
		if (node != null && !outgoingNodes.contains(node)) {
			outgoingNodes.add(node);
		}
	}
	
	public void removeOutgoingNode(final GraphNode<T> node) {
		if (node != null) {
			outgoingNodes.remove(node);
		}
	}
	
	public T getValue() {
		return value;
	}
	
	public Iterable<GraphNode<T>> getOutgoingNodes() {
		return outgoingNodes;
	}
	
	@Override
	public boolean equals(final Object other) {
		return (
			other != null &&
			(other instanceof GraphNode<?>) &&
			this.value != null &&
			((GraphNode<?>) other).value != null &&
			this.value.equals(((GraphNode<?>) other).value)
		);
	}
	
}
