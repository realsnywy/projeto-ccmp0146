package view;

import java.awt.EventQueue;

public class JLoginV extends JLoginBase {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JLoginV() {
        super("Vendas", JPaginaVendas.class);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                JLoginV frame = new JLoginV();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
