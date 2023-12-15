package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class UsuarioAtributoDAO {


    public void adicionarAtributoAlternative(int idUsuario, String nomeAtributo, int idlogado) throws SQLException {
        String sql = "INSERT INTO UsuarioAtributo (id_usuario, nome_atributo, valor_atributo) VALUES (?, ?, ?)";
        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {
            statement.setInt(1, idUsuario);
            statement.setString(2, nomeAtributo);
            statement.setInt(3, idlogado);
            statement.executeUpdate();
    
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int chavePrimaria = generatedKeys.getInt(1);
                System.out.println("Chave primária gerada automaticamente: " + chavePrimaria);
            } else {
                throw new SQLException("Falha ao obter a chave primária gerada.");
            }
        }
    }
    

    public void removerAtributo(int id) throws SQLException {
        String sql = "DELETE FROM UsuarioAtributo WHERE id = ?";
        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    public String obterValorAtributo(int idUsuario, String nomeAtributo) throws SQLException {
        String valorAtributo = "-1";
        String sql = "SELECT valor_atributo FROM UsuarioAtributo WHERE id_usuario = ? AND nome_atributo = ?";
        
        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setInt(1, idUsuario);
            statement.setString(2, nomeAtributo);
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                valorAtributo = resultSet.getString("valor_atributo");
            }
        }
        return valorAtributo;
    }

    public String obterValorAtributo(String nomeAtributo) throws SQLException {
        String valorAtributo = null;
        String sql = "SELECT valor_atributo FROM UsuarioAtributo WHERE nome_atributo = ?";
        
        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setString(1, nomeAtributo);
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                valorAtributo = resultSet.getString("valor_atributo");
            }
        }
        return valorAtributo;
    }

    public int obterIdAtributo(int idUsuario, String nomeAtributo) throws SQLException {
        int id = -1;
        String sql = "SELECT id FROM UsuarioAtributo WHERE id_usuario = ? AND nome_atributo = ?";
        
        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setInt(1, idUsuario);
            statement.setString(2, nomeAtributo);
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                id = resultSet.getInt("id");
            }
        }
        return id;
    }

}


