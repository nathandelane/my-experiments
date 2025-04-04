package com.github.nathandelane.experiments.boxes;

import java.util.Arrays;

public final class Node {

	public final Box box;

	public Node[][] children;

	public Node(final Box box) {
		this.box = box;
		this.children = new Node[][] {
			{ null /*UPPER_LEFT:0,0*/, null /*UPPER_RIGHT:0,1*/ },
			{ null /*LOWER_LEFT:1,0*/, null /*LOWER_RIGHT:1,0*/ }
		};
	}

	public Node(final Box box, final Node[][] children) {
		this.box = box;
		this.children = Arrays.copyOf(children, 2);
	}

}
