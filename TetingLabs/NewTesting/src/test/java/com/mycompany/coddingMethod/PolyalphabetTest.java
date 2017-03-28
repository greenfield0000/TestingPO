/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.coddingMethod;

import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author roman
 */
public class PolyalphabetTest {

    private Polyalphabet polyalphabet;

    public PolyalphabetTest() {
    }

    @Before
    public void setUp() {
        polyalphabet = new Polyalphabet();
    }

    @After
    public void tearDown() {
        polyalphabet = null;
    }

    /**
     * Test of getWord and setWord methods, of class Polyalphabet.
     */
    @Test
    public void testGetAndSetWord() {

        String word;

        word = "тестСтрока";
        polyalphabet.setWord(word);
        assertEquals(word, polyalphabet.getWord());

        word = "123";
        polyalphabet.setWord(word);
        assertEquals(word, polyalphabet.getWord());

        word = "";
        polyalphabet.setWord(word);
        assertEquals(word, polyalphabet.getWord());

        word = "_";
        polyalphabet.setWord(word);
        assertEquals(word, polyalphabet.getWord());

        word = " ";
        polyalphabet.setWord(word);
        assertEquals(word, polyalphabet.getWord());
    }

    /**
     * Test of getKeys method, of class Polyalphabet.
     */
    @Test
    public void testSetAndGetKeys() {

        String inputKeys = "1,2,3,4,5";
        String outputText = "";

        polyalphabet.setKeys(inputKeys);
        int[] keys = polyalphabet.getKeys();

        for (int i = 0; i < keys.length; i++) {
            if (i != keys.length - 1) {
                outputText += Integer.toString(keys[i]) + ',';
            } else {
                outputText += Integer.toString(keys[i]);
            }
        }
        assertEquals(inputKeys, outputText);

        inputKeys = "-1";
        outputText = "";
        polyalphabet.setKeys(inputKeys);
        keys = polyalphabet.getKeys();

        for (int i = 0; i < keys.length; i++) {
            if (i != keys.length - 1) {
                outputText += Integer.toString(keys[i]) + ',';
            } else {
                outputText += Integer.toString(keys[i]);
            }
        }
        assertEquals(inputKeys, outputText);
    }

    @Test(expected = NumberFormatException.class)
    public void testIsEmptyInputKeys() {
        String inputKeys = "";
        String outputText = "";
        polyalphabet.setKeys(inputKeys);
        assertEquals(inputKeys, outputText);
    }

    @Test(expected = NullPointerException.class)
    public void testNumberFormatExceptionOfNullInput() {
        String inputKeys = "null";
        String outputText = "";

        inputKeys = null;
        polyalphabet.setKeys(inputKeys);
        assertEquals(inputKeys, outputText);
    }

    @Test(expected = NullPointerException.class)
    public void testNumberFormatExceptionOfwordInput() {
        String inputKeys = "строкаКакаяТо";
        String outputText = "";

        inputKeys = null;
        polyalphabet.setKeys(inputKeys);
        assertEquals(inputKeys, outputText);
    }

    /**
     * Test of coding method, of class Polyalphabet.
     */
    @Test
    public void testCoding() {
        String testString = "тестовая_строка";
        int optionCoding = 2;

        polyalphabet.setWord(testString);
        polyalphabet.setKeys("1,2,3");
        polyalphabet.coding(optionCoding);
        assertEquals("узфуребавтфупмг", polyalphabet.getWord());

    }

    @Test
    public void testCodingWrongInputOne() {
        String testString = "тестовая строка";
        polyalphabet.setKeys("1,2,3");
        polyalphabet.setWord(testString);
        int optionCoding = 2;

        polyalphabet.coding(optionCoding);
        assertEquals("Недопустимый символ. Шифрование невозможно", polyalphabet.getWord());

        testString = "";
        polyalphabet.setWord(testString);
        polyalphabet.coding(optionCoding);
        assertEquals("", polyalphabet.getWord());

        testString = "_";
        polyalphabet.setWord(testString);
        polyalphabet.coding(optionCoding);
        assertEquals("а", polyalphabet.getWord());

        testString = "й";
        polyalphabet.setWord(testString);
        polyalphabet.coding(optionCoding);
        assertEquals("Недопустимый символ. Шифрование невозможно", polyalphabet.getWord());

        testString = "1";
        polyalphabet.setWord(testString);
        polyalphabet.coding(optionCoding);
        assertEquals("Недопустимый символ. Шифрование невозможно", polyalphabet.getWord());

        testString = "sdf";
        polyalphabet.setWord(testString);
        polyalphabet.coding(optionCoding);
        assertEquals("Недопустимый символ. Шифрование невозможно", polyalphabet.getWord());

    }

    /**
     * Test of encoding method, of class Polyalphabet.
     */
    @Test
    public void testEncoding() {

        String testString = "тестовая Cтрока";
        polyalphabet.setKeys("1,2,3");
        polyalphabet.setWord(testString);
        int optionCoding = 1;

        polyalphabet.coding(optionCoding);
        assertEquals("Недопустимый символ. Дешифрование невозможно", polyalphabet.getWord());

        testString = "";
        polyalphabet.setWord(testString);
        polyalphabet.coding(optionCoding);
        assertEquals("", polyalphabet.getWord());

        testString = "_";
        polyalphabet.setWord(testString);
        polyalphabet.coding(optionCoding);
        assertEquals("я", polyalphabet.getWord());

        testString = "й";
        polyalphabet.setWord(testString);
        polyalphabet.coding(optionCoding);
        assertEquals("Недопустимый символ. Дешифрование невозможно", polyalphabet.getWord());

        testString = "1";
        polyalphabet.setWord(testString);
        polyalphabet.coding(optionCoding);
        assertEquals("Недопустимый символ. Дешифрование невозможно", polyalphabet.getWord());

        testString = "sdf";
        polyalphabet.setWord(testString);
        polyalphabet.coding(optionCoding);
        assertEquals("Недопустимый символ. Дешифрование невозможно", polyalphabet.getWord());

    }

    /**
     * Test of setNewSymbol method, of class Polyalphabet.
     */
    @Test
    public void testSetNewSymbol() {
        String newWord = "Тестовая строка";

        polyalphabet.setWord(newWord);
        polyalphabet.setNewSymbol(0, 'a');
        String expectedString = "aестовая строка";
        assertEquals(expectedString, polyalphabet.getWord());

        polyalphabet.setWord(newWord);
        polyalphabet.setNewSymbol(5, 'X');
        expectedString = "ТестоXая строка";
        assertEquals(expectedString, polyalphabet.getWord());

        polyalphabet.setWord(newWord);
        polyalphabet.setNewSymbol(4, '2');
        expectedString = "Тест2вая строка";
        assertEquals(expectedString, polyalphabet.getWord());

    }

    @Test(expected = StringIndexOutOfBoundsException.class)
    public void testSetNewSymbolOfBoundException() {
        polyalphabet.setWord("Тест строка");
        polyalphabet.setNewSymbol(-1, 'a');
    }

    @Test(expected = StringIndexOutOfBoundsException.class)
    public void testSetNewSymbolOfBoundException100() {
        polyalphabet.setWord("Тест строка");
        polyalphabet.setNewSymbol(100, 'a');
    }

    /**
     * Test of getCharPosition method, of class Polyalphabet.
     */
    @Test
    public void testGetCharPosition() {
        assertEquals(0, polyalphabet.getCharPosition('_'));
        assertEquals(31, polyalphabet.getCharPosition('я'));
        assertEquals(12, polyalphabet.getCharPosition('м'));
        assertEquals(0, polyalphabet.getCharPosition('й'));
        assertEquals(0, polyalphabet.getCharPosition('*'));
        assertEquals(0, polyalphabet.getCharPosition('t'));
    }

    /**
     * Test of getCharFromPosition method, of class Polyalphabet.
     */
    @Test
    public void testGetCharFromPosition() {
        assertEquals('а', polyalphabet.getCharFromPosition(1));
        assertEquals('ю', polyalphabet.getCharFromPosition(30));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testGetCharFromPositionOfBoundException100() {
        polyalphabet.getCharFromPosition(100);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testSetNewSymbolOfBoundExceptionDiv1() {
        polyalphabet.getCharFromPosition(-1);
    }

}
