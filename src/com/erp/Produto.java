package com.erp;

public class Produto {
	private String id;
	private String nome;
	private double preco;
	private int quantidade;
	private float peso;
	private float pesoTotalEmEstouqe;

	public Produto(String id, String nome, double preco, int quantidade, float peso, float pesoTotalEmEstouqe) {
		this.id = id;
		this.nome = nome;
		this.preco = preco;
		this.quantidade = quantidade;
		this.peso = peso;
		this.pesoTotalEmEstouqe = pesoTotalEmEstouqe;
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
	
	public float getPeso() {
		return peso;
	}
	public float setPeso(float peso) {
		return this.peso = peso;
	}
	public float getPesoTotal() {
		return pesoTotalEmEstouqe;
	}
	public float setPesoTotal(float pesoTotalEmEstoque) {
		return this.pesoTotalEmEstouqe = pesoTotalEmEstoque;
	}

	@Override
	public String toString() {
		return id + "," + nome + "," + preco + "," + quantidade + "," + peso + "," + pesoTotalEmEstouqe;
	}

	public static Produto fromString(String str) {
		String[] parts = str.split(",");
		return new Produto(parts[0], parts[1], Double.parseDouble(parts[2]), Integer.parseInt(parts[3]), Float.parseFloat(parts[4]), Float.parseFloat(parts[5]));
	}
}
