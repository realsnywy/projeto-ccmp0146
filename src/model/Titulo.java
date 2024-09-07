package model;
import java.util.List;


/* Entender a palavra Título como uma venda ou uma compra, a compra ainda vai ser implementada, por enquanto apenas temos a venda. */

public class Titulo {
	private String id;
	private double preco;
	private boolean paga;
	//A venda agora vai ter uma lista de produtos em vez de ser um produto e uma quantidade de produtos.
	private List<Produto> produtos;
	

	public Titulo(String id, String nome, double preco, boolean paga, List<Produto> produtos) {
		this.id = id;
		this.preco = preco;
		this.paga = paga;
		this.produtos = produtos;
	}

	public String getId() {
		return id;
	}

	public double getPreco() {
		return preco;
	}

	public boolean isPago() {
		return paga;
	}

	public void setPaga(boolean paga) {
		this.paga = paga;
	}
	public String getNome() {
        return nome;
    }
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getQuantidade() {
        return quantidade;
    }
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public String getProdutoId() {
        return produtoId;
    }
	public void setProdutoId(String produtoId) {
		this.produtoId = produtoId;
	}

	@Override
	public String toString() {
		return id + "," + nome + "," + preco + "," + paga + "," + quantidade + "," + produtoId;
	}

	public static Titulo fromString(String str) {
		String[] parts = str.split(",");
		if (parts.length != 6) {
	        throw new IllegalArgumentException("Formato de string inválido: " + str);
	    }
		return new Titulo(parts[0], parts[1], Double.parseDouble(parts[2]), Boolean.parseBoolean(parts[3]), Integer.parseInt(parts[4]), parts[5]);
	}
}
