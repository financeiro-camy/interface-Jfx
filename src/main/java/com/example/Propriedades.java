package com.example;

import java.io.IOException;
import com.example.javafxproject.MainController;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Propriedades{

  public void ScreenGuider(String nomeTela, String tituloTela) throws IOException{
    FXMLLoader loader = new FXMLLoader(MainController.class.getResource(nomeTela));
            Scene scene = new Scene(loader.load());
            Stage stage = new Stage();
            stage.setTitle(tituloTela);
            stage.setScene(scene);
            stage.sizeToScene();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
  }
}