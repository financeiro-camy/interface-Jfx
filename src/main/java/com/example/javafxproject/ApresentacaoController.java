package com.example.javafxproject;

import java.io.IOException;

import com.example.Propriedades;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class ApresentacaoController {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    void Entrar(ActionEvent event) throws IOException {
        Propriedades propriedades = new Propriedades();
        propriedades.ScreenGuider("tela-login3.fxml","Tela login");
        
    }
}