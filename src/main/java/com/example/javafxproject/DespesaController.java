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
import DAO.Lancamento;
import DAO.LancamentoDAO;
import DAO.Periodicidade;
import DAO.PeriodicidadeDAO;
import DAO.UsuarioAtributoDAO;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class DespesaController {

    private int selectedCategoryId = -1;
    private int selectedPeriodicityId = -1;
    private int selectedAccountId = -1;

    @FXML
    private TextField nomeDespesa;

    @FXML
    private TextField valorDespesa;

    @FXML
    private DatePicker dataPagamento;

    @FXML
    private DatePicker dataVencimento;

    @FXML
    private CheckBox despesaCKB;

    @FXML
    private TextField descricaoDespesa;

    @FXML
    private TextField numeroParcelas;

    @FXML
    private ComboBox<String> categoriaComboBox;

    @FXML
    private ComboBox<String> periodicidadeComboBox;

    @FXML
    private ComboBox<String> contaComboBox;

    private Propriedades propriedades = new Propriedades();

    @FXML
    public void initialize() throws SQLException {
        carregarCategorias();
        carregarPeriodicidades();
        carregarContas();
    }

    public void carregarCategorias() throws SQLException {
        UsuarioAtributoDAO ua = new UsuarioAtributoDAO();
        int user_id = ua.findSessaoId();

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
        UsuarioAtributoDAO ua = new UsuarioAtributoDAO();
        int user_id = ua.findSessaoId();

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

    @FXML
    public void AdicionarDespesa() throws SQLException {
        if (selectedCategoryId != -1 && selectedPeriodicityId != -1 && selectedAccountId != -1) {

            String billName = nomeDespesa.getText();
            String billDescription = descricaoDespesa.getText();
            LocalDate billDeadline = dataVencimento.getValue();
            LocalDate billPayment = dataPagamento.getValue();
            double billPrice = Double.parseDouble(valorDespesa.getText());
            Boolean isPaid = despesaCKB.isSelected();
            int billParcelas = Integer.parseInt(numeroParcelas.getText());

            System.out.println("Nome da despesa: " + billName);
            System.out.println("Descrição da despesa: " + billDescription);
            System.out.println("Data de vencimento: " + billDeadline);
            System.out.println("Data de pagamento: " + billPayment);
            System.out.println("Preço da despesa: " + billPrice);
            System.out.println("Numero de Parcelas:" + billParcelas);
            System.out.println("Pago: " + isPaid);
            System.out.println("ID da categoria selecionada: " + selectedCategoryId);
            System.out.println("ID da periodicidade selecionada: " + selectedPeriodicityId);
            System.out.println("ID da conta selecionada: " + selectedAccountId);

            Lancamento lancamento = new Lancamento(selectedCategoryId,selectedAccountId,selectedPeriodicityId,billName,billDescription,billPrice,"despesa",billParcelas,billDeadline,isPaid,billPayment);
            LancamentoDAO lancamentoDAO = new LancamentoDAO();
            lancamentoDAO.create(lancamento);

            propriedades.exibirAlerta("Despesa cadastrada com sucesso!", "Sua despesa foi cadastrada com sucesso!");
            limparAtributosDespesa();

        } else {
            System.out.println("Deu erro, amigão");
        }
    }

    @FXML
    public void VoltarMenu() throws IOException {
        propriedades.ScreenGuider("tela-menu3.fxml", "Menu");
    }

    public void limparAtributosDespesa() {
        nomeDespesa.clear();
        valorDespesa.clear();
        dataPagamento.getEditor().clear();
        dataVencimento.getEditor().clear();
        despesaCKB.setSelected(false); 
        descricaoDespesa.clear();
        numeroParcelas.clear();
        categoriaComboBox.getSelectionModel().clearSelection(); 
        periodicidadeComboBox.getSelectionModel().clearSelection();
        contaComboBox.getSelectionModel().clearSelection();
    }
    

}
