package com.example.javafxproject;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;

public class CadastroController {

    @FXML
    private TextField nomeTextField;
    
    @FXML
    private TextField emailTextField;
    
    @FXML
    private PasswordField senhaPasswordField;
   
    @FXML
    private DatePicker dataNascimentoDatePicker;
    

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

        //terminar de inserir o DAO neste controller:

    }
}
