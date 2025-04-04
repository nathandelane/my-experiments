package com.github.nathandelane.experiments.clipboard;

import javax.swing.*;
import java.awt.datatransfer.FlavorEvent;
import java.awt.datatransfer.FlavorListener;

public final class SystemClipboardListener implements FlavorListener {

	private final DefaultListModel<String> clipboardEntries;

	public SystemClipboardListener(final DefaultListModel<String> clipboardEntries) {
		this.clipboardEntries = clipboardEntries;
	}

	@Override
	public void flavorsChanged(final FlavorEvent e) {
		final String value = e.toString();
		clipboardEntries.addElement(value);
	}

}
