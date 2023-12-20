package com.example.javafxproject;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.example.Propriedades;

import DAO.ProjetoCofrinho;
import DAO.ProjetoCofrinhoDAO;
import DAO.UsuarioAtributoDAO;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ExpandirMetaController {
    @FXML
    private TextField txfNewGoal;

    @FXML 
    private DatePicker newDateline;

    @FXML 
    private Label lblProjectUpdate;

    @FXML
    private Label lblCurrentGoal;

    @FXML 
    private Label lblCurrentDate;

    Propriedades propriedades = new Propriedades(); 

     @FXML
    public void initialize() throws SQLException {
      if ( getProject() != -1) {
       loadProjectValues();
    } else{
        propriedades.exibirAlerta("Nenhum projeto encontrado", "Erro ao encontrar projeto");
    }
}

    @FXML
    public void gotoReport() throws IOException{
        propriedades.ScreenGuider("tela-progressoPC.fxml", "Go back to report screen");
    }

    public int getProject() throws SQLException{

        UsuarioAtributoDAO uaDAO = new UsuarioAtributoDAO();
        int user_id = propriedades.getUserId();
        String searchedProject = uaDAO.obterValorAtributo(user_id, "Projeto pesquisado");
        int projectId = Integer.parseInt(searchedProject);
        System.out.println("ID do projeto selecionado: " + projectId);

        return projectId;
     }

     public void loadProjectValues() throws SQLException{

    int selectedProjectId = getProject();   

    ProjetoCofrinhoDAO pcDAO = new ProjetoCofrinhoDAO();
    ProjetoCofrinho projetoSelecionado = pcDAO.findById(selectedProjectId);
    lblProjectUpdate.setText(projetoSelecionado.getNome());
    lblCurrentGoal.setText(String.format("R$ %.2f", projetoSelecionado.getMeta_quantia()));

    LocalDate dateline = projetoSelecionado.getPrazo();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); 
    String prazoFormatado = dateline.format(formatter);
    lblCurrentDate.setText(prazoFormatado);
  }

  @FXML
  public void updateGoal() throws SQLException, IOException {
      ProjetoCofrinhoDAO pcDAO = new ProjetoCofrinhoDAO();
  
      int pctoUpdate = getProject();
      double newGoal = Double.parseDouble(txfNewGoal.getText());
      LocalDate updatedDate = newDateline.getValue();
  
      try {
          pcDAO.expandirMetaEAtualizarAtivo(pctoUpdate, newGoal, updatedDate);
          propriedades.exibirAlerta("Projeto atualizado com sucesso!", "A sua meta foi atualizada com sucesso!");
          propriedades.ScreenGuider("tela-progressoPC.fxml", "Back to report screen");
      } catch (Exception e) {
          propriedades.exibirAlerta("Erro ao atualizar o projeto", "Ocorreu um erro ao tentar atualizar o projeto. Por favor, tente novamente.");
          System.err.println("Erro ao atualizar o projeto: " + e.getMessage());
      }
  }
}
