package DAO;

import java.time.LocalDate;

public class ContasDinheiro {
    private int id;
    private int id_usuario;
    private String nome;
    private double saldoInicial;
    private LocalDate dataSaldoInicial;

    public ContasDinheiro(int id,int id_usuario, String nome, double saldoInicial, LocalDate dataSaldoInicial) {
        this.id = id;
        this.id_usuario = id_usuario;
        this.nome = nome;
        this.saldoInicial = saldoInicial;
        this.dataSaldoInicial = dataSaldoInicial;
    }

    public ContasDinheiro(int id_usuario, String nome, double saldoInicial, LocalDate dataSaldoInicial) {
        this.id_usuario = id_usuario;
        this.nome = nome;
        this.saldoInicial = saldoInicial;
        this.dataSaldoInicial = dataSaldoInicial;
    }

    public int getId(){
        return id;
    }

    public int getId_usuario(){
        return id_usuario;
    }

    public String getNome() {
        return nome;
    }

    public void setId(int id){
        this.id=id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(double saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public LocalDate getDataSaldoInicial() {
        return dataSaldoInicial;
    }

    public void setDataSaldoInicial(LocalDate dataSaldoInicial) {
        this.dataSaldoInicial = dataSaldoInicial;
    }

    @Override
    public String toString() {
        return "ContasDinheiro [nome=" + nome + ", saldoInicial=" + saldoInicial + ", dataSaldoInicial=" + dataSaldoInicial + "]";
    }
}