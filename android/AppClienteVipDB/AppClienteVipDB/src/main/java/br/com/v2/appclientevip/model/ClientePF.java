package br.com.v2.appclientevip.model;

public class ClientePF extends Cliente  {

    private int fk;
    private String cpf;
    private String nomeCompleto;
    private String dataInc;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public int getFk() {
        return fk;
    }

    public void setFk(int fk) {
        this.fk = fk;
    }

    @Override
    public String getDataInc() {
        return dataInc;
    }

    @Override
    public void setDataInc(String dataInc) {
        this.dataInc = dataInc;
    }
}
