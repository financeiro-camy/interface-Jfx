package com.example.javafxproject;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;

import com.example.Propriedades;

import java.io.IOException;
import DAO.Usuario;
import DAO.UsuarioDAO;
import DAO.Categoria;
import DAO.CategoriaDAO;


public class CadastroController {

    @FXML
    private TextField txfNome;
    
    @FXML
    private TextField txfEmail;
    
    @FXML
    private PasswordField txfSenha;

    @FXML
    private PasswordField txfSenhaTeste;
       
    Propriedades propriedades = new Propriedades();

    @FXML
    public void Cadastrar() throws IOException, SQLException  {
    
        String nome = txfNome.getText();
        String email = txfEmail.getText();
        String senha = txfSenha.getText();
        String senhaTeste = txfSenhaTeste.getText();

        String hashedPassword = BCrypt.hashpw(senha, BCrypt.gensalt());

        Usuario user = new Usuario(nome,email, hashedPassword,true);
        UsuarioDAO userDAO = new UsuarioDAO();
        userDAO.create(user);

        int id_usuario = user.getId();
        //setoresPadroes(id_usuario);

        propriedades.exibirAlerta("Agora faça o seu primeiro login!", "Nome, email e senha cadastrados com sucesso! Agora inicie a sua sessão na tela login!");

        CriarPrimeiraConta();
    }

    public void CriarPrimeiraConta() throws IOException{
        propriedades.ScreenGuider("tela-login2.fxml","Fazer login");
    }

    public void onActionLogin() throws IOException {
        propriedades.ScreenGuider("tela-login2.fxml","Login");
    }

    public void setoresPadroes(int id_usuario) throws SQLException {
        System.out.println("ID do Usuário: " + id_usuario);
    
        Categoria categoria1 = new Categoria(id_usuario, "Alimentação");
        Categoria categoria2 = new Categoria(id_usuario, "Transporte");
        Categoria categoria3 = new Categoria(id_usuario, "Lazer");
        Categoria categoria4 = new Categoria(id_usuario, "Moradia");
        Categoria categoria5 = new Categoria(id_usuario, "Educação");
    
        CategoriaDAO categoriaDAO = new CategoriaDAO();
    
        categoriaDAO.create(categoria1);
        categoriaDAO.create(categoria2);
        categoriaDAO.create(categoria3);
        categoriaDAO.create(categoria4);
        categoriaDAO.create(categoria5);
    }
    
}
