package view;

import java.awt.EventQueue;

public class JLoginE extends JLoginBase {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JLoginE() {
        super("Estoque", JPaginaEstoque.class);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                JLoginE frame = new JLoginE();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
