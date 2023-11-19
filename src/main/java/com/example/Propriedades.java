package com.example;

import java.io.IOException;
import java.sql.SQLException;

import com.example.javafxproject.MainController;

import DAO.Categoria;
import DAO.CategoriaDAO;
import DAO.Periodicidade;
import DAO.PeriodicidadeDAO;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Propriedades{

  public void ScreenGuider(String nomeTela, String tituloTela) throws IOException{
    FXMLLoader loader = new FXMLLoader(MainController.class.getResource(nomeTela));
            Scene scene = new Scene(loader.load());
            Stage stage = new Stage();
            stage.setTitle(tituloTela);
            stage.setScene(scene);
            stage.sizeToScene();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
  }

  public void exibirAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    public void setoresPadroes(int id_usuario) throws SQLException {
        System.out.println("ID do Usuário: " + id_usuario);
    
        /*Categoria categoria1 = new Categoria(id_usuario, "Alimentação");
        Categoria categoria2 = new Categoria(id_usuario, "Transporte");
        Categoria categoria3 = new Categoria(id_usuario, "Lazer");
        Categoria categoria4 = new Categoria(id_usuario, "Moradia");
        Categoria categoria5 = new Categoria(id_usuario, "Educação");
    
        CategoriaDAO categoriaDAO = new CategoriaDAO();
    
        categoriaDAO.create(categoria1);
        categoriaDAO.create(categoria2);
        categoriaDAO.create(categoria3);
        categoriaDAO.create(categoria4);
        categoriaDAO.create(categoria5);*/

        Periodicidade periodicidade1 = new Periodicidade(id_usuario,"Eventualmente");
        Periodicidade periodicidade2 = new Periodicidade(id_usuario,"Semanalmente");
        Periodicidade periodicidade3 = new Periodicidade(id_usuario,"Mensalmente");
        Periodicidade periodicidade4 = new Periodicidade(id_usuario,"Trimestralmente");
        Periodicidade periodicidade5 = new Periodicidade(id_usuario,"Semestralmente");
        Periodicidade periodicidade6 = new Periodicidade(id_usuario,"Anualmente");

        PeriodicidadeDAO periodicidadeDAO = new PeriodicidadeDAO();

        periodicidadeDAO.create(periodicidade1);
        periodicidadeDAO.create(periodicidade2);
        periodicidadeDAO.create(periodicidade3);
        periodicidadeDAO.create(periodicidade4);
        periodicidadeDAO.create(periodicidade5);
        periodicidadeDAO.create(periodicidade6);



    }

}