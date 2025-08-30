package Dto;

import Entidades.Area;

public class FuncionariosDTO {
    
    private String id;
    private String nome;
    private String email;
    private Double area;
	private String foto;

    public FuncionariosDTO(String id, String nome, String email, Double idArea, String foto) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.area = idArea;
        this.foto = foto;
    }

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
    
    public String getFoto() {
        return foto;
    }
    public void setFoto(String foto) {
        this.foto = foto;
    }
}
