package DAO;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LancamentoDAO {
    

    public Lancamento create(Lancamento lancamento) throws SQLException {
        String sql = "INSERT INTO Lancamento (id_categoria, id_conta, id_periodicidade, nome, descricao, valor, tipo, numero_parcelas, data_vencimento, pago, data_pagamento) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {
            statement.setInt(1, lancamento.getId_categoria());
            statement.setInt(2, lancamento.getId_conta());
            statement.setInt(3, lancamento.getId_periodicidade());
            statement.setString(4, lancamento.getNome());
            statement.setString(5, lancamento.getDescricao());
            statement.setDouble(6, lancamento.getValor());
            statement.setString(7, lancamento.getTipo());
            statement.setInt(8, lancamento.getNumero_parcelas());
            statement.setDate(9, Date.valueOf(lancamento.getData_vencimento()));
            statement.setBoolean(10, lancamento.isPago());
            statement.setDate(11, Date.valueOf(lancamento.getData_pagamento()));

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("A inserção falhou, nenhum registro foi criado.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int autoGeneratedId = generatedKeys.getInt(1);
                    lancamento.setId(autoGeneratedId);
                } else {
                    throw new SQLException("Falha ao obter a chave primária gerada automaticamente.");
                }
            }
        }

        return lancamento;
    }

    public Lancamento update(Lancamento lancamento) throws SQLException {
        String sql = "UPDATE Lancamento SET id_categoria=?, id_conta=?, id_periodicidade=?, nome=?, descricao=?, valor=?, tipo=?, numero_parcelas=?, data_vencimento=?, pago=?, data_pagamento=? " +
                     "WHERE id=?";

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setInt(1, lancamento.getId_categoria());
            statement.setInt(2, lancamento.getId_conta());
            statement.setInt(3, lancamento.getId_periodicidade());
            statement.setString(4, lancamento.getNome());
            statement.setString(5, lancamento.getDescricao());
            statement.setDouble(6, lancamento.getValor());
            statement.setString(7, lancamento.getTipo());
            statement.setInt(8, lancamento.getNumero_parcelas());
            statement.setDate(9, Date.valueOf(lancamento.getData_vencimento()));
            statement.setBoolean(10, lancamento.isPago());
            statement.setDate(11, Date.valueOf(lancamento.getData_pagamento()));
            statement.setInt(12, lancamento.getId());

            statement.executeUpdate();
        }

        return lancamento;
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM Lancamento WHERE id=?";

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    public Lancamento findById(int id) throws SQLException {
        String sql = "SELECT * FROM Lancamento WHERE id=?";

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSetToLancamento(resultSet);
            }
        }

        return null;
    }

    public List<Lancamento> findAll() throws SQLException {
        String sql = "SELECT * FROM Lancamento";
        List<Lancamento> lancamentos = new ArrayList<>();

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Lancamento lancamento = resultSetToLancamento(resultSet);
                lancamentos.add(lancamento);
            }
        }

        return lancamentos;
    }

    public double calcularGastosSemana(int idConta) throws SQLException {
        String sql = "SELECT SUM(l.valor) AS TotalGastos " +
                     "FROM ContasDinheiro cd " +
                     "INNER JOIN Lancamento l ON cd.id = l.id_conta " +
                     "WHERE cd.id = ? " +
                     "AND (l.tipo = 'despesa' OR l.tipo = 'projeto') " +
                     "AND l.data_pagamento >= " +
                     "  CASE " +
                     "    WHEN DAYOFWEEK(CURDATE()) = 2 THEN CURDATE() " +
                     "    ELSE DATE_SUB(CURDATE(), INTERVAL WEEKDAY(CURDATE()) DAY) - INTERVAL 6 DAY " +
                     "  END " +
                     "AND l.data_pagamento < " +
                     "  CASE " +
                     "    WHEN DAYOFWEEK(CURDATE()) = 2 THEN CURDATE() + INTERVAL 1 WEEK " +
                     "    ELSE DATE_SUB(CURDATE(), INTERVAL WEEKDAY(CURDATE()) DAY) + INTERVAL 1 WEEK " +
                     "  END;";
        
        double totalGastos = 0.0;
        
        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setInt(1, idConta);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                totalGastos = rs.getDouble("TotalGastos");
            }

            rs.close();
        }

        return totalGastos;
    }

    public double calcularGastosMes(int contaId) throws SQLException {
        String sql = "SELECT SUM(l.valor) AS TotalGastos " +
                     "FROM ContasDinheiro cd " +
                     "INNER JOIN Lancamento l ON cd.id = l.id_conta " +
                     "WHERE cd.id = ? " +
                     "AND (l.tipo = 'despesa' OR l.tipo = 'projeto') " +
                     "AND MONTH(l.data_pagamento) = MONTH(CURDATE()) " +
                     "AND YEAR(l.data_pagamento) = YEAR(CURDATE())";

        double totalGastos = 0.0;

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setInt(1, contaId);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                totalGastos = rs.getDouble("TotalGastos");
            }

            rs.close();
        }

        return totalGastos;
    }


    private Lancamento resultSetToLancamento(ResultSet rs) throws SQLException {
        return new Lancamento(
            rs.getInt("id"),
            rs.getInt("id_categoria"),
            rs.getInt("id_conta"),
            rs.getInt("id_periodicidade"),
            rs.getString("nome"),
            rs.getString("descricao"),
            rs.getDouble("valor"),
            rs.getString("tipo"),
            rs.getInt("numero_parcelas"),
            rs.getDate("data_vencimento").toLocalDate(),
            rs.getBoolean("pago"),
            rs.getDate("data_pagamento") != null ? rs.getDate("data_pagamento").toLocalDate() : null
        );
    }
}
