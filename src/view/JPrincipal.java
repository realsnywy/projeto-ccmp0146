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
import java.io.FileWriter;
import java.io.IOException;
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
		JOptionPane.showMessageDialog(null, "No momento, vamos verificar se há atualizações. Aguarde um pouco!",
				"Atualizador",
				JOptionPane.INFORMATION_MESSAGE);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
		}
		JOptionPane.showMessageDialog(null, "Não há atualizações no momento!", "Atualizador",
				JOptionPane.INFORMATION_MESSAGE);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 630, 520);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(64, 128, 128));
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

		JLabel lblNewLabel_3 = new JLabel("stockreserveoficial@gmail.com");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(302, 406, 157, 13);
		panel.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("SAC");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_4.setBounds(302, 389, 45, 13);
		panel.add(lblNewLabel_4);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(302, 384, 148, 35);
		panel.add(panel_1);

		JButton btnAtivarLicenca = new JButton("Ativar Licença");
		btnAtivarLicenca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String chaveSerie = JOptionPane.showInputDialog("Digite a chave de série:");
				String usuarioRegistrado = JOptionPane.showInputDialog("Digite o usuário registrado:");

				if (chaveSerie != null && usuarioRegistrado != null) {
					try (FileWriter writer = new FileWriter("licenca.txt")) {
						writer.write(chaveSerie + "\n" + usuarioRegistrado);
						writer.flush();
					} catch (IOException ex) {
						JOptionPane.showMessageDialog(null, "Erro ao salvar informações de licença.");
						return;
					}
					// Como é apenas uma questão de "idéia", o programa não carregará de fato uma licença toda vez que abrir. Mas tá aí. :P

					JOptionPane.showMessageDialog(null, "Licença ativada com sucesso.");
					btnAtivarLicenca.setText("Ativado!");
					btnAtivarLicenca.setEnabled(false);
				}
			}
		});
		btnAtivarLicenca.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnAtivarLicenca.setBounds(302, 350, 148, 23);
		panel.add(btnAtivarLicenca);
	}
}
