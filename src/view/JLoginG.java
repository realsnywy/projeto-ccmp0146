package view;

import java.awt.EventQueue;

public class JLoginG extends JLoginBase {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JLoginG() {
        super("Gerência", JGerencia.class);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                JLoginG frame = new JLoginG();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
