package com.example.javafxproject;

import java.io.IOException;
import java.sql.SQLException;

import DAO.Usuario;
import DAO.UsuarioDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;

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
				 exibirAlerta("Login Bem Sucedido", "Login foi relizado com sucesso!");

				 AbrirMenu();
			} else {
				exibirAlerta("Login falhou","Login falhou. Verifique suas credenciais");
			}
        }
    }
    
	public void OnActionCadastar() throws IOException{
		FXMLLoader loader = new FXMLLoader(MainController.class.getResource("tela-cadastro2.fxml"));
		Scene scene = new Scene(loader.load());
		Stage stage = new Stage();
		stage.setTitle("Cadastro");
		stage.setScene(scene);
		stage.sizeToScene();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.showAndWait();
	}

	private void exibirAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

	public void AbrirMenu() throws IOException{
	FXMLLoader loader = new FXMLLoader(MainController.class.getResource("tela-menu2.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = new Stage();
            stage.setTitle("Menu");
            stage.setScene(scene);
            stage.sizeToScene();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
	}

	public static int fazerLogin(String email, String senha) throws SQLException {
        UsuarioDAO userDAO = new UsuarioDAO();
        Usuario user = userDAO.findByEmail(email);

        if (user != null && user.isAtivo() && user.getSenha().equals(senha)) {
            return user.getId();
        }
        return -1;
    }
}

