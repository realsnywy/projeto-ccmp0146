package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.erp.Main;
import com.erp.UsuarioDAO;
import com.erp.UsuarioDAO.UsuarioLogadoReceiver;

import model.Criptografia;
import model.Usuario;

public abstract class JLoginBase extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textFieldUsuario;
    private JPasswordField passwordField;
    private String setor;
    private Class<?> telaPrincipal;

    public JLoginBase(String setor, Class<?> telaPrincipal) {//pega o nome do setor e a classe dele para configurar a tela 
        this.setor = setor;
        this.telaPrincipal = telaPrincipal;
        initialize();
    }

    private void initialize() {
        ImageIcon logo = new ImageIcon(getClass().getClassLoader().getResource("logo.png"));//coloca o logo no icone da pagina e nome dela
        setIconImage(logo.getImage());
        setTitle("StoSale");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 630, 520);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
      //passando o frame para o modo suspensão para verificar atividade
        JModoSuspenso.addActivityListener(this);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(70, 115, 121));
        panel.setBounds(73, 21, 480, 443);
        contentPane.add(panel);
        panel.setLayout(null);

        textFieldUsuario = new JTextField();
        textFieldUsuario.setBounds(100, 168, 291, 27);
        panel.add(textFieldUsuario);
        textFieldUsuario.setColumns(10);

        JLabel lblLogin = new JLabel("LOGIN");
        lblLogin.setFont(new Font("Times New Roman", Font.BOLD, 14));
        lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
        lblLogin.setBounds(206, 114, 79, 19);
        panel.add(lblLogin);

        JLabel lblUsuario = new JLabel("USUÁRIO :");
        lblUsuario.setFont(new Font("Verdana", Font.BOLD, 12));
        lblUsuario.setBounds(100, 139, 79, 19);
        panel.add(lblUsuario);

        JLabel lblSenha = new JLabel("SENHA :");
        lblSenha.setFont(new Font("Verdana", Font.BOLD, 12));
        lblSenha.setBounds(100, 219, 63, 13);
        panel.add(lblSenha);

        JButton btnEntrar = new JButton("ENTRAR");
        btnEntrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nome = textFieldUsuario.getText();
                String senha = new String(passwordField.getPassword());
                String senhaCriptografada = Criptografia.criptografar(senha);

                UsuarioDAO usuarioDAO = new UsuarioDAO();
                Usuario usuario = usuarioDAO.buscarUsuario(nome, senhaCriptografada);

                if (usuario != null) {
                    // Permitir acesso se o setor do usuário corresponder ou se for da gerência
                    if (setor.equals(usuario.getSetor()) || usuario.getSetor().equals("Gerência")) {
                        JOptionPane.showMessageDialog(btnEntrar, "Login bem-sucedido");
                        dispose();
                        try {
                            JFrame tela = (JFrame) telaPrincipal.getDeclaredConstructor().newInstance();//instanciando o novo frame com base na telaprincipal passada
                            if (tela instanceof UsuarioLogadoReceiver) {
                                ((UsuarioLogadoReceiver) tela).setUsuarioLogado(usuario);//if para passar o usuario logado
                            }
                            tela.setLocationRelativeTo(tela);
                            tela.setVisible(true);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    } else {
                        JOptionPane.showMessageDialog(btnEntrar, "Acesso negado. Usuário não autorizado para este setor.", "AVISO", JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(btnEntrar, "Usuário ou senha inválidos", "AVISO", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        btnEntrar.setBackground(new Color(68, 204, 215));
        btnEntrar.setForeground(new Color(0, 0, 0));
        btnEntrar.setFont(new Font("Times New Roman", Font.BOLD, 14));
        btnEntrar.setBounds(188, 328, 115, 35);
        panel.add(btnEntrar);

        passwordField = new JPasswordField();
        passwordField.setBounds(100, 254, 291, 27);
        panel.add(passwordField);

        JButton btnVoltar = new JButton("VOLTAR");
        btnVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                Main jPrincipal = new Main();
                jPrincipal.setLocationRelativeTo(jPrincipal);
                jPrincipal.setVisible(true);
            }
        });
        btnVoltar.setFont(new Font("Tahoma", Font.BOLD, 10));
        btnVoltar.setBounds(10, 10, 85, 21);
        panel.add(btnVoltar);

        JLabel lblTitulo = new JLabel("StoSALE");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 32));
        lblTitulo.setBounds(171, 44, 148, 31);
        panel.add(lblTitulo);

        JLabel lblSetor = new JLabel(setor.toUpperCase());
        lblSetor.setHorizontalAlignment(SwingConstants.CENTER);
        lblSetor.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lblSetor.setBounds(178, 74, 135, 25);
        panel.add(lblSetor);
    }
}
