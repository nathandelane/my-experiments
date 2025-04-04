package com.github.nathandelane.experiments.snippets;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public final class SnippetSelectionListener implements ListSelectionListener {

	private final JList<String> listOfEntries;

	public SnippetSelectionListener(final JList<String> listOfEntries) {
		this.listOfEntries = listOfEntries;
	}

	@Override
	public void valueChanged(final ListSelectionEvent e) {
		final ListModel<String> listModel = listOfEntries.getModel();

		if (!e.getValueIsAdjusting()) {
			final String value = listOfEntries.getSelectedValue();
			final StringSelection selectedValue = new StringSelection(value);

			final Clipboard systemClipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			systemClipboard.setContents(selectedValue, null);
		}
	}

}
