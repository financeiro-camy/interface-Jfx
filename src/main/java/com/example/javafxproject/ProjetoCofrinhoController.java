
package com.example.javafxproject;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.control.CheckBox;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ProjetoCofrinhoController {

    @FXML
    private TextField nomeTextField;

    @FXML
    private TextField descricaoTextField;

    @FXML
    private DatePicker prazo;

    @FXML
    private TextField metaQuantiaField; // Use TextField para a entrada de meta quantia

	@FXML
    private CheckBox ckbAtivo;

   
    @FXML
    private void criarProjeto(ActionEvent event) {
        String nome = nomeTextField.getText();
        String descricao = descricaoTextField.getText();
        LocalDate dataSelecionada = prazo.getValue();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataFormatada = dataSelecionada.format(formatter);
        double valorMeta = Double.parseDouble(metaQuantiaField.getText());
        Boolean projetoAtivo = ckbAtivo.isSelected();

        System.out.println("Nome: " + nome);
        System.out.println("Descrição: " + descricao);
        System.out.println("Prazo: " + dataFormatada);
        System.out.println("valor a alcançar: " + valorMeta);
        System.out.println("Status: " + projetoAtivo);
           
}
}