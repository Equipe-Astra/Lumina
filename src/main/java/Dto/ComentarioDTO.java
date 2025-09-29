package Dto;

public class ComentarioDTO {
	private String idComentario;
	private String idPublicacao;
	private String descricao;
	private String nomeFuncionario;
	private String imagemBase64;
	public String getIdComentario() {
		return idComentario;
	}
	public void setIdComentario(String idComentario) {
		this.idComentario = idComentario;
	}
	public String getIdPublicacao() {
		return idPublicacao;
	}
	public void setIdPublicacao(String idPublicacao) {
		this.idPublicacao = idPublicacao;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getNomeFuncionario() {
		return nomeFuncionario;
	}
	public void setNomeFuncionario(String nomeFuncionario) {
		this.nomeFuncionario = nomeFuncionario;
	}
	public String getImagemBase64() {
		return imagemBase64;
	}
	public void setImagemBase64(String imagemBase64) {
		this.imagemBase64 = imagemBase64;
	}

}
