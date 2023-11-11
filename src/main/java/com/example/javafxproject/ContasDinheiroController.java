package com.example.javafxproject;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ContasDinheiroController {

    @FXML
    private TextField txfContaNome;

    @FXML
    private TextField txfDescricao;

	@FXML
    private TextField txfSaldoInicial;

    @FXML
    private DatePicker dataSaldo;


    @FXML
    public void criarConta() {
        String nome = txfContaNome.getText();
        String descricao = txfDescricao.getText();
		double valorSaldoInicial = Double.parseDouble(txfSaldoInicial.getText());
        LocalDate dataSelecionada = dataSaldo.getValue();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataFormatada = dataSelecionada.format(formatter);
        
        System.out.println("Nome: " + nome);
        System.out.println("Descrição: " + descricao);
        System.out.println("Saldo Inicial: " + valorSaldoInicial);
        System.out.println("Data de inserção: " + dataFormatada);
           
}
}