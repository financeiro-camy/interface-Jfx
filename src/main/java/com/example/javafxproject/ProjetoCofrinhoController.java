// package com.example.javafxproject;

// import javafx.fxml.FXML;
// import javafx.scene.control.*;
// import javafx.event.ActionEvent;
// import javafx.scene.control.DatePicker;
// import java.time.LocalDate;
// import java.time.format.DateTimeFormatter;

// /* MODIFICAR ESSE CONTROLLER POIS ELE ESTÁ COM INFORMAÇÕES DE OUTRO CONTROLLER */

// public class ProjetoCofrinhoController {

//     @FXML
//     private TextField nomeTextField;
    
//     @FXML
//     private TextField descricaoTextField;
    
//     /*@FXML
//     private Button meuBotao;*/
    
//     @FXML
//     private DatePicker prazo;

    
//     @FXML
//     private double metaQuantiaField;

//     @FXML
//     private CheckBox ativo;

//     @FXML
//     private void criarProjeto(ActionEvent event) {
    

//         String nome = nomeTextField.getText();
//         String descricao = descricaoTextField.getText();
//         LocalDate dataSelecionada = prazo.getValue();
//         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

//         String dataFormatada = dataSelecionada.format(formatter);

//          try {
//         System.out.println("Nome: " + nome);
//         System.out.println("Descrição: " + descricao);
//         System.out.println("Prazo: " + dataFormatada);

//         System.out.println("Valor da meta: " + valorMeta);
//     } catch (NumberFormatException e) {
//         System.out.println("Valor da meta inválido.");
//     }

//         Boolean ativoProjeto = ativo.isSelected();

//         System.out.println("Nome: " + nome);
//         System.out.println("Descrição: " + descricao);
//         System.out.println("Prazo: " + dataFormatada);
//         System.out.println("Status do Projeto:" + ativoProjeto);


//     }
// }

package com.example.javafxproject;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;

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

           
}
}