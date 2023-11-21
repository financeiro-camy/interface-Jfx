package com.example.javafxproject;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;

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
       
    Propriedades propriedades = new Propriedades();

   

    public void Cadastrar() throws IOException, SQLException {
        String nome = txfNome.getText();
        String email = txfEmail.getText();
        String senha = txfSenha.getText();
        String senhaTeste = txfSenhaTeste.getText();
    
        if (nome.isEmpty() || email.isEmpty() || senha.isEmpty() || senhaTeste.isEmpty()) {
            propriedades.exibirAlerta("Campos Vazios", "Preencha todos os campos");
        } else if (!senha.equals(senhaTeste)) {
            propriedades.exibirAlerta("Senhas não coincidem", "As senhas não coincidem");
        } else {
            UsuarioDAO userDAO = new UsuarioDAO();
            
            boolean emailExistente = userDAO.verificarEmailExistente(email);
            if (emailExistente) {
                propriedades.exibirAlerta("Email já cadastrado", "O email já está cadastrado");
            } else {
                String hashedPassword = BCrypt.hashpw(senha, BCrypt.gensalt());
                Usuario user = new Usuario(nome, email, hashedPassword, true);
                userDAO.create(user);
    
                int id_usuario = user.getId();
                propriedades.setoresPadroes(id_usuario);
    
                propriedades.exibirAlerta("Cadastro realizado", "Cadastro realizado com sucesso! Agora inicie sua sessão com o login.");
                CriarPrimeiraConta();
            }
        }
    }
    

    public void CriarPrimeiraConta() throws IOException {
        propriedades.ScreenGuider("tela-login3.fxml", "Fazer login");
    }

    public void onActionLogin() throws IOException {
        
        propriedades.ScreenGuider("tela-login3.fxml","Login");
    }
    
}
