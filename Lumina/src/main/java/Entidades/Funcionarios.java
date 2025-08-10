package Entidades;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity

public class Funcionarios {
	@Id
	private Double id;
	private String nome;
	private String email;
	private String senha;
	
    @JoinColumn(name = "id_cargo")
    @ManyToOne
    private Cargo idCargo;

	public Double getId() {
		return id;
	}

	public void setId(Double id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public Cargo getIdCargo() {
		return idCargo;
	}

	public void setIdCargo(Cargo idCargo) {
		this.idCargo = idCargo;
	}

}
