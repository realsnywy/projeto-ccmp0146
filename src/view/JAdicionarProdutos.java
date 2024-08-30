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
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class JAdicionarProdutos extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldNomeDoProduto;
	private JTextField textFieldPrecoProduto;
	private JTextField textFieldQuantidadeProduto;
	private JTable table;
	private Estoque estoque;
	private float totalEstoque = 1000;//quantidade do espaço do estoque

	/**
	 * Launch the application.
	 */
	ImageIcon logo = new ImageIcon(getClass().getClassLoader().getResource("logo.png"));
	private JTextField textFieldPeso;
	private JTable table_1;
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
		textFieldNomeDoProduto.setBounds(45, 166, 258, 27);
		panel.add(textFieldNomeDoProduto);
		
		JLabel lblNewLabel_2 = new JLabel("NOME DO PRODUTO :");
		lblNewLabel_2.setFont(new Font("Verdana", Font.BOLD, 12));
		lblNewLabel_2.setBounds(100, 137, 148, 19);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("QUANTIDADE DO PRODUTO :");
		lblNewLabel_3.setFont(new Font("Verdana", Font.BOLD, 12));
		lblNewLabel_3.setBounds(74, 203, 200, 13);
		panel.add(lblNewLabel_3);
		
		JButton adicionarProduto = new JButton("ADICIONAR");
		adicionarProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					estoque.addProduto(textFieldNomeDoProduto, textFieldPrecoProduto, textFieldQuantidadeProduto, textFieldPeso);
					carregarProdutosNaTabela();
					carregarProdutosNaTabela2();
					textFieldNomeDoProduto.setText("");
					textFieldPrecoProduto.setText("");
					textFieldQuantidadeProduto.setText("");
					textFieldPeso.setText("");
					JOptionPane.showMessageDialog(adicionarProduto, "Produto Adicionado Com Sucesso", "AVISO", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
				
			}
		});
		adicionarProduto.setForeground(Color.BLACK);
		adicionarProduto.setFont(new Font("Times New Roman", Font.BOLD, 14));
		adicionarProduto.setBackground(new Color(68, 204, 215));
		adicionarProduto.setBounds(108, 388, 132, 35);
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
		lblNewLabel_2_1.setBounds(100, 20, 148, 31);
		panel.add(lblNewLabel_2_1);
		
		JLabel lblEstoque = new JLabel("ESTOQUE");
		lblEstoque.setHorizontalAlignment(SwingConstants.CENTER);
		lblEstoque.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblEstoque.setBounds(107, 46, 135, 25);
		panel.add(lblEstoque);
		
		JLabel lblNewLabel_2_2 = new JLabel("PREÇO DO PRODUTO :");
		lblNewLabel_2_2.setFont(new Font("Verdana", Font.BOLD, 12));
		lblNewLabel_2_2.setBounds(96, 263, 157, 19);
		panel.add(lblNewLabel_2_2);
		
		textFieldPrecoProduto = new JTextField();
		textFieldPrecoProduto.setColumns(10);
		textFieldPrecoProduto.setBounds(45, 292, 258, 27);
		panel.add(textFieldPrecoProduto);

		textFieldQuantidadeProduto = new JTextField();
		textFieldQuantidadeProduto.setColumns(10);
		textFieldQuantidadeProduto.setBounds(45, 226, 258, 27);
		panel.add(textFieldQuantidadeProduto);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(313, 32, 533, 308);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"NOME", "PREÇO $", "ID", "QUANTIDADE", "PESO TOTAL", "% ESTOQUE"
			}
		));
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel_2_3 = new JLabel("ADICIONAR PRODUTO");
		lblNewLabel_2_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_3.setFont(new Font("Verdana", Font.BOLD, 12));
		lblNewLabel_2_3.setBounds(86, 97, 176, 19);
		panel.add(lblNewLabel_2_3);
		
		textFieldPeso = new JTextField();
		textFieldPeso.setColumns(10);
		textFieldPeso.setBounds(45, 351, 258, 27);
		panel.add(textFieldPeso);
		
		JLabel lblNewLabel_2_2_1 = new JLabel("PESO DE UMA UNIDADE (KG) :");
		lblNewLabel_2_2_1.setFont(new Font("Verdana", Font.BOLD, 12));
		lblNewLabel_2_2_1.setBounds(66, 329, 217, 19);
		panel.add(lblNewLabel_2_2_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(313, 351, 533, 102);
		panel.add(scrollPane_1);
		
		table_1 = new JTable();
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
		
		 // Carrega os produtos na tabela ao iniciar o frame
        carregarProdutosNaTabela();
        carregarProdutosNaTabela2();
    }

    /**
     * Carrega os produtos na JTable.
     */
    private void carregarProdutosNaTabela() {
    	DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Limpa a tabela

        List<Produto> produtos = estoque.getProdutos();

        for (Produto produto : produtos) {
        	
        	float pesoSuportadoNoEstoque = totalEstoque/produto.getPeso();
            float nivelDoEstoque = (produto.getPesoTotal() * 100)/pesoSuportadoNoEstoque;
            
            String id = String.valueOf(produto.getId());
                model.addRow(new Object[]{
                produto.getNome(), 
                produto.getPreco(), 
                produto.getId(), 
                produto.getQuantidade(),
                produto.getPesoTotal(),
                nivelDoEstoque + "%"
            });
        }
	}
    
    private void carregarProdutosNaTabela2() {
        DefaultTableModel model = (DefaultTableModel) table_1.getModel();
        model.setRowCount(0); // Limpa a tabela

        List<Produto> produtos = estoque.getProdutos();

        for (Produto produto : produtos) {
        	float pesoSuportadoNoEstoque = totalEstoque/produto.getPeso();
            String nivelDoEstoque = consultaDeInfo(produto, pesoSuportadoNoEstoque);// Passa o produto específico

            if (nivelDoEstoque != null) {
                model.addRow(new Object[]{nivelDoEstoque});
            }
        }
    }
    
    public String consultaDeInfo(Produto produto, float pesoSuportadoNoEstoque) {
        float pesoMin = (float) (pesoSuportadoNoEstoque * 0.4);

        if (produto.getPesoTotal() <= pesoMin) {
            return "Estoque do produto: " + produto.getNome() +
                   " está em níveis baixos, por favor solicite abastecimento.";
        }
        
        return null;
    }

    
}
