package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JPrincipal frame = new JPrincipal();
					frame.setLocationRelativeTo(null);
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
	public JPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 630, 520);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 128, 192));
		panel.setBounds(73, 21, 480, 443);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Bem-Vindo");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel.setBounds(164, 71, 135, 25);
		panel.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("ESTOQUE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				JLoginE jLoginE = new JLoginE();
				jLoginE.setLocationRelativeTo(jLoginE);
				jLoginE.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setBounds(141, 168, 181, 39);
		panel.add(btnNewButton);
		
		JButton btnVenda = new JButton("VENDAS");
		btnVenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				JLoginV jLoginV = new JLoginV();
				jLoginV.setLocationRelativeTo(jLoginV);
				jLoginV.setVisible(true);
			}
		});
		btnVenda.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnVenda.setBounds(141, 219, 181, 39);
		panel.add(btnVenda);
		
		JButton btnGerencia = new JButton("GERENCIA");
		btnGerencia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				JLoginG jLoginG = new JLoginG();
				jLoginG.setLocationRelativeTo(jLoginG);
				jLoginG.setVisible(true);
			}
		});
		btnGerencia.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnGerencia.setBounds(141, 268, 181, 39);
		panel.add(btnGerencia);
		
		JLabel lblNewLabel_1 = new JLabel("Selecione O Setor");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(141, 136, 181, 13);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("StoSALE");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 32));
		lblNewLabel_2.setBounds(157, 41, 148, 31);
		panel.add(lblNewLabel_2);
	}
}