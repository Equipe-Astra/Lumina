package Entidades;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class FuncionarioPublicacaoReacaoId implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "id_funcionario")
	private Funcionarios idFuncionario;

	@ManyToOne
	@JoinColumn(name = "id_publicacao")
	private Publicacao idPublicacao;

	@ManyToOne
	@JoinColumn(name = "id_reacao")
	private Reacoes idReacao;


	public Funcionarios getFuncionario() {
		return idFuncionario;
	}

	public void setFuncionario(Funcionarios funcionario) {
		this.idFuncionario = funcionario;
	}

	public Publicacao getPublicacao() {
		return idPublicacao;
	}

	public void setPublicacao(Publicacao publicacao) {
		this.idPublicacao = publicacao;
	}

	public Reacoes getReacao() {
		return idReacao;
	}

	public void setReacao(Reacoes reacao) {
		this.idReacao = reacao;
	}

	@Override
	public int hashCode() {
		return idFuncionario.hashCode() + idPublicacao.hashCode() + idReacao.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		FuncionarioPublicacaoReacaoId other = (FuncionarioPublicacaoReacaoId) obj;
		return idFuncionario.equals(other.idFuncionario) && idPublicacao.equals(other.idPublicacao)
				&& idReacao.equals(other.idReacao);
	}
}
