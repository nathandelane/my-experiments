package com.github.nathandelane.experiments.game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public final class UserInput {

    private UserInput() { }

    public static String promptAndReadInput() {
        final StringBuilder sb = new StringBuilder();

        sb.setLength(0);

        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            final String entry = reader.readLine();

            sb.append(entry);
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }

        return sb.toString();
    }

}
