package Entidades;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Embeddable
@Table(name = "projeto_funcionario")

public class ProjetoFuncionario implements Serializable {
	@Id
	@ManyToOne
	@JoinColumn(name = "PROJETOS_ID_PROJETO")
	private Projetos projetoId;

	@Id
	@ManyToOne
	@JoinColumn(name = "FUNCIONARIOS_ID_FUNCIONARIO")
	private Funcionarios funcionarioId;

	public Projetos getProjetoId() {
		return projetoId;
	}

	public void setProjetoId(Projetos projetoId) {
		this.projetoId = projetoId;
	}

	public Funcionarios getFuncionarioId() {
		return funcionarioId;
	}

	public void setFuncionarioId(Funcionarios funcionarioId) {
		this.funcionarioId = funcionarioId;
	}
}