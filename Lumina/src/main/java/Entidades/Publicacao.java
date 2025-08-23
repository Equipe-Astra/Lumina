package Entidades;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

public class Publicacao {
	@Id
	@Column(name = "id_publicacao")
	private Double idPublicacao;
	private byte[] descricao;
	private byte[] objetivos;
	private byte[] resultados;

	@Lob
	@Column(name = "imagem")
	private byte[] imagem;

	@JoinColumn(name = "id_funcionario")
	@ManyToOne
	private Funcionarios idFuncionario;

	@JoinColumn(name = "ID_AREA")
	@ManyToOne
	private Area idArea;

	@JoinColumn(name = "id_projeto")
	@OneToOne
	private Projetos idProjeto;

	public Double getIdPublicacao() {
		return idPublicacao;
	}

	public void setIdPublicacao(Double idPublicacao) {
		this.idPublicacao = idPublicacao;
	}

	public byte[] getDescricao() {
		return descricao;
	}

	public void setDescricao(byte[] descricao) {
		this.descricao = descricao;
	}

	public byte[] getObjetivos() {
		return objetivos;
	}

	public void setObjetivos(byte[] objetivos) {
		this.objetivos = objetivos;
	}

	public byte[] getResultados() {
		return resultados;
	}

	public void setResultados(byte[] resultados) {
		this.resultados = resultados;
	}

	public byte[] getImagem() {
		return imagem;
	}

	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}

	public Funcionarios getIdFuncionario() {
		return idFuncionario;
	}

	public void setIdFuncionario(Funcionarios idFuncionario) {
		this.idFuncionario = idFuncionario;
	}

	public Area getIdArea() {
		return idArea;
	}

	public void setIdArea(Area idArea) {
		this.idArea = idArea;
	}

	public Projetos getIdProjeto() {
		return idProjeto;
	}

	public void setIdProjeto(Projetos idProjeto) {
		this.idProjeto = idProjeto;
	}

}
