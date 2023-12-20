package com.example.javafxproject;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import DAO.ProjetoCofrinho;
import DAO.ProjetoCofrinhoDAO;
import DAO.UsuarioAtributoDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

import com.example.Propriedades;

public class ProgressoPCController {
    private int selectedProjectId = -1;

    @FXML 
    private Label lblprojeto;

    @FXML 
    private Label lbldescricao;

    @FXML
    private Label lblmeta;

    @FXML
    private Label lblprazo;

    @FXML
    private Label lblatingido; 

    @FXML 
    private Label lblrestante;

    @FXML
    private Label lblpercentual;

    @FXML
    private Label lblAtivo;

    @FXML
    private ProgressBar progressPJ;

    @FXML
    private Button buttonAddValue;

    Propriedades propriedades = new Propriedades();

    @FXML
    public void initialize() throws SQLException {

        System.out.println("Inicializando o controlador ProgressoPCController...");
        if ( getProject() != -1) {
            projectReport();
            controlButtonVisibility();
        } else{
            propriedades.exibirAlerta("Nenhum projeto encontrado", "Erro ao encontrar projeto");
        }
     }

     public int getProject() throws SQLException{

        UsuarioAtributoDAO uaDAO = new UsuarioAtributoDAO();
        int user_id = propriedades.getUserId();
        String searchedProject = uaDAO.obterValorAtributo(user_id, "Projeto pesquisado");
        int projectId = Integer.parseInt(searchedProject);
        System.out.println("ID do projeto selecionado: " + projectId);

        return projectId;
     }

     private boolean isMetaAtingida() throws SQLException {

        ProjetoCofrinhoDAO pcDAO = new ProjetoCofrinhoDAO();
        selectedProjectId = getProject();
        ProjetoCofrinho cofrinho = pcDAO.findById(selectedProjectId);
            
           return cofrinho.isAtivo(); 
        }

    private void controlButtonVisibility() throws SQLException {
        boolean activeProject = isMetaAtingida();

        if (activeProject == false) {
            buttonAddValue.setVisible(false);
        } else {
            buttonAddValue.setVisible(true);
        }
    }

    public void projectReport() throws SQLException{
        ProjetoCofrinhoDAO outraInstanciaProjetosDAO = new ProjetoCofrinhoDAO();
            
        selectedProjectId = getProject();

        ProjetoCofrinho projetoSelecionado = outraInstanciaProjetosDAO.findById(selectedProjectId);

        lblprojeto.setText(projetoSelecionado.getNome());
        lbldescricao.setText(projetoSelecionado.getDescricao());
        lblmeta.setText(String.format("R$ %.2f", projetoSelecionado.getMeta_quantia()));  

        LocalDate prazo = projetoSelecionado.getPrazo();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); 
        String prazoFormatado = prazo.format(formatter);

        lblprazo.setText(prazoFormatado);

        double valorAtingido = outraInstanciaProjetosDAO.calcularValorAtingido(selectedProjectId);  
        double valorRestante = outraInstanciaProjetosDAO.calcularQuantiaRestante(selectedProjectId); 

        lblatingido.setText(String.format("R$ %.2f", valorAtingido)); 
        lblrestante.setText(String.format("R$ %.2f", valorRestante)); 

        if (projetoSelecionado.isAtivo()==false){
            lblAtivo.setText("Parabéns! Você já alcançou a sua meta!");
        } else {
            lblAtivo.setText("Projeto está ativo!");
        }

        double total = projetoSelecionado.getMeta_quantia(); 
        double valorAtingidoNormalizado = valorAtingido / total;
        progressPJ.setProgress(valorAtingidoNormalizado);

        double percentual = (valorAtingido*100)/total;

        String percentualFormatado = String.format("%.2f%%", percentual);
        lblpercentual.setText(percentualFormatado);
    }
   
    @FXML
    public void updateProject() throws IOException{

        propriedades.ScreenGuider("tela-expandirmeta.fxml", "Expandir a meta");
    }
    
    @FXML  
    public void adicionarQuantia() throws IOException {

        propriedades.ScreenGuider("tela-relatorioPC1.fxml", "Tela para adicionar quantia");

    }

    @FXML 
    public void voltarMenu() throws IOException, SQLException{

        UsuarioAtributoDAO uaDAO = new UsuarioAtributoDAO();
        int user_id = propriedades.getUserId();
        int removerID = uaDAO.obterIdAtributo(user_id,"Projeto pesquisado");
        uaDAO.removerAtributo(removerID);

        propriedades.ScreenGuider("tela-menu3.fxml","Tela Menu");
    }
}
