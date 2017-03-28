/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controllers;

import static com.mycompany.newtesting2.MainApp.mainApp;
import static com.mycompany.newtesting2.MainApp.profileJpaController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Roman
 */
public class FXMLEntryController implements Initializable {

    @FXML
    private Label errorMessage;
    @FXML
    private TextField login;
    @FXML
    private PasswordField password;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        errorMessage.setVisible(false);
        errorMessage.setText("Такого пользователя не существует");
        login.setText("");
        password.setText("");
    }

    @FXML
    private void goToRegistry(ActionEvent event) throws IOException {
        mainApp.goToInterface("FXMLRegistry");
    }

    @FXML
    private void entry(ActionEvent event) throws IOException {
        if (!isAutorized()) {
            showErrorMessage();
            return;
        }
        mainApp.goToInterface("FXMLMainForm");
    }

    private boolean isAutorized() {
        mainApp.user = profileJpaController.findNameByLoginAndPassword(
                profileJpaController.getEntityManager(),
                login.getText(),
                password.getText());

        if (mainApp.user != null) {
            return true;
        }
        return false;
    }

    private void showErrorMessage() {
        errorMessage.setVisible(true);
    }
}
