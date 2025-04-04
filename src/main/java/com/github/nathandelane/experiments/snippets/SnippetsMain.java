package com.github.nathandelane.experiments.snippets;

import javax.swing.*;
import java.awt.*;

public class SnippetsMain {

	private final JFrame mainFrame;

	private JTextArea entryField;

	private SnippetsMain() {
		mainFrame = new JFrame("Clipboard");

		setupUI();
	}

	private void setupUI() {
		mainFrame.setSize(640, 800);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		final JPanel mainPanel = new JPanel();
		mainPanel.setSize(mainFrame.getSize());

		final LayoutManager layout = new BoxLayout(mainPanel, BoxLayout.Y_AXIS);
		mainPanel.setLayout(layout);

		final DefaultListModel<String> snippets = new DefaultListModel<>();

		final JList<String> listOfEntries = new JList<>(snippets);
		listOfEntries.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listOfEntries.setBackground(Color.LIGHT_GRAY);
		listOfEntries.addListSelectionListener(new SnippetSelectionListener(listOfEntries));
		listOfEntries.addMouseListener(new SnippetsMouseListener(snippets, listOfEntries));
		listOfEntries.setCellRenderer(getJListCellRenderer());

		final JScrollPane scrollPane = new JScrollPane(listOfEntries);
		mainPanel.add(scrollPane, BorderLayout.CENTER);

		entryField = new JTextArea(5, 50);
		entryField.setMaximumSize(entryField.getPreferredSize());
		entryField.setMinimumSize(entryField.getPreferredSize());
		entryField.addKeyListener(new SnippetEntryHandler(snippets, entryField));
		entryField.addFocusListener(new EntryFieldFocusListener(entryField));
		mainPanel.add(entryField, BorderLayout.NORTH);

		mainFrame.setContentPane(mainPanel);
	}

	private ListCellRenderer<? super String> getJListCellRenderer() {
		return new DefaultListCellRenderer(){

			@Override
			public Component getListCellRendererComponent(
				final JList<?> list,
				final Object value,
				final int index,
				final boolean isSelected,
				final boolean cellHasFocus
			) {
				final JLabel listCellRendererComponent = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
				listCellRendererComponent.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0,Color.BLACK));

				if (isSelected) {
					listCellRendererComponent.setBackground(Color.BLACK);
				}

				return listCellRendererComponent;
			}

		};
	}

	private void run() {
		mainFrame.pack();
		mainFrame.setVisible(true);
		entryField.grabFocus();
	}

	public static void main(final String[] args) {
		final SnippetsMain snippetsMain = new SnippetsMain();
		snippetsMain.run();
	}

}
