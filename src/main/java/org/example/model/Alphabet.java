package org.example.model;

import java.util.List;

public class Alphabet {

    public static List<Character> letters = List.of('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n','o', 'p',
            'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z');

    public static boolean areNextInAlphabet(String first, String second) {
        return letters.indexOf(first.toLowerCase().charAt(0)) == letters.indexOf(second.toLowerCase().charAt(0)) - 1;
    }
}
