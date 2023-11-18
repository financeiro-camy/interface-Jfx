package com.example.javafxproject;

import java.sql.SQLException;

import DAO.Categoria;
import DAO.CategoriaDAO;
import DAO.UsuarioAtributoDAO;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class PersonalizaCategoriaController {
    @FXML
    private TextField categoriaPersonalizada;


    @FXML
    public void criarCategoria() throws SQLException {

        String nomeCategoria = categoriaPersonalizada.getText();

        UsuarioAtributoDAO ua = new UsuarioAtributoDAO();
        int id_usuario = ua.findSessaoId();
        System.out.println(id_usuario);

        Categoria categoria = new Categoria(id_usuario,nomeCategoria);
        CategoriaDAO categoriaDAO = new CategoriaDAO();

        categoriaDAO.create(categoria);
    }
}