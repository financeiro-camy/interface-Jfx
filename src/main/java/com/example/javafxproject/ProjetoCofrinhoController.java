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
    private PasswordField senhaPasswordField;
   
    @FXML
    private Picker dataNascimentoDatePicker;

    @FXML
    private void cadastrarUsuario(ActionEvent event) {
    
        String nome = nomeTextField.getText();
        String email = emailTextField.getText();
        String senha = senhaPasswordField.getText();
        String dataNascimento = dataNascimentoDatePicker.getValue().toString();

        System.out.println("Nome: " + nome);
        System.out.println("E-mail: " + email);
        System.out.println("Senha: " + senha);
        System.out.println("Data de Nascimento: " + dataNascimento);
    }
}
