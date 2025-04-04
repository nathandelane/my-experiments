package com.github.nathandelane.experiments.snippets;

import javax.swing.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public final class EntryFieldFocusListener implements FocusListener {

	private final JTextArea entryField;

	public EntryFieldFocusListener(final JTextArea entryField) {
		this.entryField = entryField;
	}

	@Override
	public void focusGained(final FocusEvent e) {
		if (entryField.getText() != null && !entryField.getText().equals("")) {
			entryField.setText("");
		}
	}

	@Override
	public void focusLost(final FocusEvent e) {
		entryField.requestFocus();
	}

}
