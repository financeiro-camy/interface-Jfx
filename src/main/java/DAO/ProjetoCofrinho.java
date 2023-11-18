package DAO;

import java.sql.Date;
import java.time.LocalDate;

public class ProjetoCofrinho {
    private int id;
    private int id_usuario;
    private String nome;
    private String descricao;
    private Date prazo;
    private Date data_criacao;
    private double meta_quantia;
    private boolean ativo;

    public ProjetoCofrinho(int id, int id_usuario, String nome, String descricao, LocalDate prazo, LocalDate data_criacao, double meta_quantia, boolean ativo){
        this.id = id;
        this.id_usuario = id_usuario;
        this.nome = nome;
        this.descricao = descricao;
        this.prazo =  Date.valueOf(prazo);
        this.data_criacao = Date.valueOf(data_criacao);
        this.meta_quantia = meta_quantia;
        this.ativo = ativo;
    }

    public ProjetoCofrinho(int id_usuario, String nome, String descricao, LocalDate prazo, LocalDate data_criacao, double meta_quantia, boolean ativo){
        this.id_usuario = id_usuario;
        this.nome = nome;
        this.descricao = descricao;
        this.prazo =  Date.valueOf(prazo);
        this.data_criacao = Date.valueOf(data_criacao);
        this.meta_quantia = meta_quantia;
        this.ativo = ativo;
    }

    public int getId() {
        return id;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getPrazo() {
        return prazo.toLocalDate(); 
    }

    public LocalDate getData_criacao() {
        return data_criacao.toLocalDate(); 
    }

    public double getMeta_quantia() {
        return meta_quantia;
    }

    public String getDescricao() {
        return descricao;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPrazo(LocalDate prazo) {
        this.prazo = Date.valueOf(prazo); 
    }

    public void setData_criacao(LocalDate data_criacao) {
        this.data_criacao = Date.valueOf(data_criacao); 
    }

    public void setMeta_quantia(double meta_quantia) {
        this.meta_quantia = meta_quantia;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public String toString() {
        return "Projeto Cofrinho [id=" + id + ", nome=" + nome + ", descrição=" + descricao + ", prazo =" + prazo + ", criação =" + data_criacao + ", meta =" + meta_quantia + ", ativo =" + ativo + "]";
    }
}
