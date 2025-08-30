package Entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity

public class Funcionarios {
	@Id 
	@Column(name = "id_funcionario")
	private String id;
	private String nome;
	private String email;
	private String senha;
	
    @Lob
    private byte[] foto;
	
    @JoinColumn(name = "id_cargo")
    @ManyToOne
    private Cargo idCargo;
    
    @JoinColumn(name = "ID_AREA")
    @ManyToOne
    private Area idArea;

	public String getId() {
		return id;
	}

	public void setId(String id) {
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
	
	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public Cargo getIdCargo() {
		return idCargo;
	}

	public void setIdCargo(Cargo idCargo) {
		this.idCargo = idCargo;
	}

	public Area getIdArea() {
		return idArea;
	}

	public void setIdArea(Area idArea) {
		this.idArea = idArea;
	}
}