/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.integrationtest;

import com.mycompany.coddingMethod.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author roman
 */
public class integrationtest {

    private BitRevers bitRevers;
    private Polyalphabet polyalphabet;
    private String testingText;

    public integrationtest() {
    }

    @Before
    public void setUp() {
        bitRevers = new BitRevers();
        polyalphabet = new Polyalphabet();
        testingText = "строка_для_тестирования";
    }

    @After
    public void tearDown() {
        bitRevers = null;
        polyalphabet = null;
        testingText = null;
    }

    @Test
    public void integrationTestEncodeTwoMethodsBitPoly() {
        String coddingText;
        testingText = "строка_для_тестирования";

        bitRevers.calcExitAlphabet("54321");
        coddingText = bitRevers.codText(testingText);
        polyalphabet.setKeys("1,2,3");
        polyalphabet.setWord(coddingText);
        polyalphabet.coding(2);

        coddingText = polyalphabet.getWord();

        assertEquals("тлгпмуачэ_бмнумувсщтъуа", coddingText);
    }

    @Test
    public void integrationTestEncodeTwoMethodsPolyBit() {
        testingText = "строка_для_тестирования";
        String coddingText;
        String bitKeys = "34512";
        String polyKeys = "1,2,3";
        int encodeOption = 2;

        polyalphabet.setWord(testingText);
        polyalphabet.setKeys(polyKeys);
        polyalphabet.coding(encodeOption);
        coddingText = polyalphabet.getWord();
        bitRevers.calcExitAlphabet(bitKeys);
        coddingText = bitRevers.codText(coddingText);

        assertEquals("фдьывазщу_рнщьнтфмашгтз", coddingText);
    }

    @Test
    public void integrationBigTestEncodeTwoMethodsBitPoly() {
        String coddingText;
        int encodeOption = 2;

        bitRevers.calcExitAlphabet("34512");
        coddingText = bitRevers.codText(testingText);
        polyalphabet.setWord(coddingText);
        polyalphabet.setKeys("1,2,3");
        polyalphabet.coding(encodeOption);

        coddingText = polyalphabet.getWord();

        bitRevers.calcExitAlphabet("43215");
        coddingText = bitRevers.codText(coddingText);
        polyalphabet.setWord(coddingText);
        polyalphabet.setKeys("1,2,3");
        polyalphabet.coding(encodeOption);

        assertEquals("оэьлмшбцтатэуюэчъюзчячв", polyalphabet.getWord());
    }

    @Test
    public void integrationBigTestEncodeTwoMethodsPolyBit() {
        String coddingText;
        int encodeOption = 2;

        polyalphabet.setWord(testingText);
        polyalphabet.setKeys("1,2,3");
        polyalphabet.coding(encodeOption);
        coddingText = polyalphabet.getWord();

        bitRevers.calcExitAlphabet("34512");
        coddingText = bitRevers.codText(coddingText);

        polyalphabet.setWord(coddingText);
        polyalphabet.setKeys("1,-3,8");
        polyalphabet.coding(encodeOption);

        bitRevers.calcExitAlphabet("43215");

        assertEquals("лрзо_ддычанлцжлувкрлмуи", bitRevers.codText(polyalphabet.getWord()));
    }

    @Test
    public void integrationTestEncodeAndDecodeTwoMethodsBitPoly() {
        String coddingText;
        int encodeOption = 2;
        int decodeOption = 1;

        bitRevers.calcExitAlphabet("34512");
        coddingText = bitRevers.codText(testingText);

        polyalphabet.setWord(coddingText);
        polyalphabet.setKeys("1,2,3");
        polyalphabet.coding(encodeOption);

        coddingText = polyalphabet.getWord();

        //decode
        polyalphabet.setWord(coddingText);
        polyalphabet.coding(decodeOption);
        coddingText = polyalphabet.getWord();
        //
        bitRevers.calcExitAlphabet("34512");
        coddingText = bitRevers.decodText(coddingText);

        assertEquals("строка_для_тестирования", coddingText);
    }

}
