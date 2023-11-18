package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

public class ProjetoCofrinhoDAO {
    public ProjetoCofrinho create(ProjetoCofrinho projetoCofrinho) throws SQLException {
        String sql = """
            INSERT INTO ProjetoCofrinho (id_usuario, nome, descricao, prazo, data_criacao, meta_quantia, ativo)
            VALUES (?, ?, ?, ?, ?, ?, ?);
        """;
    
        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {
            statement.setInt(1, projetoCofrinho.getId_usuario());
            statement.setString(2, projetoCofrinho.getNome());
            statement.setString(3, projetoCofrinho.getDescricao());
            statement.setDate(4, Date.valueOf(projetoCofrinho.getPrazo()));
            statement.setDate(5, Date.valueOf(projetoCofrinho.getData_criacao()));
            statement.setDouble(6, projetoCofrinho.getMeta_quantia());
            statement.setBoolean(7, projetoCofrinho.isAtivo());
            
            int rowsAffected = statement.executeUpdate();
            
            if (rowsAffected == 0) {
                throw new SQLException("Inserção falhou, nenhum registro foi adicionado.");
            }
            
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int id = generatedKeys.getInt(1); 
                projetoCofrinho.setId(id); 
            } else {
                throw new SQLException("Falha ao obter o ID gerado para o ProjetoCofrinho.");
            }
    
            return projetoCofrinho;
        }
    }
    

    public ProjetoCofrinho update(ProjetoCofrinho projetoCofrinho) throws SQLException {
        String sql = """
            UPDATE ProjetoCofrinho 
            SET id_usuario = ?, nome = ?, descricao = ?, prazo = ?, data_criacao = ?, meta_quantia = ?, ativo = ?
            WHERE id = ?;
        """;
    
        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setInt(1, projetoCofrinho.getId_usuario());
            statement.setString(2, projetoCofrinho.getNome());
            statement.setString(3, projetoCofrinho.getDescricao());
            statement.setDate(4, Date.valueOf(projetoCofrinho.getPrazo()));
            statement.setDate(5, Date.valueOf(projetoCofrinho.getData_criacao()));
            statement.setDouble(6, projetoCofrinho.getMeta_quantia());          
            statement.setBoolean(7, projetoCofrinho.isAtivo());
            statement.setInt(8, projetoCofrinho.getId());
            
            statement.executeUpdate();
            
            return projetoCofrinho;
        } catch (SQLException e) {
            return null;
        }
    }

    public void delete(Integer id) {
        String sql = "DELETE FROM ProjetoCofrinho WHERE id = ?;";

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(ProjetoCofrinho projetoCofrinho) {
        delete(projetoCofrinho.getId());
    }

    public ProjetoCofrinho findById(Integer id) {
        String sql = "SELECT * FROM ProjetoCofrinho WHERE id = ?;";

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setInt(1, id);
            
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return resultSetToProjetoCofrinho(rs);
            }

            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }

    public List<ProjetoCofrinho> findAll() {
        String sql = "SELECT * FROM ProjetoCofrinho;";
        List<ProjetoCofrinho> projetos = new ArrayList<>();

        try (
            Connection connection = Conexao.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
        ) {
            while(rs.next()) {
                projetos.add(resultSetToProjetoCofrinho(rs));
            }

            return projetos;
        
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        
    }

    
    private ProjetoCofrinho resultSetToProjetoCofrinho(ResultSet rs) throws SQLException {
        return new ProjetoCofrinho(
            rs.getInt("id"),
            rs.getInt("id_usuario"),
            rs.getString("nome"),
            rs.getString("descricao"),
            rs.getDate("prazo").toLocalDate(), 
            rs.getDate("data_criacao").toLocalDate(), 
            rs.getDouble("meta_quantia"),
            rs.getBoolean("ativo")
        );
    }
}
