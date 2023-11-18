package com.example.javafxproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.example.Propriedades;

import DAO.Categoria;
import DAO.CategoriaDAO;
import DAO.UsuarioAtributoDAO;


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

    private Propriedades propriedades = new Propriedades();

    @FXML
    public void initialize() throws SQLException {
        carregarCategorias();
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
                    propriedades.ScreenGuider("tela-personalizar-categoria.fxml","Personizar Categoria");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
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
       propriedades.ScreenGuider("tela-menu2.fxml", "Menu");
    }
}
