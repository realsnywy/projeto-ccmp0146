package com.erp;

import java.io.*;
import java.util.*;

import model.Criptografia;
import model.Usuario;

public class UsuarioDAO {
    private Map<String, Usuario> usuarios = new HashMap<>();
    private static final String ARQUIVO_USUARIOS = "usuarios.txt";

    public UsuarioDAO() {
        carregarUsuarios();
    }

    public Usuario buscarUsuario(String nome, String senhaCriptografada) {
        Usuario usuario = buscarUsuarioPorNome(nome);
        if (usuario != null && usuario.getSenha().equals(senhaCriptografada)) {
            return usuario;
        }
        return null;
    }

    public void adicionarUsuario(Usuario usuario) {
        usuarios.put(usuario.getNome(), usuario);
        salvarUsuarios();
    }

    public void removerUsuario(String nome) {
        usuarios.remove(nome);
        salvarUsuarios();
    }

    public void atualizarSenha(String nome, String novaSenha) {
        Usuario usuario = usuarios.get(nome);
        if (usuario != null) {
            usuario.setSenha(Criptografia.criptografar(novaSenha));
            salvarUsuarios();
        }
    }

    public Collection<Usuario> getUsuarios() {
        return usuarios.values();
    }

    private void salvarUsuarios() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_USUARIOS))) {
            for (Usuario usuario : usuarios.values()) {
                writer.write(usuario.getNome() + ";" + usuario.getSenha() + ";" + usuario.getNomeColaborador() + ";" + usuario.getSetor() + ";" + usuario.getVendas());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void carregarUsuarios() {
        File arquivo = new File(ARQUIVO_USUARIOS);
        if (!arquivo.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_USUARIOS))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length == 5) {
                    String nome = partes[0];
                    String senha = partes[1];
                    String nomeColaborador = partes[2];
                    String setor = partes[3];
                    double vendas = Double.parseDouble(partes[4]);
                    
                    Usuario usuario = new Usuario(nome, senha, nomeColaborador, setor, vendas);
                    usuarios.put(nome, usuario);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public Usuario buscarUsuarioPorNome(String nome) {
        Usuario usuario = usuarios.get(nome);
        if (usuario != null) {
            return usuario;
        }
        return null;
    }
    
    public void atualizarVendas(String nome, double vendas) {
        Usuario usuario = buscarUsuarioPorNome(nome);
        if (usuario != null) {
            usuario.setVendas(vendas);
            salvarUsuarios();
        }
    }
    
    public interface UsuarioLogadoReceiver {
        void setUsuarioLogado(Usuario usuario);
    }

}
