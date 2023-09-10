package org.example.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StreetNameTest {

    @Test
    void cleanTest() {
        var street1 = new StreetName("Rue des Jojos");
        assertEquals(List.of("Jojos"), street1.getCleanedNames());
    }

    @Test
    void fuzzyNextTest() {
        var street1 = new StreetName("Rue des Jojos Heureux");
        var street2 = new StreetName("Rue des Ignares Sympas");

        assertTrue(street1.isBeforeInAlphabetFuzzy(street2));
    }

    @Test
    void cleanTest1() {
        var street = new StreetName("Place de l'Abbé Georges Hénocque");
        assertEquals(List.of("Abbé", "Georges", "Hénocque"), street.getCleanedNames());
    }

    @Test
    void withDash() {
        var street = new StreetName("Allée Blaise-Cendrars");
        assertEquals(List.of("Allée", "Blaise", "Cendrars"), street.getCleanedNames());
    }

    @Test
    void nextInAlphabetWithLetterWorks() {
        var street = new StreetName("Rue Karim Seddiki");
        var other = new StreetName("Allée Louis Papa");
        assertTrue(street.isBeforeInAlphabet(other, 'k'));
    }

    @Test
    void nextInAlphabetWithLetterFilters() {
        var street = new StreetName("Rue Karim Seddiki");
        var other = new StreetName("Allée Louis Papa");
        assertFalse(street.isBeforeInAlphabet(other, 's'));
    }

    @Test
    void consecutiveLettersInName() {
        var street = new StreetName("Rue Aloha Bonjour Coucou");
        assertEquals(List.of('b', 'c'), street.nextConsecutiveLettersInName('a'));
    }

}