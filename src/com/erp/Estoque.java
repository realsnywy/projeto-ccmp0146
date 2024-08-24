package com.erp;

import java.io.*;
import java.util.*;
import javax.swing.JTextField;

public class Estoque {
	public List<Produto> produtos;
	private List<Titulo> titulos;
	public static final String PRODUTOS_ARQUIVO = "produtos.txt";
	private static final String TITULOS_ARQUIVO = "titulos.txt";

	public Estoque() throws IOException {
		produtos = new ArrayList<>();
		titulos = new ArrayList<>();
		carregaProduto();
		carregaTitulos();
	}
	
	public List<Produto> getProdutos() {
        return produtos;
    }

	public void addProduto(JTextField textFieldNomeDoProduto, JTextField textFieldPrecoProduto, JTextField textFieldQuantidadeProduto) throws IOException {
	    String nome = textFieldNomeDoProduto.getText();
	    double preco = Double.parseDouble(textFieldPrecoProduto.getText());
	    int quantidade = Integer.parseInt(textFieldQuantidadeProduto.getText());
	    
	    Produto produtoExistente = null;
	    for (Produto p : produtos) {
	        if (p.getNome().equalsIgnoreCase(nome)) { // Verifica se o produto já existe pelo nome
	            produtoExistente = p;
	            break;
	        }
	    }
	    
	    if (produtoExistente != null) {
	        // Produto já existe, incrementa a quantidade
	        produtoExistente.setQuantidade(produtoExistente.getQuantidade() + quantidade);
	    } else {
	        // Produto não existe, cria um novo
	        int tamanho1 = produtos.size() + 1;
	        String id = String.valueOf(tamanho1);
	        Produto novoProduto = new Produto(id, nome, preco, quantidade);
	        produtos.add(novoProduto);
	    }
	    
	    saveProdutos();
	}
	
	public void removerProdutoEstoque(JTextField textFieldRemover) throws IOException {//Remoção individual de produtos em estoque
		String idProduto = textFieldRemover.getText().trim();
		Produto produtoRemover = null;
		for (Produto p : produtos) {
			if (p.getId().equals(idProduto)) {
				if (p.getQuantidade() == 0) {
					produtoRemover = p;
				}else {
				    p.setQuantidade(p.getQuantidade() - 1);	
				}
				saveProdutos();
				break;
			 }
		}
			 if (produtoRemover != null) {
			        produtos.remove(produtoRemover);  // Remove o produto fora do loop
			        saveProdutos();
			 }
		}
	
	public void retirarProdutoEstoque(JTextField textFieldRemover) throws IOException {//Retirada de produtos em estoque
		 String idProduto = textFieldRemover.getText().trim();
		    Produto produtoRemover = getProdutoPorId(idProduto);
		    if (produtoRemover != null) {
		        produtos.remove(produtoRemover);
		        saveProdutos();
		    }
		}

	public String compraProduto(JTextField textFieldColocarId) throws IOException {

		String produtoId = textFieldColocarId.getText();
		Produto produto = null;
		for (Produto p : produtos) {
			if (p.getId().equals(produtoId)) {
				produto = p;
				break;
			}
		}

		if (produto != null) {
			Titulo titulo = new Titulo(UUID.randomUUID().toString(), produto.getPreco(), false, produtoId);
			titulos.add(titulo);
			saveTitulos();
			return "Produto comprado. Título gerado: " + titulo.getId();
		} else {
			return "Produto não encontrado.";
		}
	}

	public void fazPagamento() throws IOException {

		 for (Titulo t : titulos) {
		        if (!t.isPago()) { 
		            t.setPaga(true);
		        }
		    }
		    saveTitulos();
	}

	public void listarTitulosEmAberto() {
		System.out.println("Títulos em Aberto:");
		for (Titulo title : titulos) {
			if (!title.isPago()) {
				System.out.println(title.getId().toString() + " - R$ " + title.getQuantidade());
			}
		}
	}
	
	public void removerProdutoEmAberto(JTextField textFieldColocarId) throws IOException {// Remover titulos em aberto
		String idTitulo;
		idTitulo = textFieldColocarId.getText();
		for (Titulo title : titulos) {
			if(title.getProdutoId().equals(idTitulo)) {
				titulos.remove(title);
				saveTitulos();
				break;
			}
		}
	}
	public List<Object[]> getTitulosEmAbertoComDetalhes() {
        List<Object[]> titulosComDetalhes = new ArrayList<>();
        for (Titulo titulo : titulos) {
            if (!titulo.isPago()) {
                Produto produto = getProdutoPorId(titulo.getProdutoId());
                if (produto != null) {
                    titulosComDetalhes.add(new Object[]{
                        titulo.getId(),
                        produto.getNome(),
                        produto.getPreco(),
                        titulo.getQuantidade()
                    });
                }
            }
        }
        return titulosComDetalhes;
    }

    private Produto getProdutoPorId(String produtoId) {
        for (Produto produto : produtos) {
            if (produto.getId().equals(produtoId)) {
                return produto;
            }
        }
        return null;
    }

    private void carregaProduto() throws IOException {
		File file = new File(PRODUTOS_ARQUIVO);
		if (file.exists()) {
			try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
				String line;
				while ((line = reader.readLine()) != null) {
					Produto produto = Produto.fromString(line);
		                produtos.add(produto);
				}
			}
		}
	}
    
    public double calcularTotalTitulosEmAberto() {
        double total = 0;
        for (Titulo titulo : titulos) {
            if (!titulo.isPago()) {
                total += titulo.getQuantidade();
            }
        }
        return total;
    }

	private void carregaTitulos() throws IOException {
		File file = new File(TITULOS_ARQUIVO);
		if (file.exists()) {
			try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
				String line;
				while ((line = reader.readLine()) != null) {
					try {
	                    titulos.add(Titulo.fromString(line));
	                } catch (IllegalArgumentException e) {
	                    System.err.println("Linha inválida no arquivo de títulos: " + line);
	                    e.printStackTrace(); // Opcional: para mais detalhes do erro
	                }
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
