package com.example.javafxproject;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.example.Propriedades;

import DAO.ContasDinheiro;
import DAO.ContasDinheiroDAO;
import DAO.HistoricoSaldosDAO;
import DAO.ProjetoCofrinho;
import DAO.ProjetoCofrinhoDAO;
import DAO.UsuarioAtributoDAO;
import DAO.UsuarioDAO;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;


public class MenuController {

    private int selectedAccountId = -1;
    private int selectedProjectId = -1;

    @FXML
    private Label lblsaldo;

    @FXML 
    private ComboBox<String> contaComboBox;

    @FXML
    private Label mensagemBemVindo;

    @FXML
    private ComboBox<String> projectComboBox;

    @FXML
    private ProgressBar progressProject;

    private UsuarioAtributoDAO ua;
    private UsuarioDAO userDAO;
    private int idlogado;
    private boolean primeiraConta;

    Propriedades propriedades = new Propriedades();

    public MenuController() {
        ua = new UsuarioAtributoDAO();
        userDAO = new UsuarioDAO();
        try {
            idlogado = ua.findSessaoId();
            primeiraConta = userDAO.verificarContaDoUsuario(idlogado);
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
    }

    public void configurarMensagemBemVindo(String nomeUsuario) throws IOException {
        mensagemBemVindo.setText("Olá! " + nomeUsuario + "!");
        if (primeiraConta == false) {
            propriedades.exibirAlerta("Cadastre sua Primeira Conta","Cadastre sua primeira conta, por favor");
            propriedades.ScreenGuider("tela-contasdinheiro3.fxml", "Cadastrar Conta");

        } 
    }

    public String obterNomeUsuarioLogado() throws SQLException {    
        String nome = userDAO.findUserNameById(idlogado);
        return nome;
    }

    @FXML
    public void initialize() throws SQLException, IOException {
        String nomeUsuario = obterNomeUsuarioLogado(); 
        configurarMensagemBemVindo(nomeUsuario);
        System.out.println("Inicializando o controlador ProgressoPCController...");

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
            } try {
                atualizarSaldoContaSelecionada();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }


     public void atualizarSaldoContaSelecionada() throws SQLException {
        if (selectedAccountId != -1) {
            HistoricoSaldosDAO historicoSaldosDAO = new HistoricoSaldosDAO(); 
            double saldo = historicoSaldosDAO.buscarValorAtivoPorIdConta(selectedAccountId);
            lblsaldo.setText("R$ " + saldo); 
        }
    }

     public void carregarProjetos() throws SQLException{
       ProjetoCofrinhoDAO projetoDAO = new ProjetoCofrinhoDAO();
        UsuarioAtributoDAO ua = new UsuarioAtributoDAO();
        int user_id = ua.findSessaoId();

        List<ProjetoCofrinho> projetos = projetoDAO.findActiveProjects(user_id);

        projectComboBox.getItems().clear();

        projectComboBox.getItems().add("Adicionar");

        for (ProjetoCofrinho projeto : projetos) {
            projectComboBox.getItems().add(projeto.getNome());
        }

        projectComboBox.setOnAction(event -> {
            String selectedProject = projectComboBox.getSelectionModel().getSelectedItem();
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

                double valorAtingido = outraInstanciaProjetosDAO.calcularValorAtingido(selectedProjectId);  
                double total = projetoSelecionado.getMeta_quantia(); 
                double valorAtingidoNormalizado = valorAtingido / total;
                progressProject.setProgress(valorAtingidoNormalizado);

            }
        });
    }


    @FXML
    public void realizarLogout() throws IOException{
        
        propriedades.ScreenGuider("tela-finalizacao.fxml","Login");
    }

    @FXML
    public void adicionarProjeto() throws IOException{
        propriedades.ScreenGuider("tela-projetocofrinho3.fxml","Adcionar um projeto cofrinho");
    }

    @FXML
    public void adicionarReceita() throws IOException{
        propriedades.ScreenGuider("tela-receita1.fxml","Formulario Receita");
    }

    @FXML
    public void adicionarDespesa() throws IOException{
        propriedades.ScreenGuider("tela-despesa1.fxml","Formulario Despesa");
    }

    @FXML 
    public void adicionarConta() throws IOException{
        propriedades.ScreenGuider("tela-contasdinheiro3.fxml","Cadastrar uma nova conta");
    }

    @FXML
    public void adicionarQuantia() throws IOException{
        propriedades.ScreenGuider("tela-relatorioPC1.fxml","Tela Cadastrar Conta");
    }

    @FXML
    public void acompanharProgresso() throws IOException{
        propriedades.ScreenGuider("tela-progressoPC.fxml","Tela Progresso do Projeto Cofrinho");
    }
}
