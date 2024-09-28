package model;

public class Concorrencia {
	private String nome;
	private double preco;
	
	public Concorrencia(String nome, double preco) {
		this.nome = nome;
		this.preco = preco;
	}

	public String getNome() {
		return nome;
	}

	public double getPreco() {
		return preco;
	}


	@Override
	public String toString() {
		return nome + "," + preco;
	}

	public static Concorrencia fromString(String str) {
		String[] parts = str.split(",");
		if (parts.length != 2) {
	        throw new IllegalArgumentException("Formato inválido para produto: " + str);
	    }
		try {
		return new Concorrencia(parts[0], Double.parseDouble(parts[1]));
		} catch (NumberFormatException e) {
	        throw new IllegalArgumentException("Erro ao converter valores numéricos para produto: " + str, e);
	    }
	}
}
