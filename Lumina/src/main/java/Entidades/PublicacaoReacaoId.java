package Entidades;

import java.io.Serializable;
import javax.persistence.*;

@Embeddable
public class PublicacaoReacaoId implements Serializable {

    @ManyToOne
    @JoinColumn(name = "id_publicacao")
    private Publicacao publicacao;

    @ManyToOne
    @JoinColumn(name = "id_reacao")
    private Reacoes reacao;

    // getters e setters
    public Publicacao getPublicacao() {
        return publicacao;
    }

    public void setPublicacao(Publicacao publicacao) {
        this.publicacao = publicacao;
    }

    public Reacoes getReacao() {
        return reacao;
    }

    public void setReacao(Reacoes reacao) {
        this.reacao = reacao;
    }
}
