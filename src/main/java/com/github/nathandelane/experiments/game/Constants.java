package com.github.nathandelane.experiments.game;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public final class Constants {

    private Constants() { }

    public static final String[] VERBS = new String[] {
        "clean",
        "climb",
        "close",
        "dig",
        "drink",
        "drop",
        "east",
        "eat",
        "empty",
        "examine",
        "exit",
        "feel",
        "fill",
        "go",
        "hit",
        "ignite",
        "insert",
        "lift",
        "lock",
        "move",
        "north",
        "northeast",
        "northwest",
        "open",
        "press",
        "pull",
        "push",
        "put",
        "read",
        "remove",
        "sit",
        "sleep",
        "smell",
        "south",
        "southeast",
        "southwest",
        "stand",
        "swim",
        "switch",
        "take",
        "talk",
        "throw",
        "tie",
        "turn",
        "unlock",
        "untie",
        "waft",
        "wait",
        "wake",
        "wear",
        "west",
        "write"
    };

    public static final Map<String, Set<String>> VERB_SYNONYMS = new HashMap<>();
    static {
        VERB_SYNONYMS.put("clean", Set.of("rub"));
        VERB_SYNONYMS.put("close", Set.of("shut"));
        VERB_SYNONYMS.put("drink", Set.of("consume"));
        VERB_SYNONYMS.put("east", Set.of("e"));
        VERB_SYNONYMS.put("eat", Set.of("consume"));
        VERB_SYNONYMS.put("north", Set.of("n"));
        VERB_SYNONYMS.put("exist", Set.of("q", "quit"));
        VERB_SYNONYMS.put("south", Set.of("s"));
        VERB_SYNONYMS.put("west", Set.of("w"));
    }

}
