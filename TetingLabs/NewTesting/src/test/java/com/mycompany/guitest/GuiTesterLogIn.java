/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.guitest;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.junit.BeforeClass;
import org.junit.Test;
import org.loadui.testfx.GuiTest;
import org.testfx.api.FxRobot;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.loadui.testfx.Assertions.verifyThat;
import static org.loadui.testfx.GuiTest.find;
import static org.loadui.testfx.controls.Commons.hasText;

/**
 *
 * @author roman
 */
public class GuiTesterLogIn extends GuiTest {

    private static FxRobot robot;

    @BeforeClass
    public static void createRobot() {
        robot = new FxRobot();
    }

    public GuiTesterLogIn() {
    }

    @Override
    protected Parent getRootNode() {
        try {
            return FXMLLoader.load(getClass().getResource("/fxml/FXMLEntry.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(GuiTesterLogIn.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Test
    public void testTryAutorizationWrong() {
        robot.clickOn("#login").write("thisUserNotExistInDB");
        robot.clickOn("#password").write("thisUserNotExistInDB");
        robot.clickOn("#entry");
        verifyThat(find("#errorMessage"), hasText("Такого пользователя не существует"));
        robot.clickOn("#login").write("");
        robot.clickOn("#password").write("");
    }

    @Test
    public void testTryAutorizationAxcept() {
        robot.clickOn("#login").write("test");
        robot.clickOn("#password").write("test");
        robot.clickOn("#entry");
    }

}
