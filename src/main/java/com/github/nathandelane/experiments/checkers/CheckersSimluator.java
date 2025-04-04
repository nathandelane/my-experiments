package com.github.nathandelane.experiments.checkers;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/**
 * Draughts:
 * Symbol
 * HTML Code
 * HTML Entity
 * CSS Code
 * Hex Code
 * Unicode
 * Description
 * ⛀
 * &#9920;
 * \26C0
 * &#x26C0;
 * U+026C0
 * white draughts man
 * ⛁
 * &#9921;
 * \26C1
 * &#x26C1;
 * U+026C1
 * white draughts king
 * ⛂
 * &#9922;
 * \26C2
 * &#x26C2;
 * U+026C2
 * black draughts man
 * ⛃
 * &#9923;
 * \26C3
 * &#x26C3;
 * U+026C3
 * black draughts king
 */
import static com.github.nathandelane.experiments.checkers.ConsoleColors.*;

public final class CheckersSimluator {

	static final byte L = 0x01;

	static final char LIGHT_PIECE_CHAR = '⛀';

	static final byte D = 0x02;

	static final char DARK_PIECE_CHAR = '⛂';

	static final byte E = 0x00;

	static final int BOARD_DIM = 8;

	static final int PIECES_EACH = 12;

	private final Charset utf8;

	private final Charset def;

	final byte[] checkerBoard;

	final int darkPiecesLost;

	final int lightPiecesLost;

	CheckersSimluator() {
		this.checkerBoard = new byte[64];
		this.darkPiecesLost = 0;
		this.lightPiecesLost = 0;

		utf8 = Charset.forName("UTF-8");
		def = Charset.defaultCharset();
	}

	public void run() throws UnsupportedEncodingException {
		initializeBoard();

		final String lightTurnMessage = "It's light's turn. Please choose a piece.";
		final String darkTurnMessage = "It's dark's turn. Please choose a piece.";
		final String chooseSpaceToMoveToMessage = "Please choose a space to move to.";

		while (!(darkPiecesLost == PIECES_EACH || lightPiecesLost == PIECES_EACH)) {
			final String boardRender = renderBoard();
			final PrintStream printStream = new PrintStream(System.out, true, utf8.name());
			printStream.println(boardRender);

			System.exit(0);
		}
	}

	String renderBoard() {
		final StringBuilder boardRenderBuilder = new StringBuilder();

		boardRenderBuilder
			.append(RED_BACKGROUND).append("   ")
			.append(WHITE).append(BLACK_BACKGROUND).append(" A ")
			.append(BLACK).append(RED_BACKGROUND).append(" B ")
			.append(WHITE).append(BLACK_BACKGROUND).append(" C ")
			.append(BLACK).append(RED_BACKGROUND).append(" D ")
			.append(WHITE).append(BLACK_BACKGROUND).append(" E ")
			.append(BLACK).append(RED_BACKGROUND).append(" F ")
			.append(WHITE).append(BLACK_BACKGROUND).append(" G ")
			.append(BLACK).append(RED_BACKGROUND).append(" H ")
			.append(RESET)
			.append(System.lineSeparator());

		for (int rowIndex = (BOARD_DIM - 1); rowIndex >= 0; rowIndex--) {
			if ((rowIndex % 2) == 1) {
				boardRenderBuilder.append(WHITE).append(BLACK_BACKGROUND).append(" ").append(rowIndex).append(" ");
			}
			else {
				boardRenderBuilder.append(BLACK).append(RED_BACKGROUND).append(" ").append(rowIndex).append(" ");
			}

			for (int columnIndex = 0; columnIndex < BOARD_DIM; columnIndex++) {
				final byte val = checkerBoard[(rowIndex * BOARD_DIM) + columnIndex];

				if ((rowIndex % 2) == 1) {
					if ((columnIndex % 2) == 1) {
						boardRenderBuilder.append(WHITE).append(BLACK_BACKGROUND);
					}
					else {
						boardRenderBuilder.append(BLACK).append(RED_BACKGROUND);
					}
				}
				else {
					if ((columnIndex % 2) == 1) {
						boardRenderBuilder.append(BLACK).append(RED_BACKGROUND);
					}
					else {
						boardRenderBuilder.append(WHITE).append(BLACK_BACKGROUND);
					}
				}

				if (val == L) boardRenderBuilder.append(" ").append(LIGHT_PIECE_CHAR).append(" ");
				if (val == D) boardRenderBuilder.append(" ").append(DARK_PIECE_CHAR).append(" ");
				if (val == E) boardRenderBuilder.append(String.format("%3s", ((rowIndex * 8) + columnIndex)));
			}

			boardRenderBuilder.append(RESET).append(System.lineSeparator());
		}

		return boardRenderBuilder
			.append(System.lineSeparator())
			.append(System.lineSeparator())
			.toString();
	}

	private void initializeBoard() {
		final byte[] initialBoard = new byte[] {
			L, E, L, E, L, E, L, E,
			E, L, E, L, E, L, E, L,
			L, E, L, E, L, E, L, E,
			E, E, E, E, E, E, E, E,
			E, E, E, E, E, E, E, E,
			E, D, E, D, E, D, E, D,
			D, E, D, E, D, E, D, E,
			E, D, E, D, E, D, E, D
		};

		assert initialBoard.length == (8 * 8) : "Board size is not 64 bytes as expected";

//		for (int boardIndex = 0; boardIndex < )
	}

	public static void main(final String[] args) {
		final CheckersSimluator checkersSimluator = new CheckersSimluator();
		try {
			checkersSimluator.run();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

}
