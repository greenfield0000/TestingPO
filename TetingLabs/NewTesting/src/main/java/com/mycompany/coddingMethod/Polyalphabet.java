package com.mycompany.coddingMethod;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Roman
 */
public class Polyalphabet {

    private int bias;
    private int keys[];
    private final char alph[] = {'_', 'а', 'б', 'в', 'г', 'д', 'е', 'ж', 'з', 'и', 'к', 'л', 'м',
        'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ч', 'ц', 'ш', 'щ', 'ъ',
        'ы', 'ь', 'э', 'ю', 'я'};
    private StringBuffer word = null;

    public Polyalphabet() {
        bias = 0;
        word = new StringBuffer();
        keys = new int[1000];
    }

    public String getWord() {
        return word.toString();
    }

    public void setWord(String word) {
        this.word = null;
        this.word = new StringBuffer();
        this.word.append(word);
    }

    public int[] getKeys() {
        return keys;
    }

    public void setKeys(String strKeys) {

        ArrayList<String> arrayList = new ArrayList<>();
        Arrays.stream(strKeys.split(",")).forEach(arrayList::add);

        this.bias = arrayList.size();
        this.keys = arrayList.stream().mapToInt(s -> Integer.parseInt(s)).toArray();

    }

    /**
     * 2 - закодировать, 1 - декодировать
     *
     * @param option - опция (1,2)
     */
    public void coding(int option) {
        char newSymbol;
        int newPosition;
        int j = 0;

        for (int i = 0; i < word.length(); i++) {
            if (j >= bias) {
                j = 0;
            }
            int isContaint = contains(alph, word.charAt(i));
            if (isContaint < 0) {
                if (option == 2) {
                    this.setWord("Недопустимый символ. Шифрование невозможно");
                } else {
                    this.setWord("Недопустимый символ. Дешифрование невозможно");
                }
                return;
            }
            int x = getCharPosition(word.charAt(i));
            newPosition = (int) Math.floorMod((int) ((x + Math.pow(-1, option) * keys[j])), 32);
            newSymbol = getCharFromPosition(newPosition);
            setNewSymbol(i, newSymbol);
            j++;
        }
    }

    /**
     * Вставляем новый символ
     *
     * @param position
     * @param newSybol
     */
    public void setNewSymbol(int position, char newSybol) {
        word.setCharAt(position, newSybol);
    }

    /**
     * Получает позицию текущего символа
     *
     * @param curChar
     * @return
     */
    public int getCharPosition(char curChar) {
        int position = 0;
        int i = 0;
        boolean flag = true;

        String s = String.valueOf(alph);
        while (i < s.length() && (flag)) {

            if (s.charAt(i) == curChar) {
                position = i;
                flag = false;
            }
            i++;
        }
        return position;
    }

    public char getCharFromPosition(int position) {
        return alph[position];
    }

    private int contains(char[] chars, char symbol) {
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == symbol) {
                return i;
            }
        }
        return -1;
    }
}
