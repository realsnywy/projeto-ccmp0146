package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.erp.Criptografia;
import com.erp.Main;
import com.erp.Usuario;
import com.erp.UsuarioDAO;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JLoginE extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textFieldUsuario;
    private JPasswordField passwordField;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    JLoginE frame = new JLoginE();
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
    public JLoginE() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 630, 520);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JPanel panel = new JPanel();
        panel.setBackground(new Color(70, 115, 121));
        panel.setBounds(73, 21, 480, 443);
        contentPane.add(panel);
        panel.setLayout(null);
        
        textFieldUsuario = new JTextField();
        textFieldUsuario.setBounds(100, 168, 291, 27);
        panel.add(textFieldUsuario);
        textFieldUsuario.setColumns(10);
        
        JLabel lblNewLabel_1 = new JLabel("LOGIN");
        lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setBounds(206, 114, 79, 19);
        panel.add(lblNewLabel_1);
        
        JLabel lblNewLabel_2 = new JLabel("USUÁRIO :");
        lblNewLabel_2.setFont(new Font("Verdana", Font.BOLD, 12));
        lblNewLabel_2.setBounds(100, 139, 79, 19);
        panel.add(lblNewLabel_2);
        
        JLabel lblNewLabel_3 = new JLabel("SENHA :");
        lblNewLabel_3.setFont(new Font("Verdana", Font.BOLD, 12));
        lblNewLabel_3.setBounds(100, 219, 63, 13);
        panel.add(lblNewLabel_3);
        
        JButton btnNewButton = new JButton("ENTRAR");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nome = textFieldUsuario.getText();
                String senha = new String(passwordField.getPassword());
                
                UsuarioDAO usuarioDAO = new UsuarioDAO();
                Usuario usuario = usuarioDAO.buscarUsuario(nome, senha);
                
                if (usuario != null) {
                    JOptionPane.showMessageDialog(btnNewButton, "Login bem-sucedido");
                    dispose();
                    JPaginaEstoque jPaginaEstoque = new JPaginaEstoque();
                    jPaginaEstoque.setLocationRelativeTo(null);
                    jPaginaEstoque.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(btnNewButton, "Usuário ou senha inválidos", "AVISO", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        btnNewButton.setBackground(new Color(68, 204, 215));
        btnNewButton.setForeground(new Color(0, 0, 0));
        btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
        btnNewButton.setBounds(188, 328, 115, 35);
        panel.add(btnNewButton);
        
        passwordField = new JPasswordField();
        passwordField.setBounds(100, 254, 291, 27);
        panel.add(passwordField);
        
        JButton btnNewButton_1 = new JButton("VOLTAR");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                Main jPrincipal = new Main();
                jPrincipal.setLocationRelativeTo(jPrincipal);
                jPrincipal.setVisible(true);
            }
        });
        btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 10));
        btnNewButton_1.setBounds(10, 10, 85, 21);
        panel.add(btnNewButton_1);
        
        JLabel lblNewLabel_2_1 = new JLabel("StoSALE");
        lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 32));
        lblNewLabel_2_1.setBounds(171, 49, 148, 31);
        panel.add(lblNewLabel_2_1);
        
        JLabel lblEstoque = new JLabel("ESTOQUE");
        lblEstoque.setHorizontalAlignment(SwingConstants.CENTER);
        lblEstoque.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lblEstoque.setBounds(178, 79, 135, 25);
        panel.add(lblEstoque);
    }
}
