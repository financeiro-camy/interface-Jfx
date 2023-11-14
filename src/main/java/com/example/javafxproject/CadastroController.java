package com.example.javafxproject;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.sql.SQLException;

import com.example.Propriedades;

import java.io.IOException;
import DAO.Usuario;
import DAO.UsuarioDAO;


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
    public void Cadastrar() throws IOException, SQLException  {
    
        String nome = txfNome.getText();
        String email = txfEmail.getText();
        String senha = txfSenha.getText();
        String senhaTeste = txfSenhaTeste.getText();

        Usuario user = new Usuario(nome,email,senha,true);
        UsuarioDAO userDAO = new UsuarioDAO();
        userDAO.create(user);

        exibirAlerta("Só mais 1 passo!", "Nome, email e senha cadastrados com sucesso! Agora cadastre uma conta para as suas transações!");

        CriarPrimeiraConta();
    }

    public void CriarPrimeiraConta() throws IOException{

        Propriedades propriedades = new Propriedades();
        propriedades.ScreenGuider("tela-contasdinheiro2.fxml","Cadastrar conta");
    }

    public void OnActionLogin() throws IOException{

        Propriedades propriedades = new Propriedades();
        propriedades.ScreenGuider("tela-login2.fxml","Login");
    }

    public void exibirAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
