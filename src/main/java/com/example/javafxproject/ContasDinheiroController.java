package com.example.javafxproject;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

//import javafx.scene.control.Label;

public class ContasDinheiroController {
    @FXML
	private TextField txfconta_nome;
	@FXML
	private TextField txfsaldo_inicial;
    
	public void onActionCriarConta() {
    	String nomeConta = txfconta_nome.getText();
    	String saldoInicial = txfsaldo_inicial.getText();

    	System.out.println(nomeConta);
    	System.out.println(saldoInicial);
	}
}
