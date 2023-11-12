package com.example.javafxproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/*import DAO.Usuario;
import DAO.UsuarioDAO;*/

public class CadastroController {

    @FXML
    private TextField txfNome;
    
    @FXML
    private TextField txfEmail;
    
    @FXML
    private PasswordField txfSenha;

    @FXML
    private PasswordField txfSenhaTeste;
       

    @FXML
    public void Cadastrar() {
    
        String nome = txfNome.getText();
        String email = txfEmail.getText();
        String senha = txfSenha.getText();
        String senhaTeste = txfSenhaTeste.getText();

        System.out.println("Nome: " + nome);
        System.out.println("E-mail: " + email);
        System.out.println("Senha: " + senha);
        System.out.println("Senha confirmada é:" + senhaTeste);

       /*  Usuario user = new Usuario(nome,email,senha,true);
        UsuarioDAO userDAO = new UsuarioDAO();
        userDAO.create(user);*/

    }

    public void OnActionLogin() throws IOException{

        FXMLLoader loader = new FXMLLoader(MainController.class.getResource("tela-login2.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = new Stage();
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.sizeToScene();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();

    }





}
