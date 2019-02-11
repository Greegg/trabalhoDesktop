/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sc.senac.br.control;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import sc.senac.br.modelo.Categoria;
import sc.senac.br.modelo.dao.CategoriaDao;

/**
 *
 * @author hvb
 */
public class CategoriaControl {

    private JTextField nome;
    private JTextField pesquisarCat;
    private JTable jtCat;

    private CategoriaDao catDao;
    private List<Categoria> listCat;
    private Categoria categoria = null;

    public CategoriaControl() {
    }

    public CategoriaControl(JTextField nome, JTable jtCat, JTextField pesquisarCat) {
        this.nome = nome;
        this.pesquisarCat = pesquisarCat;
        this.jtCat = jtCat;

        catDao = new CategoriaDao();
        listCat = new ArrayList<>();
    }

    public void listarAction() {
        listCat = catDao.listar();
        DefaultTableModel model = (DefaultTableModel) jtCat.getModel();
        model.setNumRows(0);
        for (Categoria c : listCat) {
            model.addRow(new Object[]{
                c.getId(),
                c.getNome()
            });
        }
    }

    public void salvarAction() {
        if (categoria == null) {
            cadastrarAction();
        } else {
            alterarAction();
        }

        clearAction();
    }

    public void clearAction() {
        listarAction();
        nome.setText("");
        nome.requestFocus();
        categoria = null;
    }

    public void cadastrarAction() {
        categoria = new Categoria();
        categoria.setNome(nome.getText());

        boolean res = catDao.salvar(categoria);
        if (res == true) {
            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso");
            listarAction();
        } else {
            JOptionPane.showMessageDialog(null, "Erro ao tentar cadastrar");
        }
    }

    public void alterarAction() {
        categoria.setNome(nome.getText());
        boolean res = catDao.atualizar(categoria);
        if (res) {
            JOptionPane.showMessageDialog(null, "Editado com sucesso");
            listarAction();
        } else {
            JOptionPane.showMessageDialog(null, "Erro ao editar");
        }

    }

    public void pesquisarAction() {
        listCat = catDao.pesquisarPorNome(pesquisarCat.getText());

        DefaultTableModel model = (DefaultTableModel) jtCat.getModel();
        model.setNumRows(0);
        for (Categoria c : listCat) {
            model.addRow(new Object[]{
                c.getId(),
                c.getNome()
            });
        }
    }

    public void excluirAction() {
        categoria = getItemSelecionado();
        if (categoria == null) {
            JOptionPane.showMessageDialog(null, "Escolha uma categoria");
        } else {
            boolean res = catDao.excluir(categoria);
            if (res) {
                JOptionPane.showMessageDialog(null, "Categoria excluida");
                listarAction();
            } else {
                JOptionPane.showMessageDialog(null, " Erro ao excluir");
            }
        }

        categoria = null;
    }

    private Categoria getItemSelecionado() {
        int linha = jtCat.getSelectedRow();
        if (linha >= 0) {
            return listCat.get(linha);

        } else {
            return null;
        }
    }

    public void popularFormAction() {
        categoria = getItemSelecionado();
        if (categoria != null) {
            nome.setText(categoria.getNome());
        } else {
            JOptionPane.showMessageDialog(null, "Escolha uma categoria");
        }
        nome.setText(categoria.getNome());
    }

}
