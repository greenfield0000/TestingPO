/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.coddingMethod;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author roman
 */
public class BitReversTest {

    private BitRevers bitRevers;

    @Before
    public void setUp() {
        bitRevers = new BitRevers();
    }

    @After
    public void tearDown() {
        bitRevers = null;
    }

    /**
     * Test of calcExitAlphabet method, of class BitRevers.
     */
    @Test
    public void testCalcExitAlphabet54321() {
        char[] testExceptExitAlphabetOne = "_рзшгфмьбткъецоюасищдхнэвулыжчпя".toCharArray();
        bitRevers.calcExitAlphabet("54321");
        char[] exitAlphabet = bitRevers.getExitAlphabet();
        assertArrayEquals(testExceptExitAlphabetOne, exitAlphabet);
    }

    @Test
    public void testCalcExitAlphabetKey12345() {
        char[] testExceptExitAlphabetOne = "_абвгдежзиклмнопрстуфхцчшщъыьэюя".toCharArray();
        bitRevers.calcExitAlphabet("12345");
        char[] exitAlphabet = bitRevers.getExitAlphabet();
        assertArrayEquals(testExceptExitAlphabetOne, exitAlphabet);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testCalcExitAlphabetKeyArrayOfBoundException() {
        bitRevers.calcExitAlphabet("11234");
    }

    /**
     * Test of codText method, of class BitRevers.
     */
    @Test
    public void testCodText() {
            bitRevers.calcExitAlphabet("34512");
            assertEquals(bitRevers.codText("текст_для_кодирования"), "фстмф_иъя_туикгушзлкя");
            assertEquals(bitRevers.codText("Тест"), "фсмф");
            assertEquals(bitRevers.codText("й"), "Недопустимый символ. Шифрование не возможно");
            assertEquals(bitRevers.codText(""), "");
            assertEquals(bitRevers.codText(" "), "Недопустимый символ. Шифрование не возможно");
            assertEquals(bitRevers.codText(" "), "Недопустимый символ. Шифрование не возможно");
            assertEquals(bitRevers.codText("test"), "Недопустимый символ. Шифрование не возможно");
            assertEquals(bitRevers.codText("_"), "_");
            assertEquals(bitRevers.codText("12ыва"), "Недопустимый символ. Шифрование не возможно");

            bitRevers.calcExitAlphabet("12345");
            assertEquals(bitRevers.codText("текст_для_кодирования"), "текст_для_кодирования");
            assertEquals(bitRevers.codText("Тест"), "тест");
            assertEquals(bitRevers.codText("й"), "Недопустимый символ. Шифрование не возможно");
            assertEquals(bitRevers.codText(""), "");
            assertEquals(bitRevers.codText(" "), "Недопустимый символ. Шифрование не возможно");
            assertEquals(bitRevers.codText(" "), "Недопустимый символ. Шифрование не возможно");
            assertEquals(bitRevers.codText("test"), "Недопустимый символ. Шифрование не возможно");
            assertEquals(bitRevers.codText("_"), "_");
            assertEquals(bitRevers.codText("12ыва"), "Недопустимый символ. Шифрование не возможно");

    }

    /**
     * Test of decodText method, of class BitRevers.
     */
    @Test
    public void testDecodText() {
        bitRevers.calcExitAlphabet("34512");
        assertEquals(bitRevers.decodText("текст_для_кодирования"), "кшиек_фня_ищфдбщмгхдя");
        assertEquals(bitRevers.decodText("Тест"), "кшек");
        assertEquals(bitRevers.decodText("й"), "Недопустимый символ. Дешифрование не возможно");
        assertEquals(bitRevers.decodText(""), "");
        assertEquals(bitRevers.decodText(" "), "Недопустимый символ. Дешифрование не возможно");
        assertEquals(bitRevers.decodText(" _"), "Недопустимый символ. Дешифрование не возможно");
        assertEquals(bitRevers.decodText("test"), "Недопустимый символ. Дешифрование не возможно");
        assertEquals(bitRevers.decodText("_"), "_");
        assertEquals(bitRevers.decodText("12ыва"), "Недопустимый символ. Дешифрование не возможно");

        bitRevers.calcExitAlphabet("12345");
        assertEquals(bitRevers.decodText("текст_для_кодирования"), "текст_для_кодирования");
        assertEquals(bitRevers.decodText("Тест"), "тест");
        assertEquals(bitRevers.decodText("й"), "Недопустимый символ. Дешифрование не возможно");
        assertEquals(bitRevers.decodText(""), "");
        assertEquals(bitRevers.decodText(" "), "Недопустимый символ. Дешифрование не возможно");
        assertEquals(bitRevers.decodText(" "), "Недопустимый символ. Дешифрование не возможно");
        assertEquals(bitRevers.decodText("test"), "Недопустимый символ. Дешифрование не возможно");
        assertEquals(bitRevers.decodText("_"), "_");
        assertEquals(bitRevers.decodText("12ыва"), "Недопустимый символ. Дешифрование не возможно");

    }

}
