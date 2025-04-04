package com.github.nathandelane.experiments.clipboard;

import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.Transferable;

public final class OwnClipboard implements ClipboardOwner {

	private final Clipboard systemClipboard;

	public OwnClipboard(final Clipboard systemClipboard) {
		this.systemClipboard = systemClipboard;
	}

	@Override
	public void lostOwnership(final Clipboard clipboard, final Transferable contents) {
		try {
			Thread.sleep(20);
		} catch(Exception e) {
			System.out.println("Exception: " + e);
		}

		final Transferable clipboardContents = systemClipboard.getContents(this);

		regainOwnership(clipboardContents);
	}

	private void regainOwnership(final Transferable t) {
		systemClipboard.setContents(t, this);
	}

}
