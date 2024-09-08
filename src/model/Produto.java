package model;

import java.time.LocalDate;

public class Produto {
	private String id;
	private String nome;
	private double preco;
	private int quantidade;
	private float peso;
	private float pesoTotalEmEstouqe;
	private LocalDate vencimento;
	private boolean precoAlterado;

	public Produto(String id, String nome, double preco, int quantidade, float peso, float pesoTotalEmEstouqe, LocalDate vencimento) {
		this.id = id;
		this.nome = nome;
		this.preco = preco;
		this.quantidade = quantidade;
		this.peso = peso;
		this.pesoTotalEmEstouqe = pesoTotalEmEstouqe;
		this.vencimento = vencimento;
		this.precoAlterado = false;
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
	public String toString() {
		return id + "," + nome + "," + preco + "," + quantidade + "," + peso + "," + pesoTotalEmEstouqe + "," + vencimento;
	}

	public static Produto fromString(String str) {
		String[] parts = str.split(",");
		if (parts.length != 7) {
	        throw new IllegalArgumentException("Formato inválido para produto: " + str);
	    }
		try {
		return new Produto(parts[0], parts[1], Double.parseDouble(parts[2]), Integer.parseInt(parts[3]), Float.parseFloat(parts[4]), Float.parseFloat(parts[5]), LocalDate.parse(parts[6]));
		} catch (NumberFormatException e) {
	        throw new IllegalArgumentException("Erro ao converter valores numéricos para produto: " + str, e);
	    }
	}
}
