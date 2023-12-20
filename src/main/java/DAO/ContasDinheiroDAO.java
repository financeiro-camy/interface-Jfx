package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ContasDinheiroDAO {
    public ContasDinheiro create(ContasDinheiro conta) throws SQLException {
        String sql = """
            INSERT INTO ContasDinheiro (id_usuario, nome, saldo_inicial, data_saldo_inicial)
            VALUES (?, ?, ?, ?);
        """;
    
        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection
                .prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {
            statement.setInt(1, conta.getId_usuario());
            statement.setString(2, conta.getNome());
            statement.setDouble(3, conta.getSaldoInicial());
            statement.setDate(4, Date.valueOf(conta.getDataSaldoInicial()));
    
            int affectedRows = statement.executeUpdate();
    
            if (affectedRows == 0) {
                throw new SQLException("A inserção falhou, nenhuma linha afetada.");
            }
    
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int idGerado = generatedKeys.getInt(1);
                    
                    conta.setId(idGerado);
                } else {
                    throw new SQLException("Falha ao criar o usuário. Nenhum ID foi gerado.");
                }
            }
    
            return conta;
        }
    }

    public ContasDinheiro update(ContasDinheiro conta) throws SQLException {
        String sql = """
            UPDATE ContasDinheiro 
            SET nome = ?, saldo_inicial = ?, data_saldo_inicial = ?
            WHERE id = ?;
        """;

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setDouble(1, conta.getSaldoInicial());
            statement.setDate(2, Date.valueOf(conta.getDataSaldoInicial()));
            statement.setString(3, conta.getNome());

            statement.executeUpdate();

            return conta;
        } catch (SQLException e) {
            return null;
        }
    }

    public void delete(String nome) {
        String sql = "DELETE FROM ContasDinheiro WHERE nome = ?;";

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setString(1, nome);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(ContasDinheiro conta) {
        delete(conta.getNome());
    }

    public int buscarIdConta(String nomeConta, int id_usuario) {
        String sql = "SELECT id FROM ContasDinheiro WHERE nome = ? AND id_usuario = ?;";
        int id = -1; 

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setString(1, nomeConta);
            statement.setInt(2, id_usuario);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                id = rs.getInt("id");
            }

            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return id;
    }


    public List<ContasDinheiro> findAll() {
        String sql = "SELECT * FROM ContasDinheiro;";
        List<ContasDinheiro> contas = new ArrayList<>();

        try (
            Connection connection = Conexao.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
        ) {
            while (rs.next()) {
                contas.add(resultSetToContasDinheiro(rs));
            }

            return contas;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<ContasDinheiro> findContasByUsuario(int idUsuario) {
        String sql = "SELECT * FROM ContasDinheiro WHERE id_usuario = ?;";
        List<ContasDinheiro> contasUsuario = new ArrayList<>();
    
        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setInt(1, idUsuario);
    
            ResultSet rs = statement.executeQuery();
    
            while (rs.next()) {
                contasUsuario.add(resultSetToContasDinheiro(rs));
            }
    
            return contasUsuario;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ContasDinheiro findById(Integer id) {
        String sql = "SELECT * FROM ContasDinheiro WHERE id = ?;";

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setInt(1, id);
            
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return resultSetToContasDinheiro(rs);
            }

            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }

    private ContasDinheiro resultSetToContasDinheiro(ResultSet rs) throws SQLException {
        return new ContasDinheiro(
            rs.getInt("id"),
            rs.getInt("id_usuario"),
            rs.getString("nome"),
            rs.getDouble("saldo_inicial"),
            rs.getDate("data_saldo_inicial").toLocalDate()
        );
    }
}