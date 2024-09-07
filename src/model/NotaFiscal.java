package model;

import java.util.List;

public class NotaFiscal {

    //Leo, para entender melhor, quando for criar um objeto do tipo nota, a maioria dos dados vão vir de um título(venda OU compra).
    //A nota só vai ser gerada após o título(Venda ou Compra) constar como PAGA.
    
    private String idNota;
    private String nomeVendedor;
    private String nomeCliente;
    private List<Produto> produtos;
    private float valor;

    public NotaFiscal(String idNota, String nomeVendedor, String nomeCliente, List<Produto> produtos, float valor){
        this.idNota = idNota;
        this.nomeVendedor = nomeVendedor;
        this.nomeCliente = nomeCliente;
        this.produtos = produtos;
        this.valor = valor;

    }

    public String getIdNota(){
        return idNota;
    }

    public void setIdNota(String idNota){
        this.idNota = idNota;
    }

    public String getNomeVendedor(){
        return nomeVendedor;
    }

    public void setNomeVendedor(String nomeVendedor){
        this.nomeVendedor = nomeVendedor;
    }

    public String getNomeCliente(){
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente){
        this.nomeCliente = nomeCliente;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    // Setter para produtos
    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    // Getter para valor
    public float getValor() {
        return valor;
    }

    // Setter para valor
    public void setValor(float valor) {
        this.valor = valor;
    }






}
