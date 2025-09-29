package Dto;

public class EvolucaoMensalDTO {
    private String mes;
    private int naoIniciado;
    private int emAndamento;
    private int concluido;

  
    public EvolucaoMensalDTO(String mes, int naoIniciado, int emAndamento, int concluido) {
        this.mes = mes;
        this.naoIniciado = naoIniciado;
        this.emAndamento = emAndamento;
        this.concluido = concluido;
    }

	public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public int getNaoIniciado() {
        return naoIniciado;
    }

    public void setNaoIniciado(int naoIniciado) {
        this.naoIniciado = naoIniciado;
    }

    public int getEmAndamento() {
        return emAndamento;
    }

    public void setEmAndamento(int emAndamento) {
        this.emAndamento = emAndamento;
    }

    public int getConcluido() {
        return concluido;
    }

    public void setConcluido(int concluido) {
        this.concluido = concluido;
    }
}
