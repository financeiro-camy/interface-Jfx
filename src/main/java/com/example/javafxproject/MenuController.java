package com.example.javafxproject;

import java.io.IOException;
import java.sql.SQLException;

import com.example.Propriedades;

import DAO.UsuarioAtributoDAO;
import DAO.UsuarioDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MenuController {

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
        mensagemBemVindo.setText("Seja bem-vindo, " + nomeUsuario + "!");
        if (primeiraConta == false) {
            propriedades.exibirAlerta("Cadastre sua Primeira Conta","Cadastre sua primeira conta, por favor");
            propriedades.ScreenGuider("tela-contasdinheiro2.fxml", "Cadastrar Conta");

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
    }
}
