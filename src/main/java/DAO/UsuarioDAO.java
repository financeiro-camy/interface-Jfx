package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    public Usuario create(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO Usuario (nome, email, senha, ativo) VALUES (?, ?, ?, ?)";

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {
            statement.setString(1, usuario.getNome());
            statement.setString(2, usuario.getEmail());
            statement.setString(3, usuario.getSenha());
            statement.setBoolean(4, usuario.isAtivo());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("A inserção falhou, nenhum registro foi adicionado.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    usuario.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("A inserção falhou, nenhum ID foi retornado.");
                }
            }

            return usuario;
        }
    }

    public Usuario update(Usuario usuario) throws SQLException {
        String sql = "UPDATE Usuario SET nome = ?, email = ?, senha = ?, ativo = ? WHERE id = ?";

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setString(1, usuario.getNome());
            statement.setString(2, usuario.getEmail());
            statement.setString(3, usuario.getSenha());
            statement.setBoolean(4, usuario.isAtivo());
            statement.setInt(5, usuario.getId());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("A atualização falhou, nenhum registro foi modificado.");
            }

            return usuario;
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM Usuario WHERE id = ?";

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setInt(1, id);
            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("A exclusão falhou, nenhum registro foi removido.");
            }
        }
    }

    public Usuario findById(int id) throws SQLException {
        String sql = "SELECT * FROM Usuario WHERE id = ?";

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setInt(1, id);

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return resultSetToUsuario(rs);
                }
            }
        }

        return null;
    }

    public List<Usuario> findAll() throws SQLException {
        String sql = "SELECT * FROM Usuario";
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
        }
    }

    public Usuario findByEmail(String email) {
        String sql = "SELECT * FROM Usuario WHERE email = ?;";

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setString(1, email);

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

    private Usuario resultSetToUsuario(ResultSet rs) throws SQLException {
        return new Usuario(
            rs.getInt("id"),
            rs.getString("nome"),
            rs.getString("email"),
            rs.getString("senha"),
            rs.getBoolean("ativo")
        );
    }
}
