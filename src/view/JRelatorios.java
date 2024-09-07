package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class JRelatorios extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JRelatorios frame = new JRelatorios();
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
	public JRelatorios() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 630, 520);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(70, 115, 121));
		panel.setBounds(72, 10, 491, 463);
		contentPane.add(panel);
		
		JButton btnNewButton_1 = new JButton("VOLTAR");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				JGerencia jGerencia = null;
				jGerencia = new JGerencia();
				jGerencia.setLocationRelativeTo(jGerencia);
				jGerencia.setVisible(true);
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
		
		JButton btnRelatorioVendas = new JButton("RELATORIO VENDAS");
		btnRelatorioVendas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				JRelatoriosVendas jRelatoriosVendas = null;
				jRelatoriosVendas = new JRelatoriosVendas();
				jRelatoriosVendas.setLocationRelativeTo(jRelatoriosVendas);
				jRelatoriosVendas.setVisible(true);
			}
		});
		btnRelatorioVendas.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnRelatorioVendas.setBounds(133, 178, 225, 39);
		panel.add(btnRelatorioVendas);
		
		JButton btnRelatorioProdutos = new JButton("RELATORIOS PRODUTOS");
		btnRelatorioProdutos.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnRelatorioProdutos.setBounds(133, 227, 225, 39);
		panel.add(btnRelatorioProdutos);
	}

}
