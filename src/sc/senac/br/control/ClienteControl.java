/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sc.senac.br.control;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import sc.senac.br.modelo.Cliente;
import sc.senac.br.modelo.Endereco;
import sc.senac.br.modelo.dao.ClienteDao;
import sc.senac.br.uteis.Conversor;
import sc.senac.br.uteis.Painel;

public class ClienteControl {

    private JTextField nomeCliente;
    private JFormattedTextField dtNasc;
    private JTextField email;
    private JFormattedTextField telefone;
    private JLabel campoNomeCliente;
    private JTextField logradouro;
    private JTextField bairro;
    private JTextField cep;
    private JTextField cidade;
    private JTextField complemento;

    private JTextField tfPesquisa;

    private JTable tbCliente;
    private JTable tbEnd;

    private Cliente cliente = null;
    private List<Cliente> listaCliente;
    private List<Endereco> listaEnderecos;
    private ClienteDao clienteDao;
    private Endereco endereco;

    private EnderecoControl endCtrl;

    public ClienteControl() {
    }

    public ClienteControl(JTextField nomeCliente, JFormattedTextField dtNasc, JTextField email, JFormattedTextField telefone,
            JTextField logradouro, JTextField bairro, JTextField cep, JTextField cidade, JTextField complemento) {

        this.nomeCliente = nomeCliente;
        this.dtNasc = dtNasc;
        this.email = email;
        this.telefone = telefone;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.cep = cep;
        this.cidade = cidade;
        this.complemento = complemento;

        clienteDao = new ClienteDao();
        endCtrl = new EnderecoControl(logradouro, bairro, cidade, cep, complemento);
        endereco = new Endereco();
        listaCliente = new ArrayList<>();
    }

    public ClienteControl(JTable tbCliente, JTable tbEnd, JTextField tfPesquisa, JLabel nomeCampoCliente) {

        this.tbCliente = tbCliente;
        this.tbEnd = tbEnd;
        this.tfPesquisa = tfPesquisa;
        this.campoNomeCliente = nomeCampoCliente;

        listaCliente = new ArrayList<>();
        listaEnderecos = new ArrayList<>();
        clienteDao = new ClienteDao();
        endCtrl = new EnderecoControl(tbEnd);

        clienteDao = new ClienteDao();
    }

    public ClienteControl(JTextField nomeCliente, JFormattedTextField dtNasc, JTextField email, JFormattedTextField telefone) {
        this.nomeCliente = nomeCliente;
        this.dtNasc = dtNasc;
        this.email = email;
        this.telefone = telefone;

        clienteDao = new ClienteDao();
    }

    public void listarAction() {
        listaCliente = clienteDao.listar();
        showItensTable();
    }

    private void showItensTable() {

        DefaultTableModel model;
        model = (DefaultTableModel) tbCliente.getModel();
        model.setNumRows(0);
        for (Cliente cli : listaCliente) {

            model.addRow(new Object[]{
                cli.getId(),
                cli.getNome(),
                Conversor.dataBancoParaUsuario(cli.getDataNascimento()),
                cli.getEmail(),
                cli.getTelefone()
            });
        }
    }

    public void salvarAction() {

        if (cliente == null) {
            if (ValidacaoCliente()) {
                Painel.show("Erro de validação");
            } else {
                cadastrarAction();
            }
        } else {
            alterarAction();
        }

        cleanField();

    }
    
    public void cleanField(){
        
        nomeCliente.setText(null);
        nomeCliente.requestFocus();
        dtNasc.setText(null);
        email.setText(null);
        telefone.setText(null);
        cliente = null;
        
        logradouro.setText(null);
        bairro.setText(null);
        cidade.setText(null);
        cep.setText(null);
        complemento.setText(null);
        endereco = null;
        
    }

    public boolean ValidacaoCliente() {

        boolean ok = false;

        if (nomeCliente.getText().isEmpty()) {
            ok = true;
            Painel.show("Todos os campos são obrigatórios");
        }
        if (logradouro.getText().isEmpty()) {
            Painel.show("Data de nascimento é obrigatória");
        }
        if (bairro.getText().isEmpty()) {
            Painel.show("Email é obrigatório");
        }
        if (cidade.getText().isEmpty()) {
            Painel.show("Telefone é obrigatório");
        }

        return ok;

    }

    public void cadastrarAction() {

        cliente = new Cliente();

        cliente.setNome(nomeCliente.getText());
        cliente.setDataNascimento(Conversor.dataUsuarioParaBanco(dtNasc.getText()));
        cliente.setEmail(email.getText());
        cliente.setTelefone(telefone.getText());

        Endereco endereco = new Endereco();
        endereco.setLogradouro(logradouro.getText());
        endereco.setBairro(bairro.getText());
        endereco.setCidade(cidade.getText());
        endereco.setCep(cep.getText());
        endereco.setComplemento(complemento.getText());

        List<Endereco> enderecos = new ArrayList<>();
        enderecos.add(endereco);

        cliente.setEnderecos(enderecos);
        
        try {
            clienteDao.salvar(cliente);
            JOptionPane.showMessageDialog(null, cliente.getNome() + " cadastrado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao salvar " + e.getMessage());
        }

        limparCliente();
    }

    private void limparCliente() {
        nomeCliente.setText(null);
        nomeCliente.requestFocus();
        dtNasc.setText(null);
        email.setText(null);
        telefone.setText(null);
        cliente = null;
        logradouro.setText(null);
    }

    public void alterarAction() {

        cliente.setNome(nomeCliente.getText());
        cliente.setDataNascimento(Conversor.dataUsuarioParaBanco(dtNasc.getText()));
        cliente.setEmail(email.getText());
        cliente.setTelefone(telefone.getText());

        boolean res = clienteDao.atualizar(cliente);
        if (res) {
            Painel.show("Editado com sucesso");
        } else {
            Painel.show("Erro ao editar");
        }

    }

    public void excluirAction() {

        cliente = getItemSelecionado();
        if (cliente == null) {
            Painel.show("Escolha um cliente");
        } else {
            boolean res = clienteDao.excluir(cliente);
            if (res) {
                Painel.show("Cliente excluido");
                listarAction();
            } else {
                Painel.show("Erro ao tentar excluir");
            }
        }
        cliente = null;
    }

    public Cliente getItemSelecionado() {

        int linha = tbCliente.getSelectedRow();

        if (linha >= 0) {
            cliente = listaCliente.get(linha);
            return cliente;
        } else {
            return null;
        }
    }

    public Endereco getEndSelecionado() {

        int linha = tbEnd.getSelectedRow();

        if (linha >= 0) {
            
            endereco = endCtrl.getListaEnd().get(linha);
            
            return endereco;
        } else {
            return null;
        }
    }

    public void popularFormAction() {
        cliente = getItemSelecionado();

        if (cliente != null) {

            nomeCliente.setText(cliente.getNome());
            dtNasc.setText(Conversor.dataBancoParaUsuario(cliente.getDataNascimento()));
            email.setText(cliente.getEmail());
            telefone.setText(cliente.getTelefone());
        } else {
            Painel.show("Selecione um cliente");
        }
    }

    public void pesquisarAction() {

        listaCliente = clienteDao.pesquisarNome(tfPesquisa.getText());
        DefaultTableModel model = (DefaultTableModel) tbCliente.getModel();

        model.setRowCount(0);
        for (Cliente c : listaCliente) {
            model.addRow(new Object[]{
                c.getId(),
                c.getNome(),
                c.getDataNascimento(),
                c.getEmail(),
                c.getTelefone()
            });

        }
    }

    public void acaobotaolimpa() {
        nomeCliente.setText("");
        dtNasc.setText(null);
        email.setText("");
        telefone.setText("");
    }

    public EnderecoControl getEndCtrl() {
        return endCtrl;
    }

    public void enviarClienteEnd() {
        endCtrl.setCliente(getItemSelecionado());
    }

    public void enviarEnd() {
        endCtrl.setEndereco(getEndSelecionado());
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

}
