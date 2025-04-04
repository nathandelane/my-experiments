package com.github.nathandelane.experiments.game;

public final class Adventure {

    private boolean shouldExit;

    private Adventure() {
        shouldExit = false;
    }

    private void processInput(final String userInput) {

    }

    private void run() {
        do {
            processInput(UserInput.promptAndReadInput());
        } while (!shouldExit);
    }

    public static final void main(final String[] args) {
        final Adventure adventure = new Adventure();
        adventure.run();
    }

}
