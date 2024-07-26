package com.magalu.notification.core.util;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

class StringUtilTest {
    @Test
    void onlyNumbersRemovesNonNumericCharacters() {
        assertEquals("1234567890", StringUtil.onlyNumbers("1a2b3c4d5e6f7g8h9i0j"));
        assertEquals("", StringUtil.onlyNumbers("qwerty"));
        assertEquals("", StringUtil.onlyNumbers(""));
        assertEquals("", StringUtil.onlyNumbers(null));
    }

    @Test
    void firstCharacterToLowerConvertsFirstCharacterToLowerCase() {
        assertEquals("helloWorld", StringUtil.firstCharacterToLower("HelloWorld"));
    }

    @Test
    void isNumberReturnsTrueForNumericString() {
        assertTrue(StringUtil.isNumber("1234567890"));
    }

    @Test
    void isNumberReturnsFalseForNonNumericString() {
        assertFalse(StringUtil.isNumber("1a2b3c4d5e6f7g8h9i0j"));
    }

    @Test
    void isNumberReturnsFalseForEmptyString() {
        assertFalse(StringUtil.isNumber(""));
        assertFalse(StringUtil.isNumber(null));
    }

    @Test
    void isNullOrEmptyReturnsTrueForSomeNull() {
        assertTrue(StringUtil.isNullOrEmpty("a", "b", "c", null, "e"));
        assertTrue(StringUtil.isNullOrEmpty("a", "b", "c", "", "e"));
    }

    @Test
    void isNullOrEmptyReturnsTrueForNoOneNull() {
        assertFalse(StringUtil.isNullOrEmpty("a", "b", "c", "d", "e"));
    }

    @Test
    void isNullOrEmptyReturnsTrueForNull() {
        String s = null;
        assertTrue(StringUtil.isNullOrEmpty(s));
    }

    @Test
    void isNullOrEmptyReturnsTrueForEmptyString() {
        assertTrue(StringUtil.isNullOrEmpty(""));
    }

    @Test
    void isNullOrEmptyReturnsFalseForNonEmptyString() {
        assertFalse(StringUtil.isNullOrEmpty("Hello World"));
    }

    @Test
    void equalsIgnoreCaseReturnsTrueForMatchingString() {
        assertTrue(StringUtil.equalsIgnoreCase("Hello", "hello", "HELLO"));
    }

    @Test
    void equalsIgnoreCaseReturnsFalseForNonMatchingString() {
        assertFalse(StringUtil.equalsIgnoreCase("Other", "World", "HELLO"));
    }

    @Test
    void randomString() {
        assertNotNull(StringUtil.randomString());
    }

    @Test
    void randomStringReturnsStringOfSpecifiedLength() {
        assertEquals(10, StringUtil.randomString(10).length());
    }

    @Test
    void fillStringReturnsStringOfSpecifiedLength() {
        assertEquals("aaaaa", StringUtil.fillString("a", 5));
    }

    @Test
     void joinJoinsStringsWithSeparator() {
        assertEquals("Hello,World", StringUtil.join(Arrays.asList("Hello", "World"), ","));
    }

    @Test
    void removerAcentosRemovesAccentsFromCharacters() {
        assertEquals("aeiou", StringUtil.removerAcentos("áéíóú"));
    }

    @Test
    void abbreviateAbbreviatesStringCorrectly() {
        assertEquals("Hello W. World", StringUtil.abbreviate("Hello Wonderful World"));
    }

    @Test
    void isStopWordReturnsTrueForStopWord() {
        assertTrue(StringUtil.isStopWord("de"));
    }

    @Test
    void isStopWordReturnsFalseForNonStopWord() {
        assertFalse(StringUtil.isStopWord("hello"));
    }

    @Test
    void containsReturnsTrueForContainedValue() {
        assertTrue(StringUtil.contains("hello", "hello", "world"));
    }

    @Test
    void containsReturnsFalseForNonContainedValue() {
        assertFalse(StringUtil.contains("hello", "world"));
    }

    @Test
    void containsReturnsFalseForNullValue() {
        assertFalse(StringUtil.contains("", "world"));
        assertFalse(StringUtil.contains(null, "world"));
    }
}
