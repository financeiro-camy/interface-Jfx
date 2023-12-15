package com.example.javafxproject;

import java.sql.SQLException;

import com.example.Propriedades;

import DAO.Periodicidade;
import DAO.PeriodicidadeDAO;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class PeriodicidadeController {
    @FXML
    private TextField periodoPersonalizado;

    Propriedades propriedades = new Propriedades();

    @FXML
    public void periodoPersonalizado() throws SQLException {
        
        String nomePeriodo = periodoPersonalizado.getText();

        System.out.println("Sua periodicidade foi inserida!");
        System.out.println(nomePeriodo);

        int idlogado = propriedades.getUserId();

        Periodicidade periodicidade = new Periodicidade(idlogado,nomePeriodo);
        PeriodicidadeDAO periodicidadeDAO = new PeriodicidadeDAO();

        periodicidadeDAO.create(periodicidade);
        

    }
}
