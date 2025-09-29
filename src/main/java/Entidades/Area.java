package Entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Area {
	
	@Id
	@Column(name = "ID_AREA")
	private Double idArea;
	private String descricao;
	
	public Double getIdArea() {
		return idArea;
	}
	public void setIdArea(Double idArea) {
		this.idArea = idArea;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}