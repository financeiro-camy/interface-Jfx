package com.example.javafxproject;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import DAO.ContasDinheiro;
import DAO.ContasDinheiroDAO;
import DAO.HistoricoSaldos;
import DAO.HistoricoSaldosDAO;
import DAO.UsuarioAtributoDAO;

import java.io.IOException;
import java.sql.SQLException;
import com.example.Propriedades;


public class ContasDinheiroController {

    @FXML
    private TextField txfContaNome;

	@FXML
    private TextField txfSaldoInicial;

    @FXML
    private DatePicker dataSaldo;

    private ContasDinheiroDAO contasDinheiroDAO = new ContasDinheiroDAO();

    Propriedades propriedades = new Propriedades();

    @FXML
    public void criarConta() {
        String nome = txfContaNome.getText();
		double valorSaldoInicial = Double.parseDouble(txfSaldoInicial.getText());
        LocalDate dataSelecionada = dataSaldo.getValue();
        
        if (nome.isEmpty()) {
            propriedades.exibirAlerta("Campo Vazio", "Nome da conta é obrigatório");
            return;
        }

        if (dataSelecionada == null) {
            propriedades.exibirAlerta("Data Vazia", "Selecione a data da conta");
            return;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataFormatada = dataSelecionada.format(formatter);

        try {
            
            UsuarioAtributoDAO ua = new UsuarioAtributoDAO();
            int idlogado = ua.findSessaoId();
            System.out.println(idlogado);    

            ContasDinheiro conta = new ContasDinheiro(idlogado, nome, valorSaldoInicial, dataSelecionada);
            contasDinheiroDAO.create(conta);
            
            propriedades.exibirAlerta("Sucesso", "Conta criada com sucesso!");

            int id_conta = conta.getId();
            HistoricoSaldos historicoSaldos = new HistoricoSaldos(id_conta,dataSelecionada,valorSaldoInicial,true);
            HistoricoSaldosDAO historicoSaldosDAO = new HistoricoSaldosDAO();

            historicoSaldosDAO.create(historicoSaldos);
            

            txfContaNome.clear();
            txfSaldoInicial.clear();
            dataSaldo.setValue(null);

        } catch (SQLException e) {
            propriedades.exibirAlerta("Erro", "Erro ao criar conta: " + e.getMessage());
        }
    }  

    @FXML
    public void voltarMenu() throws IOException{
        propriedades.ScreenGuider("tela-menu3", "Tela Menu");
    }
}
