package Dto;

public class FuncionarioInfo {
	private String idFuncionario;
    private String senha;
    private String cargo;

    public FuncionarioInfo(String idFuncionario, String senha, String cargo) {
        this.idFuncionario = idFuncionario;
        this.senha = senha;
        this.cargo = cargo;
    }

	public String getIdFuncionario() {
		return idFuncionario;
	}

	public void setIdFuncionario(String idFuncionario) {
		this.idFuncionario = idFuncionario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

}
