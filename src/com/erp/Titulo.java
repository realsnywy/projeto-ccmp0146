package com.erp;

public class Titulo {
	private String id;
	private double quantidade;
	private boolean paga;

	public Titulo(String id, double quantidade, boolean paga) {
		this.id = id;
		this.quantidade = quantidade;
		this.paga = paga;
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

	@Override
	public String toString() {
		return id + "," + quantidade + "," + paga;
	}

	public static Titulo fromString(String str) {
		String[] parts = str.split(",");
		return new Titulo(parts[0], Double.parseDouble(parts[1]), Boolean.parseBoolean(parts[2]));
	}
}
