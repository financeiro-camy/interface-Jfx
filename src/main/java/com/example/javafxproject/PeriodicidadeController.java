package com.example.javafxproject;

import java.sql.SQLException;

import DAO.Periodicidade;
import DAO.PeriodicidadeDAO;
import DAO.UsuarioAtributoDAO;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class PeriodicidadeController {
    @FXML
    private TextField periodoPersonalizado;

    
    @FXML
    public void periodoPersonalizado() throws SQLException {
        
        String nomePeriodo = periodoPersonalizado.getText();

        System.out.println("Sua periodicidade foi inserida!");
        System.out.println(nomePeriodo);

        UsuarioAtributoDAO ua = new UsuarioAtributoDAO();
        int idlogado = ua.findSessaoId();

        Periodicidade periodicidade = new Periodicidade(idlogado,nomePeriodo);
        PeriodicidadeDAO periodicidadeDAO = new PeriodicidadeDAO();

        periodicidadeDAO.create(periodicidade);
        

    }
}
