package com.example.javafxproject;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.example.Propriedades;

import DAO.ContasDinheiro;
import DAO.ContasDinheiroDAO;
import DAO.HistoricoSaldosDAO;
import DAO.UsuarioAtributoDAO;
import DAO.UsuarioDAO;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;


public class MenuController {

    private int selectedAccountId = -1;

    @FXML
    private Label lblsaldo;

    @FXML 
    private ComboBox<String> contaComboBox;

    @FXML
    private Label mensagemBemVindo;

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
        carregarContas();

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
}
