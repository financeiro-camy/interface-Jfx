package com.example.javafxproject;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import DAO.ContasDinheiro;
import DAO.ContasDinheiroDAO;

import java.sql.SQLException;


public class ContasDinheiroController {

    @FXML
    private TextField txfContaNome;

	@FXML
    private TextField txfSaldoInicial;

    @FXML
    private DatePicker dataSaldo;

    private ContasDinheiroDAO contasDinheiroDAO = new ContasDinheiroDAO();

    @FXML
    public void criarConta() {
        String nome = txfContaNome.getText();
		double valorSaldoInicial = Double.parseDouble(txfSaldoInicial.getText());
        LocalDate dataSelecionada = dataSaldo.getValue();
        
        if (nome.isEmpty()) {
            exibirAlerta("Campo Vazio", "Nome da conta é obrigatório");
            return;
        }

        if (dataSelecionada == null) {
            exibirAlerta("Data Vazia", "Selecione a data da conta");
            return;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataFormatada = dataSelecionada.format(formatter);

        try {
            
            ContasDinheiro conta = new ContasDinheiro(1, nome, valorSaldoInicial, dataSelecionada);
            contasDinheiroDAO.create(conta);
            
            exibirAlerta("Sucesso", "Conta criada com sucesso!");

            txfContaNome.clear();
            txfSaldoInicial.clear();
            dataSaldo.setValue(null);

        } catch (SQLException e) {
            exibirAlerta("Erro", "Erro ao criar conta: " + e.getMessage());
        }
    }

    private void exibirAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
