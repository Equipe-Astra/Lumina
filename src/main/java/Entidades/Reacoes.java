package Entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Reacoes {
	@Id
	@Column(name = "id_reacao")
	private Double idReacao;
	private String descricao;

	public Double getIdReacao() {
		return idReacao;
	}

	public void setIdReacao(Double idReacao) {
		this.idReacao = idReacao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
