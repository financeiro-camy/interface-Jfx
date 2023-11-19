package com.example.javafxproject;

import java.io.IOException;
import java.sql.SQLException;

import com.example.Propriedades;

import DAO.UsuarioAtributoDAO;
import DAO.UsuarioDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

//PARA CASO O BOTÃO PRIVACIDADE SEJA USADO
// SERÁ NECESSÁRIO INCLUIR A CLÁUSULA DE 
// onAction #abrirPaginaWeb NO FXML

/*import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;*/

public class MenuController {

    //ADICIONAR LÓGICA DESTES BOTÕES
    
    public void verProjetos(){

    }

    public void adicionarLancamento(){

    }

    // LÓGICA PARA ADICIONAR O BOTÃO QUE ABRE SITE (TALVEZ SERÁ UMA IDEIA ABANDONADA)
    /*public void abrirPaginaWeb() {
        String url = "https://www.exemplo.com"; // A URL NESTE CASO SERIA UM ARQUIVO FEITO PELOS SITES GOOGLE

        try {
            // Cria um objeto URI a partir da string da URL
            URI uri = new URI(url);

            // Verifica se o suporte à área de trabalho é suportado no ambiente atual
            if (Desktop.isDesktopSupported()) {
                Desktop desktop = Desktop.getDesktop();
                // Verifica se a ação de browse (navegação) é suportada
                if (desktop.isSupported(Desktop.Action.BROWSE)) {
                    // Abre a página web
                    desktop.browse(uri);
                }
            }
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
            // Tratar exceções
        }
    }
} */


    // AS TRÊS IMAGENS: usuariofoto, notificacaofoto, configuracaofoto PRECISAM SER 
    // ANEXADAS COMO PUBLIC VOID POIS REALIZAM AÇÕES

    // LÓGICA PARA A FOTO USUARIOFOTO, lembrando que deverá ser alterado no fxml 
    //que a onAction deverá ser abrirConfigurações:

    /*public void abrirConfiguracoes() {
        // Criando o layout e configurando a janela de configurações...

        // Criando uma nova janela (Stage) para as configurações
        Stage configuracoesStage = new Stage();
        configuracoesStage.initModality(Modality.APPLICATION_MODAL); // Impede interação com outras janelas
        configuracoesStage.setTitle("Configurações");
        configuracoesStage.setMinWidth(250);

        // Definindo o layout na cena e exibindo a janela
        Scene scene = new Scene(new VBox(new Label("Configurações aqui...")), 250, 150);
        configuracoesStage.setScene(scene);
        configuracoesStage.initOwner(stage); // Define a janela principal como dona desta janela
        configuracoesStage.show();
    } */

    // AS OUTRAS DUAS FOTOS DEVERIAM SEGUIR A MESMA LÓGICA

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
        mensagemBemVindo.setText("Olá! " + nomeUsuario + "!");
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

    /*
     * Acredito que neste controller será necessário incluir os botões de: 
     * Ver projetos - onAction: verProjetos
     * Adicionar lancamento - onAction: adicionarLancamento
     * O BOTÃO DE PRIVACIDADE DEVERIA ABRIR UMA PÁGINA WEB - PORTANTO, DEVE SER IGNORADA POR ORA
     * 
     * A RESPEITO DA PROGRESS BAR, esta seria declarada desta maneira: 
     * a lógica deveria ser pedir ao usuário para inserir a porcentagem do progresso e o programa
     *  apresentaria para ele o progresso que ele definir, o que não é seguro. portanto:
     * HÁ CHANCE DE SER ISOLADA. 
    @FXML
    private ProgressBar progressBar;

    public void atualizarProgresso() {
        String input = inputField.getText();
        try {
            double progress = Double.parseDouble(input) / 100.0; // Assume que o input é uma porcentagem (0-100)
            progressBar.setProgress(progress);
        } catch (NumberFormatException e) {
            // Tratar o caso em que a entrada não é um número válido
            System.out.println("Por favor, insira um número válido.");
        }
     */
}
