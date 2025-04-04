package com.github.nathandelane.experiments.clipboard;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public final class TextFieldEntryHandler extends KeyAdapter {

	private final DefaultListModel<String> clipboardEntries;

	private final JTextField entryField;

	public TextFieldEntryHandler(final DefaultListModel clipboardEntries, final JTextField entryField) {
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
			clipboardEntries.addElement(entry);
		}

		super.keyReleased(e);
	}

}
