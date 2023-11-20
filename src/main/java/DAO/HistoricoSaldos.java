package DAO;

import java.sql.Date;
import java.time.LocalDate;

public class HistoricoSaldos {
    private int id;
    private int idConta;
    private Date dataRegistro;
    private double saldo;

   
    public HistoricoSaldos(int id, int idConta, LocalDate dataRegistro, double saldo) {
        this.id = id;
        this.idConta = idConta;
        this.dataRegistro = Date.valueOf(dataRegistro);
        this.saldo = saldo;
    }

    public HistoricoSaldos(int idConta, LocalDate dataRegistro, double saldo) {
        this.idConta = idConta;
        this.dataRegistro = Date.valueOf(dataRegistro);
        this.saldo = saldo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdConta() {
        return idConta;
    }

    public void setIdConta(int idConta) {
        this.idConta = idConta;
    }

    public LocalDate getDataRegistro() {
        return dataRegistro.toLocalDate();
    }

    public void setDataRegistro(LocalDate dataRegistro) {
        this.dataRegistro = Date.valueOf(dataRegistro);
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}


