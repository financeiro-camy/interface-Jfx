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
    
    public class ReceitaController {
    
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
        public void AdicionarReceita() {
        
            String revenueName = nomeReceita.getText();
            String revenueDescription = descricaoReceita.getText();
    
            LocalDate revenueDate = dataRecebido.getValue();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String dataFormatada = revenueDate.format(formatter);
    
    
            double revenueValue = Double.parseDouble(valorReceita.getText());
            Boolean isPaid = receitaCKB.isSelected();
    
            double revenueParcelas = Double.parseDouble(nParcelas.getText());
    
    
            System.out.println("Nome da despesa: " + revenueName);
            System.out.println("Descrição da despesa: " + revenueDescription);
            System.out.println("Data de vencimento: " + dataFormatada);
            System.out.println("Preço da despesa: " + revenueValue);
            System.out.println("Numero de Parcelas:"+ revenueParcelas);
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
       
    
    

