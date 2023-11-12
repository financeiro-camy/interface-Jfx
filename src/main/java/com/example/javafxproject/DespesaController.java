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
    public void AdicionarDespesa() {
    
        String billName = nomeDespesa.getText();
        String billDescription = descricaoDespesa.getText();

        LocalDate billDeadline = dataVencimento.getValue();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataFormatada = billDeadline.format(formatter);

        LocalDate billPayment =  dataPagamento.getValue();
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataFormatada1 = billPayment.format(formatter);

        double billPrice = Double.parseDouble(valorDespesa.getText());
        Boolean isPaid = despesaCKB.isSelected();

        double billParcelas = Double.parseDouble(numeroParcelas.getText());


        System.out.println("Nome da despesa: " + billName);
        System.out.println("Descrição da despesa: " + billDescription);
        System.out.println("Data de vencimento: " + dataFormatada);
        System.out.println("Data de pagamento: " + dataFormatada1);
        System.out.println("Preço da despesa: " + billPrice);
        System.out.println("Numero de Parcelas:"+ billParcelas);
        System.out.println("Pago: " + isPaid);

    }

    public void VoltarMenu() throws IOException{
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
   


