package view;

import model.NotaFiscal;
import model.Produto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.erp.Estoque;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JNotasFiscaisFrame extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable tableNotasFiscais;
    private DefaultTableModel tableModel;
    private List<NotaFiscal> notasFiscais;

    public JNotasFiscaisFrame() throws IOException {
        setTitle("Notas Fiscais");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Inicializando a lista de notas fiscais
        notasFiscais = new ArrayList<>();

        // Criando o modelo da tabela
        tableModel = new DefaultTableModel(new Object[]{"ID Nota", "Vendedor", "Cliente", "Valor", "Data"}, 0);
        tableNotasFiscais = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tableNotasFiscais);
        add(scrollPane, BorderLayout.CENTER);

        // Botão para visualizar detalhes da nota fiscal
        JButton btnVerDetalhes = new JButton("Ver Detalhes");
        btnVerDetalhes.addActionListener(e -> mostrarDetalhesNotaFiscal());
        add(btnVerDetalhes, BorderLayout.SOUTH);

        // Adicionando algumas notas fiscais de exemplo
        adicionarNotaFiscalExemplo();
    }

    private void adicionarNotaFiscalExemplo() {
        try {
            Estoque estoque = new Estoque();
            List<NotaFiscal> notas = estoque.getNotaFiscal();

            for (NotaFiscal nota : notas) {
                // Adicionando a nota fiscal à lista
                notasFiscais.add(nota);

                // Adicionando a nota fiscal à tabela
                tableModel.addRow(new Object[]{
                    nota.getIdNota(),
                    nota.getNomeVendedor(),
                    nota.getNomeCliente(),
                    nota.getValor(),
                    nota.getData()
                });
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar notas fiscais: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void mostrarDetalhesNotaFiscal() {
        int selectedRow = tableNotasFiscais.getSelectedRow();
        if (selectedRow >= 0) {
            // Obtendo o ID da nota fiscal selecionada
            String idNota = (String) tableModel.getValueAt(selectedRow, 0);

            // Encontrando a nota fiscal correspondente
            NotaFiscal notaSelecionada = null;
            for (NotaFiscal nota : notasFiscais) {
                if (nota.getIdNota().equals(idNota)) {
                    notaSelecionada = nota;
                    break;
                }
            }

            if (notaSelecionada != null) {
                // Criando um JFrame para mostrar os detalhes
                JFrame detalhesFrame = new JFrame("Detalhes da Nota Fiscal");
                detalhesFrame.setSize(600, 400);
                detalhesFrame.setLayout(new BorderLayout());

                // Painel para organizar o conteúdo
                JPanel panelDetalhes = new JPanel();
                panelDetalhes.setLayout(new GridBagLayout());
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.insets = new Insets(5, 5, 5, 5);
                gbc.anchor = GridBagConstraints.WEST;

                // Adicionando informações da nota fiscal
                gbc.gridx = 0;
                gbc.gridy = 0;
                panelDetalhes.add(new JLabel("ID Nota:"), gbc);

                gbc.gridx = 1;
                panelDetalhes.add(new JLabel(notaSelecionada.getIdNota()), gbc);

                gbc.gridx = 0;
                gbc.gridy = 1;
                panelDetalhes.add(new JLabel("Vendedor:"), gbc);

                gbc.gridx = 1;
                panelDetalhes.add(new JLabel(notaSelecionada.getNomeVendedor()), gbc);

                gbc.gridx = 0;
                gbc.gridy = 2;
                panelDetalhes.add(new JLabel("Cliente:"), gbc);

                gbc.gridx = 1;
                panelDetalhes.add(new JLabel(notaSelecionada.getNomeCliente()), gbc);

                gbc.gridx = 0;
                gbc.gridy = 3;
                panelDetalhes.add(new JLabel("Valor:"), gbc);

                gbc.gridx = 1;
                panelDetalhes.add(new JLabel(String.valueOf(notaSelecionada.getValor())), gbc);

                gbc.gridx = 0;
                gbc.gridy = 4;
                panelDetalhes.add(new JLabel("Data:"), gbc);

                gbc.gridx = 1;
                panelDetalhes.add(new JLabel(notaSelecionada.getData()), gbc);

                // Adicionando a lista de produtos
                gbc.gridx = 0;
                gbc.gridy = 5;
                gbc.gridwidth = 2;
                gbc.anchor = GridBagConstraints.CENTER;
                panelDetalhes.add(new JLabel("Produtos:"), gbc);

                // Criando o modelo da tabela para produtos
                String[] colunas = {"ID", "Nome", "Preço", "Quantidade"};
                DefaultTableModel produtoTableModel = new DefaultTableModel(colunas, 0);
                JTable tableProdutos = new JTable(produtoTableModel);

                // Adicionando produtos à tabela
                for (Produto produto : notaSelecionada.getProdutos()) {
                    produtoTableModel.addRow(new Object[]{
                        produto.getId(),
                        produto.getNome(),
                        produto.getPreco(),
                        produto.getQuantidade()
                    });
                }

                JScrollPane scrollPaneProdutos = new JScrollPane(tableProdutos);
                gbc.gridy = 6;
                gbc.fill = GridBagConstraints.BOTH;
                gbc.weightx = 1.0;
                gbc.weighty = 1.0;
                panelDetalhes.add(scrollPaneProdutos, gbc);
                
                detalhesFrame.add(panelDetalhes, BorderLayout.CENTER);
                detalhesFrame.setLocationRelativeTo(null);

                detalhesFrame.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Nota Fiscal não encontrada!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Nenhuma nota fiscal selecionada!", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                JNotasFiscaisFrame frame = new JNotasFiscaisFrame();
                frame.setVisible(true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
