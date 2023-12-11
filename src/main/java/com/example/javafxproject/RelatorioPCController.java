package com.example.javafxproject;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import com.example.Propriedades;

import DAO.Categoria;
import DAO.CategoriaDAO;
import DAO.ContasDinheiro;
import DAO.ContasDinheiroDAO;
import DAO.HistoricoSaldosDAO;
import DAO.Lancamento;
import DAO.LancamentoDAO;
import DAO.PeriodicidadeDAO;
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

        List<ProjetoCofrinho> projetos = projetoDAO.findActiveProjects(user_id);

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
     UsuarioAtributoDAO ua = new UsuarioAtributoDAO();
        int user_id = ua.findSessaoId();

 if (selectedAccountId != -1 && selectedProjectId != -1) { 
	  
    double valorInsercao = Double.parseDouble(txfQuantia.getText());
    LocalDate dataTransacao = dataInsercao.getValue();
  
        
    System.out.println("Valor a ser inserido: " + valorInsercao);
    System.out.println("Data de inserção: " + dataInsercao);
    System.out.println("Conta: " + selectedAccountId);
    System.out.println("Projeto selecionado:"+selectedProjectId);

    HistoricoSaldosDAO historicoSaldosDAO = new HistoricoSaldosDAO();
    double saldo = historicoSaldosDAO.buscarValorAtivoPorIdConta(selectedAccountId);

    if (valorInsercao <= saldo) {
       /* RelatorioPC relatorio = new RelatorioPC(selectedProjectId, selectedAccountId, valorInsercao, dataTransacao);
        RelatorioPCDAO relatorioPCDAO = new RelatorioPCDAO();
        relatorioPCDAO.create(relatorio); */

       // historicoSaldosDAO.atualizarSaldo(valorInsercao, "projeto", selectedAccountId);

       double insertedValue = historicoSaldosDAO.insertedValueProject(valorInsercao,selectedProjectId,selectedAccountId);
       historicoSaldosDAO.atualizarSaldoProjetos(valorInsercao, selectedProjectId,selectedAccountId,dataTransacao); 

        CategoriaDAO categoriaDAO = new CategoriaDAO();
        int category = categoriaDAO.buscarIdCategoria("Projeto", user_id);

        PeriodicidadeDAO periodicidadeDAO = new PeriodicidadeDAO();
        int periodicity = periodicidadeDAO.buscarIdPeriodicidade("Eventualmente", user_id);
        System.out.println("Periodicidade: " + periodicity);
        ProjetoCofrinhoDAO projetoCofrinhoDAO = new ProjetoCofrinhoDAO();
        String nomeProjeto = "Projeto Cofrinho: " + projetoCofrinhoDAO.findProjectName(selectedProjectId);

        Lancamento lancamento = new Lancamento(category,selectedAccountId, periodicity,nomeProjeto,"depósito do projeto",insertedValue,"projeto",1,dataTransacao,true,dataTransacao); 
        LancamentoDAO lancamentoDAO = new LancamentoDAO();
        lancamentoDAO.create(lancamento);

        propriedades.exibirAlerta("Inserção realizada com sucesso!", "Sua inserção foi realizada com sucesso.");
        //limparCampos();
    } else {
        propriedades.exibirAlerta("Saldo insuficiente", "Esta conta não possui saldo suficiente para realizar esta inserção. Por favor, tente novamente com outra conta.");
        limparCampos();
    }
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

