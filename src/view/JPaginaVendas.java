package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.erp.Estoque;
import com.erp.Main;
import com.erp.Produto;
import com.erp.Titulo;
import com.erp.Usuario;
import com.erp.UsuarioDAO;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class JPaginaVendas extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTable table_1;
	private Estoque estoque;
	private JTextField textFieldColocarId;
	private JTable tableTotal;
	private DefaultTableModel modelTotal;
	private Usuario usuarioLogado;
	private UsuarioDAO usuarioDAO;

	/**
	 * Launch the application.
	 */
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
		
		ImageIcon logo = new ImageIcon(getClass().getClassLoader().getResource("logo.jpg"));
        setIconImage(logo.getImage());
        setTitle("StoSale");
        
        this.usuarioDAO = new UsuarioDAO();
        this.estoque = new Estoque();
        initialize();
		estoque = new Estoque();
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 780);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
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
					estoque.removerProdutoEmAberto(textFieldColocarId);
					JOptionPane.showMessageDialog(btnRemoverProdutos, "Produto Removido", "SUCESSO", JOptionPane.INFORMATION_MESSAGE);
        			textFieldColocarId.setText("");
        			carregarTitulosEmAbertoNaTabela();
        			 atualizarTotalEmAberto();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnRemoverProdutos.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnRemoverProdutos.setBounds(24, 165, 163, 31);
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
					"NOME", "PRE\u00C7O $", "ID"
				}
			));
		scrollPane.setViewportView(table);
		carregarProdutosNaTabela();
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(490, 228, 336, 462);
		panel.add(scrollPane_1);
		
		table_1 = new JTable();
        table_1.setModel(new DefaultTableModel(
        	new Object[][] {
        	},
        	new String[] {
        		"ID T\u00CDTULO", "NOME", "PRE\u00C7O"
        	}
        ));
        scrollPane_1.setViewportView(table_1);
        carregarTitulosEmAbertoNaTabela();
        
        textFieldColocarId = new JTextField();
        textFieldColocarId.setBounds(24, 124, 336, 31);
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
        lblColoqueAId.setBounds(64, 102, 248, 25);
        panel.add(lblColoqueAId);
        
        JButton btnAdicionarCarrinho = new JButton("ADICIONAR CARRINHO");
        btnAdicionarCarrinho.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String mensagem = null;
        		try {
        			mensagem = estoque.compraProduto(textFieldColocarId);
        			JOptionPane.showMessageDialog(btnAdicionarCarrinho, mensagem);
        			textFieldColocarId.setText("");
        			carregarTitulosEmAbertoNaTabela();
        			atualizarTotalEmAberto();
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(btnAdicionarCarrinho, "Erro ao adicionar ao carrinho: " + mensagem, "Erro", JOptionPane.ERROR_MESSAGE);
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
        });
        btnAdicionarCarrinho.setFont(new Font("Tahoma", Font.BOLD, 10));
        btnAdicionarCarrinho.setBounds(197, 165, 163, 31);
        panel.add(btnAdicionarCarrinho);
        
        JLabel lblCarrinho = new JLabel("CARRINHO");
        lblCarrinho.setHorizontalAlignment(SwingConstants.CENTER);
        lblCarrinho.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblCarrinho.setBounds(548, 115, 220, 25);
        panel.add(lblCarrinho);
        
        JLabel lblTitulosEmAberto = new JLabel("TITULOS EM ABERTO");
        lblTitulosEmAberto.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulosEmAberto.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblTitulosEmAberto.setBounds(548, 202, 220, 25);
        panel.add(lblTitulosEmAberto);
        
        JButton btnPagar = new JButton("PAGAR");
        btnPagar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
			try {
				atualizarTotalVendas();
				UsuarioDAO usuarioDAO = new UsuarioDAO();
				usuarioDAO.atualizarVendas(usuarioLogado.getNome(), usuarioLogado.getVendas());
				JOptionPane.showMessageDialog(btnPagar, "Pagamento Realizado com Sucesso");
				estoque.fazPagamento();
				carregarTitulosEmAbertoNaTabela();
				atualizarTotalEmAberto();
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(btnPagar, "Erro ao processar pagamento: " + e1.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			}
        	}
        });
        btnPagar.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnPagar.setBounds(720, 150, 104, 46);
        panel.add(btnPagar);
        
        
        
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
	            model.addRow(new Object[]{produto.getNome(), produto.getPreco(), produto.getId()});
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
	 private void initialize() {
	       
	        atualizarTotalVendas();
	    }
	 private void atualizarTotalVendas() {
		 
		 double totalVendas = estoque.calcularTotalTitulosEmAberto();
		 if (usuarioLogado != null) {
			 usuarioLogado.setVendas(usuarioLogado.getVendas() + totalVendas);
	        }
	    }
}
