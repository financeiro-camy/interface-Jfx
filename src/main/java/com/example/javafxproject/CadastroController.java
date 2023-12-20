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
import java.util.regex.Matcher;
import java.util.regex.Pattern;



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
               UsuarioDAO userDAO = new UsuarioDAO();
 
               if (nome.isEmpty() || email.isEmpty() || senha.isEmpty() || senhaTeste.isEmpty()) {
                propriedades.exibirAlerta("Campos Vazios", "Preencha todos os campos");
            } else if (!isValidEmail(email)) {
                propriedades.exibirAlerta("Email Inválido", "Insira um email válido");
            } else if (!senha.equals(senhaTeste)) {
                propriedades.exibirAlerta("Senhas não coincidem", "As senhas não coincidem");
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
    
    public void CriarPrimeiraConta() throws IOException {
        propriedades.ScreenGuider("tela-login3.fxml", "Fazer login");
    }

    public void onActionLogin() throws IOException { 
        propriedades.ScreenGuider("tela-login3.fxml","Login");
    }

    public static boolean isValidEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }
}