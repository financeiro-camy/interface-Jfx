package com.example.javafxproject;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RelatorioPCController {

	@FXML
    private TextField txfQuantia;

    @FXML
    private DatePicker dataInsercao;


    @FXML
    public void InserirValor() {
        
		double valorInsercao = Double.parseDouble(txfQuantia.getText());
        LocalDate dataSelecionada = dataInsercao.getValue();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataFormatada = dataSelecionada.format(formatter);
        
        System.out.println("Saldo Inicial: " + valorInsercao);
        System.out.println("Data de inserção: " + dataInsercao);
           
}
}

