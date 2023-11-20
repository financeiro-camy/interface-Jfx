package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

public class HistoricoSaldosDAO {

    public HistoricoSaldos create(HistoricoSaldos historicoSaldos) throws SQLException {
        String sql = "INSERT INTO HistoricoSaldos (id_conta, data_registro, saldo) VALUES (?, ?, ?);";

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {
            statement.setInt(1, historicoSaldos.getIdConta());
            statement.setDate(2, Date.valueOf(historicoSaldos.getDataRegistro()));
            statement.setDouble(3, historicoSaldos.getSaldo());

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected == 0) {
                throw new SQLException("Inserção falhou, nenhum registro foi adicionado.");
            }

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int id = generatedKeys.getInt(1);
                historicoSaldos.setId(id);
            } else {
                throw new SQLException("Falha ao obter o ID gerado para o Histórico de Saldos.");
            }

            return historicoSaldos;
        }
    }

    public void delete(Integer id) {
        String sql = "DELETE FROM HistoricoSaldos WHERE id = ?;";

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

    public void delete(HistoricoSaldos historicoSaldos) {
        delete(historicoSaldos.getId());
    }

    public HistoricoSaldos findById(Integer id) {
        String sql = "SELECT * FROM HistoricoSaldos WHERE id = ?;";

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return resultSetToHistoricoSaldos(rs);
            }

            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }

    public HistoricoSaldos resultSetToHistoricoSaldos(ResultSet rs) throws SQLException {
        return new HistoricoSaldos(
            rs.getInt("id"),
            rs.getInt("id_conta"),
            rs.getDate("data_registro").toLocalDate(),
            rs.getDouble("saldo")
        );
    }
    
   
}

