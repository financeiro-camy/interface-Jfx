package com.example.javafxproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class PersonalizaCategoriaController {
    @FXML
    private TextField categoriaPersonalizada;

    /* Carlos, eu vou deixar vc arrumar esse botão do seu jeito */
    @FXML
    public void criarCategoria(ActionEvent event) {
        System.out.println("Sua periodicidade foi inserida!");
    }
}