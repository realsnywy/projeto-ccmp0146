package com.erp;

public class Produto {
	private String id;
	private String nome;
	private double preco;

	public Produto(String id, String nome, double preco) {
		this.id = id;
		this.nome = nome;
		this.preco = preco;
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

	@Override
	public String toString() {
		return id + "," + nome + "," + preco;
	}

	public static Produto fromString(String str) {
		String[] parts = str.split(",");
		return new Produto(parts[0], parts[1], Double.parseDouble(parts[2]));
	}
}
