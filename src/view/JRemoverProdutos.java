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
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.AbstractListModel;
import javax.swing.ImageIcon;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;

public class JRemoverProdutos extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldRemover;
	private JTable table;
	private Estoque estoque;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JRemoverProdutos frame = new JRemoverProdutos();
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
	public JRemoverProdutos() throws IOException {
		
		ImageIcon logo = new ImageIcon(getClass().getClassLoader().getResource("logo.jpg"));
        setIconImage(logo.getImage());
        setTitle("StoSale");
		estoque = new Estoque();
		
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
		
		textFieldRemover = new JTextField();
		textFieldRemover.setColumns(10);
		textFieldRemover.setBounds(21, 207, 277, 27);
		panel.add(textFieldRemover);
		
		JLabel lblNewLabel_1 = new JLabel("REMOVER PRODUTO");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1.setBounds(70, 119, 164, 19);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("ID DO PRODUTO :");
		lblNewLabel_2.setFont(new Font("Verdana", Font.BOLD, 12));
		lblNewLabel_2.setBounds(21, 178, 142, 19);
		panel.add(lblNewLabel_2);
		
		JButton btnRemover = new JButton("REMOVER");
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 try {
	                    estoque.removerProdutoEstoque(textFieldRemover);
	                    carregarProdutosNaTabela();
	                } catch (IOException e1) {
	                    e1.printStackTrace();
	                }
				 textFieldRemover.setText("");
				 JOptionPane.showMessageDialog(btnRemover, "Produto Removido Com Sucesso", "AVISO", JOptionPane.INFORMATION_MESSAGE);
	            }
		});
		btnRemover.setForeground(Color.BLACK);
		btnRemover.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnRemover.setBackground(new Color(68, 204, 215));
		btnRemover.setBounds(95, 272, 115, 35);
		panel.add(btnRemover);
		
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
		lblNewLabel_2_1.setBounds(78, 10, 148, 31);
		panel.add(lblNewLabel_2_1);
		
		JLabel lblEstoque = new JLabel("ESTOQUE");
		lblEstoque.setHorizontalAlignment(SwingConstants.CENTER);
		lblEstoque.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblEstoque.setBounds(85, 38, 135, 25);
		panel.add(lblEstoque);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(341, 45, 505, 408);
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
            model.addRow(new Object[]{produto.getNome(), produto.getPreco(), produto.getId()});
        }
		
	}
}
