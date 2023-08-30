package org.example.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class StreetName {

    private static final Set<String> useless = Set.of("Rue", "Avenue", "de", "du", "des", "Boulevard", "d'", "la",
            "aux", "au", "Place", "et", "en", "l'", "le", "les", "La",
            "Pont",
            //"Allée",
            "Promenade",
            "Accès",
            //"Cité"
            "Impasse");

    private final String fullName;

    public StreetName(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public List<String> getCleanedNames() {
        return Arrays.stream(this.fullName.split("[ -]+"))
                .filter(s -> !useless.contains(s))
                .map(s -> s.replace("l'", "").replace("d'", ""))
                .collect(Collectors.toList());
    }

    /**
     * returns true if the first letter of any part of the name
     * is right before the first letter of any part of the other name.
     *
     * Example: will return true for "Jean Luz" "Madame Bovary" since L is right before M
     * @param other
     * @return
     */
    public boolean isBeforeInAlphabetFuzzy(StreetName other) {
        return getCleanedNames().stream().anyMatch(s ->
             areNextInAlphabet(other, s)
        );
    }

    private static boolean areNextInAlphabet(StreetName other, String s) {
        for(String s1: other.getCleanedNames()) {
            if (Alphabet.areNextInAlphabet(s, s1)) {
                return true;
            }
        }
        return false;
    }

    public boolean isBeforeInAlphabet(StreetName other, char letter) {
        return getCleanedNames().stream()
                .filter(s -> s.toLowerCase().startsWith(String.valueOf(letter)))
                .anyMatch(s -> areNextInAlphabet(other, s));
    }

    /**
     * Returns list of next consecutive letters present in the name for a given letter
     * i.e. Rue Alexis Beauregard Canard will return 'b','c' if given letter is 'a'
     *
     * **/

    public List<Character> nextConsecutiveLettersInName(char startLetter) {
        boolean stop = true;
        List<Character> consecutiveLettersInName = new ArrayList<>();
        char currentLetter = startLetter;
        while(stop) {
            final char nextLetter = Alphabet.letters.get(Math.floorMod(Alphabet.letters.indexOf(currentLetter) +1, 26));
            if (getCleanedNames().stream().map(String::toLowerCase).anyMatch(s -> s.startsWith(String.valueOf(nextLetter)))) {
                consecutiveLettersInName.add(nextLetter);
                currentLetter = nextLetter;
            } else {
                stop = false;
            }
        }

        return consecutiveLettersInName;
    }
}
