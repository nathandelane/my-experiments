package com.github.nathandelane.experiments.snippets;

import javax.swing.*;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SnippetsMouseListener extends MouseAdapter {

	final DefaultListModel<String> snippets;

	final JList<String> listOfEntries;

	public SnippetsMouseListener(final DefaultListModel<String> snippets, final JList<String> listOfEntries) {
		this.snippets = snippets;
		this.listOfEntries = listOfEntries;
	}

	@Override
	public void mouseClicked(final MouseEvent me) {
		if ((me.getModifiersEx() & InputEvent.META_DOWN_MASK) != 0 && listOfEntries.getSelectedIndex() > -1) {
			final int selectedIndex = listOfEntries.getSelectedIndex();

			snippets.remove(selectedIndex);
		}
	}

}
