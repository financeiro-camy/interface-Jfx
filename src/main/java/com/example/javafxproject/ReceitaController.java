package com.example.javafxproject;

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
import DAO.Periodicidade;
import DAO.PeriodicidadeDAO;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
    
    public class ReceitaController {

    private int selectedCategoryId = -1;
    private int selectedPeriodicityId = -1;
    private int selectedAccountId = -1;
    
        @FXML
        private TextField nomeReceita;
    
        @FXML
        private TextField valorReceita;
    
        @FXML
        private DatePicker dataRecebido;
    
        @FXML
        private CheckBox receitaCKB;
    
        @FXML
        private TextField descricaoReceita;
    
        @FXML 
        private TextField nParcelas;

        @FXML
        private ComboBox<String> categoriaComboBox;

        @FXML 
        private ComboBox<String> contaComboBox;

        @FXML
        private ComboBox<String> periodicidadeComboBox;

        private Propriedades propriedades = new Propriedades();

    @FXML
    public void initialize() throws SQLException {
        carregarCategorias();
        carregarPeriodicidades();
        carregarContas();
    }

    public void carregarCategorias() throws SQLException {
        int user_id = propriedades.getUserId();

        CategoriaDAO categoriaDAO = new CategoriaDAO();
        List<Categoria> categorias = categoriaDAO.findAllbyId(user_id);

        categoriaComboBox.getItems().clear();

        categoriaComboBox.getItems().add("Personalizar");

        for (Categoria categoria : categorias) {
            categoriaComboBox.getItems().add(categoria.getNome());
        }

        categoriaComboBox.setOnAction(event -> {
            String selectedCategory = categoriaComboBox.getSelectionModel().getSelectedItem();
            if (selectedCategory.equals("Personalizar")) {
                try {
                    propriedades.ScreenGuider("tela-personalizar-categoria1.fxml", "Personalizar Categoria");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                int categoryId = categoriaDAO.buscarIdCategoria(selectedCategory, user_id);
                System.out.println("ID da categoria selecionada: " + categoryId);

                selectedCategoryId = categoryId;
            }
        });
    }

    public void carregarPeriodicidades() throws SQLException {
        int user_id = propriedades.getUserId();

        PeriodicidadeDAO periodicidadeDAO = new PeriodicidadeDAO();
        List<Periodicidade> periodicidades = periodicidadeDAO.findDespesasByUsuario(user_id);

        periodicidadeComboBox.getItems().clear();

        periodicidadeComboBox.getItems().add("Personalizar");

        for (Periodicidade periodicidade : periodicidades) {
            periodicidadeComboBox.getItems().add(periodicidade.getNome());
        }

        periodicidadeComboBox.setOnAction(event -> {
            String selectedPeriod = periodicidadeComboBox.getSelectionModel().getSelectedItem();
            if (selectedPeriod.equals("Personalizar")) {
                try {
                    propriedades.ScreenGuider("tela-periodicidade2.fxml", "Personalizar Periodicidade");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                PeriodicidadeDAO outraInstanciaPeriodicidadeDAO = new PeriodicidadeDAO();
                int periodicityId = outraInstanciaPeriodicidadeDAO.buscarIdPeriodicidade(selectedPeriod, user_id);
                System.out.println("ID da periodicidade selecionada: " + periodicityId);

                selectedPeriodicityId = periodicityId;
            }
        });
    }

    public void carregarContas() throws SQLException {
        ContasDinheiroDAO contasDAO = new ContasDinheiroDAO();
        int user_id = propriedades.getUserId();

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
        
    
        @FXML
        public void AdicionarReceita() throws SQLException {
        if (selectedCategoryId != -1 && selectedPeriodicityId != -1 && selectedAccountId != -1) {
            
            String revenueName = nomeReceita.getText();
            String revenueDescription = descricaoReceita.getText();
            LocalDate revenueDate = dataRecebido.getValue();
            double revenueValue = Double.parseDouble(valorReceita.getText());
            Boolean isPaid = receitaCKB.isSelected();
            int revenueParcelas = Integer.parseInt(nParcelas.getText());

            Lancamento lancamento = new Lancamento(selectedCategoryId,selectedAccountId,selectedPeriodicityId,revenueName,revenueDescription,revenueValue,"receita",revenueParcelas,revenueDate,isPaid,revenueDate);
            LancamentoDAO lancamentoDAO = new LancamentoDAO();
            lancamentoDAO.create(lancamento);

            if (lancamento.isPago()==true){
            HistoricoSaldosDAO historicoSaldosDAO = new HistoricoSaldosDAO();
            historicoSaldosDAO.atualizarSaldo(revenueValue, "receita", selectedAccountId);
            }

            propriedades.exibirAlerta("Receita cadastrada com sucesso! ", "Sua receita foi cadastrada com sucesso!");

            limparCampos();

        } else {
            System.out.println("Deu erro, amigão");
        }
        }
    
        @FXML
        public void voltarMenu() throws IOException{
        propriedades.ScreenGuider("tela-menu3.fxml", "Menu");
        }

        public void limparCampos() {
            nomeReceita.clear();
            valorReceita.clear();
            dataRecebido.getEditor().clear();
            receitaCKB.setSelected(false);
            descricaoReceita.clear();
            nParcelas.clear();
            categoriaComboBox.getSelectionModel().clearSelection();
            contaComboBox.getSelectionModel().clearSelection();
            periodicidadeComboBox.getSelectionModel().clearSelection();
        }
        
    }
    
    

