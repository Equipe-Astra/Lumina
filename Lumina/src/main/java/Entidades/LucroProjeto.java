package Entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class LucroProjeto {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lucro_seq")
	@SequenceGenerator(name = "lucro_seq", sequenceName = "SEQ_LUCRO", allocationSize = 1)
	@Column(name = "id_lucro")
	private Long idLucro;

	private double lucro;

	@JoinColumn(name = "id_projeto")
	@OneToOne
	private Projetos idProjeto;

	public Long getIdLucro() {
		return idLucro;
	}

	public void setIdLucro(Long idLucro) {
		this.idLucro = idLucro;
	}

	public double getLucro() {
		return lucro;
	}

	public void setLucro(double lucro) {
		this.lucro = lucro;
	}

	public Projetos getIdProjeto() {
		return idProjeto;
	}

	public void setIdProjeto(Projetos idProjeto) {
		this.idProjeto = idProjeto;
	}

}
