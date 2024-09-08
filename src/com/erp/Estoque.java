package com.erp;

import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import javax.swing.JTextField;

import model.NotaFiscal;
import model.Produto;
import model.Titulo;

public class Estoque {
	private List<Produto> produtos;
	private List<Titulo> titulos;
	private List<NotaFiscal> notasFiscais;
	public static final String PRODUTOS_ARQUIVO = "produtos.txt";
	private static final String TITULOS_ARQUIVO = "titulos.txt";
	private static final String NOTASFISCAIS_ARQUIVO = "notasfiscais.txt";

	public Estoque() throws IOException {
		produtos = new ArrayList<>();
		titulos = new ArrayList<>();
		notasFiscais = new ArrayList<>();
		carregaProduto();
		carregaNotasFiscais();
		carregaTitulos();
	}
	
	public List<NotaFiscal> getNotaFiscal() {
        return notasFiscais;
    }
	
	public List<Produto> getProdutos() {
        return produtos;
    }
	
	//adiciona produtos ao estoque
	public void addProduto(JTextField textFieldNomeDoProduto, JTextField textFieldPrecoProduto, JTextField textFieldQuantidadeProduto, JTextField textFieldPeso,
			JTextField textFieldDia, JTextField textFieldMes, JTextField textFieldAno) throws IOException {
	    String nome = textFieldNomeDoProduto.getText();
	    double preco = Double.parseDouble(textFieldPrecoProduto.getText().trim());
	    int quantidade = Integer.parseInt(textFieldQuantidadeProduto.getText().trim());
	    float peso = Float.parseFloat(textFieldPeso.getText().trim());
	    float pesoTotal = peso * quantidade;
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
	        produtoExistente.setPesoTotal(produtoExistente.getPeso() * produtoExistente.getQuantidade());
	    } else {
	        // Produto não existe, cria um novo
	        int tamanho1 = produtos.size() + 1;
	        String id = String.valueOf(tamanho1);
	        Produto novoProduto = new Produto(id, nome, preco, quantidade, peso, pesoTotal, vencimento);
	        produtos.add(novoProduto);
	    }
	    
	    saveProdutos();
	}
	
	public void removerProdutoEstoque(JTextField textFieldRemover, JTextField textFieldQuantidade) throws IOException {//Remoção de certa quantidade de produtos em estoque

        String idProduto = textFieldRemover.getText().trim();
        int quantidade = Integer.parseInt(textFieldQuantidade.getText().trim());
		
		for (Produto p : produtos) {
			if (p.getId().equals(idProduto)) {
				p.setQuantidade(p.getQuantidade() - quantidade);
				p.setPesoTotal(p.getPeso() * p.getQuantidade());
				saveProdutos();
				break;
			}
		 }
	}
	
	public void retirarProdutoEstoque(JTextField textFieldRemover) throws IOException {//Remove os produtos em estoque de vez
		 String idProduto = textFieldRemover.getText().trim();
		    Produto produtoRemover = getProdutoPorId(idProduto);
		    if (produtoRemover != null) {
		        produtos.remove(produtoRemover);
		        saveProdutos();
		    }
		}
	
	//adiciona um produto ao carrinho
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

	    // Verifica se existe um título em aberto
	    for (Titulo titulo : titulos) {
	        if (!titulo.isPago()) {
	            // Obtém a lista de produtos do carrinho do título atual
	            List<Produto> produtosCarrinho = titulo.getProdutosCarrinho();

	            for (Produto produtoCarrinho : produtosCarrinho) {
	                // Verifica se o ID do produto no carrinho é o mesmo que o ID do produto fornecido
	                if (produtoCarrinho.getId().equals(produto.getId())) {
	                    // Atualiza a quantidade no produto existente no carrinho
	                    produtoCarrinho.setQuantidade(produtoCarrinho.getQuantidade() + produtoQuant);
	                    produto.setQuantidade(produto.getQuantidade() - produtoQuant);
	                    
	                    // Salva as mudanças no estoque e nos títulos
	                    saveProdutos();
	                    saveTitulos();

	                    return "Produto adicionado ao carrinho. Quantidade adicionada ao produto em aberto existente.";
	                }
	            }

	            // Se o produto não for encontrado, adiciona como um novo produto no carrinho  
	                Produto novoProduto = new Produto(produto.getId(), produto.getNome(), produto.getPreco(), produtoQuant, produto.getPeso(), produto.getPesoTotal(), produto.getVencimento());
	                produtosCarrinho.add(novoProduto);
	                produto.setQuantidade(produto.getQuantidade() - produtoQuant);
	                
	                // Salva as mudanças no estoque e nos títulos
	                saveProdutos();
	                saveTitulos();

	                return "Produto adicionado ao carrinho como um novo item.";
	            
	        }
	    }

	    // Se não houver um título em aberto, cria um novo título
	    List<Produto> novoProdutosCarrinho = new ArrayList<>();
	    Produto novoProduto = new Produto(produto.getId(), produto.getNome(), produto.getPreco(), produtoQuant, produto.getPeso(), produto.getPesoTotal(), produto.getVencimento());
	    novoProdutosCarrinho.add(novoProduto);
	    Titulo novoTitulo = new Titulo(UUID.randomUUID().toString(), produto.getPreco(), false, novoProdutosCarrinho);
	    titulos.add(novoTitulo);

	    produto.setQuantidade(produto.getQuantidade() - produtoQuant);
	    saveProdutos();
	    saveTitulos();

	    return "Produto Adicionado ao carrinho. Novo Título gerado: " + novoTitulo.getId();
	}

	public void fazPagamento(String nomeVendedor, String nomeCliente) throws IOException {

		 for (Titulo t : titulos) {
		        if (!t.isPago()) { 
		        	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		            String dataDaCompra = sdf.format(new Date());
		        	NotaFiscal notaFiscal = new NotaFiscal(t.getId(), nomeVendedor, nomeCliente, t.getPreco(), dataDaCompra, t.produtosCarrinho);
		        	notasFiscais.add(notaFiscal);
		        	saveNotasFiscais();
		            t.setPaga(true);
		        }
		    }
		    saveTitulos();
	}
	
	//verifica se a quantidade de produtos no estoque é suficiente para adicionar ao carrinho
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

	//remove produtos em especifico do carrinho
	public String removerProdutoEmAberto(JTextField textFieldColocarId, JTextField textFieldQuantidadeProduto) throws IOException {
		String produtoId = textFieldColocarId.getText().trim();
	    int quantidadeParaRemover;

	    // Verifica se a quantidade é válida
	    try {
	        quantidadeParaRemover = Integer.parseInt(textFieldQuantidadeProduto.getText().trim());
	    } catch (NumberFormatException e) {
	        return "Quantidade inválida.";
	    }

	    // Itera sobre os títulos para encontrar o título em aberto correspondente ao produto
	    for (Titulo title : titulos) {
	        if (!title.isPago()) {
	            List<Produto> produtosCarrinho = new ArrayList<>(title.getProdutosCarrinho());

	            for (Produto produtoCarrinho : produtosCarrinho) {
	                if (produtoCarrinho.getId().equals(produtoId)) {
	                    if (produtoCarrinho.getQuantidade() > quantidadeParaRemover) {
	                        // Reduz a quantidade no título existente
	                        produtoCarrinho.setQuantidade(produtoCarrinho.getQuantidade() - quantidadeParaRemover);
	                        saveTitulos();  // Salva o estado atualizado dos títulos
	                     // Atualiza a quantidade do produto no estoque
	                        for (Produto p : produtos) {
	                            if (p.getId().equals(produtoId)) {
	                                p.setQuantidade(p.getQuantidade() + quantidadeParaRemover);
	                                saveProdutos();
	                                break;
	                            }
	                        }
	                        break;  // Encerra o loop, já que o produto foi encontrado e atualizado
	                    } else if (produtoCarrinho.getQuantidade() == quantidadeParaRemover) {
	                        // Remove o produto do título
	                        title.getProdutosCarrinho().remove(produtoCarrinho);
	                        saveTitulos();  // Salva o estado atualizado dos títulos

	                        // Se o título ficar vazio após a remoção, remove o título
	                        if (title.getProdutosCarrinho().isEmpty()) {
	                            titulos.remove(title);
	                            saveTitulos();
	                        }
	                        // Atualiza a quantidade do produto no estoque
	                        for (Produto p : produtos) {
	                            if (p.getId().equals(produtoId)) {
	                                p.setQuantidade(p.getQuantidade() + quantidadeParaRemover);
	                                saveProdutos();
	                                break;
	                            }
	                        }
	                        return "Produto removido com sucesso.";
	                    } else {
	                        return "Quantidade a remover excede a quantidade no título.";
	                    }
	                }
	            }
	        }
	    }
	    // Caso nenhum título em aberto correspondente seja encontrado
	    return "Título em aberto para o produto não encontrado.";
	}
	
	public void limparCarrinho() throws IOException {
	    Iterator<Titulo> iterator = titulos.iterator();
	    
	    while (iterator.hasNext()) {
	        Titulo titulo = iterator.next();
	        if (!titulo.isPago()) {
	            // Devolve a quantidade do produto ao estoque
	            List<Produto> produtosCarrinho = titulo.getProdutosCarrinho();

	            for (Produto produtoCarrinho : produtosCarrinho) {
	                for (Produto p : produtos) {
	                    if (p.getId().equals(produtoCarrinho.getId())) {
	                        p.setQuantidade(p.getQuantidade() + produtoCarrinho.getQuantidade());
	                        break;
	                    }
	                }
	            }
	            // Remove o título da lista de forma segura
	            iterator.remove();
	        }
	    }

	    // Salva o estado atualizado dos títulos e produtos
	    saveTitulos();
	    saveProdutos();
	}


	 public List<Object[]> getTitulosEmAbertoComDetalhes() { //cria um modelo de tabela para atualizar o titulo em aberto
		    List<Object[]> titulosComDetalhes = new ArrayList<>();

		    for (Titulo titulo : titulos) {
		        if (!titulo.isPago()) {
		            List<Produto> produtosCarrinho = titulo.getProdutosCarrinho();
		            for (Produto produtoCarrinho : produtosCarrinho) {
		                        titulosComDetalhes.add(new Object[]{
		                            produtoCarrinho.getId(),
		                            produtoCarrinho.getNome(),
		                            produtoCarrinho.getPreco(),
		                            produtoCarrinho.getQuantidade()
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
    
    public double calcularTotalTitulosEmAberto() {//carrega o total do titulo e manda para pagina de vendas
        double total = 0;
        for (Titulo titulo : titulos) {
            if (!titulo.isPago()) {
            	List<Produto> produtosCarrinho = titulo.getProdutosCarrinho();
                for (Produto produtoCarrinho : produtosCarrinho) {
                total = total + produtoCarrinho.getPreco() * produtoCarrinho.getQuantidade();
                }
                titulo.setPreco(total);
            }
        }
        return total;
    }

    private void carregaProduto() throws IOException {//carrega os produtos na lista quando a classe estoque é instanciada
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
    
    private void carregaNotasFiscais() throws IOException {
        File file = new File(NOTASFISCAIS_ARQUIVO);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    try {
                        NotaFiscal notaFiscal = NotaFiscal.fromString(line);
                        notasFiscais.add(notaFiscal);
                    } catch (IllegalArgumentException e) {
                        // Log a mensagem de erro e continue com a próxima linha
                        System.err.println("Erro ao processar linha do arquivo: " + line + " - " + e.getMessage());
                    }
                }
            } catch (IOException e) {
                // Exibe uma mensagem de erro se ocorrer um problema ao ler o arquivo
                System.err.println("Erro ao ler o arquivo de notas fiscais: " + e.getMessage());
                throw e;
            }
        } else {
            System.out.println("Arquivo de notas fiscais não encontrado: " + NOTASFISCAIS_ARQUIVO);
        }
    }
    
	private void carregaTitulos() throws IOException {//carrega os titulos na lista quando a classe estoque é instanciada
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

	private void saveProdutos() throws IOException {//salva os dados no txt
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(PRODUTOS_ARQUIVO))) {
			for (Produto product : produtos) {
				writer.write(product.toString());
				writer.newLine();
			}
		}
	}
	
	private void saveNotasFiscais() throws IOException {//salva os dados no txt
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(NOTASFISCAIS_ARQUIVO))) {
			for (NotaFiscal nota : notasFiscais) {
				writer.write(nota.toString());
				writer.newLine();
			}
		}
	}

	private void saveTitulos() throws IOException {//salva os titulos no txt
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
