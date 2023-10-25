package com.example.javafxproject;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;

/* MODIFICAR ESSE CONTROLLER POIS ELE ESTÁ COM INFORMAÇÕES DE OUTRO CONTROLLER */

public class ProjetoCofrinhoController {

    @FXML
    private TextField nomeTextField;
    
    @FXML
    private TextField descricaoTextField;
    
    @FXML
    private Button meuBotao;
    
    @FXML
    private TextField meuCampoDeTexto;

    @FXML
    private void cadastrarUsuario(ActionEvent event) {
    
        String texto = meuCampoDeTexto.getText();
        String nome = nomeTextField.getText();

        System.out.println("Nome: " + nome);
    }
}
