package DAO;

import java.sql.Date;
import java.time.LocalDate;

public class RelatorioPC {
    private int id;
    private int id_cofrinho;
    private int id_conta;
    private double valor;
    private Date data_insercao;

    public RelatorioPC(int id, int id_cofrinho, int id_conta, double valor, LocalDate data_insercao){
        this.id = id;
        this.id_cofrinho = id_cofrinho;
        this.id_conta = id_conta;
        this.valor = valor;
        this.data_insercao = Date.valueOf(data_insercao);
    }

    public RelatorioPC(int id_cofrinho, int id_conta, double valor, LocalDate data_insercao){
        this.id_cofrinho = id_cofrinho;
        this.id_conta = id_conta;
        this.valor = valor;
        this.data_insercao = Date.valueOf(data_insercao);
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId_cofrinho(){
        return id_cofrinho;
    }

    public void setId_cofrinho(int id_cofrinho){
        this.id_cofrinho = id_cofrinho;
    }

    public int getId_conta(){
        return id_conta;
    }

    public void setId_conta(int id_conta){
        this.id_conta = id_conta;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public LocalDate getData_insercao() {
        return data_insercao.toLocalDate();
    }

    public void setData_insercao(LocalDate data_insercao) {
        this.data_insercao = Date.valueOf(data_insercao);
    }

    @Override
    public String toString() {
        return "RelatorioPC [id =" + id + ", id_cofrinho =" + id_cofrinho + ", id_conta = " + id_conta + ", valor =" + valor + ", data_insercao =" + data_insercao + "]";
    }
}