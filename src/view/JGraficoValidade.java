package view;

import java.awt.Color;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import com.erp.Estoque;
import model.Produto;
public class JGraficoValidade extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Estoque estoque;
	private JFreeChart barChart;
	
	ImageIcon logo = new ImageIcon(getClass().getClassLoader().getResource("logo.png"));
	public JGraficoValidade() throws IOException {
		
		estoque = new Estoque();
		
		CategoryDataset dataset = createDataset();
		
		JFreeChart chart = createChart(dataset);
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		chartPanel.setBackground(Color.WHITE);
		add(chartPanel);
		setTitle("Gráfico de validades");
		setIconImage(logo.getImage());
		
		
		pack();
		setTitle("Gráfico de validades");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	private JFreeChart createChart (CategoryDataset dataset) {
		 	barChart = ChartFactory.createBarChart("Prazo de validade",
				"Validade Dos Produtos",
				"Quantidade De Produtos",
				dataset, 
				PlotOrientation.VERTICAL,
				false, 
				true, 
				false);
		    BarRenderer renderer = (BarRenderer) barChart.getCategoryPlot().getRenderer();// Ajuste a largura das barras aqui
		    renderer.setMaximumBarWidth(0.05); 
		return barChart;
	}
	
	
	private CategoryDataset createDataset() {
		
		int pertoVencer = 0;
		int longeVencer = 0;
		int naoPerecivel = 0;
		int vencidos = 0;
		LocalDate hoje = LocalDate.now();//@Feliipee013 variável para ver q dia é hj
    	
		List<Produto> produtos = estoque.getProdutos();
		for (Produto produto : produtos) {
			if(Period.between(hoje, produto.getVencimento()).getDays() > 0 
					&& Period.between(hoje, produto.getVencimento()).getDays() <= 31
	    			&& Period.between(hoje, produto.getVencimento()).getMonths() <= 1
	    			&& Period.between(hoje, produto.getVencimento()).getYears() <= 1)
				pertoVencer++;
			else if(Period.between(hoje, produto.getVencimento()).getMonths() > 1
					&& Period.between(hoje, produto.getVencimento()).getYears() < 199) 
				longeVencer++;
			else if(Period.between(hoje, produto.getVencimento()).getYears() > 200)
				naoPerecivel++;
			else if (Period.between(hoje, produto.getVencimento()).getDays() <= 0)
					vencidos++;
			
		}
		
		
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.setValue(pertoVencer, " ", "Perto de vencer");
		dataset.setValue(longeVencer," " , "Longe de vencer");
		dataset.setValue(naoPerecivel," " , "Não Perecível");
		dataset.setValue(vencidos, " ", "Vencidos");
		return dataset;
	}
}