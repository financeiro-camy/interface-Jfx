package DAO;

/*import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    public Usuario create(Usuario usuario) throws SQLException {
        String sql = """
            INSERT INTO Usuario (id, nome, email, senha, ativo)
            VALUES (?, ?, ?, ?, ?);
        """;

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setInt(1, usuario.getId());
            statement.setString(2, usuario.getNome());
            statement.setString(3, usuario.getEmail());
            statement.setString(4, usuario.getSenha());
            statement.setBoolean(5, usuario.isAtivo());

            statement.executeUpdate();

            return usuario;
        }
    }

    public Usuario update(Usuario usuario) throws SQLException {
        String sql = """
            UPDATE Usuario
            SET nome = ?, email = ?, senha = ?, ativo = ?
            WHERE id = ?;
        """;

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setString(1, usuario.getNome());
            statement.setString(2, usuario.getEmail());
            statement.setString(3, usuario.getSenha());
            statement.setBoolean(4, usuario.isAtivo());
            statement.setInt(5, usuario.getId());

            statement.executeUpdate();

            return usuario;
        } catch (SQLException e) {
            return null;
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM Usuario WHERE id = ?;";

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

    public void delete(Usuario usuario) {
        delete(usuario.getId());
    }

    public Usuario findById(int id) {
        String sql = "SELECT * FROM Usuario WHERE id = ?;";

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return resultSetToUsuario(rs);
            }

            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }

    public List<Usuario> findAll() {
        String sql = "SELECT * FROM Usuario;";
        List<Usuario> usuarios = new ArrayList<>();

        try (
            Connection connection = Conexao.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
        ) {
            while (rs.next()) {
                usuarios.add(resultSetToUsuario(rs));
            }

            return usuarios;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private Usuario resultSetToUsuario(ResultSet rs) throws SQLException {
        return new Usuario(
            rs.getInt("id"),
            rs.getString("nome"),
            rs.getString("email"),
            rs.getString("senha"),
            rs.getBoolean("ativo")
        );
    }
}*/