/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.guitest;

import java.awt.Button;
import javafx.scene.control.TextArea;
import java.awt.TextField;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import static org.loadui.testfx.Assertions.verifyThat;
import org.loadui.testfx.GuiTest;
import static org.loadui.testfx.controls.Commons.hasText;
import org.testfx.api.FxRobot;

/**
 *
 * @author roman
 */
public class GuiTesterMainWindow extends GuiTest {

    private static FxRobot robot;
    private TextArea textArea;

    @BeforeClass
    public static void createRobot() {
        robot = new FxRobot();
    }

    @Before
    public void before() {
        textArea = find("#textArea");
    }

    @AfterClass
    public static void deleteRobot() {
        robot = null;
    }

    public GuiTesterMainWindow() {
    }

    @Override
    protected Parent getRootNode() {
        try {
            return FXMLLoader.load(getClass().getResource("/fxml/FXMLMain.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(GuiTesterMainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void prepareClearTextArea() {
        robot.clickOn(textArea).write("");
    }

    public void sleepRobot() {
        robot.sleep(2000);
    }

    private void inputDialogText(String input) {
        robot.sleep(1000);
        robot.clickOn("#dialogText").write(input);
    }

    @Test
    public void accsesInputTestTextBitReverse() {
        prepareClearTextArea();
        robot.clickOn(textArea).write("текст_для_кодирования");
        robot.clickOn("#codeMenu");
        robot.clickOn("#methodCodeSix");
        inputDialogText("34512");
        robot.clickOn("#dialogOk");
        assertEquals(textArea.getText(), "фстмф_иъя_туикгушзлкя");
        sleepRobot();
    }

    @Test
    public void accsesInputUpperTestTextBitReverse() {
        prepareClearTextArea();
        robot.clickOn(textArea).write("Тест");
        robot.clickOn("#codeMenu");
        robot.clickOn("#methodCodeSix");
        inputDialogText("34512");
        robot.clickOn("#dialogOk");
        assertEquals(textArea.getText(), "фсмф");
        sleepRobot();
    }

    @Test
    public void wrongInputNoSymbolBitReverse() {
        prepareClearTextArea();
        robot.clickOn(textArea).write("й");
        robot.clickOn("#codeMenu");
        robot.clickOn("#methodCodeSix");
        inputDialogText("34512");
        robot.clickOn("#dialogOk");
        assertEquals(textArea.getText(), "Недопустимый символ. Шифрование не возможно");
        sleepRobot();
    }

    @Test
    public void wrongInputEmptyBitReverse() {
        prepareClearTextArea();
        robot.clickOn(textArea).write("");
        robot.clickOn("#codeMenu");
        robot.clickOn("#methodCodeSix");
        inputDialogText("34512");
        robot.clickOn("#dialogOk");
        assertEquals(textArea.getText(), "");
        sleepRobot();
    }

    @Test
    public void wrongInputSpaceBitReverse() {
        prepareClearTextArea();
        robot.clickOn(textArea).write(" ");
        robot.clickOn("#codeMenu");
        robot.clickOn("#methodCodeSix");
        inputDialogText("34512");
        robot.clickOn("#dialogOk");
        assertEquals(textArea.getText(), "Недопустимый символ. Шифрование не возможно");
        sleepRobot();
    }

    @Test
    public void wrongInputEngLangBitReverse() {
        prepareClearTextArea();
        robot.clickOn(textArea).write("test");
        robot.clickOn("#codeMenu");
        robot.clickOn("#methodCodeSix");
        inputDialogText("34512");
        robot.clickOn("#dialogOk");
        assertEquals(textArea.getText(), "Недопустимый символ. Шифрование не возможно");
        sleepRobot();
    }

    @Test
    public void accsesInputDownSpaceBitReverse() {
        prepareClearTextArea();
        robot.clickOn(textArea).write("_");
        robot.clickOn("#codeMenu");
        robot.clickOn("#methodCodeSix");
        inputDialogText("34512");
        robot.clickOn("#dialogOk");
        assertEquals(textArea.getText(), "_");
        sleepRobot();
    }

    @Test
    public void accsesInputRandomSymbBitReverse() {
        prepareClearTextArea();
        robot.clickOn(textArea).write("12ываnkjnknskml,lkksmklsmsklsmlsmlsknsjns");
        robot.clickOn("#codeMenu");
        robot.clickOn("#methodCodeSix");
        inputDialogText("34512");
        robot.clickOn("#dialogOk");
        assertEquals(textArea.getText(), "Недопустимый символ. Шифрование не возможно");
        sleepRobot();
    }

    @Test
    public void accsesInputPolyAlph() {
        prepareClearTextArea();
        robot.clickOn(textArea).write("тестовая_строка");
        robot.clickOn("#codeMenu");
        robot.clickOn("#methodCodeThree");
        inputDialogText("1,2,3");
        robot.clickOn("#dialogOk");
        assertEquals(textArea.getText(), "узфуребавтфупмг");
        sleepRobot();
    }

    @Test
    public void wrongInputPolyAlph() {
        prepareClearTextArea();
        robot.clickOn(textArea).write("тестовая строка");
        robot.clickOn("#codeMenu");
        robot.clickOn("#methodCodeThree");
        inputDialogText("1,2,3");
        robot.clickOn("#dialogOk");
        assertEquals(textArea.getText(), "Недопустимый символ. Шифрование невозможно");
        sleepRobot();
    }

    @Test
    public void accsesInputDownSpacePolyAlph() {
        prepareClearTextArea();
        robot.clickOn(textArea).write("_");
        robot.clickOn("#codeMenu");
        robot.clickOn("#methodCodeThree");
        inputDialogText("1,2,3");
        robot.clickOn("#dialogOk");
        assertEquals(textArea.getText(), "а");
        sleepRobot();
    }

    @Test
    public void accsesIntegration() {
        prepareClearTextArea();
        robot.clickOn(textArea).write("строка_для_тестирования");
        robot.clickOn("#codeMenu");
        robot.clickOn("#methodCodeSix");
        inputDialogText("34512");
        robot.clickOn("#dialogOk");
        sleepRobot();

        robot.clickOn("#codeMenu");
        robot.clickOn("#methodCodeThree");
        inputDialogText("1,2,3");
        robot.clickOn("#dialogOk");
        sleepRobot();

        robot.clickOn("#decodeMenu");
        robot.clickOn("#methodDecodeThree");
        inputDialogText("1,2,3");
        robot.clickOn("#dialogOk");
        sleepRobot();

        robot.clickOn("#decodeMenu");
        robot.clickOn("#methodDecodeSix");
        inputDialogText("34512");
        robot.clickOn("#dialogOk");
        sleepRobot();

        assertEquals(textArea.getText(), "строка_для_тестирования");
        sleepRobot();
    }

}
