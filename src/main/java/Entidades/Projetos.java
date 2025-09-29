package Entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import Dto.FuncionariosDTO;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "PROJETOS")
public class Projetos {

	@Transient
	private List<FuncionariosDTO> participantes;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_projeto")
	private Long idProjeto;

	@Column(name = "titulo")
	private String titulo;

	@Column(name = "descricao")
	private String descricao;

	@Column(name = "data_criacao")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCriacao;

	@Column(name = "criado_por")
	private String criadoPor;

	@ManyToOne
	@JoinColumn(name = "id_status")
	private Status status;

	@ManyToOne
	@JoinColumn(name = "id_area")
	private Area idArea;

	public Long getIdProjeto() {
		return idProjeto;
	}

	public void setIdProjeto(Long idProjeto) {
		this.idProjeto = idProjeto;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public String getCriadoPor() {
		return criadoPor;
	}

	public void setCriadoPor(String criadoPor) {
		this.criadoPor = criadoPor;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Area getIdArea() {
		return idArea;
	}

	public void setIdArea(Area idArea) {
		this.idArea = idArea;
	}
	
	public List<FuncionariosDTO> getParticipantes() {
		return participantes;
	}

	public void setParticipantes(List<FuncionariosDTO> participantes) {
		this.participantes = participantes;
	}
}