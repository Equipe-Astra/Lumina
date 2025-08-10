package Entidades;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Cargo {

    @Id
    private Double ID_CARGO;
    private String DESCRICAO;

    public Double getIdCargo() {
        return ID_CARGO;
    }

    public void setIdCargo(Double idCargo) {
        this.ID_CARGO = idCargo;
    }

    public String getDescricao() {
        return DESCRICAO;
    }

    public void setDescricao(String descricao) {
        this.DESCRICAO = descricao;
    }
}
