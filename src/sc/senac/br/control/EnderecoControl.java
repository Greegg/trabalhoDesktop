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
import sc.senac.br.modelo.Cliente;
import sc.senac.br.modelo.Endereco;
import sc.senac.br.modelo.dao.EnderecoDao;
import sc.senac.br.uteis.Painel;

/**
 *
 * @author Alunos
 */
public class EnderecoControl {

    private JTextField logradouro;
    private JTextField bairro;
    private JTextField cidade;
    private JTextField cep;
    private JTextField complemento;
    private JTable jtEnd;

    private Endereco endereco = null;
    private List<Endereco> listaEnd;
    private EnderecoDao endDAo;

    private Cliente cliente;

    public EnderecoControl() {
    }

    public EnderecoControl(JTextField logradouro, JTextField bairro, JTextField cidade, JTextField cep, JTextField complemento) {
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.cidade = cidade;
        this.cep = cep;
        this.complemento = complemento;

        listaEnd = new ArrayList<>();
        endDAo = new EnderecoDao();
    }

    public EnderecoControl(JTable jtEnd) {
        this.jtEnd = jtEnd;

        listaEnd = new ArrayList<>();
        endDAo = new EnderecoDao();
        cliente = new Cliente();
    }

    public void salvarAction() {

        if (endereco == null) {
            if (validacao()) {
                Painel.show("Erro de validação");
            } else {
                cadastrarAction();
            }
        } else {
            alterarAction();
        }

        cleanList();

    }

    public boolean validacao() {

        boolean ok = false;

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

        endereco = new Endereco();

        endereco.setLogradouro(logradouro.getText());
        endereco.setBairro(bairro.getText());
        endereco.setCidade(cidade.getText());
        endereco.setCep(cep.getText());
        endereco.setComplemento(complemento.getText());
        endereco.setCliente(cliente);

        if (endDAo.salvar(endereco)) {
            Painel.show("Cadastrado com sucesso!");
        } else {
            Painel.show("Erro ao salvar!");
        }

        logradouro.setText(null);
        bairro.requestFocus();
        cidade.setText(null);
        cep.setText(null);
        complemento.setText(null);
        endereco = null;

    }

    public void alterarAction() {

        endereco.setLogradouro(logradouro.getText());
        endereco.setBairro(bairro.getText());
        endereco.setCidade(cidade.getText());
        endereco.setCep(cep.getText());
        endereco.setComplemento(complemento.getText());

        boolean res = endDAo.atualizar(endereco);
        if (res) {
            Painel.show("Editado com sucesso");
        } else {
            Painel.show("Erro ao editar");
        }

    }

    public void listarAction() {

        listaEnd = endDAo.listar(cliente.getId());
        showItensTable();
    }

    public void excluirAction() {

        endereco = getItemSelecionado();
        if (endereco == null) {
            Painel.show("Escolha um cliente");
        } else {
            boolean res = endDAo.excluir(endereco);
            if (res) {
                Painel.show("Endereco excluido");
            } else {
                Painel.show("Erro ao tentar excluir");
            }
        }
        cliente = null;
    }

    private void showItensTable() {

        DefaultTableModel model;
        model = (DefaultTableModel) jtEnd.getModel();
        model.setNumRows(0);

        for (Endereco enderecos : listaEnd) {
            model.addRow(new Object[]{
                enderecos.getId(),
                enderecos.getLogradouro(),
                enderecos.getBairro(),
                enderecos.getCidade(),
                enderecos.getCep(),
                enderecos.getComplemento(),});
        }

    }

    public Endereco getItemSelecionado() {

        int linha = jtEnd.getSelectedRow();

        if (linha >= 0) {
            endereco = listaEnd.get(linha);
            return endereco;
        } else {
            return null;
        }
    }

    public void cleanList() {

        logradouro.setText(null);
        logradouro.requestFocus();
        bairro.setText(null);
        cidade.setText(null);
        cep.setText(null);
        complemento.setText(null);
        endereco = null;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void enviarEnd() {
        endereco = getItemSelecionado();
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Endereco getEndereco() {
        return endereco = getItemSelecionado();
    }

    public List<Endereco> getListaEnd() {
        return listaEnd;
    }

    public void setListaEnd(List<Endereco> listaEnd) {
        this.listaEnd = listaEnd;
    }

}
