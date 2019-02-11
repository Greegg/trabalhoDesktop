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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import sc.senac.br.modelo.Cliente;
import sc.senac.br.modelo.Encomenda;
import sc.senac.br.modelo.EncomendaProduto;
import sc.senac.br.modelo.Endereco;
import sc.senac.br.modelo.Entregador;
import sc.senac.br.modelo.Produto;
import sc.senac.br.modelo.dao.ClienteDao;
import sc.senac.br.modelo.dao.EncomendaDao;
import sc.senac.br.modelo.dao.EnderecoDao;
import sc.senac.br.modelo.dao.EntregadorDao;
import sc.senac.br.modelo.dao.ProdutoDao;
import sc.senac.br.uteis.Conversor;
import sc.senac.br.uteis.Painel;

/**
 *
 * @author hvb
 */
public class EncomendaControl {

    private JComboBox comboCliente;
    private JComboBox comboEndereco;
    private JComboBox comboEntregador;
    private JComboBox comboproduto;
    private JTextField quantidade;
    private JFormattedTextField dataEntrega;
    private JFormattedTextField dataPedido;
    private JTable tableList1;
    private JLabel total;

    private JTable tbEncomenda;
    private JTable tbEncProd;
    private JTextField tfPesquisa;

    private List<Cliente> listaCliente;
    private List<Endereco> listaEndereco;
    private List<Entregador> listaEntregador;
    private List<Produto> listaProduto;
    private List<EncomendaProduto> listEncProduto;
    private List<Encomenda> listaEncomenda;

    private ClienteControl cliCtrl;
    private EntregadorControl entreCtrl;
    private ProdutoControl prodControl;
    private EncomendaProdutoControl encProdCtrl;

    private ClienteDao cliDao;
    private EntregadorDao entreDao;
    private ProdutoDao prodDao;
    private EncomendaDao encDao;
    private EnderecoDao endeDao;

    private EncomendaProduto encProd;
    private Encomenda encomenda;

    private Cliente cliente;
    private Entregador entregador;
    private Endereco endereco;

    public EncomendaControl() {
    }

    public EncomendaControl(JComboBox comboCliente, JComboBox comboEndereco, JComboBox comboEntregador, JComboBox comboproduto, JTextField quantidade, JFormattedTextField dataEntrega, JFormattedTextField dataPedido, JTable tableList1) {
        this.comboCliente = comboCliente;
        this.comboEndereco = comboEndereco;
        this.comboEntregador = comboEntregador;
        this.comboproduto = comboproduto;
        this.quantidade = quantidade;
        this.dataEntrega = dataEntrega;
        this.dataPedido = dataPedido;
        this.tableList1 = tableList1;

        listaCliente = new ArrayList<>();
        listaEndereco = new ArrayList<>();
        listaEntregador = new ArrayList<>();
        listaProduto = new ArrayList<>();
        listEncProduto = new ArrayList<>();

        cliCtrl = new ClienteControl();
        entreCtrl = new EntregadorControl();
        prodControl = new ProdutoControl();

        cliDao = new ClienteDao();
        entreDao = new EntregadorDao();
        prodDao = new ProdutoDao();
        endeDao = new EnderecoDao();
        encDao = new EncomendaDao();
    }

    public EncomendaControl(JTable tbEncomenda, JTable tbEncProd, JTextField tfPesquisa, JLabel total) {

        this.tbEncomenda = tbEncomenda;
        this.tbEncProd = tbEncProd;
        this.tfPesquisa = tfPesquisa;
        this.total = total;

        listaEncomenda = new ArrayList<>();
        listEncProduto = new ArrayList<>();
        encDao = new EncomendaDao();
        encProdCtrl = new EncomendaProdutoControl(tbEncProd, total);

        cliente = new Cliente();
        entregador = new Entregador();
        endereco = new Endereco();

    }

    public void popularComboboxClientesAction() {
        listaCliente = cliDao.listar();
        comboCliente.removeAllItems();
        for (Cliente c : listaCliente) {
            comboCliente.addItem(c);
        }
    }

    public void popularComboboxProdutosAction() {
        listaProduto = prodDao.listar();
        comboproduto.removeAllItems();

        for (Produto p : listaProduto) {
            comboproduto.addItem(p);
        }
    }

    public void popularComboboxEntregadorAction() {
        listaEntregador = entreDao.listar();

        comboEntregador.removeAllItems();
        for (Entregador e : listaEntregador) {
            comboEntregador.addItem(e);
        }
    }

    public void popularComboboxEnderecoAction() {
        listaEndereco = endeDao.listar(((Cliente) comboCliente.getSelectedItem()).getId());

        comboEndereco.removeAllItems();
        for (Endereco endereco : listaEndereco) {
            comboEndereco.addItem(endereco);
        }

    }

    public void addListProduto() {

        encProd = new EncomendaProduto();

        encProd.setProduto((Produto) comboproduto.getSelectedItem());
        encProd.setQuantidade(Integer.parseInt(quantidade.getText()));
        encProd.setValor(encProd.getProduto().getValor());

        listEncProduto.add(encProd);
        encProd = null;

        showItensTable();

        encProd = null;
        quantidade.setText(null);
    }

    public void showItensTable() {
        DefaultTableModel model = (DefaultTableModel) tableList1.getModel();
        model.setNumRows(0);
        for (EncomendaProduto ep : listEncProduto) {
            model.addRow(new Object[]{
                ep.getProduto().getNome(),
                ep.getValor(),
                ep.getQuantidade()});
        }
    }

    public void excluirProduto() {

        listEncProduto.remove(Painel.showInput("Digite o numero do produto a se excluido") - 1);
    }

    public void excluirAction() {

        encomenda = getItemSelecionado();
        if (encomenda == null) {
            Painel.show("Escolha uma encomenda valida");
        } else {
            boolean res = encDao.excluir(encomenda);
            if (res) {
                Painel.show("Produto excluido");
                listarAction();
            } else {
                Painel.show("Erro ao tentar excluir");
            }
        }
    }

    public void salvarAction() {

        if (encomenda == null) {
            if (validacao()) {
                Painel.show("Erro de validação");
            } else {
                cadastrarAction();
            }
        } else {
            alterarAction();
        }
        cleanAction();
    }

    public void cleanAction() {
        encomenda = null;
        encProd = null;
        listEncProduto.clear();
        dataEntrega.setText(null);
        dataEntrega.requestFocus();
        dataPedido.setText(null);
        quantidade.setText(null);
    }

    public boolean validacao() {

        boolean ok = false;

        if (dataPedido.getText().isEmpty()) {
            Painel.show("Data de pagamento é obrigatória");
        }
        if (dataEntrega.getText().isEmpty()) {
            Painel.show("Data de vencimento é obrigatório");
        }

        return ok;

    }

    public void cadastrarAction() {

        encomenda = new Encomenda();

        encomenda.setCliente((Cliente) comboCliente.getSelectedItem());
        encomenda.setEndereco((Endereco) comboEndereco.getSelectedItem());
        encomenda.setEntregador((Entregador) comboEntregador.getSelectedItem());
        encomenda.setDataEntrega(Conversor.dataUsuarioParaBanco(dataEntrega.getText()));
        encomenda.setDataPedido(Conversor.dataUsuarioParaBanco(dataPedido.getText()));

        encomenda.setEncPRoduto(listEncProduto);

        try {
            encDao.salvar(encomenda);

            JOptionPane.showMessageDialog(null, " Pedido cadastrado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao salvar pedido: " + e.getMessage());
        }

        cleanAction();
        listaProduto.clear();
    }

    public void alterarAction() {

        encomenda.setDataEntrega(Conversor.dataUsuarioParaBanco(dataEntrega.getText()));
        encomenda.setDataPedido(Conversor.dataUsuarioParaBanco(dataPedido.getText()));

        boolean res = encDao.atualizar(encomenda);
        if (res) {
            Painel.show("Editado com sucesso");
        } else {
            Painel.show("Erro ao editar");
        }

    }

    public void listarAction() {

        listaEncomenda = encDao.listar();
        showItensTableEncomenda();
    }

    public void showItensTableEncomenda() {

        DefaultTableModel model;
        model = (DefaultTableModel) tbEncomenda.getModel();
        model.setNumRows(0);

        for (Encomenda encomendas : listaEncomenda) {
            model.addRow(new Object[]{
                encomendas.getId(),
                encomendas.getCliente().getNome(),
                encomendas.getDataPedido(),
                encomendas.getDataEntrega(),
                encomendas.getEntregador().getNome(),
                encomendas.getEndereco().getLogradouro(),});
        }

    }

    public Encomenda getItemSelecionado() {

        int linha = tbEncomenda.getSelectedRow();

        if (linha >= 0) {
            encomenda = listaEncomenda.get(linha);
            return encomenda;
        } else {
            return null;
        }
    }

    public void pesquisarAction() {

        listaEncomenda = encDao.pesquisarNome(tfPesquisa.getText());
        DefaultTableModel model = (DefaultTableModel) tbEncomenda.getModel();

        model.setRowCount(0);
        for (Encomenda encomendas : listaEncomenda) {
            model.addRow(new Object[]{
                encomendas.getId(),
                encomendas.getCliente().getNome(),
                encomendas.getDataPedido(),
                encomendas.getDataEntrega(),
                encomendas.getEntregador().getNome(),
                encomendas.getEndereco().getLogradouro(),});
        }
    }

    public void enviarEncomenda() {
        encProdCtrl.setEncomenda(getItemSelecionado());
    }

    public EncomendaProdutoControl getEncProdCtrl() {
        return encProdCtrl;
    }

}
