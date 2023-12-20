package com.example.javafxproject;

import java.sql.SQLException;
import DAO.HistoricoSaldosDAO;

public class TestarAtualizarSaldo {
    public static void main(String[] args) {

        double valorDespesa = 30.0;
        int idConta = 12;
        
        HistoricoSaldosDAO historicoSaldosDAO = new HistoricoSaldosDAO();

        try {
            historicoSaldosDAO.atualizarSaldo(valorDespesa, "despesa", idConta);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}