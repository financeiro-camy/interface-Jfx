
package com.example.javafxproject;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

import com.example.Propriedades;

import DAO.ProjetoCofrinho;
import DAO.ProjetoCofrinhoDAO;
import DAO.UsuarioAtributoDAO;

public class ProjetoCofrinhoController {

    @FXML
    private TextField nomeTextField;

    @FXML
    private TextField descricaoTextField;

    @FXML
    private DatePicker prazo;

    @FXML
    private TextField metaQuantiaField; 

	@FXML
    private CheckBox ckbAtivo;

    UsuarioAtributoDAO ua = new UsuarioAtributoDAO();
    Propriedades propriedades = new Propriedades();
    
    @FXML
    private void criarProjeto() throws SQLException {
        String nome = nomeTextField.getText();
        String descricao = descricaoTextField.getText();
        LocalDate dataSelecionada = prazo.getValue();
        LocalDate dataCriacao = LocalDate.now();
        double valorMeta = Double.parseDouble(metaQuantiaField.getText());
        Boolean projetoAtivo = ckbAtivo.isSelected();

        try {
           if  (valorMeta < 1) {
                    propriedades.exibirAlerta("Valor inválido", "A meta deve ser maior que zero.");
                } else {
                    
                    UsuarioAtributoDAO ua = new UsuarioAtributoDAO();
                    int idlogado = ua.findSessaoId();
        
                    ProjetoCofrinho projetoCofrinho = new ProjetoCofrinho(idlogado, nome, descricao, dataSelecionada, dataCriacao, valorMeta, projetoAtivo);
                    ProjetoCofrinhoDAO projetoCofrinhoDAO = new ProjetoCofrinhoDAO();
                    projetoCofrinhoDAO.create(projetoCofrinho);
        
                    propriedades.exibirAlerta("Sucesso", "Projeto criado com sucesso!");
                }

            } catch (SQLException e) {
                propriedades.exibirAlerta("Erro", "Erro ao criar projeto: " + e.getMessage());
            }
            
            }

    public void MenuPC() throws IOException{
        propriedades.ScreenGuider("tela-menu3.fxml", "Menu");
    }
}