package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioAtributoDAO {

    private static final int ID_SESSAO_FIXO = 1;

    public void adicionarAtributo(int idUsuario, String nomeAtributo, String valorAtributo) throws SQLException {
        String sql = "INSERT INTO UsuarioAtributo (id, id_usuario, nome_atributo, valor_atributo) VALUES (?, ?, ?, ?)";
        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setInt(1, ID_SESSAO_FIXO);
            statement.setInt(2, idUsuario);
            statement.setString(3, nomeAtributo);
            statement.setString(4, valorAtributo);
            statement.executeUpdate();
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

    public int findSessaoId() throws SQLException {
        String sql = "SELECT id_usuario FROM UsuarioAtributo WHERE id = ?";
        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setInt(1, ID_SESSAO_FIXO);
            
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return rs.getInt("id_usuario");
            }

            rs.close();
        }
        return -1; 
    }

}


