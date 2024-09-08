package view;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


import com.erp.Estoque;
import model.Produto;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class JRelatorioProdutos extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Estoque estoque;
	private float totalEstoque = 1000;//quantidade do espaço do estoque
	private JTextField textFieldNovoPreco;
	private JTextField textFieldIdProduto;
	private JTable table;
	private JScrollPane scrollPane_1;
	private JTable table_1;
	/**
	 * Launch the application.
	 */
	
	ImageIcon logo = new ImageIcon(getClass().getClassLoader().getResource("logo.png"));
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JRelatorioProdutos frame = new JRelatorioProdutos();
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
	public JRelatorioProdutos() throws IOException {
		
		estoque = new Estoque();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 500);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setIconImage(logo.getImage());
		setTitle("StoSale");
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(70, 115, 121));
		panel.setBounds(0, 0, 986, 463);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(473, 41, 513, 211);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.BOLD, 10));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nome", "Preço $", "ID", "Quantidade", "Vencimento"
			}
		));
		scrollPane.setViewportView(table);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(473, 252, 513, 211);
		panel.add(scrollPane_1);
		
		table_1 = new JTable();
		table_1.setFont(new Font("Tahoma", Font.BOLD, 10));
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"AVISO"
			}
		));
		scrollPane_1.setViewportView(table_1);
		
		JLabel lblNewLabel = new JLabel("MUDAR O PREÇO DO PRODUTO ");
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 12));
		lblNewLabel.setBounds(105, 271, 239, 27);
		panel.add(lblNewLabel);
		
		textFieldNovoPreco = new JTextField();
		textFieldNovoPreco.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {//@Feliipee013 Isso faz com q o textField só aceite numeros
				char ch = e.getKeyChar();
				
				if(!(Character.isDigit(ch) || ch == KeyEvent.VK_BACK_SPACE || ch == KeyEvent.VK_DELETE
						|| ch == KeyEvent.VK_PERIOD)) {
					e.consume();
				}
			}
		});
		textFieldNovoPreco.setBounds(105, 381, 225, 27);
		panel.add(textFieldNovoPreco);
		textFieldNovoPreco.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("NOVO PREÇO :");
		lblNewLabel_1.setFont(new Font("Verdana", Font.BOLD, 12));
		lblNewLabel_1.setBounds(105, 357, 102, 27);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("ID DO PRODUTO :");
		lblNewLabel_2.setFont(new Font("Verdana", Font.BOLD, 12));
		lblNewLabel_2.setBounds(105, 295, 142, 27);
		panel.add(lblNewLabel_2);
		
		textFieldIdProduto = new JTextField();
		textFieldIdProduto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {//@Feliipee013 Isso faz com q o textField só aceite numeros
				char ch = e.getKeyChar();
				
				if(!(Character.isDigit(ch)) || (ch == KeyEvent.VK_BACK_SPACE) || (ch == KeyEvent.VK_DELETE)) {
					e.consume();
				}
			}
		});
		textFieldIdProduto.setBounds(105, 320, 225, 27);
		panel.add(textFieldIdProduto);
		textFieldIdProduto.setColumns(10);
		
		JButton btnMudarPreco = new JButton("MUDAR PREÇO");
		btnMudarPreco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String idProduto = textFieldIdProduto.getText();
		        String novoPrecoStr = textFieldNovoPreco.getText();

		        try {
		            double novoPreco = Double.parseDouble(novoPrecoStr.trim());
		            
		            estoque.mudarPreco(idProduto, novoPreco);
		            
		        } catch (NumberFormatException ex) {
		            System.out.println("Formato de preço invalido: " + novoPrecoStr);
		            
		        } catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        carregarProdutosNaTabela();
		        carregarProdutosNaTabela2();
		    }
		});
		btnMudarPreco.setBackground(new Color(68, 204, 215));
		btnMudarPreco.setBounds(146, 426, 142, 27);
		panel.add(btnMudarPreco);
		
		JButton btnAbrirGrafico = new JButton("ABRIR GRÁFICO");
		btnAbrirGrafico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new GraficoBarra().setVisible(true);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnAbrirGrafico.setBounds(245, 225, 142, 27);
		panel.add(btnAbrirGrafico);
		
		JLabel lblNewLabel_3 = new JLabel("CHECAR GRÁFICO : ");
		lblNewLabel_3.setFont(new Font("Verdana", Font.BOLD, 12));
		lblNewLabel_3.setBounds(105, 225, 225, 27);
		panel.add(lblNewLabel_3);
		carregarProdutosNaTabela();
		carregarProdutosNaTabela2();

	}
	
	private void carregarProdutosNaTabela() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0); // Limpa a tabela

	List<Produto> produtos = estoque.getProdutos();
		for (Produto produto : produtos) {
				model.addRow(new Object[]{produto.getNome(), produto.getPreco(), produto.getId(), produto.getQuantidade(), produto.getVencimento()});
		}
	}
	 private void carregarProdutosNaTabela2() {
	        DefaultTableModel model = (DefaultTableModel) table_1.getModel();
	        model.setRowCount(0); // Limpa a tabela

	        List<Produto> produtos = estoque.getProdutos();

	        for (Produto produto : produtos) {
	        	
	        	LocalDate vencimento = produto.getVencimento();
	        	String prazoValidade = consultaValidade(produto, vencimento);//@Feliipee013 passa o produto e sua data de vencimento
	        	
	        	float pesoSuportadoNoEstoque = totalEstoque/produto.getPeso();
	            String nivelDoEstoque = consultaDeInfo(produto, pesoSuportadoNoEstoque);// Passa o produto específico

	            if (nivelDoEstoque != null) {
	                model.addRow(new Object[]{nivelDoEstoque});
	            }
	            
	            if(prazoValidade != null) {
	            	model.addRow(new Object []{prazoValidade});
	            	corNaLinha();
	            }
	            if(produto.isPrecoAlterado()) {
	            	  model.addRow(new Object[]{"Preço do produto: " + produto.getNome() +
	            			  " foi alterado para: R$ " + produto.getPreco()});
	            }
	        }
	    }
	 public String consultaValidade(Produto produto, LocalDate vencimento) {
	    	LocalDate hoje = LocalDate.now();//@Feliipee013 variável para ver q dia é hj
	    	if(Period.between(hoje, vencimento).getDays() > 0 && Period.between(hoje, vencimento).getDays() <= 31
	    			&& Period.between(hoje, produto.getVencimento()).getMonths() <= 1
	    			&& Period.between(hoje, produto.getVencimento()).getYears() <= 1) //@Feliipee013 Verificação para ver se falta 1 mes para determinar a validade
	    		return "A data de validade do produto: " + produto.getNome() + " está chegando ao fim";
	    
	    	else if(Period.between(hoje,vencimento).getDays() <= 0)
	    		return  "O Produto: " + produto.getNome() + " venceu";
	    	
	    	else 
	    		return null;
	    }
	    
	    public String consultaDeInfo(Produto produto, float pesoSuportadoNoEstoque) {
	        float pesoMin = (float) (pesoSuportadoNoEstoque * 0.4);

	        if (produto.getPesoTotal() <= pesoMin) {
	            return "Estoque do produto: " + produto.getNome() +
	                   " está em níveis baixos, por favor solicite abastecimento.";
	        }
	        return null;
	    }
	    
	    public void corNaLinha() {
	    	table_1.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
	    	

			/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				
				JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected,
						hasFocus, row, column);
				
				Object rowValue = table_1.getValueAt(row, 0);
				 if (rowValue != null) {
		                String cellText = rowValue.toString();
		                
		                // Check if the text indicates the product is about to expire (within 31 days)
		                if (cellText.contains("validade do produto") && cellText.contains("está chegando ao fim")) {
		                    label.setForeground(new Color(220,155,0));
		                }
		                // Check if the text indicates the product has expired
		                else if (cellText.contains("Produto") && cellText.contains("venceu")) {
		                    label.setForeground(Color.RED);
		                }
		                // Set default color if no special condition is met
		                else {
		                    label.setForeground(Color.BLACK);
		                }
		            }

		            return label;
		        }
	    	});
	}

}
