package com.example.javafxproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import DAO.Categoria;
import DAO.CategoriaDAO;


public class DespesaController {

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
    public void initialize() {
        carregarCategorias();
    }

    public void carregarCategorias() {
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        List<Categoria> categorias = categoriaDAO.findAll(1);

        categoriaComboBox.getItems().clear();

        for (Categoria categoria : categorias) {
            categoriaComboBox.getItems().add(categoria.getNome());
        }
    }

    @FXML
    public void AdicionarDespesa() {
        String billName = nomeDespesa.getText();
        String billDescription = descricaoDespesa.getText();
        LocalDate billDeadline = dataVencimento.getValue();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataFormatada = billDeadline.format(formatter);
        LocalDate billPayment = dataPagamento.getValue();
        String dataFormatada1 = billPayment != null ? billPayment.format(formatter) : "N/A";
        double billPrice = Double.parseDouble(valorDespesa.getText());
        Boolean isPaid = despesaCKB.isSelected();
        double billParcelas = Double.parseDouble(numeroParcelas.getText());

        String selectedCategoria = categoriaComboBox.getValue();
        System.out.println("Categoria selecionada: " + selectedCategoria);

        System.out.println("Nome da despesa: " + billName);
        System.out.println("Descrição da despesa: " + billDescription);
        System.out.println("Data de vencimento: " + dataFormatada);
        System.out.println("Data de pagamento: " + dataFormatada1);
        System.out.println("Preço da despesa: " + billPrice);
        System.out.println("Numero de Parcelas:" + billParcelas);
        System.out.println("Pago: " + isPaid);
    }

    @FXML
    public void VoltarMenu() throws IOException {
        FXMLLoader loader = new FXMLLoader(MainController.class.getResource("tela-menu2.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = new Stage();
        stage.setTitle("Menu");
        stage.setScene(scene);
        stage.sizeToScene();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }
}
