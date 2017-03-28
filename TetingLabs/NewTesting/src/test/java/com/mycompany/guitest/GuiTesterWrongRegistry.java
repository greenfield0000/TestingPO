/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.guitest;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.loadui.testfx.Assertions.verifyThat;
import org.loadui.testfx.GuiTest;
import static org.loadui.testfx.controls.Commons.hasText;
import org.testfx.api.FxRobot;
import static org.loadui.testfx.GuiTest.find;

/**
 *
 * @author roman
 */
public class GuiTesterWrongRegistry extends GuiTest {

    private static FxRobot robot;

    @BeforeClass
    public static void createRobot() {
        robot = new FxRobot();
    }

    @Before
    public void prepareData() {
        robot.clickOn("#name").write("");
        robot.clickOn("#login").write("");
        robot.clickOn("#password").write("");
    }

    public GuiTesterWrongRegistry() {
    }

    @Override
    protected Parent getRootNode() {
        try {
            return FXMLLoader.load(getClass().getResource("/fxml/FXMLRegistry.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(GuiTesterWrongRegistry.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Test
    public void wrongSetName() {
        robot.clickOn("#name").write("");
        robot.clickOn("#login").write("justLogin");
        robot.clickOn("#password").write("justPassword");
        robot.clickOn("#isCheckedPolyAlphabet");
        robot.clickOn("#registry");
        verifyThat(find("#errorMessage"), hasText("Не заполнены поля или не выбран ни один метод"));
        robot.sleep(1500);
    }

    @Test
    public void wrongSetLogin() {
        robot.clickOn("#name").write("justName");
        robot.clickOn("#login").write("");
        robot.clickOn("#password").write("justPassword");
        robot.clickOn("#isCheckedPolyAlphabet");
        robot.clickOn("#registry");
        verifyThat(find("#errorMessage"), hasText("Не заполнены поля или не выбран ни один метод"));
        robot.sleep(1500);
    }

    @Test
    public void wrongSetPassword() {
        robot.clickOn("#name").write("justName");
        robot.clickOn("#login").write("justLogin");
        robot.clickOn("#password").write("");
        robot.clickOn("#isCheckedPolyAlphabet");
        robot.clickOn("#registry");
        verifyThat(find("#errorMessage"), hasText("Не заполнены поля или не выбран ни один метод"));
        robot.sleep(1500);
    }

    @Test
    public void wrongNoChoiceMethod() {
        robot.clickOn("#name").write("justName");
        robot.clickOn("#login").write("justLogin");
        robot.clickOn("#password").write("justPassword");
        robot.clickOn("#registry");
        verifyThat(find("#errorMessage"), hasText("Не заполнены поля или не выбран ни один метод"));
        robot.sleep(1500);
    }

    @Test
    public void suchUserIsExists() {
        robot.clickOn("#name").write("test");
        robot.clickOn("#login").write("test");
        robot.clickOn("#password").write("test");
        robot.clickOn("#isCheckedPolyAlphabet");
        robot.clickOn("#registry");
        verifyThat(find("#errorMessage"), hasText("Такой пользователь уже существует"));
        robot.sleep(1500);
    }

}
