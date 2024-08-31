package com.erp;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import view.JLoginE;
import view.JLoginG;
import view.JLoginV;
import view.JModoSuspenso;

public class Main extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private static final String CONFIG_FILE = "config.properties";
    private static final String TIMESTAMP_KEY = "timestamp";
    private static final long AVISO_INTERVALO = 1000 * 60 * 60 * 24;
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
                    Main frame = new Main();
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
    public Main() {
        if (isAvisoNecessario()) {
            // Mostra o aviso
            JOptionPane.showMessageDialog(null, "No momento, vamos verificar se há atualizações. Aguarde um pouco!",
                    "Atualizador",
                    JOptionPane.INFORMATION_MESSAGE);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            JOptionPane.showMessageDialog(null, "Não há atualizações no momento!", "Atualizador",
                    JOptionPane.INFORMATION_MESSAGE);
            // Atualiza o timestamp no arquivo de configuração
            atualizarTimestamp();
        }
        
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

    private boolean isAvisoNecessario() {
        File file = new File(CONFIG_FILE);
        if (!file.exists()) {
            return true; // Arquivo não existe, exibe o aviso
        }
        try (InputStream input = new FileInputStream(file)) {
            Properties properties = new Properties();
            properties.load(input);
            String timestampStr = properties.getProperty(TIMESTAMP_KEY);
            if (timestampStr != null) {
                long lastTimestamp = Long.parseLong(timestampStr);
                long currentTimestamp = System.currentTimeMillis();
                return (currentTimestamp - lastTimestamp) > AVISO_INTERVALO;
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return true;
        }
    }

    private void atualizarTimestamp() {
        Properties properties = new Properties();
        properties.setProperty(TIMESTAMP_KEY, Long.toString(System.currentTimeMillis()));
        try (OutputStream output = new FileOutputStream(CONFIG_FILE)) {
            properties.store(output, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
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

