package model;
import java.util.ArrayList;
import java.util.List;


/* Entender a palavra Título como uma venda ou uma compra, a compra ainda vai ser implementada, por enquanto apenas temos a venda. */

public class Titulo {
	private String id;
	private double preco;
	private boolean paga;
	//A venda agora vai ter uma lista de produtos em vez de ser um produto e uma quantidade de produtos.
	public List<Produto> produtosCarrinho;
	

	public Titulo(String id, double preco, boolean paga, List<Produto> produtosCarrinho) {
		this.id = id;
		this.preco = preco;
		this.paga = paga;
		this.produtosCarrinho = produtosCarrinho;
	}

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public double getPreco() {
		return preco;
	}
	
	public void setPreco(double preco) {
		this.preco = preco;
	}

	public boolean isPago() {
		return paga;
	}

	public void setPaga(boolean paga) {
		this.paga = paga;
	}
	public List<Produto> getProdutosCarrinho() {
        return produtosCarrinho;
    }
	public void setProdutosCarrinho(List<Produto> produtosCarrinho) {
		this.produtosCarrinho = produtosCarrinho;
	}

	@Override
	public String toString() {
        // Convertendo a lista de produtos para uma string separada por ponto e vírgula
        StringBuilder produtosStr = new StringBuilder();
        for (Produto produto : produtosCarrinho) {
            produtosStr.append(produto.toString()).append(";");
        }
        
        // Removendo o último ponto e vírgula
        if (produtosStr.length() > 0) {
            produtosStr.deleteCharAt(produtosStr.length() - 1);
        }

        return id + "," + preco + "," + paga + "," + produtosStr;
    }
	public static Titulo fromString(String str) {
		String[] parts = str.split(",", 4);
		if (parts.length < 4) {
	        throw new IllegalArgumentException("Formato de string inválido: " + str);
	    }
		// Processando a lista de produtos
        List<Produto> produtosCarrinho = new ArrayList<>();
        String[] produtosArray = parts[3].split(";");
        for (String produtoStr : produtosArray) {
            Produto produto = Produto.fromString(produtoStr); // Conversão de string para Produto
            produtosCarrinho.add(produto);
        }
		return new Titulo(parts[0], Double.parseDouble(parts[1]), Boolean.parseBoolean(parts[2]), produtosCarrinho);
	}
}
