package com.github.nathandelane.experiments.snippets;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public final class SnippetEntryHandler extends KeyAdapter {

	private final DefaultListModel<String> clipboardEntries;

	private final JTextArea entryField;

	public SnippetEntryHandler(final DefaultListModel<String> clipboardEntries, final JTextArea entryField) {
		this.clipboardEntries = clipboardEntries;
		this.entryField = entryField;
	}

	@Override
	public void keyTyped(final KeyEvent e) {
		super.keyTyped(e);
	}

	@Override
	public void keyPressed(final KeyEvent e) {
		super.keyPressed(e);
	}

	@Override
	public void keyReleased(final KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			final String entry = entryField.getText();

			entryField.setText("");

			if (!clipboardEntries.contains(entry)) {
				clipboardEntries.addElement(entry);
			}
			else {
				clipboardEntries.removeElement(entry);
				clipboardEntries.add(0, entry);
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			entryField.setText("");
		}

		super.keyReleased(e);
	}

}
