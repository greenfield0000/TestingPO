/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.login;

import controller.codingmethods.bitrevers.BitRevers;
import controller.codingmethods.polyalphabet.CodeMachine;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Алексей
 */
public class FXMLController implements Initializable {

    static Stage STAGE;
    private BitRevers bitRevers = new BitRevers();
    private CodeMachine monoAlphabet = new CodeMachine();

    @FXML
    private Pane pane;
    @FXML
    private TextArea textArea;
    @FXML
    private MenuBar menuBar;
    @FXML
    private Menu fileMenu;
    @FXML
    private MenuItem open;
    @FXML
    private MenuItem clear;
    @FXML
    private MenuItem exit;
    @FXML
    private Menu codeMenu;
    @FXML
    private MenuItem methodCodeOne;
    @FXML
    private MenuItem methodCodeTwo;
    @FXML
    private MenuItem methodCodeThree;
    @FXML
    private MenuItem methodCodeFour;
    @FXML
    private MenuItem methodCodeFive;
    @FXML
    private MenuItem methodCodeSix;
    @FXML
    private MenuItem methodCodeSeven;
    @FXML
    private MenuItem methodCodeEight;
    @FXML
    private Menu decodeMenu;
    @FXML
    private MenuItem methodDecodeOne;
    @FXML
    private MenuItem methodDecodeTwo;
    @FXML
    private MenuItem methodDecodeThree;
    @FXML
    private MenuItem methodDecodeFour;
    @FXML
    private MenuItem methodDecodeFive;
    @FXML
    private MenuItem methodDecodeSix;
    @FXML
    private MenuItem methodDecodeSeven;
    @FXML
    private MenuItem methodDecodeEight;
    @FXML
    private Menu help;
    @FXML
    private MenuItem help1;
    @FXML
    private AnchorPane AnchorPane;
    @FXML
    private MenuItem saveas;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void onActionProperty(ActionEvent event) {
        STAGE.close();
    }

    @FXML
    private void onClearTextArea(ActionEvent event) {
        textArea.clear();
    }

    @FXML
    private void openFile(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();//Класс работы с диалогом выборки и сохранения 
        fileChooser.setTitle("Open Document");//Заголовок диалога 
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("txt files (*.txt)", "*.txt");//Расширение 
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showOpenDialog(STAGE);

        if (file != null) {
            try {
                //создаем объект FileReader для объекта File
                FileInputStream fis = new FileInputStream(file); //создаем BufferedReader с существующего FileReader для построчного считывания
                BufferedReader reader = new BufferedReader(new InputStreamReader(fis, "UTF-8")); // считаем сначала первую строку
                String line = reader.readLine();
                StringBuilder sb = new StringBuilder();
                while (line != null) {
                    sb.append(line);
                    // считываем остальные строки в цикле
                    line = reader.readLine();
                }
                textArea.setText(sb.toString());
            } catch (FileNotFoundException e) {
                System.out.println("Файл не найден");
            } catch (IOException e) {
                System.out.println("Исключение: " + e.getMessage());
            }
        }

    }

    @FXML
    private void saveFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();//Класс работы с диалогом выборки и сохранения 
        fileChooser.setTitle("Save Document");//Заголовок диалога 
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("txt files (*.txt)", "*.txt");//Расширение 
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(STAGE);//Указываем текущую сцену 
        if (file != null) {
            try {
                FileWriter fw = new FileWriter(file);
                fw.append(textArea.getText());
                fw.append("\n"); //переходим на новую строку
                fw.flush();
                fw.close();
            } catch (Exception ex) {
                System.out.println(ex.toString()); //чтобы хоть что-то знать о возможной ошибке
            }

        }
    }

    @FXML
    private void codePolyAlphabet(ActionEvent event) {
        Stage dialog = new Stage();
        dialog.initStyle(StageStyle.UTILITY);
        dialog.setTitle("Окно ввода ключа");
        VBox box = new VBox();
        box.setAlignment(Pos.CENTER);
        HBox buttons = new HBox();
        TextField txtField = new TextField();
        buttons.setAlignment(Pos.CENTER); 
        Button buttonOk = new Button("Ok");
        buttonOk.setOnAction((ActionEvent evt) -> {
            dialog.close();
            CodeMachine polyAlphabetMachine = new CodeMachine();
            polyAlphabetMachine.setWord(textArea.getText());
            polyAlphabetMachine.setKeys(txtField.getText());
            polyAlphabetMachine.coding(2);
            textArea.setText(polyAlphabetMachine.getWord().toString());
            
        });
        Button buttonEx = new Button("Cancel");
        buttonEx.setOnAction(evt -> {
            dialog.close();
        });
        buttons.getChildren().addAll(buttonOk, buttonEx);
        box.getChildren().addAll(new Label("Введите "), txtField, buttons);
        Scene scene = new Scene(box, 300, 100);
        dialog.setScene(scene);
        dialog.show();
    }

    @FXML
    private void decodePolyAlphabet(ActionEvent event) {
        Stage dialog = new Stage();
        dialog.initStyle(StageStyle.UTILITY);
        dialog.setTitle("Окно ввода ключа");
        VBox box = new VBox();
        box.setAlignment(Pos.CENTER);
        HBox buttons = new HBox();
        TextField txtField = new TextField();
        buttons.setAlignment(Pos.CENTER);
        Button buttonOk = new Button("Ok");
        buttonOk.setOnAction((ActionEvent evt) -> {
            dialog.close();
            CodeMachine polyAlphabetMachine = new CodeMachine();
            polyAlphabetMachine.setWord(textArea.getText());
            polyAlphabetMachine.setKeys(txtField.getText());
            polyAlphabetMachine.coding(1);
            textArea.setText(polyAlphabetMachine.getWord().toString());
        });
        Button buttonEx = new Button("Cancel");
        buttonEx.setOnAction(evt -> {
            dialog.close();
        });
        buttons.getChildren().addAll(buttonOk, buttonEx);
        box.getChildren().addAll(new Label("Введите ключ"), txtField, buttons);
        Scene scene = new Scene(box, 300, 100);
        dialog.setScene(scene);
        dialog.show();
    }

    @FXML
    private void codeBitRevers(ActionEvent event) {
        Stage dialog = new Stage();
        dialog.initStyle(StageStyle.UTILITY);
        dialog.setTitle("Окно ввода ключа");
        VBox box = new VBox();
        box.setAlignment(Pos.CENTER);
        HBox buttons = new HBox();
        TextField txtField = new TextField();
        buttons.setAlignment(Pos.CENTER);
        Button buttonOk = new Button("Ok");
        buttonOk.setOnAction((ActionEvent evt) -> {
            bitRevers.calcExitAlphabet(txtField.getText());
            dialog.close();
            String codeText = bitRevers.codText(textArea.getText().replaceAll("\n", " "));
            textArea.clear();
            textArea.setText(codeText);
        });
        Button buttonEx = new Button("Cancel");
        buttonEx.setOnAction(evt -> {
            dialog.close();
        });
        buttons.getChildren().addAll(buttonOk, buttonEx);
        box.getChildren().addAll(new Label("Введите ключ"), txtField, buttons);
        Scene scene = new Scene(box, 300, 100);
        dialog.setScene(scene);
        dialog.show();
    }

    @FXML
    private void decodeBitRevers(ActionEvent event) {
        Stage dialog = new Stage();
        dialog.initStyle(StageStyle.UTILITY);
        dialog.setTitle("Окно ввода ключа");
        VBox box = new VBox();
        box.setAlignment(Pos.CENTER);
        HBox buttons = new HBox();
        TextField txtField = new TextField();
        buttons.setAlignment(Pos.CENTER);
        Button buttonOk = new Button("Ok");
        buttonOk.setOnAction((ActionEvent evt) -> {

            bitRevers.calcExitAlphabet(txtField.getText());
            dialog.close();
            String decodeText = bitRevers
                    .decodText(textArea.getText().replaceAll("\n", " "));
            textArea.clear();
            textArea.setText(decodeText);
        });
        Button buttonEx = new Button("Cancel");
        buttonEx.setOnAction(evt -> {
            dialog.close();
        });
        buttons.getChildren().addAll(buttonOk, buttonEx);
        box.getChildren().addAll(new Label("Введите ключ"), txtField, buttons);
        Scene scene = new Scene(box, 300, 100);
        dialog.setScene(scene);
        dialog.show();
    }

    private void nonMethod(ActionEvent event) {
        Stage dialog = new Stage();
        dialog.initStyle(StageStyle.UTILITY);
        dialog.setTitle("Ввод ключа");
        VBox box = new VBox();
        box.setAlignment(Pos.CENTER);
        HBox buttons = new HBox();
        buttons.setAlignment(Pos.CENTER);
        Button buttonOk = new Button("Ok");
        buttonOk.setOnAction((ActionEvent evt) -> {
            dialog.close();
        });

        buttons.getChildren().addAll(buttonOk);
        box.getChildren().addAll(new Label("Метод не реализован"), buttons);
        Scene scene = new Scene(box, 300, 100);
        dialog.setScene(scene);
        dialog.show();
    }

    @FXML
    private void getHelpMethod(ActionEvent event) {
        Stage dialog = new Stage();
        dialog.initStyle(StageStyle.UTILITY);
        dialog.setTitle("Справка");
        VBox box = new VBox();
        box.setAlignment(Pos.CENTER);
        TextArea txtAereaHelp = new TextArea();
        txtAereaHelp.appendText("Полиалфавитная замена \n");

        txtAereaHelp.appendText("Побитовая перестановка\n");
        txtAereaHelp.appendText(
                "Несколько более сложной является побитовая перестановка, при которой в \n"
                + "соответствии с вектором перестановки изменяются позиции разрядов двоичного кода \n"
                + "символов открытого текста, обычно берутся ASCII коды. \n"
        );

        HBox buttons = new HBox();
        buttons.setAlignment(Pos.CENTER);
        Button buttonOk = new Button("Ok");
        buttonOk.setOnAction((ActionEvent evt) -> {
            dialog.close();
        });

        buttons.getChildren().addAll(buttonOk);
        box.getChildren().addAll(txtAereaHelp, buttons);
        Scene scene = new Scene(box, 600, 200);
        dialog.setScene(scene);
        dialog.show();
    }

}
