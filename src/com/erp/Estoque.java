package com.erp;

import java.io.*;
import java.util.*;

public class Estoque {
	private List<Produto> produtos;
	private List<Titulo> titulos;

	private static final String PRODUTOS_ARQUIVO = "produtos.txt";
	private static final String TITULOS_ARQUIVO = "titulos.txt";

	public Estoque() throws IOException {
		produtos = new ArrayList<>();
		titulos = new ArrayList<>();
		carregaProduto();
		carregaTitulos();
	}

	public void addProduto(Scanner scanner) throws IOException {
		System.out.print("ID do Produto: ");
		String id = scanner.nextLine();
		System.out.print("Nome do Produto: ");
		String nome = scanner.nextLine();
		System.out.print("Preço do Produto: ");
		double preco = scanner.nextDouble();
		scanner.nextLine(); // Consumir nova linha

		Produto produto = new Produto(id, nome, preco);
		produtos.add(produto);
		saveProdutos();
		System.out.println("Produto adicionado com sucesso.");
	}
	
	public void removerProdutoEstoque(Scanner scanner) throws IOException {//Remoção de produtos em estoque
		String idProduto;
		listaProdutos();
		System.out.print("Digite o id do produto: ");
		idProduto = scanner.nextLine();
		for (Produto p : produtos) {
			if (p.getId().equals(idProduto)) {
				produtos.remove(p);
				saveProdutos();
				break;
			}
		}
		
	}

	public void listaProdutos() {
		System.out.println("Produtos:");
		for (Produto produto : produtos) {
			System.out.println(produto.getId() + " - " + produto.getNome() + " - R$ " + produto.getPreco());
		}
	}

	public void compraProduto(Scanner scanner) throws IOException {
		listaProdutos();//Adicionei para mostrar os itens disponiveis para compra
		System.out.print("ID do Produto a comprar: ");
		String produtoId = scanner.nextLine();

		Produto produto = null;
		for (Produto p : produtos) {
			if (p.getId().equals(produtoId)) {
				produto = p;
				break;
			}
		}

		if (produto != null) {
			Titulo titulo = new Titulo(UUID.randomUUID().toString(), produto.getPreco(), false);
			titulos.add(titulo);
			saveTitulos();
			System.out.println("Produto comprado. Título gerado: " + titulo.getId());
		} else {
			System.out.println("Produto não encontrado.");
		}
	}

	public void fazPagamento(Scanner scanner) throws IOException {
		listarTitulosEmAberto();
		System.out.print("ID do Título a pagar: ");
		String tituloId = scanner.nextLine();

		Titulo titulo = null;
		for (Titulo t : titulos) {
			if (t.getId().equals(tituloId)) {
				titulo = t;
				break;
			}
		}

		if (titulo != null) {
			titulo.setPaga(true);
			saveTitulos();
			System.out.println("Título pago com sucesso.");
		} else {
			System.out.println("Título não encontrado.");
		}
	}

	public void listarTitulosEmAberto() {
		System.out.println("Títulos em Aberto:");
		for (Titulo title : titulos) {
			if (!title.isPago()) {
				System.out.println(title.getId().toString() + " - R$ " + title.getQuantidade());
			}
		}
	}
	
	public void removerProdutoEmAberto(Scanner scanner) throws IOException {// Remover titulos em aberto
		String idTitulo;
		listarTitulosEmAberto();
		System.out.println("Digite o Id do produto que será removido: ");
		idTitulo = scanner.nextLine();
		for (Titulo title : titulos) {
			if(title.getId().equals(idTitulo)) {
				titulos.remove(title);
				saveTitulos();
				break;
			}
		}
	}

	private void carregaProduto() throws IOException {
		File file = new File(PRODUTOS_ARQUIVO);
		if (file.exists()) {
			try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
				String line;
				while ((line = reader.readLine()) != null) {
					produtos.add(Produto.fromString(line));
				}
			}
		}
	}

	private void carregaTitulos() throws IOException {
		File file = new File(TITULOS_ARQUIVO);
		if (file.exists()) {
			try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
				String line;
				while ((line = reader.readLine()) != null) {
					titulos.add(Titulo.fromString(line));
				}
			}
		}
	}

	private void saveProdutos() throws IOException {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(PRODUTOS_ARQUIVO))) {
			for (Produto product : produtos) {
				writer.write(product.toString());
				writer.newLine();
			}
		}
	}

	private void saveTitulos() throws IOException {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(TITULOS_ARQUIVO))) {
			for (Titulo title : titulos) {
				writer.write(title.toString());
				writer.newLine();
			}
		}
	}
}
