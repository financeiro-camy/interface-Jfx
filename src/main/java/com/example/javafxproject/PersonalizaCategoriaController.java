package com.example.javafxproject;

import java.io.IOException;
import java.sql.SQLException;

import com.example.Propriedades;

import DAO.Categoria;
import DAO.CategoriaDAO;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class PersonalizaCategoriaController {
    @FXML
    private TextField categoriaPersonalizada;

Propriedades propriedades = new Propriedades();
    @FXML
    public void criarCategoria() throws SQLException {

        String nomeCategoria = categoriaPersonalizada.getText();

        int id_usuario = propriedades.getUserId();
        System.out.println(id_usuario);

        Categoria categoria = new Categoria(id_usuario,nomeCategoria);
        CategoriaDAO categoriaDAO = new CategoriaDAO();

        categoriaDAO.create(categoria);
    }

    @FXML
    public void voltarMenu() throws IOException{
        propriedades.ScreenGuider("tela-menu3.fxml", "Menu");
    }
}