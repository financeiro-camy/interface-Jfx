package com.example.javafxproject;

import javafx.fxml.FXML;

import java.io.IOException;
import java.sql.SQLException;

import com.example.Propriedades;

import DAO.UsuarioAtributoDAO;

public class FinalizacaoController {
  

    @FXML
   public void Logout() throws IOException, SQLException {
    
        UsuarioAtributoDAO ua = new UsuarioAtributoDAO();
        ua.removerAtributo(1);

        Propriedades propriedades = new Propriedades();
        propriedades.ScreenGuider("tela-login3.fxml","Tela Login");
        
    }
}
