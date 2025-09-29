package Entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class Status {
	
	@Id
    @Column(name = "id_status")
    private double id;

    @Column(name = "descricao")
    private String descricao;

	public double getId() {
		return id;
	}

	public void setId(double id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
   
}
