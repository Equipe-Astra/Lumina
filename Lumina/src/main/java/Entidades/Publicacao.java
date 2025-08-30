package Entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Publicacao {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "publicacao_seq")
	@SequenceGenerator(name = "publicacao_seq", sequenceName = "SEQ_PUBLICACAO", allocationSize = 1)
	@Column(name = "id_publicacao")
	private Long idPublicacao;
	
	@Lob
	private String descricao;
	
	@Lob
	private String objetivos;
	
	@Lob
	private String resultados;

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

	public Long getIdPublicacao() {
		return idPublicacao;
	}

	public void setIdPublicacao(Long idPublicacao) {
		this.idPublicacao = idPublicacao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getObjetivos() {
		return objetivos;
	}

	public void setObjetivos(String objetivos) {
		this.objetivos = objetivos;
	}

	public String getResultados() {
		return resultados;
	}

	public void setResultados(String resultados) {
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
