package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.erp.Estoque;
import com.erp.Main;
import com.erp.UsuarioDAO;
import com.erp.UsuarioDAO.UsuarioLogadoReceiver;

import model.Produto;
import model.Usuario;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class JPaginaVendas extends JFrame implements UsuarioLogadoReceiver {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTable table_1;
	private Estoque estoque;
	private JTextField textFieldColocarId;
	private JTable tableTotal;
	private DefaultTableModel modelTotal;
	private Usuario usuarioLogado;
	private JTextField textFieldQuantidadeProduto;
	private JTable tabelaData;

	/**
	 * Launch the application.
	 */
	ImageIcon logo = new ImageIcon(getClass().getClassLoader().getResource("logo.png"));
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JPaginaVendas frame = new JPaginaVendas();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the frame.
	 * @throws IOException 
	 */
    
	public JPaginaVendas() throws IOException {
		
        new UsuarioDAO();
        this.estoque = new Estoque();
		estoque = new Estoque();
		
		setIconImage(logo.getImage());
		setTitle("StoSale");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 780);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//passando o frame para o modo suspensão para verificar atividade
        JModoSuspenso.addActivityListener(this);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(70, 115, 121));
		panel.setBounds(10, 10, 876, 723);
		contentPane.add(panel);
		
		JButton btnNewButton_1 = new JButton("AJUDA");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				JAjudaVendas jAjudaVendas = new JAjudaVendas();
				jAjudaVendas.setLocationRelativeTo(jAjudaVendas);
				jAjudaVendas.setVisible(true);
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnNewButton_1.setBounds(10, 10, 85, 21);
		panel.add(btnNewButton_1);
		
		JLabel lblNewLabel_2_1 = new JLabel("StoSALE");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 32));
		lblNewLabel_2_1.setBounds(336, 10, 148, 31);
		panel.add(lblNewLabel_2_1);
		
		JLabel lblVendas = new JLabel("VENDAS");
		lblVendas.setHorizontalAlignment(SwingConstants.CENTER);
		lblVendas.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblVendas.setBounds(346, 40, 135, 25);
		panel.add(lblVendas);
        
		String[] columnNames = {"TOTAL"};
        Object[][] data = {{"0.00"}};
        modelTotal = new DefaultTableModel(data, columnNames);
        tableTotal = new JTable(modelTotal);
        tableTotal.setFont(new Font("Tahoma", Font.PLAIN, 18));
        tableTotal.setBounds(509, 124, 121, 31);
        JScrollPane scrollPaneTotal = new JScrollPane(tableTotal);
        scrollPaneTotal.setBounds(492, 150, 121, 46);
        panel.add(scrollPaneTotal);

        atualizarTotalEmAberto();
		
		JButton btnRemoverProdutos = new JButton("REMOVER CARRINHO");
		btnRemoverProdutos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(!textFieldColocarId.getText().isEmpty() && !textFieldQuantidadeProduto.getText().isEmpty()) {
					String mensagem = estoque.removerProdutoEmAberto(textFieldColocarId, textFieldQuantidadeProduto);
					JOptionPane.showMessageDialog(btnRemoverProdutos, mensagem, "AVISO", JOptionPane.INFORMATION_MESSAGE);
        			textFieldColocarId.setText("");
        			textFieldQuantidadeProduto.setText("");
        			carregarTitulosEmAbertoNaTabela();
        			atualizarTotalEmAberto();
        			carregarProdutosNaTabela();
					}else {
						JOptionPane.showMessageDialog(btnRemoverProdutos,"Informações não colocadas corretamente", "Erro", JOptionPane.ERROR_MESSAGE);
						textFieldColocarId.setText("");
        	            textFieldQuantidadeProduto.setText("");
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnRemoverProdutos.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnRemoverProdutos.setBounds(24, 178, 163, 31);
		panel.add(btnRemoverProdutos);
		
		JButton btnNewButton_1_1 = new JButton("SAIR");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Main jPrincipal = new Main();
				jPrincipal.setLocationRelativeTo(jPrincipal);
				jPrincipal.setVisible(true);
			}
		});
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnNewButton_1_1.setBounds(10, 41, 85, 21);
		panel.add(btnNewButton_1_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 228, 336, 462);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"NOME", "PRE\u00C7O $", "ID", "QUANTIDADE"
				}
			));
		scrollPane.setViewportView(table);
		carregarProdutosNaTabela();
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(490, 228, 365, 462);
		panel.add(scrollPane_1);
		
		table_1 = new JTable();
        table_1.setModel(new DefaultTableModel(
        	new Object[][] {
        	},
        	new String[] {
        		"ID PRODUTO", "NOME", "PRE\u00C7O", "QUANTIDADE"
        	}
        ));
        scrollPane_1.setViewportView(table_1);
        carregarTitulosEmAbertoNaTabela();
        
        textFieldColocarId = new JTextField();
        textFieldColocarId.setBounds(24, 84, 336, 31);
        panel.add(textFieldColocarId);
        textFieldColocarId.setColumns(10);
        
        JLabel lblProdutosEmEstoque = new JLabel("PRODUTOS EM ESTOQUE");
        lblProdutosEmEstoque.setHorizontalAlignment(SwingConstants.CENTER);
        lblProdutosEmEstoque.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblProdutosEmEstoque.setBounds(82, 206, 220, 25);
        panel.add(lblProdutosEmEstoque);
        
        JLabel lblColoqueAId = new JLabel("COLOQUE A ID DO PRODUTO:");
        lblColoqueAId.setHorizontalAlignment(SwingConstants.CENTER);
        lblColoqueAId.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblColoqueAId.setBounds(68, 62, 248, 25);
        panel.add(lblColoqueAId);
        
        JButton btnAdicionarCarrinho = new JButton("ADICIONAR CARRINHO");//adiciona intens ao carrinho
        btnAdicionarCarrinho.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String mensagem = null;
        		try {
        	        // Verifica se os campos não estão vazios
        	        if (textFieldColocarId.getText().isEmpty() || textFieldQuantidadeProduto.getText().isEmpty()) {
        	            JOptionPane.showMessageDialog(btnAdicionarCarrinho, "Por favor, preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
        	            return;
        	        }

        	        // Verifica se há estoque suficiente
        	        boolean aindaTem = estoque.aindaTemProduto(textFieldQuantidadeProduto, textFieldColocarId);

        	        if (aindaTem) {
        	            mensagem = estoque.compraProduto(textFieldColocarId, textFieldQuantidadeProduto);
        	            JOptionPane.showMessageDialog(btnAdicionarCarrinho, mensagem);

        	            // Limpa os campos de texto após a compra
        	            textFieldColocarId.setText("");
        	            textFieldQuantidadeProduto.setText("");

        	            // Atualiza a tabela e o total dos títulos em aberto
        	            carregarTitulosEmAbertoNaTabela();
        	            atualizarTotalEmAberto();
        	            carregarProdutosNaTabela();
        	        } else {
        	            JOptionPane.showMessageDialog(btnAdicionarCarrinho, "Quantidade em falta no estoque ou informações incorretas.", "Erro", JOptionPane.ERROR_MESSAGE);
        	            textFieldColocarId.setText("");
        	            textFieldQuantidadeProduto.setText("");
        	        }
        	    } catch (IOException e1) {
        	        JOptionPane.showMessageDialog(btnAdicionarCarrinho, "Erro ao adicionar ao carrinho: " + e1.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        	        e1.printStackTrace();
        	    }
        	}
        });
        btnAdicionarCarrinho.setFont(new Font("Tahoma", Font.BOLD, 10));
        btnAdicionarCarrinho.setBounds(197, 178, 163, 31);
        panel.add(btnAdicionarCarrinho);
        
        JLabel lblCarrinho = new JLabel("CARRINHO");
        lblCarrinho.setHorizontalAlignment(SwingConstants.CENTER);
        lblCarrinho.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblCarrinho.setBounds(558, 115, 220, 25);
        panel.add(lblCarrinho);
        
        JLabel lblTitulosEmAberto = new JLabel("TITULO EM ABERTO");
        lblTitulosEmAberto.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulosEmAberto.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblTitulosEmAberto.setBounds(558, 202, 220, 25);
        panel.add(lblTitulosEmAberto);
        
        JButton btnPagar = new JButton("PAGAR");
        btnPagar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
			try {
				int opcao = JOptionPane.showConfirmDialog(
					    btnPagar, 
					    "Digite o nome do Cliente:", 
					    "Concluir Compra?", 
					    JOptionPane.OK_CANCEL_OPTION, 
					    JOptionPane.INFORMATION_MESSAGE
					);

					if (opcao == JOptionPane.OK_OPTION) {
					    // O usuário clicou em OK, continue com a operação
					    String nomeCliente = JOptionPane.showInputDialog(
					        btnPagar, 
					        "Digite o nome do Cliente:", 
					        "Concluir Compra", 
					        JOptionPane.INFORMATION_MESSAGE
					    );
					    
					    // Verifica se o nome do cliente não está vazio
					    if (nomeCliente != null && !nomeCliente.trim().isEmpty()) {
							estoque.fazPagamento(usuarioLogado.getNomeColaborador(), nomeCliente);
							JOptionPane.showMessageDialog(btnPagar, "Pagamento Realizado com Sucesso");
							carregarTitulosEmAbertoNaTabela();
							atualizarTotalEmAberto();
					    } else {
					        JOptionPane.showMessageDialog(btnPagar, "O nome do cliente não pode ser vazio.", "Erro", JOptionPane.ERROR_MESSAGE);
					    }
					    
					} else if (opcao == JOptionPane.CANCEL_OPTION) {
					    // O usuário clicou em Cancelar, cancele a operação
					    JOptionPane.showMessageDialog(btnPagar, "Operação cancelada.", "Cancelado", JOptionPane.WARNING_MESSAGE);
					}
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(btnPagar, "Erro ao processar pagamento: " + e1.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			}
        }
        });
        btnPagar.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnPagar.setBounds(751, 150, 104, 46);
        panel.add(btnPagar);
        
        JLabel lblColoqueAQuantidade = new JLabel("COLOQUE A QUANTIDADE DO PRODUTO:");
        lblColoqueAQuantidade.setHorizontalAlignment(SwingConstants.CENTER);
        lblColoqueAQuantidade.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblColoqueAQuantidade.setBounds(24, 115, 336, 25);
        panel.add(lblColoqueAQuantidade);
        
        textFieldQuantidadeProduto = new JTextField();
        textFieldQuantidadeProduto.setColumns(10);
        textFieldQuantidadeProduto.setBounds(24, 137, 336, 31);
        panel.add(textFieldQuantidadeProduto);
        
        JButton btnLimpar = new JButton("LIMPAR");
        btnLimpar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
					estoque.limparCarrinho();
					carregarTitulosEmAbertoNaTabela();
		            atualizarTotalEmAberto();
		            carregarProdutosNaTabela();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
        });
        btnLimpar.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnLimpar.setBounds(623, 150, 118, 46);
        panel.add(btnLimpar);
        
        JScrollPane scrollPane_Data = new JScrollPane();
        scrollPane_Data.setBounds(768, 7, 90, 41);
        panel.add(scrollPane_Data);
        
        String[] colunas = {"DATA"};
        DefaultTableModel modelo = new DefaultTableModel(colunas, 0);
        modelo.addRow(new Object[]{""});
        tabelaData = new JTable(modelo);
        scrollPane_Data.setViewportView(tabelaData);
     // Timer para atualizar a data e hora a cada segundo (1000 milissegundos)
        Timer timer = new Timer(1000, e -> {
            // Obter a data
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String dataAtual = sdf.format(new Date());

            // Atualizar o valor da célula na tabela
            modelo.setValueAt(dataAtual, 0, 0);
        });
        timer.start();//timer para atualiaz a data a cada 1 seg
	 }
    
        
	public void setUsuarioLogado(Usuario usuario) {
        this.usuarioLogado = usuario;
    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }
	
	 private void carregarProdutosNaTabela() {
	        DefaultTableModel model = (DefaultTableModel) table.getModel();
	        model.setRowCount(0);

	        List<Produto> produtos = estoque.getProdutos();
	        for (Produto produto : produtos) {
	            model.addRow(new Object[]{produto.getNome(), produto.getPreco(), produto.getId(), produto.getQuantidade()});
	        }
	    }
	 
	 public void atualizarTotalEmAberto() {
		 try {
		        double total = estoque.calcularTotalTitulosEmAberto();
		        DecimalFormat df = new DecimalFormat("#,##0.00");
		        String totalFormatted = df.format(total);
		        
		        modelTotal.setValueAt(totalFormatted, 0, 0);
		    } catch (Exception e) {
		        e.printStackTrace();
		        modelTotal.setValueAt("Erro", 0, 0);
		    }
		
	    }
	 
	 public void carregarTitulosEmAbertoNaTabela() {
	        DefaultTableModel model = (DefaultTableModel) table_1.getModel();
	        model.setRowCount(0);

	        List<Object[]> titulosComDetalhes = estoque.getTitulosEmAbertoComDetalhes();
	        for (Object[] detalhes : titulosComDetalhes) {
	            model.addRow(detalhes);
	        }
	    }
}
