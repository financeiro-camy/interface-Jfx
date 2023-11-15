package DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {
    public Categoria create(Categoria categoria) throws SQLException {
        String sql = """
            INSERT INTO Categoria (id_usuario, nome)
            VALUES (?, ?);
        """;
    
        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {
            statement.setInt(1, categoria.getId_usuario());
            statement.setString(2, categoria.getNome());
    
            int affectedRows = statement.executeUpdate();
    
            if (affectedRows == 0) {
                throw new SQLException("A inserção falhou, nenhuma linha afetada.");
            }
    
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    categoria.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Falha ao obter o ID gerado automaticamente.");
                }
            }
    
            return categoria;
        }
    }
    

    public Categoria update(Categoria categoria) throws SQLException {
        String sql = """
            UPDATE Categoria 
            SET id_usuario = ?, nome = ?
            WHERE id = ?;
        """;
    
        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setInt(1, categoria.getId_usuario());
            statement.setString(2, categoria.getNome());
            statement.setInt(3, categoria.getId());
            
            statement.executeUpdate();
            
            return categoria;
        } catch (SQLException e) {
            return null;
        }
    }

    public void delete(Integer id) {
        String sql = "DELETE FROM Categoria WHERE id = ?;";

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

    public void delete(Categoria categoria) {
        delete(categoria.getId());
    }

    public Categoria findById(Integer id) {
        String sql = "SELECT * FROM Categoria WHERE id = ?;";

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setInt(1, id);
            
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return resultSetToCategoria(rs);
            }

            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }

    public List<Categoria> findAll(int idUsuario) {
        String sql = "SELECT * FROM Categoria WHERE id_usuario = ?;";
        List<Categoria> categorias = new ArrayList<>();
    
        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setInt(1, idUsuario);
    
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    categorias.add(resultSetToCategoria(rs));
                }
            }
    
            return categorias;
    
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    

    private Categoria resultSetToCategoria(ResultSet rs) throws SQLException {
        return new Categoria(
           // rs.getInt("id"),
            rs.getInt("id_usuario"),
            rs.getString("nome")
        );
    }
}

