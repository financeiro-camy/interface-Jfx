package com.example.javafxproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;

public class ApresentacaoController {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    void entrar(ActionEvent event) {
        Stage stage = (Stage) anchorPane.getScene().getWindow();

        try {
            String outraTelaFXML = "tela-login3.fxml";
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource(outraTelaFXML)));

            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); 
        }
    }
}