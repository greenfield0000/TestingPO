/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controllers;

import com.mycompany.db.entity.Profile;
import static com.mycompany.newtesting2.MainApp.mainApp;
import static com.mycompany.newtesting2.MainApp.profileJpaController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Roman
 */
public class FXMLRegistryController implements Initializable {

    @FXML
    private TextField login;
    @FXML
    private CheckBox isCheckedPolyAlphabet;
    @FXML
    private CheckBox isCheckedBitReverse;
    @FXML
    private PasswordField password;
    @FXML
    private TextField name;
    @FXML
    private Label errorMessage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        returnToCustomView();
    }

    @FXML
    private void goToAutorization(ActionEvent event) throws IOException {
        mainApp.goToInterface("FXMLEntry");
    }

    @FXML
    public void registry(ActionEvent event) throws IOException {
        if (isValidateField()) {
            Profile profile = new Profile();
            profile.setName(name.getText());
            profile.setLogin(login.getText());
            profile.setPassword(password.getText());
            profile.setIsbitreverse(isCheckedBitReverse.isSelected());
            profile.setIspolyalphabet(isCheckedPolyAlphabet.isSelected());

            if (Boolean.valueOf(profileJpaController.create(profile)).equals(true)) {
                mainApp.goToInterface("FXMLEntry");
                returnToCustomView();
            } else {
                showErrorMessageUserIsExist();
            }
        } else {
            showErrorMessageNotValidField();
        }
    }

    private void showErrorMessageNotValidField() {
        errorMessage.setVisible(false);
        errorMessage.setText("Не заполнены поля или не выбран ни один метод");
        errorMessage.setVisible(true);
    }

    private boolean isValidateField() {
        return (isPresedAtListOne() && isTextFieldNotEmpty());
    }

    private void showErrorMessageUserIsExist() {
        errorMessage.setVisible(false);
        errorMessage.setText("Такой пользователь уже существует");
        errorMessage.setVisible(true);
    }

    /**
     * Выбран хотя бы один метод ?
     *
     * @return
     */
    private boolean isPresedAtListOne() {
        return (isCheckedPolyAlphabet.isSelected()
                || isCheckedBitReverse.isSelected());
    }

    /**
     * Заполнены все текстовые поля ?
     *
     * @return
     */
    private boolean isTextFieldNotEmpty() {
        return ((!login.getText().isEmpty())
                && (!name.getText().isEmpty())
                && (!password.getText().isEmpty()));
    }

    private void returnToCustomView() {
        errorMessage.setVisible(false);
        errorMessage.setText("Не выбран не один из методов или неверно заполнены поля!");
        name.setText("");
        login.setText("");
        password.setText("");
        isCheckedBitReverse.setSelected(false);
        isCheckedPolyAlphabet.setSelected(false);
    }

}
