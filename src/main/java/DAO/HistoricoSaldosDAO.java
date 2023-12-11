package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;


import java.sql.Date;

public class HistoricoSaldosDAO {

    public HistoricoSaldos create(HistoricoSaldos historicoSaldos) throws SQLException {
        String sql = "INSERT INTO HistoricoSaldos (id_conta, data_registro, saldo, ativo) VALUES (?, ?, ?, ?);";

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {
            statement.setInt(1, historicoSaldos.getIdConta());
            statement.setDate(2, Date.valueOf(historicoSaldos.getDataRegistro()));
            statement.setDouble(3, historicoSaldos.getSaldo());
            statement.setBoolean(4, historicoSaldos.getAtivo());

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

    public double buscarValorAtivoPorIdConta(int idConta) {
        String sql = "SELECT saldo FROM HistoricoSaldos WHERE id_conta = ? AND ativo = true;";

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setInt(1, idConta);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return rs.getDouble("saldo");
            }

            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1; 
    }

    public int buscarIdAtivoPorIdConta(int idConta) {
        String sql = "SELECT id FROM HistoricoSaldos WHERE id_conta = ? AND ativo = true;";

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setInt(1, idConta);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return rs.getInt("id");
            }

            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1; 
    }

    public void atualizarAtivoParaFalse(int idHistoricoSaldos) {
        String sql = "UPDATE HistoricoSaldos SET ativo = false WHERE id = ?;";

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setInt(1, idHistoricoSaldos);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated == 0) {
                System.out.println("Nenhuma linha foi atualizada.");
            } else {
                System.out.println("O campo ativo foi atualizado para false com sucesso!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizarSaldo(double valor, String tipo, int id_conta) throws SQLException {
    HistoricoSaldosDAO historicoSaldosDAO = new HistoricoSaldosDAO();

    double saldo = historicoSaldosDAO.buscarValorAtivoPorIdConta(id_conta);
    System.out.println(saldo);
    int id_desatual = historicoSaldosDAO.buscarIdAtivoPorIdConta(id_conta);
    double saldonovo;

    if ("despesa".equals(tipo) || "projeto".equals(tipo)) {
        saldonovo = saldo - valor;
    } else {
        saldonovo = saldo + valor;
    }

    historicoSaldosDAO.atualizarAtivoParaFalse(id_desatual);

    LocalDate dataRegistro = LocalDate.now();
    HistoricoSaldos historicoSaldos = new HistoricoSaldos(id_conta, dataRegistro, saldonovo, true);

    historicoSaldosDAO.create(historicoSaldos);
}

public void atualizarSaldoProjetos(double valor, int projeto, int id_conta, LocalDate dataTransacao) throws SQLException {
    HistoricoSaldosDAO historicoSaldosDAO = new HistoricoSaldosDAO();
    ProjetoCofrinhoDAO projetoCofrinhoDAO = new ProjetoCofrinhoDAO();

    double saldo = historicoSaldosDAO.buscarValorAtivoPorIdConta(id_conta);
    int id_desatual = historicoSaldosDAO.buscarIdAtivoPorIdConta(id_conta);
    double valorArrecadadoProjeto = projetoCofrinhoDAO.calcularValorAtingido(projeto);
    double valorMetaProjeto = projetoCofrinhoDAO.findMetaQuantiaById(projeto);

    double saldonovo;

    double valorNecessario = valorMetaProjeto - valorArrecadadoProjeto;

    if (valor > valorNecessario){
        saldonovo = saldo - valorNecessario;
        RelatorioPC relatorioPC = new RelatorioPC(projeto,id_conta,valorNecessario, dataTransacao);
        RelatorioPCDAO relatorioPCDAO = new RelatorioPCDAO();
        relatorioPCDAO.createAlternative(relatorioPC,valorMetaProjeto,valorArrecadadoProjeto);

    } else {
        saldonovo = saldo - valor;
        RelatorioPC relatorioPC = new RelatorioPC(projeto,id_conta, valor, dataTransacao);
        RelatorioPCDAO relatorioPCDAO = new RelatorioPCDAO();
        relatorioPCDAO.createAlternative(relatorioPC,valorMetaProjeto,valorArrecadadoProjeto);

    }

    historicoSaldosDAO.atualizarAtivoParaFalse(id_desatual);

    LocalDate dataRegistro = LocalDate.now();
    HistoricoSaldos historicoSaldos = new HistoricoSaldos(id_conta, dataRegistro, saldonovo, true);

    historicoSaldosDAO.create(historicoSaldos);

}

public double insertedValueProject(double valor, int projeto, int id_conta){
    ProjetoCofrinhoDAO projetoCofrinhoDAO = new ProjetoCofrinhoDAO();

    double valorArrecadadoProjeto = projetoCofrinhoDAO.calcularValorAtingido(projeto);
    double valorMetaProjeto = projetoCofrinhoDAO.findMetaQuantiaById(projeto);

    double valorNecessario = valorMetaProjeto - valorArrecadadoProjeto;

    if (valor > valorNecessario){
        return valorNecessario;
    } else {
        return valor;
    }
}


    public HistoricoSaldos resultSetToHistoricoSaldos(ResultSet rs) throws SQLException {
        return new HistoricoSaldos(
            rs.getInt("id"),
            rs.getInt("id_conta"),
            rs.getDate("data_registro").toLocalDate(),
            rs.getDouble("saldo"),
            rs.getBoolean("ativo")
        );
    }
    
   
}

