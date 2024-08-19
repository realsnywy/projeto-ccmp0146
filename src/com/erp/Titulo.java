package com.erp;

public class Titulo {
	private String id;
	private double quantidade;
	private boolean paga;
	private String produtoId;

	public Titulo(String id, double quantidade, boolean paga, String produtoId) {
		this.id = id;
		this.quantidade = quantidade;
		this.paga = paga;
		this.produtoId = produtoId;
	}

	public String getId() {
		return id;
	}

	public double getQuantidade() {
		return quantidade;
	}

	public boolean isPago() {
		return paga;
	}

	public void setPaga(boolean paga) {
		this.paga = paga;
	}
	public String getProdutoId() {
        return produtoId;
    }

	@Override
	public String toString() {
		return id + "," + quantidade + "," + paga + "," + produtoId;
	}

	public static Titulo fromString(String str) {
		String[] parts = str.split(",");
		if (parts.length != 4) {
	        throw new IllegalArgumentException("Formato de string inválido: " + str);
	    }
		return new Titulo(parts[0], Double.parseDouble(parts[1]), Boolean.parseBoolean(parts[2]), parts[3]);
	}
}
