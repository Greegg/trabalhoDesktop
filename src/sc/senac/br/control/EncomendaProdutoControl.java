/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sc.senac.br.control;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import sc.senac.br.modelo.Encomenda;
import sc.senac.br.modelo.EncomendaProduto;
import sc.senac.br.modelo.dao.EncomendaProdutoDao;
import sc.senac.br.uteis.Conversor;
import sc.senac.br.uteis.Painel;

/**
 *
 * @author hvb
 */
public class EncomendaProdutoControl {

    private JTable tbEncProd;
    private JLabel total;

    private List<EncomendaProduto> listEncProd;
    private EncomendaProduto encProd = null;
    private EncomendaProdutoDao encProdDao;

    private Encomenda encomenda;

    public EncomendaProdutoControl() {
    }

    public EncomendaProdutoControl(JTable tbEncProd, JLabel total) {
        this.tbEncProd = tbEncProd;
        this.total = total;

        listEncProd = new ArrayList<>();
        encProdDao = new EncomendaProdutoDao();
        encomenda = new Encomenda();

    }

    public void listarAction() {

        listEncProd = encProdDao.lerPorId(encomenda.getId());
        showItensTable();
    }

    public void showItensTable() {

        DefaultTableModel model;
        model = (DefaultTableModel) tbEncProd.getModel();
        model.setNumRows(0);

        float total = 0;

        for (EncomendaProduto encomendaProdutos : listEncProd) {
            
            model.addRow(new Object[]{
                encomendaProdutos.getProduto().getNome(),
                Conversor.realBancoUsuario(encomendaProdutos.getProduto().getValor()),
                encomendaProdutos.getQuantidade(),
                Conversor.realBancoUsuario(encomendaProdutos.getSubTotal())});
            total += encomendaProdutos.getSubTotal();
        }
        this.total.setText("" + Conversor.realBancoUsuario(total));
    }

    public void excluirAction() {

        encProd = getItemSelecionado();
        if (encProd == null) {
            Painel.show("Escolha um produto valido");
        } else {
            boolean res = encProdDao.excluir(encProd);
            if (res) {
                Painel.show("Produto excluido");
                listarAction();
            } else {
                Painel.show("Erro ao tentar excluir");
            }
        }
    }

    public EncomendaProduto getItemSelecionado() {

        int linha = tbEncProd.getSelectedRow();

        if (linha >= 0) {
            encProd = listEncProd.get(linha);
            return encProd;
        } else {
            return null;
        }
    }

    
    public Encomenda getEncomenda() {
        return encomenda;
    }

    public void setEncomenda(Encomenda encomenda) {
        this.encomenda = encomenda;
    }

}
