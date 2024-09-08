package com.erp;

import java.time.LocalDate;

public class Produto {
	private String id;
	private String nome;
	private double preco;
	private int quantidade;
<<<<<<< Updated upstream:src/com/erp/Produto.java

	public Produto(String id, String nome, double preco, int quantidade) {
=======
	private float peso;
	private float pesoTotalEmEstouqe;
	private LocalDate vencimento;
	private boolean precoAlterado;

	public Produto(String id, String nome, double preco, int quantidade, float peso, float pesoTotalEmEstouqe, LocalDate vencimento) {
>>>>>>> Stashed changes:src/model/Produto.java
		this.id = id;
		this.nome = nome;
		this.preco = preco;
		this.quantidade = quantidade;
<<<<<<< Updated upstream:src/com/erp/Produto.java
=======
		this.peso = peso;
		this.pesoTotalEmEstouqe = pesoTotalEmEstouqe;
		this.vencimento = vencimento;
		this.precoAlterado = false;
>>>>>>> Stashed changes:src/model/Produto.java
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

	public LocalDate getVencimento() {
		return vencimento;
	}

	public void setVencimento(LocalDate vencimento) {
		this.vencimento = vencimento;
	}

	public boolean isPrecoAlterado() {
		return precoAlterado;
	}

	public void setPrecoAlterado(boolean precoAlterado) {
		this.precoAlterado = precoAlterado;
	}
	
	public void setNovoPreco(double novoPreco) {
		this.preco = novoPreco;
		this.precoAlterado = true;
		
	}

	@Override
<<<<<<< Updated upstream:src/com/erp/Produto.java
	public String toString() {
		return id + "," + nome + "," + preco + "," + quantidade;
=======
	public String toString() {	
		return id + "," + nome + "," + preco + "," + quantidade + "," + peso + "," + pesoTotalEmEstouqe + "," + vencimento;
>>>>>>> Stashed changes:src/model/Produto.java
	}

	public static Produto fromString(String str) {
		
		
		String[] parts = str.split(",");
<<<<<<< Updated upstream:src/com/erp/Produto.java
		return new Produto(parts[0], parts[1], Double.parseDouble(parts[2]), Integer.parseInt(parts[3]));
=======
		return new Produto(parts[0], parts[1], Double.parseDouble(parts[2]), Integer.parseInt(parts[3]), Float.parseFloat(parts[4]), Float.parseFloat(parts[5])
				, LocalDate.parse(parts[6]));
>>>>>>> Stashed changes:src/model/Produto.java
	}
}
