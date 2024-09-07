package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class JRelatoriosVendas extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tabelaData;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JRelatoriosVendas frame = new JRelatoriosVendas();
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
	public JRelatoriosVendas() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 913, 764);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(64, 128, 128));
		panel.setBounds(10, 0, 876, 723);
		contentPane.add(panel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 165, 838, 435);
		panel.add(scrollPane);
		
		tabelaData = new JTable();
		scrollPane.setViewportView(tabelaData);
		
		JLabel lblNewLabel_2_1 = new JLabel("StoSALE");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 32));
		lblNewLabel_2_1.setBounds(343, 22, 148, 31);
		panel.add(lblNewLabel_2_1);
		
		JLabel lblGerencia = new JLabel("GERÊNCIA");
		lblGerencia.setHorizontalAlignment(SwingConstants.CENTER);
		lblGerencia.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblGerencia.setBounds(350, 52, 135, 25);
		panel.add(lblGerencia);
		
		JButton btnVoltar = new JButton("VOLTAR");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				JRelatorios jRelatorios = null;
				jRelatorios = new JRelatorios();
				jRelatorios.setLocationRelativeTo(jRelatorios);
				jRelatorios.setVisible(true);
			}
		});
		btnVoltar.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnVoltar.setBounds(20, 10, 85, 21);
		panel.add(btnVoltar);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(768, 7, 90, 41);
		panel.add(scrollPane_1);
		
		String[] colunas = {"DATA"};
        DefaultTableModel modelo = new DefaultTableModel(colunas, 0);
        modelo.addRow(new Object[]{""});
        tabelaData = new JTable(modelo);
        scrollPane_1.setViewportView(tabelaData);
     // Timer para atualizar a data e hora a cada segundo (1000 milissegundos)
        Timer timer = new Timer(1000, e -> {
            // Obter a data
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String dataAtual = sdf.format(new Date());

            // Atualizar o valor da célula na tabela
            modelo.setValueAt(dataAtual, 0, 0);
        });
        timer.start();//timer para atualiaz a data a cada 1 seg
	}
}
