package com.erp;

public class Produto {
	private String id;
	private String nome;
	private double preco;
	private int quantidade;

	public Produto(String id, String nome, double preco, int quantidade) {
		this.id = id;
		this.nome = nome;
		this.preco = preco;
		this.quantidade = quantidade;
	}

	public String getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public double getPreco() {
		return preco;
	}

	public int getQuantidade() {
		return quantidade;
	}
	public int setQuantidade(int quantidade) {
		return this.quantidade = quantidade;
	}

	@Override
	public String toString() {
		return id + "," + nome + "," + preco + "," + quantidade;
	}

	public static Produto fromString(String str) {
		String[] parts = str.split(",");
		return new Produto(parts[0], parts[1], Double.parseDouble(parts[2]), Integer.parseInt(parts[3]));
	}
}
