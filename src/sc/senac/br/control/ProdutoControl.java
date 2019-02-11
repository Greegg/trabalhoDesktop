/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sc.senac.br.control;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import sc.senac.br.modelo.Categoria;
import sc.senac.br.modelo.Produto;
import sc.senac.br.modelo.dao.CategoriaDao;
import sc.senac.br.modelo.dao.ProdutoDao;
import sc.senac.br.uteis.Conversor;
import sc.senac.br.uteis.Painel;

/**
 *
 * @author hvb
 */
public class ProdutoControl {
    
    private JTextField nome;
    private JFormattedTextField valor;
    private JTextField descricao;
    private JTextField tfPesquisa;
    private JComboBox cbCategoria;
    private JTable tbProdutos;
    
    private Produto produto = null;
    private List<Produto> lista;
    private ProdutoDao produtoDao;
    private CategoriaDao categoriaDao;
    
    public ProdutoControl() {
    }
    
    public ProdutoControl(JTextField nome, JFormattedTextField valor, JTextField descricao, JTextField tfPesquisa, JComboBox cbCategoria, JTable tbmProdutos) {
        this.nome = nome;
        this.valor = valor;
        this.descricao = descricao;
        this.tfPesquisa = tfPesquisa;
        this.cbCategoria = cbCategoria;
        this.tbProdutos = tbmProdutos;
        
        lista = new ArrayList<>();
        produtoDao = new ProdutoDao();
        categoriaDao = new CategoriaDao();
    }
    
    public void listarAction() {
        lista = produtoDao.listar();
        showItensTable();
    }
    
    private void showItensTable() {
        DefaultTableModel model = (DefaultTableModel) tbProdutos.getModel();
        model.setNumRows(0);
        
        for (Produto p : lista) {
            model.addRow(new Object[]{
                p.getId(),
                p.getNome(),
                p.getValor(),
                p.getDescricao()});
        }
    }
    
    public void popularComboboxCategoriasAction() {
        cbCategoria.removeAllItems();
        for (Categoria c : categoriaDao.listar()) {
            cbCategoria.addItem(c);
        }
    }
    
    public void salvarAction() {
        if (produto == null) {
            cadastrarAction();
        } else {
            alterarAction();
        }
        listarAction();
        cleanAction();
    }
    
    public void cleanAction() {
        produto = null;
        nome.setText("");
        descricao.setText("");
        valor.setText("");
        nome.requestFocus();
    }
    
    public void cadastrarAction() {
        produto = new Produto();
        produto.setNome(nome.getText());
        produto.setValor(Conversor.realUsuarioParaBanco(valor.getText()));
        produto.setDescricao(descricao.getText());
        produto.setCategoria((Categoria) cbCategoria.getSelectedItem());
        if (produtoDao.salvar(produto)) {
            Painel.show(produto.getNome() + " Cadastrado com sucesso!");
            listarAction();
        } else {
            Painel.show("Erro ao editar!");
        }
    }
    
    public void alterarAction() {
        
        produto.setNome(nome.getText());
        produto.setValor(Conversor.realUsuarioParaBanco(valor.getText()));
        produto.setDescricao(descricao.getText());
        produto.setCategoria((Categoria) cbCategoria.getSelectedItem());
        
        if (produtoDao.atualizar(produto)) {
            Painel.show("Editado com sucesso!");
        } else {
            Painel.show("Erro ao editar!");
        }
    }
    
    public void excluirAction() {
        produto = getItemSelecionado();
        
        if (produto != null) {
            if (produtoDao.excluir(produto)) {
                Painel.show("Excluido com sucesso!");
                listarAction();
            } else {
                Painel.show("Erro ao editar!");
            }
        } else {
            Painel.show("Selecione um produto!");
        }
        
        produto = null;
    }
    
    public void pesquisarAction() {
        String termo = tfPesquisa.getText();
        lista = produtoDao.pesquisarNome(termo);
        showItensTable();
    }
    
    private Produto getItemSelecionado() {
        int linha = tbProdutos.getSelectedRow();
        if (linha >= 0) {
            return lista.get(linha);
        } else {
            return null;
        }
    }
    
    public void popularFormAction() {
        
        produto = getItemSelecionado();
        
        if (produto != null) {
            nome.setText(produto.getNome());
            //valor.setText("" + produto.getValor());
            valor.setValue(produto.getValor());
            descricao.setText(produto.getDescricao());
            cbCategoria.setSelectedItem(produto.getCategoria());
        } else {
            Painel.show("Selecione um produto!");
        }
    }
}
