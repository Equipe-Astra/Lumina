package Dto;

import Entidades.Area;

public class FuncionariosDTO {
    
    private String id;
    private String nome;
    private String email;
    private Double area;

    public FuncionariosDTO(String id, String nome, String email, Double idArea) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.area = idArea;
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
}
