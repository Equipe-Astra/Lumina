package Dto;

import java.io.Serializable;
import java.util.Date;

public class LucroProjetoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private int idProjeto;
    private String titulo;
    private Date dataCriacao;
    private double lucro;

    public LucroProjetoDTO() {
    }

    public LucroProjetoDTO(int idProjeto, String titulo, Date dataCriacao, double lucro) {
        this.idProjeto = idProjeto;
        this.titulo = titulo;
        this.dataCriacao = dataCriacao;
        this.lucro = lucro;
    }

    public int getIdProjeto() {
        return idProjeto;
    }

    public void setIdProjeto(int idProjeto) {
        this.idProjeto = idProjeto;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public double getLucro() {
        return lucro;
    }

    public void setLucro(double lucro) {
        this.lucro = lucro;
    }
}
