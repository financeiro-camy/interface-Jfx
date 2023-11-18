package DAO;


    import java.sql.*;
    import java.util.ArrayList;
    import java.util.List;
    
    public class PeriodicidadeDAO {
        public Periodicidade create(Periodicidade periodicidade) throws SQLException {
            String sql = "INSERT INTO Periodicidade (id_usuario, nome) VALUES (?, ?);";
    
            try (
                Connection connection = Conexao.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ) {
                statement.setInt(1, periodicidade.getIdUsuario());
                statement.setString(2, periodicidade.getNome());
    
                int affectedRows = statement.executeUpdate();
    
                if (affectedRows == 0) {
                    throw new SQLException("A inserção falhou, nenhuma linha afetada.");
                }
    
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int idGerado = generatedKeys.getInt(1);
                        periodicidade.setId(idGerado);
                    } else {
                        throw new SQLException("Falha ao criar a periodicidade. Nenhum ID foi gerado.");
                    }
                }
    
                return periodicidade;
            }
        }
    
        public Periodicidade update(Periodicidade periodicidade) throws SQLException {
            String sql = "UPDATE Periodicidade SET nome = ? WHERE id = ?;";
    
            try (
                Connection connection = Conexao.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
            ) {
                statement.setString(1, periodicidade.getNome());
                statement.setInt(2, periodicidade.getId());
    
                int affectedRows = statement.executeUpdate();
    
                if (affectedRows == 0) {
                    throw new SQLException("A atualização falhou, nenhuma linha afetada.");
                }
    
                return periodicidade;
            }
        }
    
        public void delete(int id) {
            String sql = "DELETE FROM Periodicidade WHERE id = ?;";
    
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
    
        public Periodicidade findById(int id) {
            String sql = "SELECT * FROM Periodicidade WHERE id = ?;";
    
            try (
                Connection connection = Conexao.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
            ) {
                statement.setInt(1, id);
    
                ResultSet rs = statement.executeQuery();
    
                if (rs.next()) {
                    return resultSetToPeriodicidade(rs);
                }
    
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
    
            return null;
        }
    
        public List<Periodicidade> findAll() {
            String sql = "SELECT * FROM Periodicidade;";
            List<Periodicidade> periodicidades = new ArrayList<>();
    
            try (
                Connection connection = Conexao.getConnection();
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(sql);
            ) {
                while (rs.next()) {
                    periodicidades.add(resultSetToPeriodicidade(rs));
                }
    
                return periodicidades;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    
        private Periodicidade resultSetToPeriodicidade(ResultSet rs) throws SQLException {
            return new Periodicidade(
                rs.getInt("id"),
                rs.getInt("id_usuario"),
                rs.getString("nome")
            );
        }
    }
      

