
package com.example.javafxproject;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class MainController {

@FXML
protected void onAbrirProjetoCofrinho() throws IOException {
FXMLLoader loader = new FXMLLoader(MainController.class.getResource("tela-login2.fxml"));
Scene scene = new Scene(loader.load());
Stage stage = new Stage();
stage.setTitle("Projeto Cofrinho");
stage.setScene(scene);
stage.sizeToScene();
stage.initModality(Modality.APPLICATION_MODAL);
stage.showAndWait();
}

@FXML
protected void onContaDinheiro() throws IOException {
FXMLLoader loader = new FXMLLoader(MainController.class.getResource("tela-contasdinheiro.fxml"));
Scene scene = new Scene(loader.load());
Stage stage = new Stage();
stage.setTitle("Contas Dinheiro");
stage.setScene(scene);
stage.sizeToScene();
stage.initModality(Modality.APPLICATION_MODAL);
stage.showAndWait();
}

}

