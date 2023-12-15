package com.example.javafxproject;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;
import com.example.Propriedades;

import DAO.ProjetoCofrinhoDAO;
import DAO.UsuarioAtributoDAO;

public class BuscarRelatorioController {

    UsuarioAtributoDAO uaDAO = new UsuarioAtributoDAO();
    ProjetoCofrinhoDAO pcDAO = new ProjetoCofrinhoDAO();
    Propriedades propriedades = new Propriedades();
    
    @FXML
    private TextField searchedProject;

    @FXML
    public void realizarPesquisa() throws SQLException, IOException {
        int user_id = propriedades.getUserId();
        String search = searchedProject.getText();
        System.out.println(search);

        int searchedID = pcDAO.findIdByUserIdAndName(search, user_id);
        System.out.println("O id do projeto pesquisado foi: " + searchedID);

        if (searchedID == -1) {
        propriedades.exibirAlerta("Resultado não encontrado", "Nenhum projeto com o nome digitado foi encontrado. Por favor verifique se há algum erro de digitação.");
        } else {
            uaDAO.adicionarAtributoAlternative(user_id, "Projeto pesquisado", searchedID);
            propriedades.ScreenGuider("tela-progressoPC.fxml", "Tela do Relatório");
        }
}

    @FXML 
    public void voltarMenu() throws IOException, SQLException{

        propriedades.ScreenGuider("tela-menu3.fxml", "Menu");

    }

    @FXML
    public void addProject() throws IOException{
        propriedades.ScreenGuider("tela-projetocofrinho3.fxml", "Tela criar novo projeto");
    }

}
