package com.example.javafxproject;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import DAO.ProjetoCofrinho;
import DAO.ProjetoCofrinhoDAO;
import DAO.UsuarioAtributoDAO;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
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
    private ComboBox<String> projetoComboBox;

    
    @FXML
    private ProgressBar progressPJ;


    Propriedades propriedades = new Propriedades();

    @FXML
    public void initialize() throws SQLException {
        System.out.println("Inicializando o controlador ProgressoPCController...");
        carregarProjetos();

    }

    public void carregarProjetos() throws SQLException{
       ProjetoCofrinhoDAO projetoDAO = new ProjetoCofrinhoDAO();
        UsuarioAtributoDAO ua = new UsuarioAtributoDAO();
        int user_id = ua.findSessaoId();

        List<ProjetoCofrinho> projetos = projetoDAO.findProjectsByUserId(user_id);

        projetoComboBox.getItems().clear();

        projetoComboBox.getItems().add("Adicionar");

        for (ProjetoCofrinho projeto : projetos) {
            projetoComboBox.getItems().add(projeto.getNome());
        }

        projetoComboBox.setOnAction(event -> {
            String selectedProject = projetoComboBox.getSelectionModel().getSelectedItem();
            if (selectedProject.equals("Adicionar")) {
                try {
                    propriedades.ScreenGuider("tela-projetocofrinho3.fxml", "Adicionar Projeto Cofrinho");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                ProjetoCofrinhoDAO outraInstanciaProjetosDAO = new ProjetoCofrinhoDAO();
                int projectId = outraInstanciaProjetosDAO.findIdByUserIdAndName(selectedProject,user_id);
                System.out.println("ID do projeto selecionado: " + projectId);

                selectedProjectId = projectId;

                ProjetoCofrinho projetoSelecionado = outraInstanciaProjetosDAO.findById(selectedProjectId);

                lblprojeto.setText(projetoSelecionado.getNome());
                lbldescricao.setText(projetoSelecionado.getDescricao());
                lblmeta.setText(String.valueOf(projetoSelecionado.getMeta_quantia()));

                LocalDate prazo = projetoSelecionado.getPrazo();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); 
                String prazoFormatado = prazo.format(formatter);

                lblprazo.setText(prazoFormatado);

                double valorAtingido = outraInstanciaProjetosDAO.calcularValorAtingido(selectedProjectId);  
                double valorRestante = outraInstanciaProjetosDAO.calcularQuantiaRestante(selectedProjectId); 

                lblatingido.setText(String.valueOf(valorAtingido));
                lblrestante.setText(String.valueOf(valorRestante));

                if (projetoSelecionado.isAtivo()==false){
                    lblAtivo.setText("Parabéns! Você já alcançou a sua meta!");
                } else {
                    lblAtivo.setText("Projeto está ativo! Você ainda está no caminho!");
                }

                double total = projetoSelecionado.getMeta_quantia(); 
                double valorAtingidoNormalizado = valorAtingido / total;
                progressPJ.setProgress(valorAtingidoNormalizado);

                double percentual = (valorAtingido*100)/total;

                lblpercentual.setText(String.valueOf(percentual));

            }
        });
    }

   

    @FXML 
    public void adicionarQuantia() {

    }

}
