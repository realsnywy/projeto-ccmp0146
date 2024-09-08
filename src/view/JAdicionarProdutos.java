package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.erp.Estoque;
import com.erp.Produto;

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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class JAdicionarProdutos extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldNomeDoProduto;
	private JTextField textFieldPrecoProduto;
	private JTextField textFieldQuantidadeProduto;
	private JTable table;
	private Estoque estoque;
<<<<<<< Updated upstream
=======
	private float totalEstoque = 1000;//quantidade do espaço do estoque
	private JTextField textFieldDia;
	private JTextField textFieldMes;
	private JTextField textFieldAno;
>>>>>>> Stashed changes

	/**
	 * Launch the application.
	 */
	ImageIcon logo = new ImageIcon(getClass().getClassLoader().getResource("logo.png"));
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JAdicionarProdutos frame = new JAdicionarProdutos();
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
	public JAdicionarProdutos() throws IOException {
		
		estoque = new Estoque();
		
		setIconImage(logo.getImage());
		setTitle("StoSale");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 890, 520);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(70, 115, 121));
		panel.setBounds(10, 10, 856, 463);
		contentPane.add(panel);
		
		textFieldNomeDoProduto = new JTextField();
		textFieldNomeDoProduto.setColumns(10);
<<<<<<< Updated upstream
		textFieldNomeDoProduto.setBounds(37, 166, 275, 27);
=======
		textFieldNomeDoProduto.setBounds(45, 119, 258, 27);
>>>>>>> Stashed changes
		panel.add(textFieldNomeDoProduto);
		
		JLabel lblNewLabel_2 = new JLabel("NOME DO PRODUTO :");
		lblNewLabel_2.setFont(new Font("Verdana", Font.BOLD, 12));
		lblNewLabel_2.setBounds(100, 90, 148, 19);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("QUANTIDADE DO PRODUTO :");
		lblNewLabel_3.setFont(new Font("Verdana", Font.BOLD, 12));
		lblNewLabel_3.setBounds(72, 156, 200, 13);
		panel.add(lblNewLabel_3);
		
		JButton adicionarProduto = new JButton("ADICIONAR");
		adicionarProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
<<<<<<< Updated upstream
					estoque.addProduto(textFieldNomeDoProduto, textFieldPrecoProduto, textFieldQuantidadeProduto);
=======
					estoque.addProduto(textFieldNomeDoProduto, textFieldPrecoProduto, textFieldQuantidadeProduto, textFieldPeso,
							textFieldDia, textFieldMes, textFieldAno);//@Feliipee013 Passando o dia, mes e ano para adicionar 
					carregarProdutosNaTabela();						  //a data de vencimento
					carregarProdutosNaTabela2();
>>>>>>> Stashed changes
					textFieldNomeDoProduto.setText("");
					textFieldPrecoProduto.setText("");
					textFieldQuantidadeProduto.setText("");
					JOptionPane.showMessageDialog(adicionarProduto, "Produto Adicionado Com Sucesso", "AVISO", JOptionPane.INFORMATION_MESSAGE);
                    carregarProdutosNaTabela();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
				
			}
		});
		adicionarProduto.setForeground(Color.BLACK);
		adicionarProduto.setFont(new Font("Times New Roman", Font.BOLD, 14));
		adicionarProduto.setBackground(new Color(68, 204, 215));
<<<<<<< Updated upstream
		adicionarProduto.setBounds(108, 362, 132, 35);
=======
		adicionarProduto.setBounds(116, 418, 132, 35);
>>>>>>> Stashed changes
		panel.add(adicionarProduto);
		
		JButton btnNewButton_1 = new JButton("VOLTAR");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				JPaginaEstoque jPaginaEstoque = new JPaginaEstoque();
				jPaginaEstoque.setLocationRelativeTo(jPaginaEstoque);
				jPaginaEstoque.setVisible(true);
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnNewButton_1.setBounds(10, 432, 85, 21);
		panel.add(btnNewButton_1);
		
		JLabel lblNewLabel_2_1 = new JLabel("StoSALE");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 32));
		lblNewLabel_2_1.setBounds(100, 0, 148, 31);
		panel.add(lblNewLabel_2_1);
		
		JLabel lblEstoque = new JLabel("ESTOQUE");
		lblEstoque.setHorizontalAlignment(SwingConstants.CENTER);
		lblEstoque.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblEstoque.setBounds(113, 31, 135, 25);
		panel.add(lblEstoque);
		
		JLabel lblNewLabel_2_2 = new JLabel("PREÇO DO PRODUTO :");
		lblNewLabel_2_2.setFont(new Font("Verdana", Font.BOLD, 12));
		lblNewLabel_2_2.setBounds(91, 216, 157, 19);
		panel.add(lblNewLabel_2_2);
		
		textFieldPrecoProduto = new JTextField();
		textFieldPrecoProduto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {//@Feliipee013 Isso faz com q o textField só aceite numeros
				char ch = e.getKeyChar();
				
				if(!(Character.isDigit(ch) || ch == KeyEvent.VK_BACK_SPACE || ch == KeyEvent.VK_DELETE
						|| ch == KeyEvent.VK_PERIOD)) {
					e.consume();
				}
			}
		});
		textFieldPrecoProduto.setColumns(10);
<<<<<<< Updated upstream
		textFieldPrecoProduto.setBounds(37, 292, 275, 27);
=======
		textFieldPrecoProduto.setBounds(45, 245, 258, 27);
>>>>>>> Stashed changes
		panel.add(textFieldPrecoProduto);

		textFieldQuantidadeProduto = new JTextField();
		textFieldQuantidadeProduto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {//@Feliipee013 Isso faz com q o textField só aceite numeros
				char ch = e.getKeyChar();
				
				if(!(Character.isDigit(ch)) || (ch == KeyEvent.VK_BACK_SPACE) || (ch == KeyEvent.VK_DELETE)) {
					e.consume();
				}
			}
		});
		textFieldQuantidadeProduto.setColumns(10);
<<<<<<< Updated upstream
		textFieldQuantidadeProduto.setBounds(37, 226, 275, 27);
=======
		textFieldQuantidadeProduto.setBounds(45, 179, 258, 27);
>>>>>>> Stashed changes
		panel.add(textFieldQuantidadeProduto);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(341, 32, 505, 408);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.BOLD, 10));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"NOME", "PREÇO $", "ID", "QUANTIDADE"
			}
		));
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel_2_3 = new JLabel("ADICIONAR PRODUTO");
		lblNewLabel_2_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_3.setFont(new Font("Verdana", Font.BOLD, 12));
		lblNewLabel_2_3.setBounds(91, 61, 176, 19);
		panel.add(lblNewLabel_2_3);
<<<<<<< Updated upstream
=======
		
		textFieldPeso = new JTextField();
		textFieldPeso.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {//@Feliipee013 Isso faz com q o textField só aceite numeros
				char ch = e.getKeyChar();
				
				if(!(Character.isDigit(ch) || ch == KeyEvent.VK_BACK_SPACE || ch == KeyEvent.VK_DELETE 
						|| ch == KeyEvent.VK_PERIOD)) {
					e.consume();
				}
			}
		});
		textFieldPeso.setColumns(10);
		textFieldPeso.setBounds(45, 311, 258, 27);
		panel.add(textFieldPeso);
		
		JLabel lblNewLabel_2_2_1 = new JLabel("PESO DE UMA UNIDADE (KG) :");
		lblNewLabel_2_2_1.setFont(new Font("Verdana", Font.BOLD, 12));
		lblNewLabel_2_2_1.setBounds(68, 282, 217, 19);
		panel.add(lblNewLabel_2_2_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(313, 351, 533, 102);
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
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		table_1.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		scrollPane_1.setViewportView(table_1);
		
		JLabel lblNewLabel_2_2_1_1 = new JLabel("DATA DE VENCIMENTO : ");//@Feliipee013 Daqui até o próximo comentraio é a interface 
		lblNewLabel_2_2_1_1.setFont(new Font("Verdana", Font.BOLD, 12));//para pegar as datas de vencimento
		lblNewLabel_2_2_1_1.setBounds(72, 348, 217, 19);
		panel.add(lblNewLabel_2_2_1_1);
		
		textFieldDia = new JTextField();
		textFieldDia.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {//@Feliipee013 Isso faz com q o textField só aceite numeros
				char ch = e.getKeyChar();
				
				if(!(Character.isDigit(ch)) || (ch == KeyEvent.VK_BACK_SPACE) || (ch == KeyEvent.VK_DELETE)) {
					e.consume();
				}
			}
		});
		textFieldDia.setBounds(72, 377, 34, 19);
		panel.add(textFieldDia);
		textFieldDia.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("DIA :");
		lblNewLabel.setBounds(45, 380, 61, 13);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("MÊS :");
		lblNewLabel_1.setBounds(116, 380, 44, 13);
		panel.add(lblNewLabel_1);
		
		textFieldMes = new JTextField();
		textFieldMes.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {//@Feliipee013 Isso faz com q o textField só aceite numeros
				char ch = e.getKeyChar();
				
				if(!(Character.isDigit(ch)) || (ch == KeyEvent.VK_BACK_SPACE) || (ch == KeyEvent.VK_DELETE)) {
					e.consume();
				}
			}
		});
		textFieldMes.setColumns(10);
		textFieldMes.setBounds(156, 377, 34, 19);
		panel.add(textFieldMes);
		
		JLabel lblNewLabel_1_1 = new JLabel("ANO :");
		lblNewLabel_1_1.setBounds(200, 380, 61, 13);
		panel.add(lblNewLabel_1_1);
		
		textFieldAno = new JTextField();
		textFieldAno.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {//@Feliipee013 Isso faz com q o textField só aceite numeros
				char ch = e.getKeyChar();
				
				if(!(Character.isDigit(ch)) || (ch == KeyEvent.VK_BACK_SPACE) || (ch == KeyEvent.VK_DELETE)) {
					e.consume();
				}
			}
		});
		textFieldAno.setColumns(10);
		textFieldAno.setBounds(241, 377, 44, 19);
		panel.add(textFieldAno);//Aqui termina de pegar as telas de interface
		
>>>>>>> Stashed changes
		 // Carrega os produtos na tabela ao iniciar o frame
        carregarProdutosNaTabela();
    }

    /**
     * Carrega os produtos na JTable.
     */
    private void carregarProdutosNaTabela() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Limpa a tabela

        List<Produto> produtos = estoque.getProdutos();
        for (Produto produto : produtos) {
            model.addRow(new Object[]{produto.getNome(), produto.getPreco(), produto.getId(), produto.getQuantidade()});
        }
		
	}
<<<<<<< Updated upstream
=======
    
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
            }
        }
    }
    
    public String consultaValidade(Produto produto, LocalDate vencimento) {
    	LocalDate hoje = LocalDate.now();//@Feliipee013 variável para ver q dia é hj
    	
    	if(Period.between(hoje, vencimento).getMonths() == 1) //@Feliipee013 Verificação para ver se falta 1 mes para terminar a validade
    		return "A data de validade do produto: " + produto.getNome() + " está chegando ao fim";
    	else if(Period.between(hoje,vencimento).getDays() <= 0)
    		return "O Produto: " + produto.getNome() + " venceu";
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
>>>>>>> Stashed changes
}
