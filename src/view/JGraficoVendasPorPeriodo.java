package view;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import model.NotaFiscal;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class JGraficoVendasPorPeriodo extends JFrame {

    private JTree tree;
    private JPanel contentPane;
    private JPanel chartPanel; // Painel para exibir o gráfico
    private Map<String, Map<String, Map<String, List<NotaFiscal>>>> vendasPorPeriodo;
    
    ImageIcon logo = new ImageIcon(getClass().getClassLoader().getResource("logo.png"));

    public JGraficoVendasPorPeriodo(List<NotaFiscal> notasFiscais) {
    	setIconImage(logo.getImage());
        setTitle("Gráficos de Vendas por Período");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 1190, 786); // Ajusta o tamanho da janela
        
        contentPane = new JPanel();
        contentPane.setBackground(new Color(64, 128, 128));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        // Criar árvore de períodos
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 10, 204, 729);
        contentPane.add(scrollPane);
        
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Vendas por Período");
        DefaultTreeModel treeModel = new DefaultTreeModel(root);
        tree = new JTree(treeModel);
        scrollPane.setViewportView(tree);

        // Organizar as notas fiscais por ano, mês e dia
        vendasPorPeriodo = organizarVendasPorPeriodo(notasFiscais);
        
        // Adicionar os períodos na árvore
        for (String ano : vendasPorPeriodo.keySet()) {
            DefaultMutableTreeNode anoNode = new DefaultMutableTreeNode(ano);
            root.add(anoNode);
            for (String mes : vendasPorPeriodo.get(ano).keySet()) {
                DefaultMutableTreeNode mesNode = new DefaultMutableTreeNode(mes);
                anoNode.add(mesNode);
                for (String dia : vendasPorPeriodo.get(ano).get(mes).keySet()) {
                    DefaultMutableTreeNode diaNode = new DefaultMutableTreeNode(dia);
                    mesNode.add(diaNode);
                }
            }
        }
        treeModel.reload();

        // Painel para o gráfico
        chartPanel = new JPanel(new BorderLayout());
        chartPanel.setBounds(220, 10, 946, 729); // Ajusta o tamanho e a posição do painel do gráfico
        contentPane.add(chartPanel);
        
        // Adicionar ActionListener na árvore
        tree.addTreeSelectionListener(e -> {
            DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
            if (selectedNode == null) return;

            String dia = null;
            String mes = null;
            String ano = null;
            DefaultMutableTreeNode rootNode = (DefaultMutableTreeNode) treeModel.getRoot();

            if (selectedNode.isLeaf() && !selectedNode.equals(rootNode)) {
                // Obter ano, mês e dia selecionados
                dia = selectedNode.toString();
                DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) selectedNode.getParent();
                if (parentNode != null) {
                    mes = parentNode.toString();
                    DefaultMutableTreeNode grandParentNode = (DefaultMutableTreeNode) parentNode.getParent();
                    if (grandParentNode != null) {
                        ano = grandParentNode.toString();
                    }
                }
            } else if (selectedNode.getChildCount() > 0 && selectedNode.getParent() != null && selectedNode.getChildAt(0).isLeaf() && !selectedNode.equals(rootNode)) {
                // Nó selecionado é um mês se tem filhos (dias) e o pai é um nó de ano
                if (selectedNode.getParent().getChildCount() > 0) {
                    mes = selectedNode.toString();
                    ano = ((DefaultMutableTreeNode) selectedNode.getParent()).toString();
                }
            } else if (selectedNode.getParent() != null && !selectedNode.equals(rootNode) ) {
                // Nó selecionado é um ano se não tem pai
                ano = selectedNode.toString();
            }

            // Atualizar o gráfico com os dados correspondentes
            atualizarGrafico(ano, mes, dia);
        });
    }

    private Map<String, Map<String, Map<String, List<NotaFiscal>>>> organizarVendasPorPeriodo(List<NotaFiscal> notasFiscais) {
        Map<String, Map<String, Map<String, List<NotaFiscal>>>> vendasPorPeriodo = new HashMap<>();

        for (NotaFiscal nota : notasFiscais) {
            String[] dataPartes = nota.getData().split("/");
            String dia = dataPartes[0];
            String mes = dataPartes[1];
            String ano = dataPartes[2];

            vendasPorPeriodo
                .computeIfAbsent(ano, k -> new HashMap<>())
                .computeIfAbsent(mes, k -> new HashMap<>())
                .computeIfAbsent(dia, k -> new ArrayList<>())
                .add(nota);
        }
        return vendasPorPeriodo;
    }
    
    private void atualizarGrafico(String ano, String mes, String dia) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        if (dia != null && mes != null) {
            // Atualizar para um dia específico

            // Adicionar as vendas do dia selecionado
            Map<String, List<NotaFiscal>> notasDoMes = vendasPorPeriodo
                .getOrDefault(ano, new HashMap<>())
                .getOrDefault(mes, new HashMap<>());

            double totalVendasDiaSelecionado = 0;
            for (NotaFiscal nota : notasDoMes.getOrDefault(dia, new ArrayList<>())) {
                totalVendasDiaSelecionado += nota.getValor();
            }
            dataset.addValue(totalVendasDiaSelecionado, "Vendas", dia + "/" + mes + "/" + ano);

        } else if (mes != null && ano != null) {
            // Atualizar para um mês específico

            // Adicionar as vendas de todos os dias do mês selecionado
            Map<String, List<NotaFiscal>> notasDoMesSelecionado = vendasPorPeriodo
                .getOrDefault(ano, new HashMap<>())
                .getOrDefault(mes, new HashMap<>());

            for (String diaDoMes : notasDoMesSelecionado.keySet()) {
                double totalVendasDia = 0;
                for (NotaFiscal nota : notasDoMesSelecionado.get(diaDoMes)) {
                    totalVendasDia += nota.getValor();
                }
                dataset.addValue(totalVendasDia, "Vendas", diaDoMes);
            }

        } else if (ano != null) {
            // Atualizar para um ano específico

            // Adicionar as vendas de todos os meses do ano selecionado
            Map<String, Map<String, List<NotaFiscal>>> mesesDoAno = vendasPorPeriodo.getOrDefault(ano, new HashMap<>());

            for (Map.Entry<String, Map<String, List<NotaFiscal>>> mesEntry : mesesDoAno.entrySet()) {
                String mesSelecionado = mesEntry.getKey();
                double totalVendasMes = 0;
                for (List<NotaFiscal> notasDoDia : mesEntry.getValue().values()) {
                    for (NotaFiscal nota : notasDoDia) {
                        totalVendasMes += nota.getValor();
                    }
                }
                dataset.addValue(totalVendasMes, "Vendas", mesSelecionado + "/" + ano);

            }
        }

        // Criar gráfico de barras
        JFreeChart chart = ChartFactory.createBarChart(
            "Vendas para " + (dia != null ? dia : mes != null ? mes : ano),
            "Período",
            "Total de Vendas",
            dataset,
            PlotOrientation.VERTICAL,
            true, true, false
        );

        // Ajustar a largura das barras
        BarRenderer renderer = (BarRenderer) chart.getCategoryPlot().getRenderer();
        renderer.setMaximumBarWidth(0.05); // Ajuste a largura das barras aqui


        // Atualizar o painel do gráfico
        chartPanel.removeAll(); // Limpar o painel do gráfico
        ChartPanel newChartPanel = new ChartPanel(chart);
        newChartPanel.setPreferredSize(new Dimension(550, 540));
        chartPanel.add(newChartPanel, BorderLayout.CENTER);
        chartPanel.revalidate();
        chartPanel.repaint();
    }

}
