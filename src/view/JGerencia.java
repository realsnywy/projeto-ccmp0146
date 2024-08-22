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
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JGerencia extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JGerencia frame = new JGerencia();
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
	public JGerencia() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 630, 520);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(70, 115, 121));
		panel.setBounds(65, 10, 491, 463);
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
		btnNewButton_1.setBounds(10, 432, 85, 21);
		panel.add(btnNewButton_1);
		
		JLabel lblNewLabel_2_1 = new JLabel("StoSALE");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 32));
		lblNewLabel_2_1.setBounds(171, 35, 148, 31);
		panel.add(lblNewLabel_2_1);
		
		JLabel lblGerencia = new JLabel("GERENCIA");
		lblGerencia.setHorizontalAlignment(SwingConstants.CENTER);
		lblGerencia.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblGerencia.setBounds(178, 65, 135, 25);
		panel.add(lblGerencia);
		
		JButton btnListarProdutos = new JButton("CADASTRAR USUÁRIO");
		btnListarProdutos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				JCadastroUsuario jCadastroUsuario = new JCadastroUsuario();
				jCadastroUsuario.setLocationRelativeTo(jCadastroUsuario);
				jCadastroUsuario.setVisible(true);
			}
		});
		btnListarProdutos.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnListarProdutos.setBounds(145, 178, 209, 39);
		panel.add(btnListarProdutos);
		
		JButton btnAjuda = new JButton("AJUDA");
		btnAjuda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				JAjuda jAjuda = new JAjuda();
				jAjuda.setLocationRelativeTo(jAjuda);
				jAjuda.setVisible(true);
			}
		});
		btnAjuda.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAjuda.setBounds(145, 236, 209, 39);
		panel.add(btnAjuda);
	}
}
