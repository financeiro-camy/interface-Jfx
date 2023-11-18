package com.example.javafxproject;

import java.io.IOException;
import java.sql.SQLException;

import DAO.Usuario;
import DAO.UsuarioAtributoDAO;
import DAO.UsuarioDAO;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import org.mindrot.jbcrypt.BCrypt; 

import com.example.Propriedades;

public class LoginUsuarioController {
    @FXML
    private TextField txfEmail;

    @FXML
    private PasswordField txfSenha;

    public void Entrar() throws IOException, SQLException {
        String email = txfEmail.getText();
        String senha = txfSenha.getText();

        if (email.isEmpty() || senha.isEmpty()) {
            exibirAlerta("Campos Vazios", "Email e senha são obrigatórios");
        } else {
            int idlogado = fazerLogin(email, senha);
            if (idlogado != -1) {

                UsuarioAtributoDAO uaDAO = new UsuarioAtributoDAO();
                uaDAO.adicionarAtributo(idlogado, "id_usuario",idlogado);
                
                exibirAlerta("Login Bem Sucedido", "Login foi relizado com sucesso!");
                AbrirMenu();
            } else {
                exibirAlerta("Login falhou", "Login falhou. Verifique suas credenciais");
            }
        }
    }

    public void OnActionCadastar() throws IOException {
        Propriedades propriedades = new Propriedades();
        propriedades.ScreenGuider("tela-cadastro2.fxml", "Cadastro");
    }

    private void exibirAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    public void AbrirMenu() throws IOException {
        Propriedades propriedades = new Propriedades();
        propriedades.ScreenGuider("tela-menu2.fxml", "Menu");
    }

    public static int fazerLogin(String email, String senha) throws SQLException {
        UsuarioDAO userDAO = new UsuarioDAO();
        Usuario user = userDAO.findByEmail(email);

        if (user != null && user.isAtivo() && BCrypt.checkpw(senha, user.getSenha())) {
            return user.getId();
        }
        return -1;
    }
}
