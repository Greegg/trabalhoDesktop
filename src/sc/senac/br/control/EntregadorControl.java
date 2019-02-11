/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sc.senac.br.control;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import sc.senac.br.modelo.Entregador;
import sc.senac.br.modelo.dao.EntregadorDao;
import sc.senac.br.uteis.Conversor;
import sc.senac.br.uteis.Painel;

/**
 *
 * @author Alunos
 */
public class EntregadorControl {

    private JTextField nome;
    private JTextField cnh;

    private JTable jtEntegador;

    private List<Entregador> listaEntregador;
    private EntregadorDao entreDao;
    private Entregador entregador = null;

    public EntregadorControl() {
    }

    public EntregadorControl(JTextField nome, JTextField cnh, JTable entregadorTable) {
        this.nome = nome;
        this.cnh = cnh;
        this.jtEntegador = entregadorTable;

        listaEntregador = new ArrayList<>();
        entreDao = new EntregadorDao();
    }

    public void cleanField() {
        nome.setText(null);
        nome.requestFocus();
        cnh.setText(null);
        entregador = null;
    }

    public void listarAction() {
        listaEntregador = entreDao.listar();
        showItensTable();
    }

    private void showItensTable() {

        DefaultTableModel model;
        model = (DefaultTableModel) jtEntegador.getModel();
        model.setNumRows(0);
        for (Entregador entregador : listaEntregador) {

            model.addRow(new Object[]{
                entregador.getId(),
                entregador.getNome(),
                entregador.getCnh(),});
        }
    }

    public void salvarAction() {

        if (entregador == null) {
            if (validacaoAction()) {
                Painel.show("Erro de validação");
            } else {
                cadastrarAction();
            }
        } else {
            alterarAction();
        }

        cleanField();

    }

    public boolean validacaoAction() {

        boolean ok = false;

        if (nome.getText().isEmpty()) {
            ok = true;
            Painel.show("Todos os campos são obrigatórios");
        }
        if (cnh.getText().isEmpty()) {
            Painel.show("Data de nascimento é obrigatória");
        }
        return ok;

    }

    public void cadastrarAction() {

        entregador = new Entregador();
        entregador.setNome(nome.getText());
        entregador.setCnh(cnh.getText());

        if (entreDao.salvar(entregador)) {
            Painel.show("Cadastrado com sucesso!");
        } else {
            Painel.show("Erro ao salvar!");
        }
        cleanField();
    }

    public void alterarAction() {

        entregador.setNome(nome.getText());
        entregador.setCnh(cnh.getText());

        if (entreDao.atualizar(entregador)) {
            Painel.show("Editado com sucesso");
        } else {
            Painel.show("Erro ao editar");
        }
        cleanField();
    }

    public void excluirAction() {

        entregador = getItemSelecionado();
        if (entregador == null) {
            Painel.show("Escolha um entregador");
        } else {
            boolean res = entreDao.excluir(entregador);
            if (res) {
                Painel.show("Entregador excluido");
                listarAction();
            } else {
                Painel.show("Erro ao tentar excluir");
            }
        }
        entregador = null;
    }

    public Entregador getItemSelecionado() {

        int linha = jtEntegador.getSelectedRow();

        if (linha >= 0) {
            entregador = listaEntregador.get(linha);
            return entregador;
        } else {
            return null;
        }
    }

    public void popularFormAction() {
        entregador = getItemSelecionado();

        if (entregador != null) {

            nome.setText(entregador.getNome());
            cnh.setText(entregador.getCnh());
        } else {
            Painel.show("Selecione um cliente");
        }
    }
}
