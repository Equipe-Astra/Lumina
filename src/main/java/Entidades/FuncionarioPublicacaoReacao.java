package Entidades;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Funcionario_Publicacao_Reacao")
public class FuncionarioPublicacaoReacao {

	@EmbeddedId
	private FuncionarioPublicacaoReacaoId id;

	
	public FuncionarioPublicacaoReacaoId getId() {
		return id;
	}

	public void setId(FuncionarioPublicacaoReacaoId id) {
		this.id = id;
	}

	public Funcionarios getFuncionario() {
		return id.getFuncionario();
	}

	public void setFuncionario(Funcionarios f) {
		id.setFuncionario(f);
	}

	public Publicacao getPublicacao() {
		return id.getPublicacao();
	}

	public void setPublicacao(Publicacao p) {
		id.setPublicacao(p);
	}

	public Reacoes getReacao() {
		return id.getReacao();
	}

	public void setReacao(Reacoes r) {
		id.setReacao(r);
	}
}
