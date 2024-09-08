package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.erp.Estoque;

import model.NotaFiscal;
import javax.swing.JComboBox;

public class JRelatoriosVendas extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tabelaData;
	private JTable tabelaRelatorio;
	private JTable tableTotal;
	private JComboBox<String> comboBoxDia;
	private JComboBox<String> comboBoxMes;
	private JComboBox<String> comboBoxAno;

	/**
	 * Launch the application.
	 */
	ImageIcon logo = new ImageIcon(getClass().getClassLoader().getResource("logo.png"));
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JRelatoriosVendas frame = new JRelatoriosVendas();
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
	 * @throws IOException 
	 */
	public JRelatoriosVendas() throws IOException {
		setIconImage(logo.getImage());
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
		scrollPane.setBounds(10, 87, 856, 435);
		panel.add(scrollPane);
		
		tabelaRelatorio = new JTable();
        tabelaRelatorio.setModel(new DefaultTableModel(
        	new Object[][] {
        	},
        	new String[] {
        		"NOME VENDEDOR", "DATA", "VALOR DA VENDA", "ID NOTA FISCAL"
        	}
        ));
		scrollPane.setViewportView(tabelaRelatorio);
		carregarProdutosNaTabela();
		
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
		
		Estoque estoque = new Estoque();
        List<NotaFiscal> notasFiscais = estoque.getNotaFiscal();
        List<String> nomesVendedores = new ArrayList<>();
        nomesVendedores.add("Todos os vendedores");
        for (NotaFiscal nota : notasFiscais) {
            if (!nomesVendedores.contains(nota.getNomeVendedor())) {
                nomesVendedores.add(nota.getNomeVendedor());
            }
        }
		JComboBox<String> comboBoxFiltro = new JComboBox<>(nomesVendedores.toArray(new String[0]));
        comboBoxFiltro.setBounds(10, 554, 135, 31);
        panel.add(comboBoxFiltro);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(768, 7, 90, 41);
		panel.add(scrollPane_1);
		
		String[] colunas = {"DATA"};
        DefaultTableModel modelo = new DefaultTableModel(colunas, 0);
        modelo.addRow(new Object[]{""});
        tabelaData = new JTable(modelo);
        scrollPane_1.setViewportView(tabelaData);
        
        comboBoxDia = new JComboBox<>();
        comboBoxDia.addItem("");
        for (int dia = 1; dia <= 31; dia++) {
            comboBoxDia.addItem(String.valueOf(dia));
        }
        comboBoxDia.setBounds(155, 554, 38, 30);
        panel.add(comboBoxDia);
        
        JLabel lblNewLabel = new JLabel("Nome Vendedor");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblNewLabel.setBounds(20, 532, 100, 13);
        panel.add(lblNewLabel);
        
        JLabel lblDia = new JLabel("DIA");
        lblDia.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblDia.setBounds(156, 531, 26, 13);
        panel.add(lblDia);
        
        JLabel lblMs = new JLabel("Mês");
        lblMs.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblMs.setBounds(219, 532, 26, 13);
        panel.add(lblMs);
        
        comboBoxMes = new JComboBox<>();
        comboBoxMes.addItem("");
        for (int mes = 1; mes <= 12; mes++) {
            comboBoxMes.addItem(String.valueOf(mes));
        }
        comboBoxMes.setBounds(213, 554, 38, 30);
        panel.add(comboBoxMes);
        
        comboBoxAno = new JComboBox<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        int ano = Integer.parseInt(sdf.format(new Date()));
        comboBoxAno.addItem("");
        for (int anos = 1970; anos <= ano; anos++) {
            comboBoxAno.addItem(String.valueOf(anos));
        }
        comboBoxAno.setBounds(269, 553, 57, 30);
        panel.add(comboBoxAno);
        
        JLabel lblAno = new JLabel("ANO");
        lblAno.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblAno.setBounds(281, 533, 26, 13);
        panel.add(lblAno);
        
        JButton btnNewButton = new JButton("FILTRAR");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String filtroNomeVendedor = (String) comboBoxFiltro.getSelectedItem();
                String dia = (String) comboBoxDia.getSelectedItem();
                String mes = (String) comboBoxMes.getSelectedItem();
                String ano = (String) comboBoxAno.getSelectedItem();
                try {
					filtrarNotasFiscais(filtroNomeVendedor, dia, mes, ano);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
        });
        btnNewButton.setBounds(348, 551, 85, 31);
        panel.add(btnNewButton);
        
        String[] columnNames = {"TOTAL VENDAS"};
        Object[][] data = {{"0.00"}};
        DefaultTableModel modelTotal = new DefaultTableModel(data, columnNames);
        tableTotal = new JTable(modelTotal);
        tableTotal.setFont(new Font("Tahoma", Font.PLAIN, 18));
        tableTotal.setBounds(509, 124, 121, 31);
        JScrollPane scrollPaneTotal = new JScrollPane(tableTotal);
        scrollPaneTotal.setBounds(743, 533, 123, 46);
        panel.add(scrollPaneTotal);
        
        JButton btnNewButton_1 = new JButton("REDEFINIR");
        btnNewButton_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
					carregarProdutosNaTabela();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
        });
        btnNewButton_1.setBounds(743, 589, 123, 31);
        panel.add(btnNewButton_1);
        
        JButton btnVendasPorPeriodo = new JButton("GRAFICOS VENDAS POR PERIODO");
        btnVendasPorPeriodo.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
                    Estoque estoque = new Estoque();
                    List<NotaFiscal> notasFiscais = estoque.getNotaFiscal();
                    JGraficoVendasPorPeriodo graficoVendas = new JGraficoVendasPorPeriodo(notasFiscais);
                    graficoVendas.setLocationRelativeTo(graficoVendas);
                    graficoVendas.setVisible(true);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao abrir gráficos de vendas: " + ex.getMessage());
                }
        	}
        });
        btnVendasPorPeriodo.setBounds(10, 607, 260, 31);
        panel.add(btnVendasPorPeriodo);
        
        JButton btnNotasFiscais = new JButton("NOTAS FISCAIS");
        btnNotasFiscais.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		JNotasFiscaisFrame jNotas = null;
				try {
					jNotas = new JNotasFiscaisFrame();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        		jNotas.setLocationRelativeTo(jNotas);
        		jNotas.setVisible(true);
        	}
        });
        btnNotasFiscais.setBounds(10, 648, 260, 31);
        panel.add(btnNotasFiscais);
        
     // Timer para atualizar a data e hora a cada segundo (1000 milissegundos)
        Timer timer = new Timer(1000, e -> {
            // Obter a data
            SimpleDateFormat datinha = new SimpleDateFormat("dd/MM/yyyy");
            String dataAtual = datinha.format(new Date());

            // Atualizar o valor da célula na tabela
            modelo.setValueAt(dataAtual, 0, 0);
        });
        timer.start();//timer para atualiaz a data a cada 1 seg
	}
	
	private void carregarProdutosNaTabela() throws IOException {
	    DefaultTableModel model = (DefaultTableModel) tabelaRelatorio.getModel();
	    model.setRowCount(0);
	    Estoque estoque = new Estoque();
	    List<NotaFiscal> notasFiscais = estoque.getNotaFiscal(); 
	    for (NotaFiscal nota : notasFiscais) {
	        model.addRow(new Object[]{
	            nota.getNomeVendedor(),
	            nota.getData(),
	            nota.getValor(),
	            nota.getIdNota()
	        });
	    }
	}
	
	private void filtrarNotasFiscais(String nomeVendedor, String dia, String mes, String ano) throws IOException {
        DefaultTableModel model = (DefaultTableModel) tabelaRelatorio.getModel();
        model.setRowCount(0); // Limpa a tabela antes de adicionar novos dados
        
        Estoque estoque = new Estoque();
        List<NotaFiscal> notasFiscais = estoque.getNotaFiscal();

        List<NotaFiscal> notasFiltradas = new ArrayList<>();

        for (NotaFiscal nota : notasFiscais) {
            boolean adicionarNota = true;

            String[] dataPartes = nota.getData().split("/");
            String dias = dataPartes[0];
            String meses = dataPartes[1];
            String anos = dataPartes[2];

            // Verificações de filtro
            if (!nomeVendedor.equalsIgnoreCase("Todos os vendedores") && !nota.getNomeVendedor().equalsIgnoreCase(nomeVendedor)) {
                adicionarNota = false;
            }
            if (!dia.isEmpty() && !dias.equals(dia)) {
                adicionarNota = false;
            }
            if (!mes.isEmpty() && !meses.equals(mes)) {
                adicionarNota = false;
            }
            if (!ano.isEmpty() && !anos.equals(ano)) {
                adicionarNota = false;
            }

            if (adicionarNota) {
                notasFiltradas.add(nota);
            }
        }

        // Atualiza a tabela com as notas fiscais filtradas
        double totalVendas = 0;
        for (NotaFiscal nota : notasFiltradas) {
            model.addRow(new Object[]{
                nota.getNomeVendedor(),
                nota.getData(),
                nota.getValor(),
                nota.getIdNota()
            });
            totalVendas += nota.getValor();
        }

        // Atualiza o total na tabela de total
        DefaultTableModel modelTotal = (DefaultTableModel) tableTotal.getModel();
        modelTotal.setValueAt(String.format("%.2f", totalVendas), 0, 0);
    }
}
