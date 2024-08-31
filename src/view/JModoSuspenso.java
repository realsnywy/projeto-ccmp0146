package view;

import javax.swing.*;

import java.awt.Color;
import java.awt.event.*;

public class JModoSuspenso extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    private JFrame janelaPrincipal; // Referência para a janela principal

    public JModoSuspenso(JFrame janelaPrincipal) {
        this.janelaPrincipal = janelaPrincipal; // Armazena a referência para a janela principal

        setTitle("Programa em Modo de Suspensão");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Fecha esta janela em vez de sair do programa
        setLocationRelativeTo(null);

        // Adiciona um Listener de atividade para sair do modo de suspensão
        addMouseMotionListener(new AtividadeListener());
        addKeyListener(new AtividadeListener());
    }

    // Entra em modo de suspensão
    public void entrarModoSuspensao() {
        SwingUtilities.invokeLater(() -> {
            // Exemplo: mostra uma mensagem e desabilita a interface
            JOptionPane.showMessageDialog(this, "Programa entrou em modo de suspensão.");
            setVisible(true);
            if (janelaPrincipal != null) {
                janelaPrincipal.setEnabled(false); // Desabilita a janela principal
                janelaPrincipal.getContentPane().setBackground(Color.DARK_GRAY);
            }
        });
    }

    // Sai do modo de suspensão e reativa a janela principal
    private void sairModoSuspensao() {
        if (janelaPrincipal != null) {
            janelaPrincipal.setEnabled(true); // Reativa a janela principal
            janelaPrincipal.toFront(); // Traz a janela principal para a frente
            janelaPrincipal.getContentPane().setBackground(null);
        }
        dispose(); // Fecha a janela de modo suspenso
    }

    // Listener de Atividade
    private class AtividadeListener extends MouseAdapter implements KeyListener {
        @Override
        public void mouseMoved(MouseEvent e) {
            sairModoSuspensao();
        }

        @Override
        public void keyPressed(KeyEvent e) {
            sairModoSuspensao();
        }

        @Override
        public void keyReleased(KeyEvent e) {}

        @Override
        public void keyTyped(KeyEvent e) {}
    }

}