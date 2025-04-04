package com.github.nathandelane.experiments.compress;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public final class Base64EncodeDirectory {

	final File startPathDirectory;
	final File copyPathDirectory;

	Base64EncodeDirectory(final File startPathDirectory, final File copyPathDirectory) {
		this.startPathDirectory = startPathDirectory;
		this.copyPathDirectory = copyPathDirectory;

		if (!this.copyPathDirectory.exists()) {
			final boolean success = this.copyPathDirectory.mkdir();
		}
	}

	public void encode() {
		System.out.format("Encoding all files under %s...%n", startPathDirectory.getAbsolutePath());

		encodeFilesUnderDirectory(startPathDirectory, copyPathDirectory);
	}

	private void encodeFilesUnderDirectory(final File sourceDirectory, final File copyDirectory) {
		System.out.format("In directory %s:%n%n", sourceDirectory.getAbsolutePath());

		final File[] files = sourceDirectory.listFiles();

		for (final File next : files) {
			if (!next.getName().startsWith(".") && !next.getName().equals("target")) {
				final String newPath = String.format("%s%s%s", copyDirectory.getAbsolutePath(), File.separatorChar, next.getName());

				if (next.isDirectory()) {
					final File newDir = new File(newPath);

					if (!newDir.exists()) {
						System.out.format("Creating directory in copy dir: %s%n", newPath);

						newDir.mkdir();
					}
					else {
						System.out.format("Directory already exists in copy dir: %s%n", newPath);
					}

					encodeFilesUnderDirectory(next, newDir);
				}
				else {
					System.out.format("Encoding file in copy dir: %s%n", newPath);

					final File newFile = new File(newPath);

					encodeFile(next, newFile);
				}
			}
		}
	}

	private void encodeFile(final File sourceFile, final File destinationFile) {
		System.out.format("Encoding %s to %s...%n", sourceFile.getAbsolutePath(), destinationFile.getAbsolutePath());

		final StringBuilder fileStringBuilder = new StringBuilder();

		try (
			final InputStream is = new FileInputStream(sourceFile);
			final InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
			final BufferedReader reader = new BufferedReader(streamReader)
		) {
			boolean isFirstLine = true;
			String line;

			while ((line = reader.readLine()) != null) {
				if (!isFirstLine) fileStringBuilder.append(System.lineSeparator());

				fileStringBuilder.append(line);

				if (isFirstLine) isFirstLine = false;
			}
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}

		final String fullFileAsString = fileStringBuilder.toString();
		final String encodedFullFileString = Base64.getEncoder().encodeToString(fullFileAsString.getBytes());

		try (final PrintWriter pw = new PrintWriter(destinationFile)) {
			pw.print(encodedFullFileString);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(final String[] args) {
		if (args.length < 2) {
			System.out.println("Usage: base64encode <directory-path> <copy-path>");
		}
		else {
			final String startPath = args[0];
			final File startPathDirectory = new File(startPath);

			if (!startPathDirectory.exists()) {
				System.out.format("Could not locate start path %s.%n", startPath);
				System.exit(1);
			}
			else if (!startPathDirectory.isDirectory()) {
				System.out.format("Start path %s is not a directory. Please provide a directory to start from.%n", startPath);
				System.exit(1);
			}

			final String copyPath = args[1];
			final File copyPathDirectory = new File(copyPath);

			final Base64EncodeDirectory app = new Base64EncodeDirectory(startPathDirectory, copyPathDirectory);
			app.encode();
		}
	}

}
