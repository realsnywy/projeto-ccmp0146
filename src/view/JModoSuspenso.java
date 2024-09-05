package view;

import javax.swing.*;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.*;

public class JModoSuspenso extends JFrame {

    private static final long serialVersionUID = 1L;
    private static Timer timerInatividade; // Timer compartilhado para monitorar inatividade
    private static final int TEMPO_INATIVIDADE = 600000; // Tempo de inatividade em milissegundos


    // Adiciona o listener de atividade para componentes ou janelas
    public static void addActivityListener(Component componente) {
    	iniciarTimer(componente);
        AtividadeListener listener = new AtividadeListener();
        componente.addMouseMotionListener(listener);
        componente.addKeyListener(listener);
        
        // Adicione listeners para os componentes
        if (componente instanceof Container) {
            for (Component child : ((Container) componente).getComponents()) {
                addActivityListener(child);
            }
        }
    }


    // Inicia ou reinicia o timer de inatividade
    private static void iniciarTimer(Component componente) {
        if (timerInatividade == null) {
            timerInatividade = new Timer(TEMPO_INATIVIDADE, e -> entrarModoSuspensao(componente));
        }
        timerInatividade.setRepeats(false); // Executa apenas uma vez após o intervalo
        reiniciarTimer();
    }

    // Reinicia o timer de inatividade
    public static void reiniciarTimer() {
        if (timerInatividade != null) {
            timerInatividade.restart();
        }
    }

    // Entra em modo de suspensão
    public static void entrarModoSuspensao(Component componente) {
        SwingUtilities.invokeLater(() -> {
            if (componente != null) {
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(componente);
                if (frame != null) {
                    frame.setEnabled(false); // Desabilita a janela principal
                }

                // Mostra o JOptionPane e espera o usuário clicar "OK"
                JOptionPane.showMessageDialog(componente,
                    "Programa entrou em modo de suspensão. Clique OK para sair.",
                    "Modo de Suspensão",
                    JOptionPane.INFORMATION_MESSAGE
                );

                // Após clicar "OK", reativa a janela principal e reinicia o timer
                if (frame != null) {
                    frame.setEnabled(true); // Reativa a janela principal
                    frame.toFront(); // Traz a janela principal para a frente
                }
                reiniciarTimer(); // Reinicia o timer ao sair do modo de suspensão
            }
        });
    }

    // Listener de Atividade
    private static class AtividadeListener extends MouseAdapter implements KeyListener {
        @Override
        public void mouseMoved(MouseEvent e) {
            reiniciarTimer();
        }

        @Override
        public void keyPressed(KeyEvent e) {
            reiniciarTimer();
        }

        @Override
        public void keyReleased(KeyEvent e) {}

        @Override
        public void keyTyped(KeyEvent e) {}
    }
}
