package model;

public class Usuario {
    private String nome;
    private String senha;
    private String nomeColaborador;
    private String setor;
    private  double vendas;

    public Usuario(String nome, String senha, String nomeColaborador, String setor, double vendas) {
        this.nome = nome;
        this.senha = senha;
        this.nomeColaborador = nomeColaborador;
        this.setor = setor;
        this.vendas = vendas;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }
    public String getNomeColaborador() {
        return nomeColaborador;
    }

    public void setNomeColaborador(String nomeColaborador) {
        this.nomeColaborador = nomeColaborador;
    }
    public double getVendas() {
        return vendas;
    }

    public void setVendas(double vendas) {
        this.vendas = vendas;
    }
}
