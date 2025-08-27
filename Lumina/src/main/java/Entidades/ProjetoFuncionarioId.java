package Entidades;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ProjetoFuncionarioId implements Serializable {

	private double projetoId;
	private String funcionarioId;
	

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjetoFuncionarioId that = (ProjetoFuncionarioId) o;
        return Objects.equals(projetoId, that.projetoId) &&
               Objects.equals(funcionarioId, that.funcionarioId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projetoId, funcionarioId);
    }
}
