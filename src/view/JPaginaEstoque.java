package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.erp.Main;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class JPaginaEstoque extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	ImageIcon logo = new ImageIcon(getClass().getClassLoader().getResource("logo.png"));
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JPaginaEstoque frame = new JPaginaEstoque();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JPaginaEstoque() {
		setIconImage(logo.getImage());
		setTitle("StoSale");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 630, 520);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(70, 115, 121));
		panel.setBounds(74, 10, 480, 443);
		contentPane.add(panel);
		
		JButton btnNewButton_1 = new JButton("SAIR");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Main jPrincipal = new Main();
				jPrincipal.setLocationRelativeTo(jPrincipal);
				jPrincipal.setVisible(true);
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnNewButton_1.setBounds(10, 412, 85, 21);
		panel.add(btnNewButton_1);
		
		JLabel lblNewLabel_2_1 = new JLabel("StoSALE");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 32));
		lblNewLabel_2_1.setBounds(174, 10, 148, 31);
		panel.add(lblNewLabel_2_1);
		
		JLabel lblEstoque = new JLabel("ESTOQUE");
		lblEstoque.setHorizontalAlignment(SwingConstants.CENTER);
		lblEstoque.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblEstoque.setBounds(181, 40, 135, 25);
		panel.add(lblEstoque);
		
		JButton btnListarProdutos = new JButton("ADCIONAR PRODUTOS");
		btnListarProdutos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				JAdicionarProdutos jAdicionarProdutos = null;
				try {
					jAdicionarProdutos = new JAdicionarProdutos();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				jAdicionarProdutos.setLocationRelativeTo(jAdicionarProdutos);
				jAdicionarProdutos.setVisible(true);
			}
		});
		btnListarProdutos.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnListarProdutos.setBounds(144, 151, 209, 39);
		panel.add(btnListarProdutos);
		
		JButton btnRemoverProdutos = new JButton("REMOVER PRODUTOS");
		btnRemoverProdutos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				JRemoverProdutos jRemoverProdutos = null;
				try {
					jRemoverProdutos = new JRemoverProdutos();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				jRemoverProdutos.setLocationRelativeTo(jRemoverProdutos);
				jRemoverProdutos.setVisible(true);
			}
		});
		btnRemoverProdutos.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnRemoverProdutos.setBounds(144, 200, 209, 39);
		panel.add(btnRemoverProdutos);
		
		JButton btnAjuda = new JButton("AJUDA");
		btnAjuda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				JAjudaEstoque jAjudaEstoque = new JAjudaEstoque();
				jAjudaEstoque.setLocationRelativeTo(jAjudaEstoque);
				jAjudaEstoque.setVisible(true);
			}
		});
		btnAjuda.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAjuda.setBounds(144, 249, 209, 39);
		panel.add(btnAjuda);
	}
}
