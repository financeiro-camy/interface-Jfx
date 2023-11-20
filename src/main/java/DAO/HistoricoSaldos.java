package DAO;

import java.sql.Date;
import java.time.LocalDate;

public class HistoricoSaldos {
    private int id;
    private int idConta;
    private Date dataRegistro;
    private double saldo;
    private boolean ativo;

   
    public HistoricoSaldos(int id, int idConta, LocalDate dataRegistro, double saldo, boolean ativo) {
        this.id = id;
        this.idConta = idConta;
        this.dataRegistro = Date.valueOf(dataRegistro);
        this.saldo = saldo;
        this.ativo = ativo;
    }

    public HistoricoSaldos(int idConta, LocalDate dataRegistro, double saldo, boolean ativo) {
        this.idConta = idConta;
        this.dataRegistro = Date.valueOf(dataRegistro);
        this.saldo = saldo;
        this.ativo = ativo;
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

    public boolean getAtivo(){
        return ativo;
    }

    public void setAivo(boolean ativo) {
        this.ativo = ativo;
    }
}


