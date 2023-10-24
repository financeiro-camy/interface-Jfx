package com.example.javafxproject;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class LoginUsuarioController {
	@FXML
	private TextField txfEmail;
	@FXML
	private TextField txfSenha;
	@FXML
	private CheckBox ckbCadastro;
    
	public void onActionLogar() {
    	String email = txfEmail.getText();
    	String telefone = txfSenha.getText();
    	Boolean aceitaPromocao = ckbCadastro.isSelected();

    	System.out.println(email);
    	System.out.println(telefone);
	}
    
}

