package model;

import java.util.ArrayList;
import java.util.List;

public class NotaFiscal {

    //Leo, para entender melhor, quando for criar um objeto do tipo nota, a maioria dos dados vão vir de um título(venda OU compra).
    //A nota só vai ser gerada após o título(Venda ou Compra) constar como PAGA.
    
    private String idNota;
    private String nomeVendedor;
    private String nomeCliente;
    private List<Produto> produtosNotas;
    private double valor;
    private String data;

    public NotaFiscal(String idNota, String nomeVendedor, String nomeCliente, double valor, String data, List<Produto> produtosNotas){
        this.idNota = idNota;
        this.nomeVendedor = nomeVendedor;
        this.nomeCliente = nomeCliente;
        this.produtosNotas = produtosNotas;
        this.valor = valor;
        this.data = data;

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
        return produtosNotas;
    }

    // Setter para produtos
    public void setProdutos(List<Produto> produtosNotas) {
        this.produtosNotas = produtosNotas;
    }

    // Getter para valor
    public double getValor() {
        return valor;
    }

    // Setter para valor
    public void setValor(double valor) {
        this.valor = valor;
    }
    
    public String getData() {
        return data;
    }

    // Setter para valor
    public void setValor(String data) {
        this.data = data;
    }
    
    @Override
	public String toString() {
        // Convertendo a lista de produtos para uma string separada por ponto e vírgula
        StringBuilder produtosStr = new StringBuilder();
        for (Produto produto : produtosNotas) {
            produtosStr.append(produto.toString()).append(";");
        }
        
        // Removendo o último ponto e vírgula
        if (produtosStr.length() > 0) {
            produtosStr.deleteCharAt(produtosStr.length() - 1);
        }

        return idNota + "," + nomeVendedor + "," + nomeCliente + "," + valor + "," + data + "," + produtosStr;
    }
	public static NotaFiscal fromString(String str) {
		String[] parts = str.split(",", 6);
		if (parts.length < 6) {
	        throw new IllegalArgumentException("Formato de string inválido: " + str);
	    }
		// Processando a lista de produtos
        List<Produto> produtosNotas = new ArrayList<>();
        String[] produtosArray = parts[5].split(";");
        for (String produtoStr : produtosArray) {
            Produto produto = Produto.fromString(produtoStr); // Conversão de string para Produto
            produtosNotas.add(produto);
        }
		return new NotaFiscal(parts[0], parts[1], parts[2], Double.parseDouble(parts[3]), parts[4], produtosNotas);
	}
}