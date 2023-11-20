package com.example.javafxproject;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import com.example.Propriedades;

import DAO.ContasDinheiro;
import DAO.ContasDinheiroDAO;
import DAO.HistoricoSaldosDAO;
import DAO.ProjetoCofrinho;
import DAO.ProjetoCofrinhoDAO;
import DAO.RelatorioPC;
import DAO.RelatorioPCDAO;
import DAO.UsuarioAtributoDAO;

public class RelatorioPCController {

  private int selectedAccountId = -1;
  private int selectedProjectId = -1;


  @FXML
  private TextField txfQuantia;

  @FXML
  private DatePicker dataInsercao;

  @FXML
  private ComboBox<String> contaComboBox;

  @FXML
  private ComboBox<String> projetoComboBox;

  Propriedades propriedades = new Propriedades();
  
  @FXML
    public void initialize() throws SQLException {
        carregarContas();
        carregarProjetos();
    }


  public void carregarContas() throws SQLException {
        ContasDinheiroDAO contasDAO = new ContasDinheiroDAO();
        UsuarioAtributoDAO ua = new UsuarioAtributoDAO();
        int user_id = ua.findSessaoId();

        List<ContasDinheiro> contas = contasDAO.findContasByUsuario(user_id);

        contaComboBox.getItems().clear();

        contaComboBox.getItems().add("Adicionar");

        for (ContasDinheiro conta : contas) {
            contaComboBox.getItems().add(conta.getNome());
        }

        contaComboBox.setOnAction(event -> {
            String selectedAccount = contaComboBox.getSelectionModel().getSelectedItem();
            if (selectedAccount.equals("Adicionar")) {
                try {
                    propriedades.ScreenGuider("tela-contasdinheiro3.fxml", "Adicionar Conta");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                ContasDinheiroDAO outraInstanciaContasDAO = new ContasDinheiroDAO();
                int accountId = outraInstanciaContasDAO.buscarIdConta(selectedAccount, user_id);
                System.out.println("ID da conta selecionada: " + accountId);

                selectedAccountId = accountId;
            }
        });
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
            }
        });
    }


  @FXML
  public void InserirValor() throws SQLException {
 if (selectedAccountId != -1 && selectedProjectId != -1) { 
	  
    double valorInsercao = Double.parseDouble(txfQuantia.getText());
    LocalDate dataTransacao = dataInsercao.getValue();
  
        
    System.out.println("Valor a ser inserido: " + valorInsercao);
    System.out.println("Data de inserção: " + dataInsercao);
    System.out.println("Conta: " + selectedAccountId);
    System.out.println("Projeto selecionado:"+selectedProjectId);

    RelatorioPC relatorio = new RelatorioPC(selectedProjectId,selectedAccountId,valorInsercao,dataTransacao);
    RelatorioPCDAO relatorioPCDAO = new RelatorioPCDAO();
    relatorioPCDAO.create(relatorio);

    System.out.println(selectedAccountId);
    HistoricoSaldosDAO historicoSaldosDAO = new HistoricoSaldosDAO();
    historicoSaldosDAO.atualizarSaldo(valorInsercao, "projeto", selectedAccountId);

    propriedades.exibirAlerta("Inserção realiza do sucesso!", "Sua inserçaõ foi relalizada com sucesso.");
    //limparCampos();

  } else {
    System.out.println("Deu erro, amigão");
}       
  }

  @FXML
  public void voltarMenu() throws IOException{
    propriedades.ScreenGuider("tela-menu3.fxml","Menu");
  }

  public void limparCampos() {
    txfQuantia.clear();
    dataInsercao.setValue(null);
    contaComboBox.getSelectionModel().clearSelection();
    projetoComboBox.getSelectionModel().clearSelection();
}
}

