package com.github.nathandelane.experiments.clipboard;

import javax.swing.*;
import java.awt.*;

public final class ClipboardMain {

	private static final int MAX_ENTRIES = 50;

	private JFrame mainFrame;

	private final DefaultListModel clipboardEntries;

	private JList<String> listOfEntries;

	private JTextField entryField;

	private ClipboardMain() {
		mainFrame = new JFrame("Clipboard");
		clipboardEntries = new DefaultListModel();

		setupUI();
	}

	private void setupUI() {
		mainFrame.setSize(640, 800);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		final JPanel mainPanel = new JPanel();
		final LayoutManager layout = new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS);
		mainPanel.setLayout(layout);

		listOfEntries = new JList<>(clipboardEntries);
		listOfEntries.addListSelectionListener(new EntrySelectionListener(listOfEntries));

		final JScrollPane scrollPane = new JScrollPane(listOfEntries);
		mainPanel.add(scrollPane, BorderLayout.CENTER);

		entryField = new JTextField("* Enter new item here *");
		entryField.addKeyListener(new TextFieldEntryHandler(clipboardEntries, entryField));
		mainPanel.add(entryField, BorderLayout.NORTH);

		Toolkit.getDefaultToolkit().getSystemClipboard().addFlavorListener(new SystemClipboardListener(clipboardEntries));

		mainFrame.setContentPane(mainPanel);
	}

	private void run() {
		mainFrame.pack();
		mainFrame.setVisible(true);
		entryField.grabFocus();
	}

	public static void main(final String[] args) {
		final ClipboardMain clipboardMain = new ClipboardMain();
		clipboardMain.run();
	}

}
