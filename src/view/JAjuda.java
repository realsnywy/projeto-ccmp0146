package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;

import javax.swing.JTree;
import javax.swing.JTextArea;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.Font;
import java.awt.Desktop;
import java.net.URI;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class JAjuda extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextArea textArea;
    private Timer timerInatividade;
    private int tempoInatividadeSegundos = 30;

    /**
     * Launch the application.
     */
    ImageIcon logo = new ImageIcon(getClass().getClassLoader().getResource("logo.png"));
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    JAjuda frame = new JAjuda();
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
    public JAjuda() {
    	setIconImage(logo.getImage());
		setTitle("StoSale");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 630, 520);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
     // Adiciona Listeners de atividade
        addMouseMotionListener(new AtividadeListener());
        addKeyListener(new AtividadeListener());
        iniciarTimerInatividade();

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(70, 115, 121));
        panel.setBounds(72, 10, 494, 463);
        contentPane.add(panel);

        JLabel lblNewLabel_2_1 = new JLabel("StoSALE");
        lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 32));
        lblNewLabel_2_1.setBounds(174, 10, 148, 31);
        panel.add(lblNewLabel_2_1);

        JLabel lblAjuda = new JLabel("AJUDA");
        lblAjuda.setHorizontalAlignment(SwingConstants.CENTER);
        lblAjuda.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lblAjuda.setBounds(181, 40, 135, 25);
        panel.add(lblAjuda);

        JButton btnAjuda = new JButton("VIDEOS");
        btnAjuda.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnAjuda.setBounds(324, 394, 160, 25);
        panel.add(btnAjuda);

        JButton btnVoltar = new JButton("VOLTAR");
        btnVoltar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dispose();
				JGerencia jGerencia = new JGerencia();
				jGerencia.setLocationRelativeTo(jGerencia);
				jGerencia.setVisible(true);
        	}
        });
        btnVoltar.setFont(new Font("Tahoma", Font.BOLD, 10));
        btnVoltar.setBounds(10, 10, 85, 21);
        panel.add(btnVoltar);

        // Criar o modelo da árvore com dicas
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Ajuda");

        DefaultMutableTreeNode estoqueNode = new DefaultMutableTreeNode("Estoque");
        DefaultMutableTreeNode adicionarProdutoNode = new DefaultMutableTreeNode("Adicionar produto");
        estoqueNode.add(adicionarProdutoNode);
        estoqueNode.add(new DefaultMutableTreeNode("Remover produto"));

        DefaultMutableTreeNode vendaNode = new DefaultMutableTreeNode("Venda");
        vendaNode.add(new DefaultMutableTreeNode("Adicionar ao carrinho"));
        vendaNode.add(new DefaultMutableTreeNode("Finalizar venda"));
        //vendaNode.add(new DefaultMutableTreeNode("Cancelar venda"));

        DefaultMutableTreeNode gerenciaNode = new DefaultMutableTreeNode("Gerência");
        gerenciaNode.add(new DefaultMutableTreeNode("Gerenciar usuários"));
        //gerenciaNode.add(new DefaultMutableTreeNode("Relatórios"));
        //gerenciaNode.add(new DefaultMutableTreeNode("Configurações"));

        root.add(estoqueNode);
        root.add(vendaNode);
        root.add(gerenciaNode);

        JTree tree = new JTree(root);
        tree.setBounds(10, 91, 170, 293);
        panel.add(tree);

        // Adicionar JTextArea para exibir as dicas
        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setBounds(190, 91, 294, 293);
        panel.add(textArea);
        
        JTextArea txtrParaMaisInformaes = new JTextArea();
        txtrParaMaisInformaes.setFont(new Font("Monospaced", Font.PLAIN, 12));
        txtrParaMaisInformaes.setText("Para mais informações contate o SAC ou \r\nclique no botão \"VIDEOS\" ao lado para ser\r\nredirecionado para videos informativos.");
        txtrParaMaisInformaes.setBounds(10, 394, 306, 59);
        panel.add(txtrParaMaisInformaes);
        
        JButton btnChat = new JButton("CHAT");
        btnChat.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI("https://discord.gg/kBMt6KShp7\r\n"));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
        	}
        });
        btnChat.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnChat.setBounds(324, 428, 160, 25);
        panel.add(btnChat);

        // Adicionar TreeSelectionListener para detectar seleção de nós
        tree.addTreeSelectionListener(new TreeSelectionListener() {
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                if (selectedNode == null) return;

                String selectedNodeName = selectedNode.toString();
                switch (selectedNodeName) {
                    case "Adicionar produto":
                        textArea.setText("Para adicionar um produto, vá até o menu de estoque \n"
                        		       + "e clique em 'Adicionar produto'. Preencha as informações \n"
                        		       + "Nome, Id e Preço do Produto e clique em 'Adicionar'.\n"
                        		       + "As informações do produto serão listadas \n"
                        		       + "ao lado e uma mensagem confirmará a adição.");
                        break;
                    case "Remover produto":
                        textArea.setText("Para remover um produto, informe o Id do produto \n"
                        		       + "que deseja remover na lista de estoque exibida ao lado.\n"
                        		       + "Clique em 'Remover'.");
                        break;
                    /*case "Atualizar quantidade":
                        textArea.setText("Para atualizar a quantidade de um produto, \n"
                                       + "selecione o produto na lista, clique em \n"
                                       + "'Atualizar quantidade', insira a nova \n"
                                       + "quantidade e confirme.");
                        break; */ //Sem opção de atualizar quantidades
                    case "Adicionar ao carrinho":
                        textArea.setText("Para adicionar um item ao carrinho, \n"
                                       + "digite o id do produto e clique em \n"
                                       + "'Adicionar ao carrinho'. O produto \n"
                                       + "será listado nos títulos em aberto.");
                        break;
                    case "Finalizar venda":
                        textArea.setText("Para finalizar a venda, confira os itens \n"
                                       + "no carrinho e clique em 'PAGAR'.\n"
                                       );
                        break;
                    /*case "Cancelar venda":
                        textArea.setText("Para cancelar uma venda em andamento, \n"
                                       + "clique em 'Cancelar venda'. Todos os \n"
                                       + "itens do carrinho serão removidos.");
                        break; */ // Ainda n tem a opção de cancelar
                    case "Gerenciar usuários":
                        textArea.setText("Para gerenciar usuários, acesse a área de \n"
                                       + "gerência e depois em 'CADASTRAR USUARIO'. Adicione, remova ou edite \n"
                                       + "informações dos usuários.");
                        break;
                    /*case "Relatórios":
                        textArea.setText("Acesse relatórios para visualizar dados \n"
                                       + "de vendas, estoque e desempenho. \n"
                                       + "Selecione o período desejado e gere \n"
                                       + "o relatório.");
                        break;
                    case "Configurações":
                        textArea.setText("No menu de configurações, ajuste as \n"
                                       + "preferências do sistema e personalize \n"
                                       + "o aplicativo conforme necessário.");
                        break;*/ //Ainda n tem opções de configurações e Relatórios
                    default:
                        textArea.setText("");
                }
            }
        });

        // Configurar o botão "AJUDA" para abrir uma página no YouTube
        btnAjuda.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI("www.youtube.com/watch?v=aV_YB42Ae1M"));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
 // Inicia o timer para detectar a inatividade
    private void iniciarTimerInatividade() {
        timerInatividade = new Timer();
        timerInatividade.schedule(new TimerTask() {
            @Override
            public void run() {
                entrarModoSuspensao();
            }
        }, tempoInatividadeSegundos * 1000); // Converte segundos para milissegundos
    }

    // Reinicia o timer de inatividade sempre que o usuário estiver ativo
    private void reiniciarTimerInatividade() {
        timerInatividade.cancel();
        iniciarTimerInatividade();
    }

    // Classe interna para detectar atividade
    private class AtividadeListener extends MouseAdapter implements KeyListener {
        @Override
        public void mouseMoved(MouseEvent e) {
            reiniciarTimerInatividade();
        }

        @Override
        public void keyPressed(KeyEvent e) {
            reiniciarTimerInatividade();
        }

        @Override
        public void keyReleased(KeyEvent e) {}

        @Override
        public void keyTyped(KeyEvent e) {}
    }
    private void entrarModoSuspensao() {
        SwingUtilities.invokeLater(() -> {
            // Exibe a tela de modo suspenso
            JModoSuspenso modoSuspenso = new JModoSuspenso(this); // Passa a referência da janela principal
            modoSuspenso.entrarModoSuspensao();
        });
    }
}
