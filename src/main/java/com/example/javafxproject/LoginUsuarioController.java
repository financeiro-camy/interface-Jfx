package com.example.javafxproject;

import java.io.IOException;

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

	public void Entrar() throws IOException {
    	String email = txfEmail.getText();
    	String senha = txfSenha.getText();

		if (email.isEmpty() || senha.isEmpty()) {
            exibirAlerta("Campos Vazios", "Email e senha são obrigatórios");
        } else {
            AbrirMenu();
        }
    }
    
	public void OnActionCadastar() throws IOException{
		FXMLLoader loader = new FXMLLoader(MainController.class.getResource("tela-cadastro.fxml"));
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
	FXMLLoader loader = new FXMLLoader(MainController.class.getResource("menu.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = new Stage();
            stage.setTitle("Menu");
            stage.setScene(scene);
            stage.sizeToScene();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
	}
}

