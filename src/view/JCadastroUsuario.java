package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.erp.UsuarioDAO;

import model.Criptografia;
import model.Usuario;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class JCadastroUsuario extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textFieldNomeDoColaborador;
    private JTable table;
    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    private JTextField textFieldSenha;
    private JComboBox<String> comboBoxSetor;
    private JTable tabelaData;
    
    ImageIcon logo = new ImageIcon(getClass().getClassLoader().getResource("logo.png"));
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    JCadastroUsuario frame = new JCadastroUsuario();
                    frame.setVisible(true);
                    frame.setLocationRelativeTo(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public JCadastroUsuario() throws IOException {
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 913, 764);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        setIconImage(logo.getImage());
      //passando o frame para o modo suspensão para verificar atividade
        JModoSuspenso.addActivityListener(this);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(64, 128, 128));
        panel.setLayout(null);
        panel.setBounds(10, 0, 876, 723);
        contentPane.add(panel);

        textFieldNomeDoColaborador = new JTextField();
        textFieldNomeDoColaborador.setColumns(10);
        textFieldNomeDoColaborador.setBounds(41, 124, 217, 31);
        panel.add(textFieldNomeDoColaborador);

        textFieldSenha = new JTextField();
        textFieldSenha.setColumns(10);
        textFieldSenha.setBounds(268, 124, 217, 31);
        panel.add(textFieldSenha);

        JButton btnAdicionarUsuario = new JButton("ADICIONAR USUÁRIO");
        btnAdicionarUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
					adicionarUsuario();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });
        btnAdicionarUsuario.setBounds(695, 124, 163, 31);
        panel.add(btnAdicionarUsuario);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 165, 838, 435);
        panel.add(scrollPane);

        table = new JTable();
        table.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] {
                "Nome do Colaborador", "Nome de Usuário", "Setor"
            }
        ));
        scrollPane.setViewportView(table);

        atualizarTabela();

        JButton btnRemoverUsuario = new JButton("REMOVER USUÁRIO");
        btnRemoverUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
					removerUsuario();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });
        btnRemoverUsuario.setBounds(230, 624, 163, 31);
        panel.add(btnRemoverUsuario);

        JButton btnRedefinirSenha = new JButton("REDEFINIR SENHA");
        btnRedefinirSenha.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
					redefinirSenha();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });
        btnRedefinirSenha.setBounds(440, 624, 163, 31);
        panel.add(btnRedefinirSenha);

        JLabel lblNewLabel_2_1 = new JLabel("StoSALE");
        lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 32));
        lblNewLabel_2_1.setBounds(343, 22, 148, 31);
        panel.add(lblNewLabel_2_1);

        JLabel lblGerencia = new JLabel("GERÊNCIA");
        lblGerencia.setHorizontalAlignment(SwingConstants.CENTER);
        lblGerencia.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lblGerencia.setBounds(350, 52, 135, 25);
        panel.add(lblGerencia);

        JLabel lblNomeDoColaborador = new JLabel("NOME DO COLABORADOR:");
        lblNomeDoColaborador.setHorizontalAlignment(SwingConstants.CENTER);
        lblNomeDoColaborador.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblNomeDoColaborador.setBounds(20, 99, 248, 25);
        panel.add(lblNomeDoColaborador);

        JLabel lblSenhaDoColaborador = new JLabel("SENHA DO COLABORADOR:");
        lblSenhaDoColaborador.setHorizontalAlignment(SwingConstants.CENTER);
        lblSenhaDoColaborador.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblSenhaDoColaborador.setBounds(248, 99, 248, 25);
        panel.add(lblSenhaDoColaborador);
        
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
        btnVoltar.setBounds(20, 10, 85, 21);
        panel.add(btnVoltar);

        comboBoxSetor = new JComboBox<>(new String[] {"Vendas", "Estoque", "Gerência"});
        comboBoxSetor.setBounds(495, 124, 190, 31);
        panel.add(comboBoxSetor);

        JLabel lblSetor = new JLabel("SETOR:");
        lblSetor.setHorizontalAlignment(SwingConstants.CENTER);
        lblSetor.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblSetor.setBounds(495, 99, 190, 25);
        panel.add(lblSetor);
        
        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(768, 7, 90, 41);
        panel.add(scrollPane_1);
        
        String[] colunas = {"DATA"};
        DefaultTableModel modelo = new DefaultTableModel(colunas, 0);
        modelo.addRow(new Object[]{""});
        tabelaData = new JTable(modelo);
        scrollPane_1.setViewportView(tabelaData);
     // Timer para atualizar a data e hora a cada segundo (1000 milissegundos)
        Timer timer = new Timer(1000, e -> {
            // Obter a data
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String dataAtual = sdf.format(new Date());

            // Atualizar o valor da célula na tabela
            modelo.setValueAt(dataAtual, 0, 0);
        });
        timer.start();//timer para atualiaz a data a cada 1 seg
    }

    private String gerarNomeUsuarioUnico(String nomeColaborador) {
        String base = nomeColaborador.replaceAll("\\s+", "").toLowerCase();
        base = base.length() > 5 ? base.substring(0, 5) : base;
        String nomeUsuario;
        boolean existe;
        int contador = 1;

        do {
            nomeUsuario = base + contador;
            if (nomeUsuario.length() > 8) {
                nomeUsuario = nomeUsuario.substring(0, 8);
            }
            final String nomeUsuarioVerificar = nomeUsuario;
            existe = usuarioDAO.getUsuarios().stream().anyMatch(u -> u.getNome().equals(nomeUsuarioVerificar));
            contador++;
        } while (existe);

        return nomeUsuario;
    }

    private void adicionarUsuario() throws IOException {
        String nomeColaborador = textFieldNomeDoColaborador.getText().trim();
        String senha = textFieldSenha.getText().trim();
        if (nomeColaborador.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nome e senha não podem estar vazios.");
            return;
        }
        String nomeUsuario = gerarNomeUsuarioUnico(nomeColaborador);
        String setor = (String) comboBoxSetor.getSelectedItem();
        Usuario usuario = new Usuario(nomeUsuario, Criptografia.criptografar(senha), nomeColaborador, setor);

        usuarioDAO.adicionarUsuario(usuario);
        textFieldNomeDoColaborador.setText("");
        textFieldSenha.setText("");
        atualizarTabela();
    }

    private void removerUsuario() throws IOException {
        int linhaSelecionada = table.getSelectedRow();
        if (linhaSelecionada != -1) {
            String nomeUsuario = (String) table.getValueAt(linhaSelecionada, 1);
            usuarioDAO.removerUsuario(nomeUsuario);
            atualizarTabela();
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um usuário para remover.");
        }
    }

    private void redefinirSenha() throws IOException {
        int linhaSelecionada = table.getSelectedRow();
        if (linhaSelecionada != -1) {
            String nomeUsuario = (String) table.getValueAt(linhaSelecionada, 1);
            String novaSenha = JOptionPane.showInputDialog(this, "Digite a nova senha:");
            if (novaSenha != null && !novaSenha.trim().isEmpty()) {
                usuarioDAO.atualizarSenha(nomeUsuario, novaSenha);
                atualizarTabela();
                JOptionPane.showMessageDialog(this, "A senha foi redefinida.");
            } else {
                JOptionPane.showMessageDialog(this, "A senha não pode ser vazia.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um usuário para redefinir a senha.");
        }
    }

    private void atualizarTabela() throws IOException {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        for (Usuario usuario : usuarioDAO.getUsuarios()) {
            model.addRow(new Object[]{usuario.getNomeColaborador(), usuario.getNome(), usuario.getSetor()});
        }
    }

}
