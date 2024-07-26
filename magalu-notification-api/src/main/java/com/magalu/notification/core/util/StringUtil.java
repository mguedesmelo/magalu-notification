package com.magalu.notification.core.util;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public final class StringUtil {
    private StringUtil() {
        // Empty
    }

    public static String onlyNumbers(String str) {
        if (!StringUtil.isEmpty(str)) {
            return str.replaceAll("[^0123456789]", "");
        } else {
            return "";
        }
    }

    public static String firstCharacterToLower(String s) {
        return s.substring(0, 1).toLowerCase() + s.substring(1);
    }

    public static boolean isNumber(String n) {
        if (StringUtil.isEmpty(n)) {
            return false;
        }
        return n.trim().matches("\\d+");
    }

    public static boolean isNotEmpty(String text) {
        return !StringUtil.isEmpty(text);
    }

    /**
     * Verify if the provided text is null or empty.
     *
     * @param text Text to be verified.
     * @return boolean Returns <code>true</code> if the text is null or empty,
     *         <code>false</code> otherwise.
     */
    public static boolean isEmpty(String text) {
        return (text == null || text.trim().length() == 0 || text.trim().isEmpty());
    }

    /**
     * Verify if some of the texts is null or empty.
     *
     * @param text Text to be verified.
     * @return boolean Returns <code>true</code> if some of the texts is null or empty,
     *         <code>false</code> otherwise.
     */
    public static boolean isEmpty(String... texts) {
        for (String text : texts) {
            if (StringUtil.isEmpty(text)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Verify if the provided text is equals to any position of the array.
     *
     * @param text  Text to be verified.
     * @param texts Array containing the texts to verify.
     * @return <code>true</code> if the text argument is equals to any text of the
     *         array, <code>false</code> otherwise.
     */
    public static boolean equalsIgnoreCase(String text, String... texts) {
        if (StringUtil.isEmpty(text) || texts == null || texts.length == 0) {
            return false;
        }
        for (String t : texts) {
            if (text.equalsIgnoreCase(t)) {
                return true;
            }
        }

        return false;
    }

    public static String randomString(int size) {
        String validChars = "abcdefghijklmnopqrstuvwyz0123456789";
        StringBuilder toReturn = new StringBuilder();
        String temp = UUID.randomUUID().toString();
        for (int i = 0; i < temp.length(); i++) {
            String c = temp.substring(i, i + 1);
            if (validChars.contains(c.toLowerCase())) {
                toReturn.append(c);
            }
            if (toReturn.length() >= size) {
                break;
            }
        }
        return toReturn.toString();
    }

    public static String randomString() {
        return UUID.randomUUID().toString();
    }

    public static String fillString(String s, int quantity) {
        StringBuilder toReturn = new StringBuilder();
        if (!isEmpty(s)) {
            for (int i = 0; i < quantity; i++) {
                toReturn.append(s);
            }
        }
        return toReturn.toString();
    }

    public static String join(Collection<String> collection, String sep) {
        StringBuilder toReturn = new StringBuilder();
        boolean first = true;
        for (String item : collection) {
            if (!first) {
                toReturn.append(sep);
            }
            toReturn.append(item);
            first = false;
        }

        return toReturn.toString();
    }

    public static String removerAcentos(String str) {
        return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    }

    public static String abbreviate(String s) {
        StringBuilder toReturn = new StringBuilder("");
        if (!StringUtil.isEmpty(s)) {
            String[] wordsArray = s.split(" ");
            if (wordsArray.length > 1) {
                List<String> words = new ArrayList<>(0);
                words.clear();
                words.addAll(List.of(wordsArray));
                String firstWord = words.get(0);
                words.remove(0);
                String lastWord = words.get(words.size() - 1);
                words.remove(words.size() - 1);

                toReturn.append(firstWord);

                for (String w : words) {
                    toReturn.append(" ");
                    if (w.length() <= 3) {
                        toReturn.append(w);
                    } else {
                        toReturn.append(w.substring(0, 1));
                        toReturn.append(".");
                    }
                }

                toReturn.append(" ");
                toReturn.append(lastWord);

            }
        }
        return toReturn.toString();
    }

    public static boolean isStopWord(String s) {
        final String stopWordsList = "dadedidodu";
        return stopWordsList.contains(s);
    }

    public static boolean contains(String s, String... values) {
        if (StringUtil.isEmpty(s)) {
            return false;
        }
        for (String v : values) {
            if (s.equalsIgnoreCase(v)) {
                return true;
            }
        }
        return false;
    }

    public static boolean doesNotContains(String s, String... values) {
        return !StringUtil.contains(s, values);
    }
}
