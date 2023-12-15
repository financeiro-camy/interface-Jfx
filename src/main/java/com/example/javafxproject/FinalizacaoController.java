package com.example.javafxproject;

import javafx.fxml.FXML;

import java.io.IOException;
import java.sql.SQLException;

import com.example.Propriedades;

import DAO.UsuarioAtributoDAO;

public class FinalizacaoController {
  
    Propriedades propriedades = new Propriedades();

    @FXML
   public void Logout() throws IOException, SQLException {
    
        UsuarioAtributoDAO uaDAO = new UsuarioAtributoDAO();
        int user_id = propriedades.getUserId();
        uaDAO.removerAtributo(user_id);

        propriedades.ScreenGuider("tela-login3.fxml","Tela Login");
        
    }
}
