/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.guitest;

import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.loadui.testfx.GuiTest;
import org.testfx.api.FxRobot;

/**
 *
 * @author roman
 */
public class GuiTesterSuccsesRegistry extends GuiTest {

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

    public GuiTesterSuccsesRegistry() {
    }

    @Override
    protected Parent getRootNode() {
        try {
            return FXMLLoader.load(getClass().getResource("/fxml/FXMLRegistry.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(GuiTesterSuccsesRegistry.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Test
    public void successfulRegistration() {
        robot.clickOn("#name").write("newUser");
        robot.clickOn("#login").write("newUser" + getRandNumberToSrt());
        robot.clickOn("#password").write("newUser");
        robot.clickOn("#isCheckedPolyAlphabet");
        robot.clickOn("#registry");
        robot.sleep(1500);
    }

    private String getRandNumberToSrt() {
        return String.valueOf(new Random().nextInt());
    }

}
