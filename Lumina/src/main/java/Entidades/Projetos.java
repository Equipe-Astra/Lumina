package Entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity

public class Projetos {

    @Id
    @Column(name = "id_projeto")
    private double idProjeto;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "descricao")
    private byte[] descricao; 

    @Column(name = "data_criacao")
    private Date dataCriacao;

    @Column(name = "criado_por")
    private double criadoPor;

    @ManyToOne
    @JoinColumn(name = "id_status")
    private Status status;
    
    @ManyToOne
    @JoinColumn(name = "ID_AREA")
    private Area idArea;


	public double getIdProjeto() {
		return idProjeto;
	}

	public void setIdProjeto(double idProjeto) {
		this.idProjeto = idProjeto;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public byte[] getDescricao() {
		return descricao;
	}

	public void setDescricao(byte[] descricao) {
		this.descricao = descricao;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public double getCriadoPor() {
		return criadoPor;
	}

	public void setCriadoPor(double criadoPor) {
		this.criadoPor = criadoPor;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
}