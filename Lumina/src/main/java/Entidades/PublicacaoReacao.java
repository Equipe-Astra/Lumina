package Entidades;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "publicacao_reacao")
public class PublicacaoReacao implements Serializable {

    @EmbeddedId
    private PublicacaoReacaoId id;

    @Column(name = "quantidade")
    private Double quantidade;

    public PublicacaoReacaoId getId() {
        return id;
    }

    public void setId(PublicacaoReacaoId id) {
        this.id = id;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }
}
