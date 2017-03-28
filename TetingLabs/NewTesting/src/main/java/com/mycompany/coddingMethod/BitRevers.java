package com.mycompany.coddingMethod;

/**
 * @author Roman
 */
public class BitRevers {

    // Откртый алфавит
    private final char[] openAlphabet = new char[32];
    // Закрытый алфавит
    private final char[] exitAlphabet = new char[32];

    public BitRevers() {
        openAlphabet[0] = '_';
        for (int i = 0; i < 9; i++) {
            openAlphabet[i + 1] = (char) ('а' + i);
        }
        for (int i = 10; i < 32; ++i) {
            openAlphabet[i] = (char) ('а' + i);
        }
    }

    public char[] getOpenAlphabet() {
        return openAlphabet;
    }

    public char[] getExitAlphabet() {
        return exitAlphabet;
    }

    /**
     * Вычислить закрытый алфавит по заданому ключу
     *
     * @param key ключ
     */
    public void calcExitAlphabet(String key) {
        int[] arrayKey = findePosition(key);
        for (int i = 0; i < 32; i++) {
            StringBuilder stI = new StringBuilder(Integer.toBinaryString(i));
            for (int j = stI.length(); j < 5; j++) {
                stI.insert(0, "0");
            }
            StringBuilder charAlph = new StringBuilder();
            char[] mass = stI.toString().toCharArray();
            for (int anArrayKey : arrayKey) {
                charAlph.append(mass[anArrayKey]);
            }
            int exitI = Integer.parseInt(charAlph.toString(), 2);
            exitAlphabet[i] = openAlphabet[exitI];
        }
    }

    /**
     * Найти в каких позициях произошла перестановка
     *
     * @param arrayKey
     * @return
     */
    private int[] findePosition(String arrayKey) {
        int[] result = new int[arrayKey.length()];
        result[0] = arrayKey.indexOf("1");
        result[1] = arrayKey.indexOf("2");
        result[2] = arrayKey.indexOf("3");
        result[3] = arrayKey.indexOf("4");
        result[4] = arrayKey.indexOf("5");
        return result;
    }

    /**
     * Закодировать текст
     *
     * @param originalText текст
     * @return закодированный текст
     */
    public String codText(String originalText) {
        originalText = originalText.replaceAll("\n", " ").toLowerCase();
        char[] text = originalText.toCharArray();
        char[] result = new char[text.length];
        for (int i = 0; i < text.length; i++) {
            int codeCurSymb = contains(openAlphabet, text[i]);
            if (codeCurSymb < 0) {
                return "Недопустимый символ. Шифрование не возможно";
            }
            result[i] = exitAlphabet[codeCurSymb];
        }
        return String.valueOf(result);
    }

    /**
     * Найти символ в алфавите
     *
     * @param chars алфавит
     * @param symbol символ
     * @return индекс найденного символа, или -1 в случае отсутствия
     */
    private int contains(char[] chars, char symbol) {
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == symbol) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Раскодировать текст
     *
     * @param originalText текст
     * @return раскодированный текст
     */
    public String decodText(String originalText) {
        originalText = originalText.replaceAll("\n", " ").toLowerCase();
        char[] text = originalText.toCharArray();
        char[] result = new char[text.length];
        for (int i = 0; i < text.length; i++) {
            int codeCurSymb = contains(exitAlphabet, text[i]);
            if (codeCurSymb < 0) {
                return "Недопустимый символ. Дешифрование не возможно";
            }
            result[i] = openAlphabet[codeCurSymb];
        }
        return String.valueOf(result);
    }
}
