package com.erp;

import java.io.*;
import java.time.LocalDate;
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

<<<<<<< Updated upstream
	public void addProduto(JTextField textFieldNomeDoProduto, JTextField textFieldPrecoProduto, JTextField textFieldQuantidadeProduto) throws IOException {
=======
	public void addProduto(JTextField textFieldNomeDoProduto, JTextField textFieldPrecoProduto, JTextField textFieldQuantidadeProduto, JTextField textFieldPeso,
			JTextField textFieldDia, JTextField textFieldMes, JTextField textFieldAno) throws IOException {
>>>>>>> Stashed changes
	    String nome = textFieldNomeDoProduto.getText();
	    double preco = Double.parseDouble(textFieldPrecoProduto.getText());
	    int quantidade = Integer.parseInt(textFieldQuantidadeProduto.getText());
	    
	    LocalDate vencimento = LocalDate.of(3000, 12, 12); //@Felipee013 Se n adicionar data == produto n perecível
	    												   // aí a data vai pra 12/12/3000
	    
	    if(!textFieldDia.getText().trim().isEmpty() && !textFieldMes.getText().trim().isEmpty() && !textFieldAno.getText().trim().isEmpty()) {
	    	int dia = Integer.parseInt(textFieldDia.getText().trim());//checa para ver se adicionou data aí transforma en int
            int mes = Integer.parseInt(textFieldMes.getText().trim());
            int ano = Integer.parseInt(textFieldAno.getText().trim());
            vencimento = LocalDate.of(ano, mes, dia);
	    }
	     
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
<<<<<<< Updated upstream
	        Produto novoProduto = new Produto(id, nome, preco, quantidade);
	        produtos.add(novoProduto);
=======
	        Produto novoProduto = new Produto(id, nome, preco, quantidade, peso, pesoTotal, vencimento);//@Feliipee013 Passando a data
	        produtos.add(novoProduto);																	//para adicionar ao produto.txt
>>>>>>> Stashed changes
	    }
	    
	    saveProdutos();
	}
	
	public void removerProdutoEstoque(JTextField textFieldRemover, JTextField textFieldQuantidade) throws IOException {//Remoção individual de produtos em estoque

        String idProduto = textFieldRemover.getText().trim();
        int quantidade = Integer.parseInt(textFieldQuantidade.getText().trim());
		
		for (Produto p : produtos) {
			if (p.getId().equals(idProduto)) {
				p.setQuantidade(p.getQuantidade() - quantidade);	
				saveProdutos();
				break;
			}
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

	public String compraProduto(JTextField textFieldColocarId, JTextField textFieldQuantidadeProduto) throws IOException {
	    String produtoId = textFieldColocarId.getText().trim();
	    int produtoQuant = Integer.parseInt(textFieldQuantidadeProduto.getText().trim());
	    Produto produto = null;

	    // Encontra o produto correspondente ao ID fornecido
	    for (Produto p : produtos) {
	        if (p.getId().equals(produtoId)) {
	            produto = p;
	            break;
	        }
	    }

	    // Verifica se o produto foi encontrado
	    if (produto == null) {
	        return "Produto não encontrado.";
	    }

	    // Verifica se a quantidade desejada está disponível no estoque
	    if (produto.getQuantidade() < produtoQuant) {
	        return "Quantidade solicitada excede o estoque disponível.";
	    }

	    // Verifica se existe um título em aberto para este produto
	    for (Titulo title : titulos) {
	        if (!title.isPago() && title.getProdutoId().equals(produto.getId())) {
	            // Atualiza a quantidade no título existente
	            title.setQuantidade(title.getQuantidade() + produtoQuant);
	            produto.setQuantidade(produto.getQuantidade() - produtoQuant);
	            saveTitulos();
	            return "Produto comprado. Quantidade adicionada ao Título em aberto existente.";
	        }
	    }

	    // Se não houver um título em aberto, cria um novo título
	    Titulo novoTitulo = new Titulo(UUID.randomUUID().toString(), produto.getNome(), produto.getPreco(), false, produtoQuant, produto.getId());
	    titulos.add(novoTitulo);
	    produto.setQuantidade(produto.getQuantidade() - produtoQuant);
	    saveTitulos();

	    return "Produto comprado. Novo Título gerado: " + novoTitulo.getId();
	}

	public void fazPagamento() throws IOException {

		 for (Titulo t : titulos) {
		        if (!t.isPago()) { 
		            t.setPaga(true);
		        }
		    }
		    saveTitulos();
	}

	public boolean aindaTemProduto(JTextField textFieldQuantidadeProduto, JTextField textFieldColocarId) {
	    String quantidadeText = textFieldQuantidadeProduto.getText().trim();
	    String produtoId = textFieldColocarId.getText().trim();
	    
	    if (quantidadeText.isEmpty() || produtoId.isEmpty()) {
	        return false; // Retorna false se algum campo estiver vazio
	    }

	    int aindaTem;
	    try {
	        aindaTem = Integer.parseInt(quantidadeText);
	    } catch (NumberFormatException e) {
	        return false; // Retorna false se a conversão falhar
	    }

	    for (Produto p : produtos) {
	        if (produtoId.equalsIgnoreCase(p.getId()) && p.getQuantidade() >= aindaTem) {
	            return true;
	        }
	    }
	    return false;
	}

	
	public String removerProdutoEmAberto(JTextField textFieldColocarId, JTextField textFieldQuantidadeProduto) throws IOException {
	    String produtoId = textFieldColocarId.getText().trim();
	    int quantidadeParaRemover = Integer.parseInt(textFieldQuantidadeProduto.getText().trim());

	    // Itera sobre os títulos para encontrar o título em aberto correspondente ao produto
	    for (Titulo title : titulos) {
	        if (!title.isPago() && title.getProdutoId().equals(produtoId)) {
	            if (title.getQuantidade() > quantidadeParaRemover) {
	                // Reduz a quantidade no título existente
	                title.setQuantidade(title.getQuantidade() - quantidadeParaRemover);
	            } else if (title.getQuantidade() == quantidadeParaRemover) {
	                // Remove o título se a quantidade for exatamente a mesma
	                titulos.remove(title);
	            } else {
	                return "Quantidade a remover excede a quantidade no título.";
	            }

	            // Atualiza a quantidade do produto no estoque
	            for (Produto p : produtos) {
	                if (p.getId().equals(produtoId)) {
	                    p.setQuantidade(p.getQuantidade() + quantidadeParaRemover);
	                    break;
	                }
	            }

	            // Salva o estado atualizado dos títulos
	            saveTitulos();
	            return "Produto removido com sucesso.";
	        }
	    }

	    // Caso nenhum título em aberto correspondente seja encontrado
	    return "Título em aberto para o produto não encontrado.";
	}
	
	 public void limparCarrinho() throws IOException {
	        // Itera sobre os títulos em aberto usando um iterador
	        Iterator<Titulo> iterator = titulos.iterator();
	        while (iterator.hasNext()) {
	            Titulo title = iterator.next();
	            if (!title.isPago()) {
	                // Devolve a quantidade do produto ao estoque
	                for (Produto p : produtos) {
	                    if (p.getId().equals(title.getProdutoId())) {
	                        p.setQuantidade(p.getQuantidade() + title.getQuantidade());
	                        break;
	                    }
	                }
	                // Remove o título em aberto
	                iterator.remove();
	            }
	        }

	        // Salva o estado atualizado dos títulos e produtos
	        saveTitulos();
	        saveProdutos();
	    }

	public List<Object[]> getTitulosEmAbertoComDetalhes() {
        List<Object[]> titulosComDetalhes = new ArrayList<>();
        for (Titulo titulo : titulos) {
            if (!titulo.isPago()) {
                Produto produto = getProdutoPorId(titulo.getProdutoId());
                if (produto != null) {
                    titulosComDetalhes.add(new Object[]{
                        titulo.getId(),
                        titulo.getProdutoId(),
                        titulo.getNome(),
                        titulo.getPreco(),
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
                total = total + titulo.getPreco() * titulo.getQuantidade();
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
	public void mudarPreco (String idProduto ,double novoPreco) throws IOException { //@Feliipee013 muda o  preco de um produto

		for (Produto p : produtos) {
	        if (p.getId().equalsIgnoreCase(idProduto)) { //@Feliipee013
	        	p.setNovoPreco(novoPreco);
	        	saveProdutos();
	            break;
	        }
	    }
	}
}
