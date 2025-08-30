package Entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Comentarios {
		@Id
		@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comentario_seq")
		@SequenceGenerator(name = "comentario_seq", sequenceName = "SEQ_COMENTARIO", allocationSize = 1)
		@Column(name = "id_comentario")
		private Long idComentario;
		
		private String descricao;
		
		@JoinColumn(name = "id_funcionario")
		@ManyToOne
		private Funcionarios idFuncionario;

		@JoinColumn(name = "id_publicacao")
		@ManyToOne
		private Publicacao idPublicacao;

		public Long getIdComentario() {
			return idComentario;
		}

		public void setIdComentario(Long idComentario) {
			this.idComentario = idComentario;
		}

		public String getDescricao() {
			return descricao;
		}

		public void setDescricao(String descricao) {
			this.descricao = descricao;
		}

		public Funcionarios getIdFuncionario() {
			return idFuncionario;
		}

		public void setIdFuncionario(Funcionarios idFuncionario) {
			this.idFuncionario = idFuncionario;
		}

		public Publicacao getIdPublicacao() {
			return idPublicacao;
		}

		public void setIdPublicacao(Publicacao idPublicacao) {
			this.idPublicacao = idPublicacao;
		}	

}
