package com.example.javafxproject;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
public class LancamentoController {
    @FXML
    private TextField idCategoriaField;

    @FXML
    private TextField idContaField;

    // Adicione mais campos FXML para outros atributos do lançamento

    @FXML
    private void salvarLancamento() {
        // Implemente a lógica para salvar o lançamento aqui
        System.out.println("Lançamento salvo!");
    }

    @FXML
    private void cancelar() {
        // Implemente a lógica para cancelar aqui
        System.out.println("Operação cancelada.");
    }
}