package DAO;

import java.sql.Date;
import java.time.LocalDate;

public class Lancamento {
    private int id;
    private int id_categoria;
    private int id_conta;
    private int id_periodicidade;
    private String nome;
    private String descricao;
    private double valor;
    private String tipo;
    private int numero_parcelas;
    private Date data_vencimento;
    private boolean pago;
    private Date data_pagamento;

    public Lancamento(int id, int id_categoria, int id_conta, int id_periodicidade, String nome, String descricao,
                      double valor, String tipo, int numero_parcelas, LocalDate data_vencimento,
                      boolean pago, LocalDate data_pagamento) {
        this.id = id;
        this.id_categoria = id_categoria;
        this.id_conta = id_conta;
        this.id_periodicidade = id_periodicidade;
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.tipo = tipo;
        this.numero_parcelas = numero_parcelas;
        this.data_vencimento = Date.valueOf(data_vencimento);
        this.pago = pago;
        this.data_pagamento = Date.valueOf(data_pagamento);
    }

    public Lancamento(int id_categoria, int id_conta, int id_periodicidade, String nome, String descricao,
                      double valor, String tipo, int numero_parcelas, LocalDate data_vencimento,
                      boolean pago, LocalDate data_pagamento) {
        this.id_categoria = id_categoria;
        this.id_conta = id_conta;
        this.id_periodicidade = id_periodicidade;
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.tipo = tipo;
        this.numero_parcelas = numero_parcelas;
        this.data_vencimento = Date.valueOf(data_vencimento);
        this.pago = pago;
        this.data_pagamento = Date.valueOf(data_pagamento);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public int getId_conta() {
        return id_conta;
    }

    public void setId_conta(int id_conta) {
        this.id_conta = id_conta;
    }

    public int getId_periodicidade() {
        return id_periodicidade;
    }

    public void setId_periodicidade(int id_periodicidade) {
        this.id_periodicidade = id_periodicidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getNumero_parcelas() {
        return numero_parcelas;
    }

    public void setNumero_parcelas(int numero_parcelas) {
        this.numero_parcelas = numero_parcelas;
    }

    public LocalDate getData_vencimento() {
        return data_vencimento.toLocalDate();
    }

    public void setData_vencimento(LocalDate data_vencimento) {
        this.data_vencimento = Date.valueOf(data_vencimento);
    }

    public boolean isPago() {
        return pago;
    }

    public void setPago(boolean pago) {
        this.pago = pago;
    }

    public LocalDate getData_pagamento() {
        return data_pagamento.toLocalDate();
    }

    public void setData_pagamento(LocalDate data_pagamento) {
        this.data_pagamento = Date.valueOf(data_pagamento);
    }

    @Override
    public String toString() {
        return "Lancamento [id=" + id + ", id_categoria=" + id_categoria + ", id_conta=" + id_conta + ", id_periodicidade=" + id_periodicidade
                + ", nome=" + nome + ", descricao=" + descricao + ", valor=" + valor + ", tipo=" + tipo + ", numero_parcelas="
                + numero_parcelas + ", data_vencimento=" + data_vencimento + ", pago=" + pago + ", data_pagamento=" + data_pagamento + "]";
    }
}
