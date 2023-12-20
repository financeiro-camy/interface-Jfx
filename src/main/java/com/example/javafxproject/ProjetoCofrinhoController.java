
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

    UsuarioAtributoDAO ua = new UsuarioAtributoDAO();
    Propriedades propriedades = new Propriedades();
    
    /* @FXML
    private void criarProjeto() throws SQLException {
        String nome = nomeTextField.getText();
        String descricao = descricaoTextField.getText();
        LocalDate dataSelecionada = prazo.getValue();
        LocalDate dataCriacao = LocalDate.now();
        double valorMeta = Double.parseDouble(metaQuantiaField.getText());

        try {
           if  (valorMeta < 1) {
                    propriedades.exibirAlerta("Valor inválido", "A meta deve ser maior que zero.");
                } else {
                    
                    UsuarioAtributoDAO ua = new UsuarioAtributoDAO();
                    int idlogado = ua.findSessaoId();
        
                    ProjetoCofrinho projetoCofrinho = new ProjetoCofrinho(idlogado, nome, descricao, dataSelecionada, dataCriacao, valorMeta, true);
                    ProjetoCofrinhoDAO projetoCofrinhoDAO = new ProjetoCofrinhoDAO();
                    projetoCofrinhoDAO.create(projetoCofrinho);
        
                    propriedades.exibirAlerta("Sucesso", "Projeto criado com sucesso!");
                }

            } catch (SQLException e) {
                propriedades.exibirAlerta("Erro", "Erro ao criar projeto: " + e.getMessage());
            }
            
            }
*/

@FXML
    private void criarProjeto() throws SQLException {
        String nome = nomeTextField.getText();
        String descricao = descricaoTextField.getText();
        LocalDate dataSelecionada = prazo.getValue();
        LocalDate dataCriacao = LocalDate.now();
        double valorMeta = Double.parseDouble(metaQuantiaField.getText());

        try {
            if (valorMeta < 1) {
                propriedades.exibirAlerta("Valor inválido", "A meta deve ser maior que zero.");
            } else {

                int idlogado = propriedades.getUserId();

                ProjetoCofrinhoDAO projetoCofrinhoDAO = new ProjetoCofrinhoDAO();

                int idProjetoExistente = projetoCofrinhoDAO.findIdByUserIdAndName(nome, idlogado);
                if (idProjetoExistente != -1) {
                    propriedades.exibirAlerta("Erro", "Já existe um projeto com esse nome para este usuário.");
                } else {

                ProjetoCofrinho projetoCofrinho = new ProjetoCofrinho(idlogado, nome, descricao, dataSelecionada, dataCriacao, valorMeta, true);
                projetoCofrinhoDAO.create(projetoCofrinho);
                propriedades.exibirAlerta("Sucesso", "Projeto criado com sucesso!");
                }
            }

        } catch (SQLException e) {
            propriedades.exibirAlerta("Erro", "Erro ao criar projeto: " + e.getMessage());
        }
    }
    
    public void MenuPC() throws IOException{
        propriedades.ScreenGuider("tela-buscarrelatorio.fxml", "Buscar relatório");
    }
}